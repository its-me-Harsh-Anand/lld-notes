public class HashSet<E> {

    private transient HashMap<E, Object> map;

    private static final Object PRESENT = new Object();

    public HashSet() {
        map = new HashMap<>();
    }

    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    public boolean remove(Object o) {
        return map.remove(o) != null;
    }

    public int size() {
        return map.size();
    }
}

/*
A set is a collection of unique elements which supports 3 basic operations: insert(el), remove(el) and contains(el)
Insert:
	check if el alreay exists
		if exists, then ignore and return
		else add

Remove:
	check if el exists
		if exists, then delete it
		else return

Contains:
	check if el exists
		if exists, return true
		else return false

see that all the functions are searching the element first, hence in order to minimize the time complexity, we should minimize searching mechanism
To search something, if we linearly traverse it would take O(n) time. but what if we exactly know the position of element then search would be O(1)
1. keep the element at array's index, then search(el) => directly check arr[el]
	ex: insert(1000) => arr[1000] = 1000

	but in this approach, we end up creating 1000 size array just for storing one data.

2. Can we somehow encode this element to get the efficient position, so that for searching we can decode that element and get its position and directly go to that position in array
	ex: insert(1000) and current array size is 10
	then encode : el % size
		index = 1000 % 10 = 0
		arr[0] = 1000

		now if someone searches 1000, we call hash(1000) => returns 0, then go to 0 and check if element is there

3. but in that approach, all the element that ends with 0 (10, 20, 30, 100 ...) will have same index number i.e, 0 (this is called collision)
	array will look like : vector<vector<int>> v(size);         {v[0], v[1] etc are called buckets}
		v[0].push_back(1000)
		v[0].push_back(10)
		v[0].push_back(20)

		in this case too, Now we only search the bucket corresponding to the hash value, instead of searching every element in the set.
		ideally, no. of bucket = no. of elements => this is called load factor
		Load Factor simply answers : "On average, how many elements are stored per bucket?"
		Formula: Load Factor = Number of Elements / Number of Buckets
			ex: 10 bucket and 5 element, load factor = 0.5, which means 0.5 elements are there in each bucket on an average
			ex: 10 bucket and 50 element, load factor = 5, meaning 5 element per bucket on an average => search becomes slower

			JAVA MAINTAINS LOAD FACTOR = 0.75

4. Now, If the hash function distributes elements uniformly across buckets, then each bucket contains only a few elements on average. Therefore, searching, insertion, and deletion become O(1) on average.
	to reduce the collision, define a better hash function. 
	Use a good hash function that distributes elements uniformly across buckets. Also, increase the number of buckets when the table becomes too full (rehashing).
*/

/*
JAVA SPECIFIC CONCEPTS
1. HashSet in java uses HashMap, with default value of the key as PRESENT (singleton object)
2. Java uses a load factor of 0.75
3. Java utilizes HashMap to serve HashSet functions
*/