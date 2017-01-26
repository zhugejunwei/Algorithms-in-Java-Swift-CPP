public class Main {
    public static void A_star(int[][] graph, int start, int end) {
        int V = graph.length;
        
        // the set of nodes already evaluated
        Set<Integer> closedSet = new HashSet<>();
        
        // the set of currently discovered nodes that are already evaluated
        // initially only the start node is known.
        Set<Integer> openSet = new HashSet<>();
        openSet.add(start);
        
        // the most efficient previous step, i.e. the parent node
        int[] cameFrom = new int[V];
        for (int i = 0; i < V; i++) cameFrom[i] = i;
        
        // for each node, the cost of getting from start node to that node
        int[] gScore = new int[V];
        Arrays.fill(gScore, Integer.MAX_VALUE);
        gScore[start] = 0;
        
        // fScore = gScore + hScore
        // hScore is the estimated cost to move from that node to the end
        // The value is partly known, partly heuristic
        int[] fScore = new int[V];
        Arrays.fill(fScore, Integer.MAX_VALUE);
        // 3 ways to calculate "h"
        //      1. Euclidean Distance (Exact Heuristics)
        //          when there are no blocked cells/obstacles
        //      h = sqrt ( (current_cell.x – goal.x)2 + (current_cell.y – goal.y)2 )
        
        //      2.1 Manhattan Distance (Approximation Heuristics)
        //          When we are allowed to move only in four directions only
        //       h = abs (current_cell.x – goal.x) + abs (current_cell.y – goal.y)
        
        //      2.2 Diagonal Distance (Approximation Heuristics)
        //          When we are allowed to move 8 directions only
        //       h = max { abs(current_cell.x – goal.x), abs(current_cell.y – goal.y) }
        
        fScore[start] = hScore(start, end);
        
        while (!openSet.isEmpty()) {
            int cur = minF(openSet, fScore, V);
            
            // reach the end point
            if (cur == end) {
                constructPath(cameFrom, cur, start);
            }
            
            // current node has been evaluated
            openSet.remove(cur);
            closedSet.add(cur);
            
            for (int v = 0; v < V; v++) { // for each neighbor of cur
                // not a neighbor, undirected graph
                if (graph[cur][v] == 0) continue;
                // has been evaluated
                if (closedSet.contains(v)) continue;
                
                // the dist from start to the neighbor
                int tmp_gScore = gScore[cur] + graph[cur][v];
                
                if (!openSet.contains(v)) openSet.add(v); // Discover a new node
                else if (tmp_gScore >= gScore[v]) continue; // this is not a better path
                
                // else, this is a better path
                cameFrom[v] = cur;
                gScore[v] = tmp_gScore;
                fScore[v] = gScore[v] + hScore(v, end);
            }
        }
    }
    
    private static int hScore(int start, int end) {
        // here we use Manhattan dist
        return 2 * Math.abs(end - start);
    }
    
    private static int minF(Set<Integer> openSet, int[] fScore, int V) {
        int minVal = Integer.MAX_VALUE, idx = -1;
        for (int i = 0; i < V; i++) {
            if (openSet.contains(i) && fScore[i] < minVal) {
                minVal = fScore[i];
                idx = i;
            }
        }
        return idx;
    }
    
    private static void constructPath(int[] cameFrom, int cur, int start) {
        // construct path
        LinkedList<Integer> finalPath = new LinkedList<>();
        finalPath.addFirst(cur + 1);
        while (true) {
            cur = cameFrom[cur];
            finalPath.addFirst(cur + 1);
            if (cur == start) break;
        }
        System.out.print(finalPath);
        System.out.println();
    }
    
    public static void main(String[] args) {
        int graph[][] = {
            {0,0,0,1,0},
            {0,0,0,1,1},
            {0,0,0,0,1},
            {1,1,0,0,1},
            {0,1,1,1,0},
        };
        A_star(graph, 0, 2);
    }
}
