public class Solution {
    class Trie {
        Trie[] next;
        String word;
        public Trie() {
            next = new Trie[26];
        }
    }
    
    Trie root = new Trie(); // Trie root
    
    private void buildTrie(String word) {
        Trie node = root;
        for (char c : word.toCharArray()) {
            if (node.next[c - 'a'] == null) node.next[c - 'a'] = new Trie();
            node = node.next[c - 'a'];
        }
        node.word = word;
    }
    
    private boolean isValid(String word, int count) {
        if (word.isEmpty() && count > 1) {
            return true;
        }
        Trie node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (node.next[c - 'a'] == null) {
                return false;
            }
            node = node.next[c - 'a'];
            
            if (node.word != null) {
                String newWord = word.substring(i + 1);
                if (map.containsKey(newWord)) return map.get(newWord);
                if (isValid(newWord, count + 1)) {
                    map.put(word, true);
                    return true;
                }
            }
        }
        return false;
    }
    
    Map<String, Boolean> map = new HashMap();
    
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, new Comparator<String>(){
            public int compare(String a, String b) {
                return a.length() - b.length();
            }
        });
        
        Set<String> dict = new HashSet(); // smaller words
        List<String> res = new ArrayList();
        
        for (String word : words) {
            dict.add(word);
            buildTrie(word);
            if (isValid(word, 0)) res.add(word);
        }
        return res;
    }
}
