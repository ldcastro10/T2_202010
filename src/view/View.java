package view;

import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
		public void printMenu()
		{
            System.out.println("1. Cargar Archivo");
			System.out.println("2. Procesar cola para consultar el grupo ordenado más grande");
			System.out.println("3. Procesar pila para buscar últimos comparendos");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(Modelo modelo)
		{
			// TODO implementar
		}
}
