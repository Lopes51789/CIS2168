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
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    /**
     * Stringify your list
     * Cases to handle:
     * Empty list
     * Single element list
     * Multi-element list
     * Use "==>" to delimit each node
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node<E> e = head;
        if(e==null){
            return "EMPTY LIST";
        }if(size == 1){
            return e.item.toString();
        }

        result.append(e.item + "-->");
        e = e.next;

        while(e.next != null && e != head){
            result.append(e.item + "-->");
            e = e.next;
        }

        return result.toString();
    }

    /**
     * Add a node to the end of the list(tail)
     * HINT: Use the overloaded add method as a helper method
     * */
    public boolean add(E item) {
        Node nodeToBeAdded = new Node(item);
        if(this.head == null){
            this.head = nodeToBeAdded;
            this.tail = this.head;
            this.tail.next = this.head;
        }else{
            tail.next = nodeToBeAdded;
            tail = nodeToBeAdded;
        }
        size++;
        return true;
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
        if(this.head == null){
            this.head = nodeToBeAdded;
            this.tail = this.head;
            this.tail.next = this.head;
        }

        if(index == 0){
            nodeToBeAdded.next = this.head;
            this.head = nodeToBeAdded;
            this.tail.next = this.head;
        }

        else if(index >= this.size){
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
        //Empty list
        if(head == null){
            return null;
        }
        //Out of Bounds
        if(index < 0 || index >= size){
            return null;
        }

        Node temp = head;
        int i = 0;
        //First element
        if(index == 0){
            head = temp.next;
            return null;
        }

        while(temp!=null && i<index-1){
            temp = temp.next;
            i++;
        }
        temp.next = temp.next.next;
        return null;
    }

    //Check code on canvas if there are any instructions
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
            //"starts at head"
            nextItem = (Node<E>) head;
            index = 0;
        }

        // Returns true if there is a next node
        public boolean hasNext() {
            if(nextItem != null){
                return true;
            }
            return false;
        }

        // Advances the iterator to the next item
        // Should wrap back around to the head
        public E next() {
            Node<E> temp = nextItem;
            nextItem = nextItem.next;
            index++;
            return temp.item;
        }

        // Remove the last node visited by the .next() call
        // For example, if we had just created an iterator,
        // the following calls would remove the item at index 1 (the second person in the ring)
        // next() next() remove()
        // Use CircularLinkedList.this to reference the CircularLinkedList parent class
        public void remove(){
            Node<E> secondLast = (Node<E>) head;
            for(int i = 0; i< index - 1; i++){
                if(hasNext()){
                    secondLast = secondLast.next;
                }
            }
            secondLast = null;
        }
    }


    // The Node class
     private static class Node<E>{
        E item;
        Node<E> next;
        public Node(E item) {
            this.item = item;
        }

        @Override
        public String toString(){
            return this.item.toString();
        }
    }


    /**
     * Return Node<E> found at the specified index
     * Be sure to check for out of bounds cases
     * */
    public Node<E> getNode(int index) {
        Node <E> current = head;
        int count = 0;
        while(current!=null){
            if(count == index){
                return current;
            }
            count++;
            current = current.next;
        }
        return null;
    }


    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of soldiers: ");
        int soldiers = scan.nextInt();
        System.out.println("Enter count: ");
        int count = scan.nextInt();
        CircularLinkedList army = new CircularLinkedList();
        for(int i = 0; i<=soldiers; i++){
            army.add(i+1);
        }


        System.out.println("Filled LikedList: "+ army);
        ListIterator<E> li = new ListIterator<E>();
        while(army.size>2){
            for(int i = 0; i<2;i++){
                army.next();
            }
            army.remove();

        }
    }
}
