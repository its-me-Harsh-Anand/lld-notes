public class Queue {
	private LinkedList<Integer> list;

	public Queue(){
		list = new LinkedList<>();
	}

	public void push(Integer el){
		list.addLast(el); // or list.add(el)
	}

	public void pop(){
		if(list.isEmpty()){
			throw new RuntimeException("Queue is empty");
		}
		list.removeFirst();
	}

	public int front(){
		if(list.isEmpty()){
			throw new RuntimeException("Queue is empty");
		}
		return list.getFirst();
	}
}

/*
1. Above is the implementation of Queue using java's LinkedList.
2. Naming convention for function is similar to cpp STL but in Java, offer, poll and peek terminology is common rather than push, pop and front
3. For using Queue in java, simple take ArrayDeque q, and use q.offer, q.poll and q.peek
/*