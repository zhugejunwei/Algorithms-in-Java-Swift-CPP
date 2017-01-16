// iteration 8ms
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
        if (root == null) return null;
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.right == null || root.left == null) {
                return root.left == null ? root.right : root.left;
            } else {
                // find the minimum node at the right side
                TreeNode minNode = findMin(root.right);
                // replace root with min node
                root.val = minNode.val;
                root.right = deleteNode(root.right, minNode.val);
            }
        }
        return root;
    }
    
    private TreeNode findMin(TreeNode right) {
        TreeNode minNode = right;
        while (minNode.left != null) {
            minNode = minNode.left;
        }
        return minNode;
    }
}
