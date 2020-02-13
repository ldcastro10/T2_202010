package controller;


import java.util.List;
import java.util.Scanner;

import model.data_structures.Node;
import model.data_structures.Queue;
import model.data_structures.Stack;
import model.Comparendo;
import model.logic.Modelo;

import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo<Comparendo> modelo;
	
	/* Instancia de la Vista*/
	private View view;
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo<Comparendo>();
	}
		
	public void run() throws Exception 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
				case 1:
				    modelo = new Modelo<Comparendo>();
					
				    long start = System.currentTimeMillis();
				    modelo.cargarDatos();
				    long end = System.currentTimeMillis();
				    
				    view.printMessage("Tiempo de carga (seg): " + (end-start)/1000.0);

					break;
					
		
			
			
				case 2:
					System.out.println("Procesar cola para consultar el grupo ordenado mas grande");
					System.out.println("Ingrese la infraccion a buscar");
					String pInfraccion = lector.next();
					try {
						Queue<Node<Comparendo>> resultados = modelo.consecutivosinfra(pInfraccion);
						for (int i = 0; i < resultados.darTamanio(); i++)
						{
							Comparendo comp = resultados.sacarDeCola().getObject();
							System.out.println("------------------------------------------------------------------");
							System.out.println("Comparendo "+(i+1)+":");
							System.out.println("Infraccion: "+comp.getInfra());
							System.out.println("Id: "+comp.getid());
							System.out.println("Fecha: "+comp.getfecha());
							System.out.println("Clase: "+comp.getclase());
							System.out.println("Servicio: "+comp.gettipo());
							System.out.println("Localidad: "+comp.getlocalidad());
							System.out.println("-------------------------------------------------------------------");					
						}
					} catch (Exception e) {e.printStackTrace();}
					break;

				case 3:
					System.out.println("Procesar pila para buscar los ultimos comparendos");
					System.out.println("Ingrese la infraccion a buscar");
					String comp = lector.next();
					System.out.println("Ingrese la cantidad de comparendos a buscar");
					int cantidad = lector.nextInt();
					
					
		
					Queue<Node<Comparendo>> resultado = modelo.ultimosComparendos(comp, cantidad);				
					System.out.println("Resultado de la consulta: ");
					
					for(int i = 0; i < resultado.darTamanio();i++)
					{
						
						String Infraccion = resultado.sacarDeCola().getObject().getInfra();
						int Id = resultado.sacarDeCola().getObject().getid();
						//Date Fecha = resultado.sacarDeCola().getObject().getfecha();
						String Clase = resultado.sacarDeCola().getObject().getclase();
						String Servicio = resultado.sacarDeCola().getObject().gettipo();
						String Localidad = resultado.sacarDeCola().getObject().getlocalidad();
				
						System.out.println("------------------------------------------------------------------");
						System.out.println("Comparendo "+(i+1)+":");
						System.out.println("Infraccion: "+Infraccion);
						System.out.println("Id: "+Id);
						System.out.println("Fecha: "+Infraccion);
						System.out.println("Clase: "+Clase);
						System.out.println("Servicio: "+Servicio);
						System.out.println("Localidad: "+Localidad);
						System.out.println("-------------------------------------------------------------------");
					}

					view.printMessage("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}
