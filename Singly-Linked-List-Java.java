public class SinglyLinkedList {
	private static class Node {
		private int val;
		private Node next;
	}
	private int size;
	private Node head;
	private Node tail;
}

/*
1. LinkedList in java is the implementation of doubly linked list which uses two major interfaces, List and Deque
	List and Deque both are implemented, why?
	LinkedList implements both List and Deque because a doubly linked list naturally supports indexed list operations as well as efficient insertion/removal at both ends (queue, deque, and stack operations).
		you can do both in O(1): add(0, val) and addFirst(val) ::: first one is indexed operation in which you know index value and index calculation is performed internally(by function node(index)), but second one is non-indexed operation and without having extra effort of index calculation, it adds the element at first
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

2. Node is defined as static class inside LinkedList because inner class in java, whenever created an object of it, stores the reference of its parent class.
	Here we dont want any Node to access the LinkedList object because Node does not need to access instance members(ex: size, first, last, modCount) of the outer class.
	Note: Make a nested class static if it does not need to access instance members of the outer class.

3. In Java, the transient keyword is used to exclude specific fields of an object from being serialized. It ensures that sensitive or unnecessary data is not saved when the object is converted into a byte stream.
	The default Serialization process ignores fields declared as transient.
	Transient fields are initialized with default values during deserialization.
	LinkedList and other has their own serializer and deserializer (readObject, writeObject)
		this is done because, java default serializer would serialize whole chain node, even it's internal pointers and everything, which can increase size and many other reasons
		instead LinkedList and ArrayList both has its own serializer and it serializes only important part so that it can be deserialized again correctly
		Ask yourself: In LinkedList, do you require serializing prev and next value of each node?
		Ans: No, just store the element in array, serialize it, and then if someone deserialize it, it can construct LinkedList from just array element
			Here, serialized byte stream size decreased, and we successfully serialized our element :)


*/