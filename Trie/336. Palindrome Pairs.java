// using trie tree
public class Solution {
    class TrieNode{
        TrieNode[] next;
        int idx;
        Set<Integer> set;
        
        public TrieNode() {
            next = new TrieNode[128];
            idx = -1;
            set = new HashSet();
        }
    }
    
    TrieNode root = new TrieNode();
    private void buildTrie(String word, int i) {
        TrieNode node = root;
        for (int j = word.length() - 1; j >= 0; j--) {
            char c = word.charAt(j);
            if (node.next[c] == null) node.next[c] = new TrieNode();
            if (isPl(word, 0, j)) node.set.add(i);
            node = node.next[c];
        }
        node.idx = i;
        node.set.add(i);
    }
    
    String[] words;
    public List<List<Integer>> palindromePairs(String[] words) {
        this.words = words;
        List<List<Integer>> res = new ArrayList();
        if (words == null || words.length == 0) return new ArrayList();
        
        // build trie
        for (int i = 0; i < words.length; i++)
            buildTrie(words[i], i);
        
        // search in trie
        for (int i = 0; i < words.length; i++)
            searchTrie(i, res);
        
        return res;
    }
    
    private void searchTrie(int i, List<List<Integer>> res) {
        TrieNode node = root;
        String word = words[i];
        for (int j = 0; j < word.length(); j++) {
            if (node.idx != -1 && node.idx != i && isPl(word, j, word.length() - 1)) {
                res.add(Arrays.asList(i, node.idx));
            }
            
            node = node.next[word.charAt(j)];
            
            if (node == null) return;
        }
        for (int k : node.set) {
            if (i != k) res.add(Arrays.asList(i, k));
        }
    }
    
    private boolean isPl(String t, int l, int r) {
        while (l < r) {
            if (t.charAt(l++) != t.charAt(r--))
                return false;
        }
        return true;
    }
}

// iteration, using hashmap, same idea
public class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList();
        
        Map<String, Integer> idxMap = new HashMap();
        for (int i = 0; i < words.length; i++)
            idxMap.put(words[i], i);
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j <= word.length(); j++) {
                // if left substring is pl, check if right substring's reversed is in the map
                // right_reversed + word == Palindrome
                if (isPl(word, 0, j - 1)) {
                    String right_reversed = new StringBuilder(word.substring(j)).reverse().toString();
                    if (idxMap.containsKey(right_reversed) && idxMap.get(right_reversed) != i) {
                        res.add(Arrays.asList(idxMap.get(right_reversed), i));
                    }
                }
                // right substring is pl
                // word + left reversed substring = Palindrome
                if (isPl(word, j, word.length() - 1)) {
                    String left_reversed = new StringBuilder(word.substring(0, j)).reverse().toString();
                    // use j < word.length() to skip duplicates
                    // abcd and dcba: 01, 10,
                    if (idxMap.containsKey(left_reversed) && idxMap.get(left_reversed) != i && j < word.length()) {
                        res.add(Arrays.asList(i, idxMap.get(left_reversed)));
                    }
                }
            }
        }
        return res;
    }
    
    private boolean isPl(String word, int l, int r) {
        while (l < r) {
            if (word.charAt(l++) != word.charAt(r--))
                return false;
        }
        return true;
    }
}
