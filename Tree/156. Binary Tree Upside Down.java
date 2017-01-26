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

// using stack
public class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) return root;
        TreeNode pre = null, cur = root, newRoot = null;
        Deque<TreeNode> stack = new ArrayDeque();
        while (cur.left != null) {
            stack.push(cur);
            cur = cur.left;
        }
        newRoot = cur;
        while (!stack.isEmpty()) {
            pre = stack.pop();
            cur.left = pre.right;
            cur.right = pre;
            pre.left = pre.right = null;
            cur = pre;
        }
        return newRoot;
    }
}
