/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    Map<Integer, Integer> map; // value, index in inorder
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        map = new HashMap();
        for (int i = 0; i < inorder.length; i++) map.put(inorder[i], i);
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }
    
    private TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        // end condition
        if (preStart > preorder.length - 1 || inStart > inEnd) return null;
        
        // root
        TreeNode root = new TreeNode(preorder[preStart]);
        
        // index of current root in inorder
        int idx = map.get(root.val);
        
        root.left = helper(preStart + 1, inStart, idx - 1, preorder, inorder);
        // idx - inStart + 1 == offset length in inorder
        root.right = helper(preStart + idx - inStart + 1, idx + 1, inEnd, preorder, inorder);
        return root;
    }
}
