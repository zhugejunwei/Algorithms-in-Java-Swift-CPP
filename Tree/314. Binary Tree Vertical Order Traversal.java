public class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        Queue<Integer> cols = new ArrayDeque<>();
        
        q.add(root);
        cols.add(0);
        
        // use min and max to get the map.values
        int min = 0, max = 0;
        
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            int col = cols.poll();
            
            // use map to record the node.val at the same col
            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<Integer>());
            }
            
            map.get(col).add(node.val);
            
            if (node.left != null) {
                q.add(node.left);
                cols.add(col - 1);
                min = Math.min(col - 1, min);
            }
            
            if (node.right != null) {
                q.add(node.right);
                cols.add(col + 1);
                max = Math.max(col + 1, max);
            }
        }
        
        for (int i = min; i <= max; i++) {
            res.add(map.get(i));
        }
        return res;
    }
}


////  faster

public class Solution {
    private int min = 0, max = 0;
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        
        // compute the range using dfs
        computeRange(root, 0);
        
        // initialize the res list;
        for (int i = min; i <= max; i++) res.add(new ArrayList<Integer>());
        
        Queue<TreeNode> q = new ArrayDeque<>();
        Queue<Integer> cols = new ArrayDeque<>();
        
        q.add(root);
        
        // -min is root
        cols.add(-min);
        
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            int col = cols.poll();
            
            res.get(col).add(node.val);
            
            if (node.left != null) {
                q.add(node.left);
                cols.add(col - 1);
            }
            
            if (node.right != null) {
                q.add(node.right);
                cols.add(col + 1);
            }
        }
        return res;
    }
    
    private void computeRange(TreeNode root, int i) {
        if (root == null) return;
        min = Math.min(min, i);
        max = Math.max(max, i);
        computeRange(root.left, i - 1);
        computeRange(root.right, i + 1);
    }
}
