package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.Comparendo;

import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.Node;
import model.data_structures.Queue;
import model.data_structures.Stack;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo<T> {
	
	public static String PATH = "./data/comparendos_dei_2018_small.geojson";
//	public static String PATH = "./data/comparendos_dei_2018.geojson";
     private IQueue<Node<Comparendo>> dataQueue;
	public IStack<T> dataStack;
	
	public Modelo()
	{
		dataStack = (IStack<T>) new Stack<T>();
        dataQueue = (IQueue<Node<Comparendo>>) new Queue<Node<Comparendo>>();
	}
	
	@SuppressWarnings("unchecked")
	public void cargarDatos() {
		
		//TODO Cambiar la clase del contenedor de datos por la Estructura de Datos propia adecuada para resolver el requerimiento 
		

		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();
			
			
			SimpleDateFormat parser=new SimpleDateFormat("yyyy/MM/dd");

			for(JsonElement e: e2) {
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();
				
				String s = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();	
				Date FECHA_HORA = parser.parse(s); 
				
				String MEDIO_DETE = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETE").getAsString();
				String CLASE_VEHI = e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHI").getAsString();
				String TIPO_SERVI = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVI").getAsString();
				String INFRACCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DES_INFRAC = e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRAC").getAsString();	
				String LOCALIDAD = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();

				double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsDouble();
				
				double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsDouble();

				Comparendo c = new Comparendo(OBJECTID, FECHA_HORA, DES_INFRAC, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, LOCALIDAD, longitud, latitud);
				Node<Comparendo> node = new Node<Comparendo>(c);
				dataStack.push(((Node<T>) node));
			}

		} catch (FileNotFoundException | ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
		public Queue<Node<Comparendo>> consecutivosinfra(String pInfraccion) throws Exception 
		{
			Queue<Node<Comparendo>> masGrande = new Queue<Node<Comparendo>>();
			Node<Comparendo> n = (Node<Comparendo>)dataQueue.sacarDeCola();
			while( n != null)
			{	
				String infra = ((Comparendo)n.getObject()).getInfra();
				if(infra.compareTo(pInfraccion) >= 0 )  
				{
					Queue<Node<Comparendo>> cola = (Queue<Node<Comparendo>>)new Queue<Node<Comparendo>>();
					cola.ponerEnCola(n);
					Node<Comparendo> siguiente = (Node<Comparendo>)dataQueue.sacarDeCola();
					String infraDelSiguiente = ((Comparendo)siguiente.getObject()).getInfra();
					while ( infraDelSiguiente.compareTo(infra) >= 0 )
					{
						cola.ponerEnCola(n);
						siguiente = (Node<Comparendo>)dataQueue.sacarDeCola();
						infraDelSiguiente = ((Comparendo)siguiente.getObject()).getInfra();
					} 
				
					if (cola.darTamanio() > masGrande.darTamanio())
						masGrande = cola;
				}	
				n = (Node<Comparendo>)dataQueue.sacarDeCola();
			}
			return masGrande;
		}
		
		public Queue<Node<Comparendo>> ultimosComparendos(String pInfraccion, int cantidad)
		{
			Queue<Node<Comparendo>> cola = new Queue<Node<Comparendo>>();
			int comparendos  = 0;
			Comparendo dato = (Comparendo)dataStack.pop();
	
				while( !dataStack.isEmpty() && dato !=null && comparendos < cantidad)
				{
					if(dato !=null )
					{
						if(dato.getInfra().equals(pInfraccion))
						{
							cola.ponerEnCola(new Node<Comparendo>(dato));
							comparendos++;
						}
					}
					dato = (Comparendo)dataStack.pop();
				}
			
			return cola;
		}
		
}
