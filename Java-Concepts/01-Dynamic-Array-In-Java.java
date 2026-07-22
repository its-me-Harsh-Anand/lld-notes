public class ArrayList {
	private int size;
	private int[] array;

	private static final int DEFAULT_INITIAL_CAPACITY = 10;

	public ArrayList(){
		this.size = 0;
		this.array = new int[0];
	}

	private void grow(){
		int oldCapacity = this.array.length;
		int newCapacity;
		if(oldCapacity == 0){
			newCapacity = DEFAULT_INITIAL_CAPACITY;
		} else {
			newCapacity = oldCapacity + (oldCapacity >> 1);
		}

		int[] newArray = new int[newCapacity];
		for(int i = 0; i<size; i++){
			newArray[i] = this.array[i];
		}
		this.array = newArray;
	}

	public void add(int num){
		if(this.size == this.array.length){
			this.grow();
		}
		array[this.size] = num;
		this.size++;
	}

	public void add(int index, int num){
		if(index < 0 || index > this.size){
			throw new IndexOutOfBoundsException("Index : " + index + ", Size: "+ this.size);
		}

		if(this.size == this.array.length){
			this.grow();
		}

		int totalElToBeMoved = this.size - index;
		if(totalElToBeMoved > 0) System.arraycopy(this.array, index, this.array, index+1, totalElToBeMoved);

		this.size++;
		this.array[index] = num;
	}

	public int get(int index){
		if(index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Index : " + index + ", Size: "+ this.size);
		}

		return this.array[index];
	}

	public int remove(int index){
		if(index < 0 || index >= this.size){
			throw new IndexOutOfBoundsException("Index : " + index + ", Size: "+ this.size);
		}

		int oldValue = this.array[index];
		int totalElToBeMoved = this.size - index - 1;

		if(totalElToBeMoved >0) System.arraycopy(this.array, index+1, this.array, index, totalElToBeMoved);

		this.size--;
		this.array[size] = 0;

		return oldValue;
	}

	public int size(){
		return this.size;
	}
}

/*
Notes: 
1. Lazy Initialization: Initialize the internal array with a capacity of 0 to save memory when a list is created but never used.
2. Smart Growth Mechanics: When the first element is added, allocate a default capacity of 10. For subsequent expansions (when size == array.length), grow the capacity by 1.5x using bitwise shifting (`oldCapacity >> 1`) to balance performance and memory waste.
3. shifting/copying operation:
	int[] newArray = new int[newCapacity];
	for(int i = 0; i<size; i++){
		newArray[i] = this.array[i];
	}
	this.array = newArray;

	can be done more efficiently with
	System.arraycopy(srcArray, srcArrayCopyFromIndex, destArray, destArrayPasteFromIndex, totalElementToCopy)
4. ArrayList in java is the implementation of dynamic array and is not synchronized. meaning this is not thread safe. 
	For synchronized and thread safety, use Vector in java, which is also the implementation of dynamic array.
5. Common function from cpp vector to java vector/arrayList
	v.push_back() ==> v.add()
	v.pop_back() ==> v.remove(v.size()-1);
	v.size() ==> v.size();
	v[0] ==> v.get(i);
*/