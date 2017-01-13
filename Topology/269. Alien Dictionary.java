public class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        Map<Character, Set<Character>> graph = new HashMap();
        Map<Character, Integer> inDegree = new HashMap();
        // init first char
        for (String word : words) {
            for (char c : word.toCharArray()) {
                inDegree.put(c, 0);
            }
        }
        
        int n = words.length;
        // build graph
        // graph: from cur to next
        for (int i = 0; i < n - 1; i++) {
            char[] cur = words[i].toCharArray();
            char[] next = words[i + 1].toCharArray();
            int len = Math.min(cur.length, next.length);
            for (int j = 0; j < len; j++) {
                char c1 = cur[j], c2 = next[j];
                if (c1 != c2) { // if chars are not same
                    Set<Character> set = new HashSet();
                    if (graph.containsKey(c1)) set = graph.get(c1);
                    if (!set.contains(c2)) {
                        set.add(c2);
                        graph.put(c1, set);
                        inDegree.put(c2, inDegree.get(c2) + 1);
                    }
                    
                    break;
                } else {
                    // if chars are the same, but cur string len = next string len + 1,
                    // which means "abc", "ab" are not valid, the order should be "ab", "abc"
                    if (j < cur.length - 1 && j >= next.length - 1) return "";
                }
            }
        }
        // traverse graph with BFS, build a path
        Queue<Character> q = new ArrayDeque();
        for (Character from : inDegree.keySet()) {
            if (inDegree.get(from) == 0) q.offer(from);
        }
        
        while (!q.isEmpty()) {
            char from = q.poll();
            sb.append(from);
            if (graph.containsKey(from)) {
                for (char to : graph.get(from)) {
                    inDegree.put(to, inDegree.get(to) - 1);
                    if (inDegree.get(to) == 0) {
                        q.offer(to);
                    }
                }
            }
        }
        
        if (sb.length() != inDegree.size()) return "";
        
        return sb.toString();
    }
}

//  Or use list<Character> to record the next nodes instead of set<>, in this way, we can just increase indegree[] for the next char, no matter it is duplicate or not.
public class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        Map<Character, List<Character>> graph = new HashMap();   // list here
        Map<Character, Integer> inDegree = new HashMap();
        // init first char
        for (String word : words) {
            for (char c : word.toCharArray()) {
                inDegree.put(c, 0);
            }
        }
        
        int n = words.length;
        // build graph
        // graph: from cur to next
        for (int i = 0; i < n - 1; i++) {
            char[] cur = words[i].toCharArray();
            char[] next = words[i + 1].toCharArray();
            int len = Math.min(cur.length, next.length);
            for (int j = 0; j < len; j++) {
                char c1 = cur[j], c2 = next[j];
                if (c1 != c2) { // if chars are not same
                    if (graph.containsKey(c1)) {
                        graph.get(c1).add(c2);
                    } else {
                        List<Character> list = new ArrayList();
                        list.add(c2);
                        graph.put(c1, list);
                    }
                    inDegree.put(c2, inDegree.get(c2) + 1);  // we can just increase indegree whenever there is a c2
                    
                    break;
                } else {
                    // if chars are the same, but cur string len = next string len + 1,
                    // which means "abc", "ab" are not valid, the order should be "ab", "abc"
                    if (j < cur.length - 1 && j >= next.length - 1) return "";
                }
            }
        }
        // traverse graph with BFS, build a path
        Queue<Character> q = new ArrayDeque();
        for (Character from : inDegree.keySet()) {
            if (inDegree.get(from) == 0) q.offer(from);
        }
        
        while (!q.isEmpty()) {
            char from = q.poll();
            sb.append(from);
            if (graph.containsKey(from)) {
                for (char to : graph.get(from)) {
                    inDegree.put(to, inDegree.get(to) - 1);
                    if (inDegree.get(to) == 0) {
                        q.offer(to);
                    }
                }
            }
        }
        
        if (sb.length() != inDegree.size()) return "";
        
        return sb.toString();
    }
}

