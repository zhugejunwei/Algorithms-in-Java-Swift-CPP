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
