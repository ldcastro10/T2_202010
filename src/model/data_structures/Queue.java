package model.data_structures;

public class Queue<E extends Node> implements IQueue<E>
{
	private E first;
	private int size;
	private E last;

	public Queue(){
		first = null;
		last = null;
		int size = 0;
	}

	public void ponerEnCola(E elem) {
		if ( size == 0)
		{	first = elem;
			last = elem;	}
		else 
		{	last.setNext(elem);
			last = elem;	}			
		size++;
	}

	public E sacarDeCola() throws Exception {
		if(first == null)
			throw new Exception("La lista está vacia");
		E primero = first;
		first = (E)first.getNext();
		size--;
		return primero;
	}	

	public int darTamanio() {
		return size;
	}

	public boolean vacia() {
		return first == null;
	}

	public E darPrimero() {
		return first;
	}
}