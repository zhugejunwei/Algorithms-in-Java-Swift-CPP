public class Solution {
    class TrieNode {
        TrieNode[] nodes;
        String word;
        public TrieNode() {
            nodes = new TrieNode[128];
            word = null;
        }
    }
    
    TrieNode root = new TrieNode();
    
    private void buildTrie(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.nodes[c] == null) node.nodes[c] = new TrieNode();
            node = node.nodes[c];
        }
        node.word = word;
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        if (board.length == 0 || board[0].length == 0 || words.length == 0) return new ArrayList();
        int m = board.length, n = board[0].length;
        for (String word : words) {
            buildTrie(word);
        }
        List<String> res = new ArrayList();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char cur = board[i][j];
                if (root.nodes[cur] != null) {
                    TrieNode node = root;
                    boolean[][] vis = new boolean[m][n];
                    vis[i][j] = true;
                    findWord(res, board, i, j, node.nodes[cur], vis);
                }
            }
        }
        return res;
    }
    
    int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private void findWord(List<String> res, char[][] board, int i, int j, TrieNode node, boolean[][] vis) {
        if (node.word != null) {
            res.add(node.word);
            node.word = null;
            // return;
        }
        int m = board.length, n = board[0].length;
        for (int[] d : dir) {
            int x = i + d[0];
            int y = j + d[1];
            if (x < 0 || x >= m || y < 0 || y >= n || vis[x][y] || node.nodes[board[x][y]] == null) continue;
            vis[x][y] = true;
            findWord(res, board, x, y, node.nodes[board[x][y]], vis);
            vis[x][y] = false;
        }
    }
}
