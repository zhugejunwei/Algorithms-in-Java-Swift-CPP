/* Given a binary search tree and a number,
 inserts a new node with the given number in
 the correct place in the tree. Returns the new
 root pointer which the caller should then use
 (the standard trick to avoid using reference
 parameters). */
Node insert(Node node, int data) {

    /* 1. If the tree is empty, return a new,
     single node */
    if (node == null) {
        return (new Node(data));
    } else {
        Node temp = null;
        /* 2. Otherwise, recur down the tree */
        if (data <= node.data) {
            temp = insert(node.left, data);
            node.left = temp;
            temp.parent = node;
        } else {
            temp = insert(node.right, data);
            node.right = temp;
            temp.parent = node;
        }
        /* return the (unchanged) node pointer */
        return node;
    }
}
