public class StackUsingArray {
	private Vector<Integer> v;
	
	public StackUsingArray(){
		v = new Vector<Integer>();
	}

	public void push(int val){
		v.add(val);
	}

	public int pop(){
		if(v.size() == 0){
			throw new RuntimeException("Stack is empty");
		}
		int top = v.get(v.size()-1); 

		v.remove(v.size()-1);
		return top;
	}

	public int top(){
		 if(v.size() == 0){
	        throw new RuntimeException("Stack is empty");
	    }
		return v.get(v.size() -1);
	}

	public boolean isEmpty(){
		return v.size()==0;

	}
}

/*
1. Stack in Java extends Vector, which is a synchronized dynamic array in java
2. Vector in java has default initial capacity of 10 whereas ArrayList has 0 initially but grows to 10 when the first element added.
	Also, grow() in Vector grows the vector by 2x but ArrayList by 1.5x only
3. In java, instead of Vector, use ArrayList because this is not syncronized and is with less overhead.
	Common function from cpp vector to java vector/arrayList
	v.push_back() ==> v.add()
	v.pop_back() ==> v.remove(v.size()-1);
	v.size() ==> v.size();
	v[0] ==> v.get(i);
4. For Stack in java, use ArrayDeque<> stack; stack.push(), stack.pop(), stack.peek()
*/