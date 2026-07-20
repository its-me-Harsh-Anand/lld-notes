import java.util.ArrayList;
import java.util.Collections;

public class HeapSort {
	public void heapSort(ArrayList<Integer> arr){
		heapify(arr); // this will create valid heap

		int lastIndex = arr.size()-1;

		while(lastIndex > 0){
			Collections.swap(arr, 0, lastIndex);
			siftDown(arr, 0,lastIndex-1);
			lastIndex--;
		}
	}
	private void heapify(ArrayList<Integer> arr){
		int n = arr.size();
		for(int i=(n/2)-1; i>=0; i--){
			siftDown(arr,i, arr.size()-1);
		}
	}
	private void siftDown(ArrayList<Integer> arr, int i, int n){
		int lchild = 2*i+1;
		int rchild = 2*i+2;

		int max = i;
		if(lchild<=n && arr.get(max) < arr.get(lchild)){
			max = lchild;
		}
		if(rchild<=n && arr.get(max) < arr.get(rchild)){
			max = rchild;
		}
		if(max != i){
			Collections.swap(arr, max, i);
			siftDown(arr, max, n);
		}
	}

	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<>();

		arr.add(-1);
		arr.add(5);
		arr.add(8);
		arr.add(3);
		arr.add(2);

		HeapSort heapSort = new HeapSort();
		heapSort.heapSort(arr);
		System.out.println(arr);
	}
}

/*
1. Get all the element in array
2. call heapify, which will traverse from i=(n/2)-1 to 0 to build a valid max heap by calling siftDown on each i
3. now since we made max heap, item at 0 is max, hence swap(0, n-1) and call siftDown(0, n-2), which will ignore last index
4. repeat 3 until n=1
*/

/*
In an array-based binary heap, the left child of index i is 2*i + 1. A node has children only if 2*i + 1 < n. Solving this gives i < n/2, so indices n/2 through n-1 are all leaves. Since leaves are already valid heaps, the last non-leaf node is (n/2) - 1, and heap construction starts from there and moves upward." for i=(n/2)-1 till 0, call siftDown(i)

total time complexity of heapify:
    (n/2) items are leaf and require 0 sift down
    (n/4) items are at height-1 and require 1 sift down at max
    (n/8) items are at height-2 and require 2 sift down at max
    ...and so on

    total = (n/2)*0 + (n/4)*1 + (n/8)*2 + (n/16)*3 + ...
    total = n

hence heapify takes O(n).
If you call pq.offer(i) for each i=0 to n, then it will take n*log(n) which is bad.
*/

// Below is the usage of PriorityQueue using a custom Comparator. In java if you don't provide, it will take the natural ordering of element (Integer, Double)
// And natural ordering of mostly primitive type looking element is ascending, hence min heap by default
/*
import java.util.Comparator;
import java.util.PriorityQueue;

public class Temp {
    public static class Rectangle {
        Integer width;
        Integer height;

        public Rectangle(Integer width, Integer height){
            this.width = width;
            this.height = height;
        }
        public Integer area(){
            return width * height;
        }
    }
    public static void main(String[] args) {
        PriorityQueue<Rectangle> pq = new PriorityQueue<>(
                new Comparator<Rectangle>() {
                    @Override
                    public int compare(Rectangle o1, Rectangle o2) {
                        if(o1.height.equals(o2.height)){
                            return o1.width - o2.width;
                        }
                        return o1.height - o2.height; // for min-heap, use o1.area() - o2.area() and for max-heap, use o2.area() - o1.area()
                    }
                }
        );

        Rectangle r1 = new Rectangle(5,3);
        Rectangle r2 = new Rectangle(4,3);
        Rectangle r3 = new Rectangle(6,7);

        pq.offer(r1);
        pq.offer(r2);
        pq.offer(r3);

        System.out.println(pq.poll().area());
    }
}
*/