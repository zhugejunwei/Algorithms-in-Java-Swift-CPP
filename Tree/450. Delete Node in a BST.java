// 8ms
public class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null || root.val == key) return deleteRoot(root);
        TreeNode node = root;
        while (true) {
            if (key < node.val) {
                if (node.left == null || node.left.val == key) {
                    node.left = deleteRoot(node.left);
                    break;
                }
                node = node.left;
            } else {
                if (node.right == null || node.right.val == key) {
                    node.right = deleteRoot(node.right);
                    break;
                }
                node = node.right;
            }
        }
        return root;
    }
    
    private TreeNode deleteRoot(TreeNode root) {
        if (root == null) return root;
        if (root.right == null) return root.left;
        TreeNode node = root.right;
        while (node.left != null) node = node.left;
        node.left = root.left;
        return root.right;
    }
}

// recursion - 10ms
public class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;
        if (root.val < key) root.right = deleteNode(root.right, key);
        else if (root.val > key) root.left = deleteNode(root.left, key);
        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            TreeNode minNode = findMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, root.val);
        }
        return root;
    }
    private TreeNode findMin(TreeNode root) {
        TreeNode node = root;
        while (node.left != null) node = node.left;
        return node;
    }
}
