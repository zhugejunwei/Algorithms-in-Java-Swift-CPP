public class Solution {
    class TrieNode {
        TrieNode[] nodes;
        String word;
        TrieNode() {
            this.nodes = new TrieNode[26]; // children nodes
            this.word = null; // start character
        }
    }
    private void add(TrieNode root, String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.nodes[idx] == null) node.nodes[idx] = new TrieNode();
            node = node.nodes[idx];
        }
        node.word = word;
    }
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> res = new ArrayList<>();
        TrieNode root = new TrieNode();
        for (String word:words) add(root, word); // build the Trie tree
        int wordLen = words[0].length();
        TrieNode[] rows = new TrieNode[wordLen]; // the words matrix wordLen*wordLen
        for (int i = 0; i < wordLen; i++) rows[i] = root;
        helper(0, 0, wordLen, rows, res);
        return res;
    }
    // 每行为一个独立的root
    private void helper(int row, int col, int len, TrieNode[] rows, List<List<String>> res) {
        if (row == len && col == row) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                list.add(new String(rows[i].word));
            }
            res.add(list);
            return;
        }
        if (col < len) {
            TrieNode pre_row = rows[row];
            TrieNode pre_col = rows[col];
            for (int i = 0; i < 26; i++) {
                if (rows[row].nodes[i] != null && rows[col].nodes[i] != null) {
                    rows[row] = rows[row].nodes[i];
                    if (col != row) rows[col] = rows[col].nodes[i];
                    helper(row, col + 1, len, rows, res);
                    rows[row] = pre_row;
                    if (col != row) rows[col] = pre_col;
                }
            }
        } else {
            helper(row + 1, row + 1, len, rows, res);
        }
    }
}

// a little bit faster
public class Solution {
    class TrieNode {
        TrieNode[] next = new TrieNode[128];
        String word = null;
    }
    
    TrieNode root = new TrieNode();
    private void buildTrie(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.next[c] == null) node.next[c] = new TrieNode();
            node = node.next[c];
        }
        node.word = word;
    }
    
    public List<List<String>> wordSquares(String[] words) {
        if (words == null || words.length == 0)
            return new ArrayList();
        for (String word : words) buildTrie(word);
        int len = words[0].length();
        
        TrieNode[] rows = new TrieNode[len];
        Arrays.fill(rows, root);
        
        List<List<String>> res = new ArrayList();
        helper(rows, res, len, 0, 0);
        return res;
    }
    
    private void helper(TrieNode[] rows, List<List<String>> res, int len, int row, int col) {
        if (row == col && col == len) {
            List<String> list = new ArrayList();
            for (int i = 0; i < len; i++) {
                list.add(rows[i].word);
            }
            res.add(list);
        } else if (col < len) {
            TrieNode preRow = rows[row];
            TrieNode preCol = rows[col];
            for (char c = 'a'; c <= 'z'; c++) {
                if (preRow.next[c] == null || preCol.next[c] == null) continue;
                rows[row] = preRow.next[c];
                rows[col] = preCol.next[c];
                helper(rows, res, len, row, col + 1);
            }
            rows[row] = preRow;
            rows[col] = preCol;
        } else {
            helper(rows, res, len, row + 1, row + 1);
        }
    }
}
