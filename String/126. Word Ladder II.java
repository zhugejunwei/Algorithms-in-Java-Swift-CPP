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
