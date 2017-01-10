public class WordDictionary {
    class TrieNode {
        String word;
        TrieNode[] next;
        public TrieNode() {
            word = null;
            next = new TrieNode[128];
        }
    }
    
    TrieNode root = new TrieNode();
    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.next[c] == null) node.next[c] = new TrieNode();
            node = node.next[c];
        }
        node.word = word;
    }
    
    public boolean search(String word) {
        TrieNode node = root;
        return search(word.toCharArray(), node, 0);
    }
    
    public boolean search(char[] word, TrieNode node, int i) {
        if (i == word.length) {
            return node.word != null && node.word.length() == word.length;
        }
        
        char c = word[i];
        if (c == '.') {
            for (char j = 'a'; j <= 'z'; j++) {
                if (node.next[j] == null) continue;
                if (search(word, node.next[j], i + 1)) return true;
            }
        } else {
            return node.next[c] != null && search(word, node.next[c], i + 1);
        }
        return false;
    }
}
