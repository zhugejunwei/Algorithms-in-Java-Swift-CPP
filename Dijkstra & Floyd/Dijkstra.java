class Dijkstra
{
    public static int minDist(int[] dist, boolean[] pathSet, int V) {
        int minDist = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < V; v++) {
            if (!pathSet[v] && dist[v] < minDist) {
                minDist = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
    
    public static void dijkstra(int graph[][], int src ,int V)
    {
        int[] dist = new int[V];
        boolean[] pathSet = new boolean[V];
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            pathSet[i] = false;
        }
        dist[src] = 0;
        for (int count = 0; count < V - 1; count++) {
            int u = minDist(dist, pathSet, V);
            pathSet[u] = true;
            
            for (int v = 0; v < V; v++) {
                if (!pathSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE
                    && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }
        for (int i = 0; i < V; i++) {
            System.out.print(dist[i] + " ");
        }
    }
}
