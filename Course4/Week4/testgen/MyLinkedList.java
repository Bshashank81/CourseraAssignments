package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		
		size=0;
		head=new LLNode<E>(null);
		tail=new LLNode<E>(null);
		head.next=tail;
		tail.prev=head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		
		if(element==null) {
			throw new NullPointerException();
		}
		LLNode<E> current=new LLNode<E>(element);
		if(head.next==tail) {
			current.next=tail;
			current.prev=head;
			head.next=current;
			tail.prev=current;
			size++;
		}
		else {
			current.prev=tail.prev;
			tail.prev=current;
			current.next=tail;
			current.prev.next=current;
			size++;
			
		}
		//System.out.println(tail.prev.data);
		
		return false;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if(index==-1||index>=this.size) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> temp=head.next;;
		int currentIdx=0;
		
		while(temp!=null) {
			
			
			if(currentIdx==index) {
				
				return temp.data;
			}
			temp=temp.next;
			currentIdx++;
		}
		
		
		return null;
	}

	/**
	 * Add an element to the list at the specified inde
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		int currentIdx=0;
		LLNode<E> temp=head.next;
		if(element==null) {
			throw new NullPointerException();
		}
		if(index==-1||index>this.size) {
			throw new IndexOutOfBoundsException();
		}
		if(index==this.size) {
			this.add(element);
		}
		else {
			
			LLNode<E> current=new LLNode<E>(element);
			while(temp!=null) {
				if(currentIdx==index) {
					current.next=temp;
					current.prev=temp.prev;
					temp.prev=current;
					current.prev.next=current;
					size++;
					break;
					
				}
				currentIdx++;
				temp=temp.next;
			}
			
		}
		
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
	
		if(index==-1||index>=this.size) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> temp=head.next;
		int currentIndex=0;
		while(temp!=null) {
			if(currentIndex==index) {
				E value=temp.data;
				temp.prev.next=temp.next;
				temp.next.prev=temp.prev;
				temp.next=null;
				temp.prev=null;
				size--;
				
				return value;
			}
			temp=temp.next;
			currentIndex++;
	
	
		}
		
		
		return null;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if(index==-1||index>=this.size) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> temp=head.next;
		int currentIndex=0;
		while(temp!=null) {
			if(currentIndex==index) {
				E value=temp.data;
				temp.data=element;
				return value;
			}
			temp=temp.next;
			currentIndex++;

		}
		return null;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
