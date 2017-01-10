public class Solution {
    public boolean canFinish(int numCourses, int[][] pre) {
        int[] inLinkCount = new int[numCourses];
        List<List<Integer>> graph = new ArrayList(numCourses);
        initGraph(inLinkCount, graph, pre);
        return solveWithBFS(inLinkCount, graph);
    }
    
    private void initGraph(int[] inLinkCount, List<List<Integer>> graph, int[][] pre) {
        int n = inLinkCount.length;
        while (n-- > 0) graph.add(new ArrayList());
        for (int[] edge : pre) {
            inLinkCount[edge[0]]++;
            graph.get(edge[1]).add(edge[0]); // preCourse -> nextCourse
        }
    }
    
    private boolean solveWithBFS(int[] inLinkCount, List<List<Integer>> graph) {
        int n = inLinkCount.length;
        int count = 0;
        Queue<Integer> toVisit = new ArrayDeque();
        for (int i = 0; i < n; i++) {
            if (inLinkCount[i] == 0) toVisit.offer(i);
        }
        while (!toVisit.isEmpty()) {
            int cur = toVisit.poll();
            count++;
            for (int to : graph.get(cur)) {
                inLinkCount[to]--;
                if (inLinkCount[to] == 0) toVisit.offer(to);
            }
        }
        return count == n;
    }
}
