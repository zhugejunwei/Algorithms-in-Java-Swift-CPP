// recursion
public class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) return root;
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        
        return newRoot;
    }
}

// iteration
public class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) return root;
        TreeNode cur = root, next = null, tmp = null, pre = null;
        while (cur != null) {
            next = cur.left;
            cur.left = tmp;
            tmp = cur.right;
            
            cur.right = pre;
            pre = cur;
            
            cur = next;
        }
        return pre;
    }
}
