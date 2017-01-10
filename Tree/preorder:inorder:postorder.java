// ===================== pre ==================//

public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    Deque<TreeNode> stack = new ArrayDeque<>();
    TreeNode p = root;
    while (!stack.isEmpty() || root != null) {
        if (p != null) {
            stack.push(p);
            list.add(p.val);  // add before children
            p = p.left;
        } else {
            TreeNode node = stack.pop();
            p = node.right;
        }
    }
    return list;
}
// recursion:



// ===================== In ==================//

public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    Deque<TreeNode> stack = new ArrayDeque<>();
    TreeNode p = root;
    while (!stack.isEmpty() || p != null) {
        if (p != null) {
            stack.push(p);
            p = p.left;
        } else {
            TreeNode node = stack.pop();
            list.add(node.val); // add after left, before right
            p = node.right;
        }
    }
    return list;
}
// recursion
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();
        helper(root, list);
        return list;
    }
    
    private void helper(TreeNode root, List<Integer> list) {
        if (root == null) return;
        helper(root.left, list);
        list.add(root.val);
        helper(root.right, list);
    }
}

// ===================== post ==================//

public List<Integer> postorderTraversal(TreeNode root) {
    LinkedList<Integer> list = new LinkedList<>();
    Deque<TreeNode> stack = new ArrayDeque<>();
    TreeNode p = root;
    while (!stack.isEmpty() || p != null) {
        if (p != null) {
            stack.push(p);
            list.addFirst(p.val);  // add before children from last index
            p = p.right;
        } else {
            TreeNode node = stack.pop();
            p = node.left;
        }
    }
    return list;
}
// recursion:



// get successor of a given node in bst
public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    if (root == null) return null;
    
    if (root.val <= p.val) {
        return inorderSuccessor(root.right, p);
    } else {
        TreeNode left = inorderSuccessor(root.left, p);
        return left != null ? left : root;
    }
}

// get predecessor of a given node in bst
public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    if (root == null) return null;
    
    if (root.val >= p.val) {
        return inorderSuccessor(root.left, p);
    } else {
        TreeNode right = inorderSuccessor(root.right, p);
        return right != null ? right : root;
    }
}

