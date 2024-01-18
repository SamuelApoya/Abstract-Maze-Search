
/**
 * Author: Samuel Apoya
 */

/**
 * A linkedList that implements the iterable interface.
 */

import java.util.ArrayList;
import java.util.Iterator;

public class LinkedList<T> implements Stack<T>,Queue<T>, Iterable<T>{


    public T pop(){
        return remove();
    }



    public void push(T item){
        add(item);
    }


    public void offer(T data){
        // effectively addLast
        if (size == 0){
			// create node
			Node<T> newNode = new Node<T>(data);
			// attach head to new node
			this.head = newNode;
			// attach tail to new node
			this.tail = newNode;
		}
		else {
			// create node
			Node<T> newNode = new Node<T>(data);
			newNode.setPrev(tail);
			this.tail.setNext(newNode);
			this.tail = newNode;
		}
		this.size++;

    }

    public T peek(){
        return get(0);
    }


    public T poll(){
        Node<T> walker = this.head;
        // removing head
        T data = walker.getData();
        this.head = this.head.getNext();
        size--;
        if(size == 0){
            this.tail = null;
        } else {
            this.head.setPrev(null);
        } return data;
    }




    private class LLIterator implements Iterator<T> {
        private Node<T> holdCuurent;

        public LLIterator(Node<T> head) {
            holdCuurent = head;
        }

        public boolean hasNext() {
            return (this.holdCuurent != null);
        }

        public T next() {
            T data = holdCuurent.getData();
            this.holdCuurent = this.holdCuurent.getNext();
            return data;
        }
    }

    // Return a new LLIterator pointing to the head of the list
    public Iterator<T> iterator() {
        return new LLIterator(this.head);
    }


    public ArrayList<T> toArrayList(LinkedList<T> myLinkedList){
        ArrayList<T> myArrayList = new ArrayList<>();
        Node<T> temp = myLinkedList.head;
        while (temp != null){
            myArrayList.add(temp.data);
            temp = temp.getNext();
        }
        return myArrayList;
    }

    public static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        public Node(T item) {
            data = item;
            next = null;
            prev = null;
        }

        public Node(T item, Node<T> next) {
            data = item;
            this.next = next;

        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> newNext) {
            next = newNext;

        }

        public Node<T> getNext() {
            return next;
        }

        public void setPrev(Node<T> newPrev) {
            prev = newPrev;

        }

        public Node<T> getPrev() {
            return prev;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList() {
        size = 0;
        head = null;
    }

    public void add(T item) {
        Node<T> newNode = new Node<T>(item, head);
        // Node<T> newNode = new Node<T>(item);
        // newNode.setNext(head);
        if (size == 0){
            tail = newNode;
        } else {
            head.setPrev(newNode);
        }
        head = newNode;
        size++;
    }

    public void add(int index, T item) {
        Node<T> walker = head;
        if (index == 0){
            add(item);
        }
        else{
            for (int i = 0; i < index -1; i++){
                walker = walker.getNext();
            } // at this point, walker is sitting at index - 1
            Node<T> newNode = new Node<T>(item, walker.getNext());
            walker.setNext(newNode);
            size++;
                
            }
        }


    public void clear() {
        size = 0;
        head = null;
    }

    public boolean contains(Object o) {
        Node<T> walker = head;
        for (int i = 0; i < size; i++) {
            if (walker.getData().equals(o)) {
                return true;
            } else {
                walker = walker.getNext();
            }
        }
        return false;
    }

    public boolean equals(Object o) {
        if (!(o instanceof LinkedList)) {
            return false;
        }
        // If I have reached this line, o must be a LinkedList
        LinkedList oAsALinkedList = (LinkedList) o;
        // Now I have a reference to something Java knows is a LinkedList!
        Node<T> walker = head;
        Node oWalker = oAsALinkedList.head;
        while (walker != null) {
            if (!walker.getData().equals(oWalker.getData())) {
                return false;
            } else {
                walker = walker.getNext();
                oWalker = oWalker.getNext();
            }
        }
        return true;
    }

    public T get(int index) {
        Node<T> walker = head;
        for (int i = 0; i < index; i++) {
            walker = walker.getNext();

        } // At the end of this for loop, walker is at the specified index.
        return walker.getData();
    }

    public boolean isEmpty() {
        return size == 0;

    }

    public T remove() {
        T firstItem = head.getData();
        head = head.getNext();
        size--;
        return firstItem;
    }

    public T remove(int index) {
        Node<T> walker = head;
        for (int i = 0; i < index - 1; i++) {
            walker = walker.getNext();
        } // At the end of this for loop,
          // walker is at index - 1.
        Node<T> toRemove = walker.getNext();
        Node<T> newNext = toRemove.getNext();
        walker.setNext(newNext);
        size--;
        return toRemove.getData();
    }

    public int size() {
        return size;

    }

    public String toString() {
        String list1 = "";
        Node<T> walker = head;
        for (int i = 0; i < size; i++) {
            list1 += walker.getData() + ", ";
            walker = walker.getNext();
        }
        return list1;
    }




    public void addFirst(T data) {
		// TODO

			// create new node with item
			Node newNode = new Node(data);
			if(size == 0){
	
				// attach both head and tail to new node
				this.head = newNode;
				this.tail = newNode;
			}
			else {
				// attach new node next to head
				newNode.setNext(head);
				head.setPrev(newNode);
				
				// move head to new node
				this.head = newNode;
			}
			this.size++;
	}

	/**
	 * This method adds the given {@code data} to the end of the list.
	 * 
	 * @param data the data to be added into the list.
	 */
	public void addLast(T data) {
		// TODO
		if (size == 0){
			// create node
			Node newNode = new Node(data);
			// attach head to new node
			this.head = newNode;
			// attach tail to new node
			this.tail = newNode;
		}
		else {
			// create node
			Node newNode = new Node(data);
			newNode.setPrev(tail);
			this.tail.setNext(newNode);
			this.tail = newNode;
		}
		this.size++;
	}

	/**
	 * This method returns and removes the first entry of the list.
	 * 
	 * @return the last entry of the list.
	 */
	public T removeFirst(){
		// TODO

			Node walker = this.head;
			// removing head
			T data = (T) walker.getData();
			this.head = this.head.getNext();
			this.head.setPrev(null);
			size--;
			if(size == 0){
				this.tail = null;
			}
			return data;

		// This return statement is only to let the code compile as is.
		// When you are ready, replace this return statement with the correct 
		// value.
		
	}

	/**
	 * This method returns and removes the last entry of the list.
	 * 
	 * @return the last entry of the list.
	 */
	public T removeLast(){
		// TODO
		Node removed = this.tail;
		Node previous = this.tail.getPrev();
		this.tail = previous;
		this.tail.setNext(null);
		size--;
		T data = (T) removed.getData();

		if (size == 0) {
			// removing head
			this.head = null;
		}
		// This return statement is only to let the code compile as is.
		// When you are ready, replace this return statement with the correct 
		// value.
		return data;

	}






}


