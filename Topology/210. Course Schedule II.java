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

// BFS 2
public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return new int[0];
        
        // build graph
        LinkedList<LinkedList<Integer>> graph = new LinkedList();
        for (int i = 0; i < numCourses; i++) graph.add(new LinkedList());
        int[] inDegree = new int[numCourses];
        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]);
            inDegree[pre[0]]++;
        }
        
        int[] res = new int[numCourses];
        
        // solved by BFS
        Queue<Integer> q = new ArrayDeque();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) q.offer(i);
        }
        
        int idx = 0;
        while (!q.isEmpty()) {
            if (q.isEmpty()) return new int[0];
            int cur = q.poll();
            if (cur < 0 || cur >= numCourses) return new int[0];
            res[idx++] = cur;
            for (int next : graph.get(cur)) {
                inDegree[next]--;
                if (inDegree[next] == 0)
                    q.offer(next);
            }
        }
        
        return idx == numCourses ? res : new int[0];
    }
}

// ====================== DFS =======================//
// https://discuss.leetcode.com/topic/13873/two-ac-solution-in-java-using-bfs-and-dfs-with-explanation
public class Solution {
    private int N = 0;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        // create Courses
        Course[] courses = new Course[numCourses];
        for (int i = 0; i < numCourses; i++) {
            courses[i] = new Course(i);
        }
        // init graph
        for (int[] pre : prerequisites) {
            courses[pre[0]].add(courses[pre[1]]);
        }
        // traverse graph
        for (int i = 0; i < numCourses; i++) {
            if (isCycle(courses[i], res))
                return new int[0];
        }
        return res;
    }
    
    private boolean isCycle(Course c, int[] res) {
        if (c.tested) return false;
        if (c.visited) return true;
        c.visited = true;
        for (Course pre : c.pre) {
            if (isCycle(pre, res))
                return true;
        }
        c.tested = true;
        res[N++] = c.id;
        return false;
    }
    
    class Course {
        int id;
        boolean tested, visited;
        List<Course> pre = new ArrayList();
        public Course(int id) {
            this.id = id;
        }
        public void add(Course c) {
            pre.add(c);
        }
    }
}
