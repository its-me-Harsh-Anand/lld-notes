public class PriorityQueue {

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
*/