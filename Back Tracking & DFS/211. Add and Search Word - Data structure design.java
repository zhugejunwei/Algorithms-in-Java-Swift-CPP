public class WordDictionary {
    class TrieNode {
        TrieNode[] nodes;
        String word;
        TrieNode() {
            this.nodes = new TrieNode[26];
            this.word = null;
        }
    }
    
    TrieNode root = new TrieNode();
    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.nodes[idx] == null) node.nodes[idx] = new TrieNode();
            node = node.nodes[idx];
        }
        node.word = word;
    }
    
    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        TrieNode node = root;
        return searchWord(word.toCharArray(), node, 0);
    }
    
    private boolean searchWord(char[] word, TrieNode node, int i) {
        if (i == word.length) {
            return node.word != null && node.word.length() == word.length;
        }
        if (word[i] != '.') {
            return node.nodes[word[i] - 'a'] != null && searchWord(word, node.nodes[word[i] - 'a'], i + 1);
        } else {
            for (int j = 0; j < 26; j++) {
                if (node.nodes[j] != null) {
                    if (searchWord(word, node.nodes[j], i + 1)) return true;
                }
            }
        }
        return false;
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
