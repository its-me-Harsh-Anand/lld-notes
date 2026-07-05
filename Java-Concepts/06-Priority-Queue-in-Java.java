public class PriorityQueue {
	private final ArrayList<Integer> arr = new ArrayList<Integer>();

	public PriorityQueue(){
		
	}

	public void insert(int p){
		arr.add(p);
		siftUp(arr.size()-1);
	}

	public int extractMax(){
		if(arr.size()==0) {
			throw new NoSuchFieldException("PriorityQueue is empty");
		}

		int root = arr.get(0);
		Collections.swap(arr, 0, arr.size()-1);
		arr.remove(arr.size() -1);
		siftDown(0);

		return root;
	}

	public int peek(){
		if(arr.size()==0) {
			throw new NoSuchFieldException("PriorityQueue is empty");
		}
		return arr.get(0);
	}

	public boolean isEmpty(){
		return arr.size() == 0;
	}

	public int size(){
		return arr.size();
	}

	private void siftDown(int index){
		if(index >= arr.size()) return;

		int lchild = 2*index + 1;
		int rchild = 2*index + 2;

		int largestSoFar = index;
		if(lchild < arr.size() && arr.get(lchild) > arr.get(largestSoFar)){
			largestSoFar = lchild;
		}
		if(rchild < arr.size() && arr.get(rchild) > arr.get(largestSoFar)){
			largestSoFar = rchild;
		}
		if(largestSoFar == index) return;

		Collections.swap(arr, index, largestSoFar);
		siftDown(largestSoFar);
		
	}
	private void siftUp(int index){
		if(index <=0) return;

		int parent = (index-1)/2;
		if(arr.get(index) > arr.get(parent)){
			Collections.swap(arr, index, parent);
			siftUp(parent);
		}
	}
}

/*
Priority Queue is a generalization of queue where each element is assigned a priority and elements come out in order by priority.
Ex: Scheduling jobs: want to process job based on the priority and while processing a job, new job may arrive

It has two basic functions: 
	Insert(p) : Insert a new element with priority p 
	ExtractMax() : extracts an element with maximum priority
Additional functions we can implement is : 
	ChangePriority(it, p) : change the priority of an element pointed by "it" to "p" 
	Remove(it) : remove an element pointed by iterator "it"
	GetMax() : returns the element with maximum priority (without changing the set of elements)
Algorithms that used priority queue:
	Dijsktra's algorithm: finding a shortest path in a graph
	Prim's algorithm: constructing a minimum spanning tree of a graph
	Huffman's algorithm: constructing an optimum prefix-free encoding of a string
	Heap sort: sorting a given sequence
Naive Implementation:
	If we implement priotity queue by array or DLL, then either of the two operation will take linear time
	In case of unsorted array: inserting will take O(1) but getMax will take O(n)
	In case of sorted array: inserting will take O(n) (O(logn) for searching the position and O(n) to shift them) but getMax will take O(1)
	In case of linkedlist : same thing happen
Better Implementation:
	Using binary tree -> then go to complete binary tree -> then go to array that represent complete binary tree
	all the operation will take O(tree height) or say O(logn) where n = number of element

Java Specific concepts:
	:: PriorityQueue class in java uses primitive array and it works on the concept of dynamic array.
	It starts with DEFAULT_INITIAL_CAPACITY of 11, and when full it calls its grow() function.
		if (oldCapacity < 64)
		    newCapacity = oldCapacity + (oldCapacity + 2);
		else
		    newCapacity = oldCapacity + (oldCapacity >> 1);
	
	::Comparator interface in java has only one abstract method (compare()) others are either static, or with body or equals() which is inherited from Object class
	Also it is a functional interface which means we can implement this interface on the fly like Runnable. example:
	PriorityQueue<Integer> pq = new PriorityQueue<Integer>(
		new Comparator<Integer>(){
			@override
			public int compare(Integer a, Integer b){
				return b-a;  // for MAX_HEAP
			}
		}
	)
	|||||||||||||above is on the go implementation of Comparator interface|||||||||||||||||||

	OR,
	PriorityQueue<Integer> pq = new PriorityQueue<Integer>(
		(a,b) -> b-a
	);
	|||||||||||| java internally treat above lambda expression as the implementation of Comparator||||||||||||||||||

=======================================
Implementation via complete binary tree which can be represented by an array.
1. Insert()
	Insert element at leaf node (at the end of the array)
	Call SiftUp()
		siftup will check with parent, if parent < current, swap and then call siftUp recursively on parent else return.

2. ExtractMax()
	Get the root (first element of array)
	swap it with leaf (swap(1st and last element of array))
	remove that leaf node
	now call SiftDown(root).
		if(root < max(left or right child)) swap root with max child
		call SiftDown(swapped child) recursively till this property is not satisfied or you reach leaf node
3. ChangePriority(it, p)
	if "p" is greater than previous priority, it means its children are safe (because childres was less than parent earlier too and now this time it will be less too)
		hence call SiftUp()
	else if "p" is less than previous priority, it means it's parent is safe but not the children
		hence call SiftDown()

4. Remove(it)
	call ChangePriority(it, infinity) => this will bring that element to top/root
	then call ExtractMax() => this will swap root with leaf node then internally apply SiftDown

All above operation takes log(n) or O(tree height)
We can represent complete binary tree by array where root is at 0-th index.
For index i, its child are at 2*i+1 and 2*i+2
For index i, its parent is at (i-1)/2
*/