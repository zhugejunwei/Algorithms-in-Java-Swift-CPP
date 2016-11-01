public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        TreeNode node = root;
        while (node != null) {
            if (node.left != null) {
                TreeNode pre = node.left;
                while (pre.right != null && pre.right != node) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = node;
                    node = node.left;
                } else {
                    pre.right = null;
                    list.add(node.val);
                    node = node.right;
                }
            } else {
                list.add(node.val);
                node = node.right;
            }
        }
        return list;
    }
}
