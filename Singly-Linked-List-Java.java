public class SinglyLinkedList {
	private static class Node {
		int val;
		Node next;

		public Node(int val){
			this.val = val;
		}
	}
	private transient int size = 0;
	private transient Node head;
	private transient Node tail;

	public SinglyLinkedList(){

	}

	public void pushFront(int val){
		Node node = new Node(val);

		if(head == null){
			head = tail = node;
		} else {
			node.next = head;
			head = node;
		}
		size++;
	}
	public void pushBack(int val){
		Node node = new Node(val);

		if(tail == null){
			head = tail = node;
		} else {
			tail.next = node;
			tail = node;
		}
		size++;
	}

	public void popFront(){
		if(head == null){
			return;
		}
		if(head == tail){
			head = tail = null;
			size--;
			return;
		}
		head = head.next;
		size--;
	}

	public void popBack(){
		if(tail == null){
			return;
		}
		if(head == tail){
			head = tail = null;
			size--;
			return;
		}
		Node temp = head;
		while(temp.next != tail){
			temp = temp.next;
		}
		temp.next = null;
		tail = temp;
		size--;
	}

	public int topFront(){
		if(head == null){
			throw new  NoSuchElementException();
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
			if(temp.val==key) return true;
			temp = temp.next;
		}
		return false;
	}

	public void erase(int key){
		if(head == null) return;

		if(head.val == key){
			popFront();
			return;
		}

		Node prev = head;
		Node curr = head.next;

		while(curr != null){
			if(curr.val == key){
				prev.next = curr.next;
				if(curr == tail){
					tail = prev;
				}
				size--;
				return;
			}
			prev = curr;
			curr = curr.next;
		}
	}

	public boolean isEmpty(){
		return size==0;
	}

}

/*
1. LinkedList in java is the implementation of doubly linked list which uses two major interfaces, List and Deque
	List and Deque both are implemented, why?
	LinkedList implements both List and Deque because a doubly linked list naturally supports indexed list operations as well as efficient insertion/removal at both ends (queue, deque, and stack operations).
		you can do both in O(1): add(0, val) and addFirst(val) ::: first one is indexed operation in which you know index value and index calculation is performed internally(by function node(index)), but second one is non-indexed operation and without having extra effort of index calculation, it adds the element at first
		addFirst() is a deque-specific O(1) operation, while add(0, val) is a list operation whose efficiency depends on implementation; in LinkedList it is O(1), but in ArrayList it is O(n).

	    * Returns the (non-null) Node at the specified element index.
	    Node<E> node(int index) {
	        if (index < (size >> 1)) {
	            Node<E> x = first;
	            for (int i = 0; i < index; i++)
	                x = x.next;
	            return x;
	        } else {
	            Node<E> x = last;
	            for (int i = size - 1; i > index; i--)
	                x = x.prev;
	            return x;
	        }
	    }
	LinkedList implements List for sequence-based operations and Deque for efficient front/back, queue, and stack operations.

2. Node is defined as static class inside LinkedList because inner class in java, whenever created an object of it, stores the reference of its parent class and increases memory overhead.
	Here we dont want any Node to access the LinkedList object because Node does not need to access instance members(ex: size, first, last, modCount) of the outer class.
	Note: Make a nested class static if it does not need to access instance members of the outer class.

3. In Java, the transient keyword is used to exclude specific fields of an object from being serialized. It ensures that sensitive or unnecessary data is not saved when the object is converted into a byte stream.
	The default Serialization process ignores fields declared as transient.
	Transient fields are initialized with default values during deserialization.
	Some JDK collections like LinkedList and ArrayList define custom serialization methods (writeObject / readObject) to control how data is serialized instead of relying on default Java serialization.
		this is done because, java default serializer would serialize whole chain node, even it's internal pointers and everything, which can increase size and many other reasons
		instead LinkedList and ArrayList both has its own serializer and it serializes only important part so that it can be deserialized again correctly
		Ask yourself: In LinkedList, do you require serializing prev and next value of each node?
		Ans: No, just store the element in array, serialize it, and then if someone deserialize it, it can construct LinkedList from just array element
			Here, serialized byte stream size decreased, and we successfully serialized our element :) 

	transient is used when a class implements Serializable and you don’t want certain fields to be serialized.

4. When deleting/adding a node from the list, don't ever do value swapping, instead always maintain a prev, curr, next type of pointers, to change the pointing logically.

public void pushFront : O(1)
public void pushBack  : O(1)
public void popFront  : O(1)
public void popBack   : O(n)
public int topFront   : O(1)
public int topBack    : O(1)
public boolean find   : O(n)
public void erase     : O(n)
public boolean isEmpty: O(1)

popBack - this can be made O(1) by DoublyLinkedList
*/