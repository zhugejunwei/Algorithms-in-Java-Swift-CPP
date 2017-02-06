// better style
public class Solution {
    Map<String, List<String>> graph = new HashMap();
    public List<List<String>> findLadders(String begin, String end, List<String> wordList) {
        List<List<String>> res = new ArrayList();
        if (wordList == null || !wordList.contains(end)) return res;
        Set<String> dict = new HashSet(wordList);
        dict.remove(begin);
        Queue<String> q = new ArrayDeque();
        q.add(begin);
        boolean found = false;
        while (!q.isEmpty()) {
            int level = q.size();
            Set<String> vis = new HashSet();
            while (level-- > 0) {
                String cur = q.poll();
                for (int i = 0; i < cur.length(); i++) {
                    StringBuilder sb = new StringBuilder(cur);
                    for (char c = 'a'; c <= 'z'; c++) {
                        sb.setCharAt(i, c);
                        String newStr = sb.toString();
                        if (!dict.contains(newStr)) continue;
                        
                        // skip duplicates
                        if (vis.add(newStr)) q.add(newStr);
                        
                        if (graph.containsKey(newStr)) {
                            graph.get(newStr).add(cur);
                        } else {
                            List<String> list = new ArrayList();
                            list.add(cur);
                            graph.put(newStr, list);
                        }
                        if (newStr.equals(end)) found = true;
                    }
                }
            }
            if (found) break;
            dict.removeAll(vis);
        }
        
        List<String> list = new ArrayList();
        list.add(end);
        routeHelper(begin, end, res, list);
        return res;
    }
    
    private void routeHelper(String begin, String end, List<List<String>> res, List<String> list) {
        if (begin.equals(end)) {
            res.add(new ArrayList(list));
            return;
        }
        if (graph.containsKey(end))
            for (String pre : graph.get(end)) {
                list.add(0, pre);
                routeHelper(begin, pre, res, list);
                list.remove(0);
            }
    }
}


// origin style
public class Solution {
    List<List<String>> res;
    Map<String, List<String>> map; // graph
    public List<List<String>> findLadders(String begin, String end, Set<String> dict) {
        res = new ArrayList<List<String>>();
        map = new HashMap();
        Set<String> unvisited = new HashSet(dict);
        Set<String> visited = new HashSet();
        unvisited.add(end);
        unvisited.remove(begin); // prevent duplicate
        Queue<String> q = new ArrayDeque(); // bfs to build graph
        q.add(begin);
        int cur = 1, next = 0;
        boolean found = false;
        while (!q.isEmpty()) {
            String word = q.poll();
            cur--;
            for (int i = 0; i < word.length(); i++) {
                StringBuilder sb = new StringBuilder(word);
                for (char c = 'a'; c <= 'z'; c++) {
                    sb.setCharAt(i, c);
                    String newWord = sb.toString();
                    
                    if (!unvisited.contains(newWord)) continue;
                    
                    if (visited.add(newWord)) {
                        next++; // nextlevel length
                        q.offer(newWord);
                    }
                    
                    // build adjacent graph
                    if (map.containsKey(newWord)) {
                        map.get(newWord).add(word);
                    } else {
                        LinkedList<String> l = new LinkedList();
                        l.add(word);
                        map.put(newWord, l);
                    }
                    if (newWord.equals(end)) found = true;
                }
            }
            if (cur == 0) { // if the cur level is over
                if (found) break;
                cur = next;
                next = 0;
                unvisited.removeAll(visited);
                visited.clear();
            }
        }
        backTrack(end, begin, new LinkedList());
        return res;
    }
    
    private void backTrack(String end, String begin, LinkedList<String> list) {
        if (begin.equals(end)) {
            list.addFirst(begin);
            res.add(new LinkedList(list));
            list.removeFirst();
            return;
        }
        list.addFirst(end);
        if (map.containsKey(end)) {
            for (String word : map.get(end)) {
                backTrack(word, begin, list);
            }
        }
        list.removeFirst();
    }
}

