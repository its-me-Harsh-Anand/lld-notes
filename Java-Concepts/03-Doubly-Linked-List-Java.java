public class LinkedList {

	private Node head;
	private Node tail;
	private int size = 0;

	private static class Node {
		Node prev;
		Node next;
		int val;

		public Node(int val){
			this.val = val;
		}
	}

	public void pushFront(int key){
		Node node = new Node(key);

		if(head == null){
			head = tail = node;
			size++;
			return;
		}
		node.next = head;
		head.prev = node;
		head = node;

		size++;
	}

	public void pushBack(int key){
		Node node = new Node(key);

		if(tail == null){
			head = tail = node;
			size++;
			return;
		}

		tail.next = node;
		node.prev = tail;
		tail = node;

		size++;
	}  

	public void popFront(){
		if(head == null){
			throw new NoSuchElementException();
		}
		if(head == tail){
			head = tail = null;
			size--;
			return;
		}

		Node temp = head.next;
		head.next = null;
		temp.prev = null;
		head = temp;
		size--;
	}  

	public void popBack(){
		if(tail == null){
			throw new NoSuchElementException();
		}
		if(head == tail){
			head = tail = null;
			size--;
			return;
		}

		Node temp = tail.prev;

		temp.next = null;
		tail.prev = null;
		tail = temp;
		size--;
	}   

	public int topFront(){
		if(head == null){
			throw new NoSuchElementException();
		}
		return head.val;
	}
	public int topBack(){
		if(tail == null){
			throw new NoSuchElementException();
		}
		return tail.val;
	}  
	public boolean find(int key){
		Node temp = head;
		while(temp != null){
			if(temp.val == key){
				return true;
			}
			temp = temp.next;
		}
		return false;
	}  
	public void erase(int key){
		if(head == null){
			return;
		}

		Node temp = head;
		while(temp != null){
			if(temp.val == key){
				if(temp == head){
					popFront();
				} else if(temp == tail){
					popBack();
				} else {
					Node p = temp.prev;
					Node n = temp.next;

					p.next = n;
					n.prev = p;
					size--;
				}
				return;
			}
			temp = temp.next;
		}
	}  
	public boolean isEmpty(){
		return size == 0;
	}

}

/*
1. Any node will be a part of GC garbage collection, if that is unreachable by Garbage Collector.
2. For a custom fail-fast iterator : implement Iterable<> interface and implement iterator() class within it
	@Override
	public Iterator<Integer> iterator() {
	    return new DLLIterator();
	}
	
	private class DLLIterator implements java.util.Iterator<Integer> {

	    private Node current = head;
	    private int expectedModCount = modCount;

	    @Override
	    public boolean hasNext() {
	        return current != null;
	    }

	    @Override
	    public Integer next() {

	        if (modCount != expectedModCount) {
	            throw new java.util.ConcurrentModificationException();
	        }

	        if (current == null) {
	            throw new java.util.NoSuchElementException();
	        }

	        int val = current.val;
	        current = current.next;
	        return val;
	    }
	}
public void pushFront : O(1)
public void pushBack  : O(1)
public void popFront  : O(1)
public void popBack   : O(1) => in singly linked list it was O(n)
public int topFront   : O(1)
public int topBack    : O(1)
public boolean find   : O(n)
public void erase     : O(n)
public boolean isEmpty: O(1)

3. LinkedList in java is the implementation of List and Deque interface, which means it can be used as Array, Stack and Queue
Below are common function of LinkedList in java

LinkedList<Integer> list = new LinkedList<>();
For List: addFirst, addLast, removeFirst, removeLast, getFirst, getLast, size, isEmpty
For Stack: push, pop, top, isEmpty
For Queue: offer, poll, peek, isEmpty
*/