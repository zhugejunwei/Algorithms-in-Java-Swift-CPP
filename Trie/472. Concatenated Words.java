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

// no map, 92.92%
public class Solution {
    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word = null;
    }
    
    TrieNode root = new TrieNode();
    private void buildTrie(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.next[c - 'a'] == null) node.next[c - 'a'] = new TrieNode();
            node = node.next[c - 'a'];
        }
        node.word = word;
    }
    
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList();
        if (words == null || words.length <= 2) return res;
        // build trie tree
        for (String word : words) {
            buildTrie(word);
        }
        
        // iterate trie tree
        for (String word : words) {
            if (isValid(word.toCharArray(), 0, 0))
                res.add(word);
        }
        return res;
    }
    
    // note: String.substring() is in O(n) time
    private boolean isValid(char[] word, int start, int count) {
        if (start == word.length && count >= 2) return true;
        TrieNode node = root;
        for (int i = start; i < word.length; i++) {
            if (node.next[word[i] - 'a'] == null) break;
            node = node.next[word[i] - 'a'];
            if (node.word != null) {
                if (isValid(word, i + 1, count + 1)) {
                    return true;
                }
            }
        }
        return false;
    }
}


// dp, slower

public class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList();
        Set<String> set = new HashSet(); // the shorter words before current word
        
        // sort by the length
        Arrays.sort(words, new Comparator<String>(){
            public int compare(String a, String b) {
                return a.length() - b.length();
            }
        });
        
        for (String word : words) {
            if (isValid(word, set)) res.add(word);
            set.add(word);
        }
        
        return res;
    }
    
    private boolean isValid(String word, Set<String> set) {
        if (set.isEmpty()) return false;
        boolean[] dp = new boolean[word.length() + 1];
        dp[0] = true; // empty string
        for (int j = 1; j <= word.length(); j++) {
            for (int i = 0; i < j; i++) {
                if (!dp[i]) continue;
                if (set.contains(word.substring(i, j))) {
                    dp[j] = true;
                    break;
                }
            }
        }
        return dp[word.length()];
    }
}
