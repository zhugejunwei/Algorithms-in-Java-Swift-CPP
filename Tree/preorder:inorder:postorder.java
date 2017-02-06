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
// //深度优先遍历， c++
void depthFirstSearch(Tree root){
    stack<Node *> nodeStack;  //使用C++的STL标准模板库
    nodeStack.push(root);
    Node *node;
    while(!nodeStack.empty()){
        node = nodeStack.top();
        printf(format, node->data);  //遍历根结点
        nodeStack.pop();
        if(node->rchild){
            nodeStack.push(node->rchild);  //先将右子树压栈
        }
        if(node->lchild){
            nodeStack.push(node->lchild);  //再将左子树压栈
        }
    }
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
        list.add(root.val); // inorder
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
// 真正的最后遍历根节点 !!
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque();
        List<Integer> list = new ArrayList();
        TreeNode cur = root;
        TreeNode lastVisited = null;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                // peek node is cur node
                TreeNode peekNode = stack.peek();
                // if this cur node has right child, and right child is not visited, go right
                if (peekNode.right != null && peekNode.right != lastVisited) {
                    cur = peekNode.right;
                } else {
                    // if right child has been visited, that means left and right are both visited
                    // then visit current node
                    lastVisited = stack.pop();
                    list.add(lastVisited.val);
                }
            }
        }
        return list;
    }
}
// using two stacks
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        if (root == null) return res;
        Stack<TreeNode> s1 = new Stack();
        Stack<TreeNode> s2 = new Stack();
        s1.push(root);
        while (!s1.isEmpty()) {
            TreeNode cur = s1.pop();
            s2.push(cur);
            
            if (cur.left != null) {
                s1.push(cur.left);
            }
            if (cur.right != null) {
                s1.push(cur.right);
            }
        }
        
        while (!s2.isEmpty()) {
            res.add(s2.pop().val);
        }
        return res;
    }
}


// recursion:
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        helper(root, res);
        return res;
    }
    
    private void helper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        helper(root.left, res);
        helper(root.right, res);
        res.add(root.val); // postorder
    }
}


// get successor of a given node in bst, left - root - "right"
// right side, left most node.
public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    if (root == null) return null;
    
    if (root.val <= p.val) {
        return inorderSuccessor(root.right, p);
    } else {
        TreeNode left = inorderSuccessor(root.left, p);
        return left != null ? left : root;
    }
}

// get predecessor of a given node in bst, "left" - root - right
// left side, right most node
public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
    if (root == null) return null;
    
    if (root.val >= p.val) {
        return inorderPredecessor(root.left, p);
    } else {
        TreeNode right = inorderPredecessor(root.right, p);
        return right != null ? right : root;
    }
}

