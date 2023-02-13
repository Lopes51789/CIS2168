package circularlinkedlist;
import java.util.Iterator;
import java.util.Scanner;

public class CircularLinkedList<E> implements Iterable<E> {
	Node<E> head;
	Node<E> tail;
	int size;

	/**
	 * Implement a constructor
	 * */
	public CircularLinkedList() {
		size = 0;
	}

	/**
	 * Return Node<E> found at the specified index
	 * Be sure to check for out of bounds cases
	 * */
	private Node<E> getNode(int index) {
		if (index >= size){//Find the out of bounds
			return null;
		}

		return CircularLinkedList.get(index);

	}

	/**
	 * Add a node to the end of the list(tail)
	 * HINT: Use the overloaded add method as a helper method
	 * */
	public boolean add(E item) {
		Node nodeToBeAdded = new Node(item);
		CircularLinkedList.add(item);
		return false;
	}

	
	/**
	 * Cases to handle:
	 * Out of bounds
	 * Adding an element to an empty list
	 * Adding an element to the front
	 * Adding an element to the end
	 * Adding an element in the middle
	 * 		HINT: Remember to keep track of the list's size
	 */
	public void add(int index, E item) {
		Node nodeToBeAdded = new Node(item);
		if(head == null){
			head = nodeToBeAdded;
			tail = head;
			tail.next = head;
		}

		if(index == 0){
			nodeToBeAdded.next = head;
			head = nodeToBeAdded;
			tail.next = head;
		}

		else if(index >= size){
			nodeToBeAdded.next = head;
			tail.next = nodeToBeAdded;
			tail = nodeToBeAdded;
		}

		else{
			int initialIndex = 0;
			Node start = head;
			while(initialIndex < index -1){
				start = start.next;
				initialIndex++;
			}
			nodeToBeAdded.next = start.next;
			start.next = nodeToBeAdded;
		}
		size++;
	}

	/** Cases to handle:
	 * Out of bounds
	 * Removing the last element in the list
	 * Removing the first element in the list
	 * Removing the last element
	 * Removing an element from the middle
	 * 		HINT: Remember to keep track of the list's size
	 */
	public E remove(int index) {
		if(index>=size){//find the out of binds
			return null;
		}

		CircularLinkedList.remove(index)

	}
	
	/**
	 * Stringify your list
	 * Cases to handle:
	 * Empty list
	 * Single element list
	 * Multi-element list
	 * Use "==>" to delimit each node
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		Node<E> e = head;
		if(e==null){
			return 'EMPTY LIST';
		}if(size == 1){
			return e.item.toString();
		}

		result.append(e.item + "-->");
		e = e.next;

		while(e.next != null && e != head){
			result.append(e.item + '-->');
			e = e.next;
		}

		return result.toString();
	}


	public Iterator<E> iterator() {

		return new ListIterator<E>();
	}
	
	// This class is not static because it needs to reference its parent class
	private class ListIterator<E> implements Iterator<E> {
		Node<E> nextItem;
		Node<E> prev;
		int index;
		
		@SuppressWarnings("unchecked")
		// Creates a new iterator that starts at the head of the list
		public ListIterator() {
			nextItem = (Node<E>) head;
			index = 0;
		}

		// Returns true if there is a next node
		public boolean hasNext() {

			return false;
		}
		
		// Advances the iterator to the next item
		// Should wrap back around to the head
		public E next() {

			return null;
	
		}
		
		// Remove the last node visted by the .next() call
		// For example, if we had just created an iterator,
		// the following calls would remove the item at index 1 (the second person in the ring)
		// next() next() remove()
		// Use CircularLinkedList.this to reference the CircularLinkedList parent class
		public void remove() {

		}
		
	}
	
	// The Node class
	private static class Node<E>(){
		E item;
		Node<E> next;
		public Node(E item) {
			this.item = item;
		}
	}
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of soldiers: ");
		int soldiers = scan.nextInt();
		System.out.println("Enter count: ");
		int count = scan.nextInt();

		CircularLinkedList army = new CircularLinkedList(){

		}

	}
}
