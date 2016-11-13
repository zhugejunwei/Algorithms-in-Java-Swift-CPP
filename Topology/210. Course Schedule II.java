// ====================== BFS =======================//

public class Solution {
    // https://discuss.leetcode.com/topic/13873/two-ac-solution-in-java-using-bfs-and-dfs-with-explanation
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] incLinkCounts = new int[numCourses];
        List<List<Integer>> adjs = new ArrayList<>(numCourses);
        iniGraph(incLinkCounts, adjs, prerequisites);
        
        return solveByBFS(incLinkCounts, adjs);
    }
    
    private void iniGraph(int[] incLinkCounts, List<List<Integer>> adjs, int[][] prerequisites) {
        int n = incLinkCounts.length;
        while(n-- > 0) adjs.add(new ArrayList<>());
        for (int[] edge:prerequisites) {
            incLinkCounts[edge[0]]++;
            adjs.get(edge[1]).add(edge[0]);
        }
    }
    
    private int[] solveByBFS(int[] incLinkCounts, List<List<Integer>> adjs) {
        int len = incLinkCounts.length;
        int[] order = new int[len];
        Queue<Integer> toVisit = new ArrayDeque();
        for (int i = 0; i < len; i++) {
            if (incLinkCounts[i] == 0) toVisit.offer(i);
        }
        int visited = 0;
        while (!toVisit.isEmpty()) {
            int from = toVisit.poll();
            order[visited++] = from;
            for (int to : adjs.get(from)) {
                incLinkCounts[to]--;
                if(incLinkCounts[to] == 0) toVisit.offer(to);
            }
        }
        return visited == len ? order : new int[0];
    }
}

// ====================== DFS =======================//

public class Solution {
    // https://discuss.leetcode.com/topic/13873/two-ac-solution-in-java-using-bfs-and-dfs-with-explanation
    private int N = 0;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        Course[] courses = new Course[numCourses];
        for (int i = 0; i < numCourses; i++) {
            courses[i] = new Course(i);
        }
        for (int i = 0; i < prerequisites.length; i++) {   // init graph
            courses[prerequisites[i][0]].add(courses[prerequisites[i][1]]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (isCycle(courses[i], result))
                return new int[0];
        }
        return result;
    }
    
    private boolean isCycle(Course cur, int[] result) {
        if (cur.tested == true) return false;
        if (cur.visited == true) return true;
        cur.visited = true;
        for (Course c : cur.pre) {
            if (isCycle(c, result)) {
                return true;
            }
        }
        cur.tested = true;
        result[N++] = cur.number;
        return false;
    }
    
    class Course {
        boolean visited = false;
        boolean tested = false;
        int number;
        List<Course> pre = new ArrayList<Course>();
        public Course(int i) {
            number = i;
        }
        public void add(Course c) {
            pre.add(c);
        }
    }
}
