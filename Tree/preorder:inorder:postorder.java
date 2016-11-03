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
