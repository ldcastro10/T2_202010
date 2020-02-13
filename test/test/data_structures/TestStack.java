package test.data_structures;

import model.data_structures.Nodo;
import model.data_structures.Stack;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class TestStack{

	
	private Stack<Integer> stack;
	@Before
	public void setUp1()
	{
		stack = new Stack<Integer>();
	}

	public void setUp2()
	{
		int i = 1;
		while(i <= 10)
		{
			Nodo<Integer> nodo = new Nodo<Integer>(i);
			stack.push(nodo);
			i++;
		}
	}
	@Test
	public void testStack()
	{
		assertTrue(stack != null);
		setUp2();
		assertTrue(stack.size() == 10);
	}


	@Test
	public void testPush() 
	{
		stack.push((Nodo<Integer>)new Nodo<Integer>(3));
		assertTrue(((Integer)stack.getFirst()) == 3);
		setUp2();
		assertTrue((Integer)stack.getFirst() == 10);
	}

	@Test
	public void testPop() {
		assertNull(stack.getFirst());
		setUp2();
		assertTrue(((Integer) stack.pop()) == 7);
		assertTrue(((Integer) stack.pop()) == 6);
		assertTrue(((Integer) stack.pop()) == 5);
	}
	@Test
	public void testSize() 
	{
		assertTrue(stack.size() == 0);
		setUp2();
		assertTrue(stack.size() == 10);
	}

	@Test
	public void testIsEmpty() 
	{	
		assertTrue(stack.isEmpty());
		setUp2();
		assertFalse(stack.isEmpty());
	}

	@Test
	public void testGetFirst() 
	{
		assertNull(stack.getFirst());
		setUp2();
		assertTrue(((Integer) stack.getFirst()) == 10);
	}

}
