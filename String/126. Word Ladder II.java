public class Solution {
    public List<List<String>> findLadders(String begin, String end, List<String> wordList) {
        List<List<String>> res = new ArrayList();
        Map<String, List<String>> graph = new HashMap();
        Set<String> unvis = new HashSet(wordList);
        unvis.remove(begin);
        if (!unvis.contains(end)) return new ArrayList();
        
        Queue<String> q = new ArrayDeque();
        q.offer(begin);
        boolean found = false;
        while (!q.isEmpty()) {
            int size = q.size();
            Set<String> nextLevel = new HashSet();
            while (size-- > 0) {
                String cur = q.poll();
                for (int i = 0; i < cur.length(); i++) {
                    StringBuilder sb = new StringBuilder(cur);
                    for (char c = 'a'; c <= 'z'; c++) {
                        sb.setCharAt(i, c);
                        String newWord = sb.toString();
                        if (!unvis.contains(newWord)) continue;
                        
                        // skip duplicates
                        if (nextLevel.add(newWord)) q.offer(newWord);
                        
                        if (!graph.containsKey(newWord))
                            graph.put(newWord, new ArrayList());
                        graph.get(newWord).add(cur);
                        
                        if (newWord.equals(end))
                            found = true;
                    }
                }
            }
            if (found) break;
            unvis.removeAll(nextLevel);
        }
        
        helper(graph, res, end, begin, new LinkedList());
        return res;
    }
    
    private void helper(Map<String, List<String>> graph, List<List<String>> res, String end, String begin, LinkedList<String> list) {
        if (begin.equals(end)) {
            list.addFirst(end);
            res.add(new LinkedList(list));
            list.removeFirst();
        }
        
        list.addFirst(end);
        if (graph.containsKey(end)) {
            for (String pre : graph.get(end)) {
                helper(graph, res, pre, begin, list);
            }
        }
        list.removeFirst();
    }
}


// very fast, two end solution
public class Solution {
    public List<List<String>> findLadders(String start, String end, List<String> wordList) {
        Set<String> dict = new HashSet(wordList);
        if (!dict.contains(end)) return new ArrayList();
        // hash set for both ends
        Set<String> set1 = new HashSet<String>();
        Set<String> set2 = new HashSet<String>();
        
        // initial words in both ends
        set1.add(start);
        set2.add(end);
        
        // we use a map to help construct the final result
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        
        // build the map
        helper(dict, set1, set2, map, false);
        
        List<List<String>> res = new ArrayList<List<String>>();
        List<String> sol = new ArrayList<String>(Arrays.asList(start));
        
        // recursively build the final result
        generateList(start, end, map, sol, res);
        
        return res;
    }
    
    boolean helper(Set<String> dict, Set<String> set1, Set<String> set2, Map<String, List<String>> map, boolean flip) {
        if (set1.isEmpty()) {
            return false;
        }
        
        if (set1.size() > set2.size()) {
            return helper(dict, set2, set1, map, !flip);
        }
        
        // remove words on current both ends from the dict
        dict.removeAll(set1);
        dict.removeAll(set2);
        
        // as we only need the shortest paths
        // we use a boolean value help early termination
        boolean done = false;
        
        // set for the next level
        Set<String> set = new HashSet<String>();
        
        // for each string in end 1
        for (String str : set1) {
            for (int i = 0; i < str.length(); i++) {
                char[] chars = str.toCharArray();
                
                // change one character for every position
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    chars[i] = ch;
                    
                    String word = new String(chars);
                    
                    // make sure we construct the tree in the correct direction
                    String key = flip ? word : str;
                    String val = flip ? str : word;
                    
                    List<String> list = map.containsKey(key) ? map.get(key) : new ArrayList<String>();
                    
                    if (set2.contains(word)) {
                        done = true;
                        
                        list.add(val);
                        map.put(key, list);
                    }
                    
                    if (!done && dict.contains(word)) {
                        set.add(word);
                        
                        list.add(val);
                        map.put(key, list);
                    }
                }
            }
        }
        
        // early terminate if done is true
        return done || helper(dict, set2, set, map, !flip);
    }
    
    void generateList(String start, String end, Map<String, List<String>> map, List<String> sol, List<List<String>> res) {
        if (start.equals(end)) {
            res.add(new ArrayList<String>(sol));
            return;
        }
        
        // need this check in case the diff between start and end happens to be one
        // e.g "a", "c", {"a", "b", "c"}
        if (!map.containsKey(start)) {
            return;
        }
        
        for (String word : map.get(start)) {
            sol.add(word);
            generateList(word, end, map, sol, res);
            sol.remove(sol.size() - 1);
        }
    }
}
