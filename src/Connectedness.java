
import java.util.Scanner;

// https://open.kattis.com/problems/connectedness
// not fast enough, see Connectedness1.java for a faster version using BufferedReader and StringTokenizer
public class Connectedness {

    static class DSU {
        private int[] parent;
        private int[] size;
        private int components;

        public DSU(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            components = n;

            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        // Find the representative of the component containing x.
        // Path compression makes future searches faster.
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        // Merge the components containing x and y.
        // Return true if two different components were merged.
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return false;
            }

            // Union by size: attach the smaller tree to the larger.
            if (size[rootX] < size[rootY]) {
                int temp = rootX;
                rootX = rootY;
                rootY = temp;
            }

            parent[rootY] = rootX;
            size[rootX] += size[rootY];
            components--;

            return true;
        }

        public int getComponents() {
            return components;
        }
    }

    public static void main(String[] args) {
        /*
         * The problem is to determine the earliest point at which a graph becomes connected
         * as edges are added one by one. We can use a Disjoint Set Union (DSU) data structure
         * to efficiently manage the connected components of the graph.
         *
         * The DSU will allow us to union two vertices and check if they belong to the same component.
         * We will keep track of the number of connected components, and when it reaches 1,
         * we know the graph is fully connected.
         */
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        // A graph with one vertex is already connected.
        if (n == 1) {
            System.out.println(0);
            sc.close();
            return;
        }

        DSU dsu = new DSU(n);

        for (int i = 1; i <= m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            dsu.union(u, v);

            // The graph becomes connected for the first time.
            if (dsu.getComponents() == 1) {
                System.out.println(i);
                sc.close();
                return;
            }
        }

        // All edges were processed, but the graph is disconnected.
        System.out.println(-1);

        sc.close();
    }
}
