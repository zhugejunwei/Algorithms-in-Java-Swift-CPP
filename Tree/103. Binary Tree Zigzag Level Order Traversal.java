public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        traverse(root, res, 0);
        return res;
    }
    
    private void traverse(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) return;
        
        if (res.size() <= level) {
            List<Integer> list = new ArrayList();
            res.add(list);
        }
        
        List<Integer> cur = res.get(level);
        if (level % 2 == 0) cur.add(root.val);
        else cur.add(0, root.val);
        
        traverse(root.left, res, level + 1);
        traverse(root.right, res, level + 1);
    }
}
