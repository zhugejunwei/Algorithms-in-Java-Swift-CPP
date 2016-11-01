public class WordDistance {
    
    Map<String, List<Integer>> map;
    
    public WordDistance(String[] words) {
        map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String cur = words[i];
            if (map.containsKey(cur)) {
                map.get(cur).add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(cur, list);
            }
        }
    }
    
    public int shortest(String word1, String word2) {
        int res = Integer.MAX_VALUE;
        List<Integer> l1 = map.get(word1);
        List<Integer> l2 = map.get(word2);
        for (int i = 0, j = 0; i < l1.size() && j < l2.size();) {
            int x = l1.get(i), y = l2.get(j);
            if (x < y) {
                res = Math.min(res, y - x);
                i++;
            } else {
                res = Math.min(res, x - y);
                j++;
            }
        }
        return res;
    }
}

// Your WordDistance object will be instantiated and called as such:
// WordDistance wordDistance = new WordDistance(words);
// wordDistance.shortest("word1", "word2");
// wordDistance.shortest("anotherWord1", "anotherWord2");
