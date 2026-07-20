// use int[] parent and int[] rank instead;

public class DisjointSet {
    private ArrayList<Integer> parent;
    private ArrayList<Integer> rank;

    public DisjointSet(int size){
        parent = new ArrayList<>(size+1);
        rank = new ArrayList<>(size+1);

        for(int i=0; i<=size; i++){
            parent.add(i);
            rank.add(0);
        }
    }

    public int find(int i){
        if(i == parent.get(i)){
            return i;
        }
        int parentI = find(parent.get(i));
        parent.set(i, parentI);
        return parentI;
    }

    public void union(int a, int b){
        int parentA = find(a);
        int parentB = find(b);

        if(parentA == parentB) return;

        if(rank.get(parentA) < rank.get(parentB)){
            parent.set(parentA, parentB);
        } else if (rank.get(parentA) > rank.get(parentB)){
            parent.set(parentB, parentA);
        } else {
            parent.set(parentB, parentA);
            rank.set(parentA, rank.get(parentA)+1);
        }
    }
}