import java.util.*;

class Pair {
    int first;
    int second;

    Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

public class Final2 {
    // Function to find the shortest path between two nodes in a graph
    static void shortestPath(int start, int end, ArrayList<ArrayList<Pair>> adj, int n) {
        PriorityQueue<Pair> p = new PriorityQueue<>((a, b) -> a.second - b.second);

        boolean[] vis = new boolean[n];
        int[] key = new int[n];
        int[] parent = new int[n];

        Arrays.fill(parent, -1);

        for (int i = 0; i < n; i++) {
            key[i] = Integer.MAX_VALUE;
        }
        key[start] = 0;
        p.add(new Pair(start, 0));

        while (!p.isEmpty()) {
            Pair curr = p.poll();
            int x = curr.first;
            int wt = curr.second;
            if (vis[x]) {
                continue;
            }
            vis[x] = true;
            for (Pair k : adj.get(x)) {
                int ve = k.first;
                int w = k.second;
                if (key[ve] > key[x] + w) {
                    key[ve] = key[x] + w;
                    p.add(new Pair(ve, key[ve]));
                    parent[ve] = x; // Update the parent node
                }
            }
        }

        // Reconstruct the path from end to start
        ArrayList<Integer> path = new ArrayList<>();
        int node = end;
        while (node != -1) {
            path.add(node);
            node = parent[node];
        }
        Collections.reverse(path);

        // Print the path
        System.out.println("Shortest path from " + start + " to " + end + " with a distance of " + key[end] + ":");
        for (int i : path) {
            System.out.print(i + " ");
        }
        System.out.println();

        
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of cities");
        int n = sc.nextInt();
        
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            System.out.println("Enter the number of neighbour places of city "+i);
            int numneighbours=sc.nextInt();
            for(int j=0;j<numneighbours;j++){
                int a=j+1;
                System.out.println("Enter neighbor place of "+i+":");
                int neighborNode = sc.nextInt();
                System.out.print("Enter distance to neighbor place " + neighborNode + " from "+i+" :");
                int weight = sc.nextInt();
                adj.get(i).add(new Pair(neighborNode,weight));
            }
        }
        System.out.println("Please Enter the start city");
        int start=sc.nextInt();
        System.out.println("Please Enter the end city");
        int end=sc.nextInt();
        if(start<0 || end>=n){
            System.out.println("Please check your start and end cities");
        }
        else{
            shortestPath(start, end, adj, n); // Find the shortest path from start to end
        }
        
    }
}
