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
    // row和col这里都是虚的概念，rows[]是个已经装好了所有word的数组，里面是每个word的letter。
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
