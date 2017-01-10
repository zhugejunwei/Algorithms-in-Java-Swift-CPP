class TrieNode {
    TrieNode[] nodes;
    String word;
    public TrieNode() {
        this.nodes = new TrieNode[26];
        this.word = null;
    }
}

public class Trie {
    private TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.nodes[idx] == null) node.nodes[idx] = new TrieNode();
            node = node.nodes[idx];
        }
        node.word = word;
    }
    
    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode node = root;
        int i = 0;
        while (i < word.length()) {
            char c = word.charAt(i);
            if (node.nodes[c - 'a'] != null) {
                node = node.nodes[c - 'a'];
            } else return false;
            ++i;
            if (i == word.length()) {
                return node.word != null && node.word.equals(word);
            }
        }
        return false;
    }
    
    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        int i = 0;
        while (i < prefix.length()) {
            char c = prefix.charAt(i);
            if (node.nodes[c - 'a'] != null) {
                node = node.nodes[c - 'a'];
            } else return false;
            ++i;
        }
        return i == prefix.length();
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");
