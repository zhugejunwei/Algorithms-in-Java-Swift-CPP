/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Deque<TreeNode> stack = new ArrayDeque();
        TreeNode n = root;
        while (n != null || !stack.isEmpty()) {
            if (n != null) {
                stack.push(n);
                sb.append(n.val).append(",");
                n = n.left;
            } else {
                TreeNode tmp = stack.pop();
                n = tmp.right;
            }
        }
        return sb.toString();
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] arr = data.split(",");
        int[] nodes = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = Integer.valueOf(arr[i]);
        }
        return buildTree(nodes, 0, nodes.length - 1);
    }
    
    private TreeNode buildTree(int[] nodes, int l, int r) {
        if (nodes.length == 0 || l > r) return null;
        TreeNode root = new TreeNode(nodes[l]);
        int k = l + 1;
        while (k < nodes.length && nodes[l] > nodes[k]) k++;
        root.left = buildTree(nodes, l + 1, k - 1);
        root.right = buildTree(nodes, k, r);
        return root;
    }
}
// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
