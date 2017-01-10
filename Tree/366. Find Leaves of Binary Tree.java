public class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        findLeaves(res, root);
        return res;
    }
    
    private int findLeaves(List<List<Integer>> res, TreeNode node) {
        if (node == null) return -1;
        int height = 1 + Math.max(findLeaves(res, node.left), findLeaves(res, node.right));
        if (res.size() <= height) res.add(new ArrayList());
        res.get(height).add(node.val);
        return height;
    }
}
