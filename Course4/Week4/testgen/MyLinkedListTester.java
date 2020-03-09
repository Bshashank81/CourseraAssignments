/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		
		
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
		
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		
		catch (IndexOutOfBoundsException e) {
		}
		
		
	}
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		try {
			emptyList.add(null);
			fail("Check addition of null values");
		}
		catch (NullPointerException e) {
			
		}
		shortList.add(1,"C");
		longerList.add(7,11);
		longerList.add(11,12);
		list1.add(0,30);
		//assertEquals("addAtIndex : check elem of emptyList is correct ", 0, emptyList.size());
		assertEquals("addAtIndex : check elements added to shortList is correct ", "C",shortList.get(1));
		assertEquals("addAtIndex : check elements added to longerList is correct ", (Integer)11,longerList.get(7));
		assertEquals("addAtIndex : check elements added to longerList is correct ",(Integer)12,longerList.get(11));
		assertEquals("addAtIndex : check elements added to list1 is correct ",(Integer)30,list1.get(0));
		
		assertEquals("size : check size of shortList is correct ", 3, shortList.size());
		assertEquals("size : check size of longerList is correct ", 12, longerList.size());
		assertEquals("size : check size of list1 is correct ", 4, list1.size());
		
		
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	
	
	@Test

	public void testRemove()
	{

		
		
		try {
			shortList.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.remove(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
	
		
		shortList.add(1,"C");
		longerList.add(7,11);
		longerList.add(11,12);
		list1.add(0,30);
		assertEquals("Remove: check a is correct ",(Integer)30, list1.remove(0));
		assertEquals("Remove: check element 0 is correct ", (Integer)65, list1.get(0));
		assertEquals("Remove: check size is correct ", 3, list1.size());
		
		assertEquals("Remove: check value is correct ","C", shortList.remove(1));
		assertEquals("Remove: check value is correct ", "B", shortList.get(1));
		assertEquals("Remove: check size is correct ", 2, shortList.size());
		
		
		
		assertEquals("Remove: check a is correct ",(Integer)11,longerList.remove(7));
		assertEquals("Remove: check element 0 is correct ", (Integer)7, longerList.get(7));
		
		assertEquals("Remove: check a is correct ",(Integer)12,longerList.remove(10));
		assertEquals("Remove: check element 0 is correct ",10,longerList.size());
		
		
		// TODO: Add more tests here
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		try {
			emptyList.add(null);
			fail("Check addition of null values");
		}
		catch (NullPointerException e) {
			
		}
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
	
		assertEquals("size : check size of emptyList is correct ", 0, emptyList.size());
		assertEquals("size : check size of shortList is correct ", 2, shortList.size());
		assertEquals("size : check size of longerList is correct ", 10, longerList.size());
		assertEquals("size : check size of list1 is correct ", 3, list1.size());
	}
	
	
	

	/** Test setting an element in the list */
	@Test
	public void testSet()
	{

		try {
			shortList.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.remove(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		assertEquals("Set:Check first", "A", shortList.set(0, "B"));
		assertEquals("Set Check  " ,(Integer)2, longerList.set(2,12));
		assertEquals("Set Check " ,(Integer)12, longerList.get(2));
		assertEquals("Set Check " ,(Integer)42, list1.set(2,44));
		assertEquals("Set Check " ,(Integer)44, list1.get(2));
		
		
		
		
	   
	    
	}
	
	
	// TODO: Optionally add more test methods.
	
}
