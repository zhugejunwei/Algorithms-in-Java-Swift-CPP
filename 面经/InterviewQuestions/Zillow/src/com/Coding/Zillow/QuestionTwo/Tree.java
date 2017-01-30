package com.Coding.Zillow.QuestionTwo;

public class Tree {
    private Node root = null;

    public Tree(int data) {
        root = new Node(data);
    }

    public Tree() {
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public void insert(int data) {
        if (isEmpty()) {
            root = new Node(data);
        } else {
            insertNode(root, data);
        }
    }

    private void insertNode(Node root, int data) {
        if (data < root.getData()) {
            if (root.left == null) {
                Node node = new Node(data);
                root.left = node;
            } else {
                insertNode(root.left, data);
            }
        }
        if (data > root.getData()) {
            if (root.right == null) {
                Node node = new Node(data);
                root.right = node;
            } else {
                insertNode(root.right, data);
            }
        }
        if (data == root.getData()) {
            if (root.middle == null) {
                Node node = new Node(data);
                root.middle = node;
            } else {
                insertNode(root.middle, data);
            }
        }
    }

    public boolean delete(int data) {
        if (root == null) {
            return false;
        }
        return deleteNode(data);
    }

    private boolean deleteNode(int data) {
        Node node = root;
        Node parent = null;
        Node target = null;

        while (node != null) {
            if (node.getData() == data) {
                target = node;
                break;
            } else if (node.getData() < data) {
                parent = node;
                node = node.getRight();
            } else {
                parent = node;
                node = node.getLeft();
            }
        }

        if (target == null) {
            return false;
        }

        if (parent == null || target == root) {
            if (target.getMiddle() != null) {
                while (target.getMiddle() != null) {
                    parent = target;
                    target = target.getMiddle();
                }
                parent.setMiddle(null);
                return true;
            } else if (target.getLeft() != null) {
                if (target.getRight() == null
                        || target.getLeft().getRight() == null) {
                    root = root.getLeft();
                    return true;
                } else {
                    parent = target;
                    target = target.getLeft();
                    while (target.getRight() != null) {
                        parent = target;
                        target = target.getRight();
                    }
                    parent.setRight(null);
                    root.setValue(target.getData());
                    return true;
                }
            } else {
                root = root.getRight();
                return true;
            }
        } else {
            boolean isLeft = false;
            if (parent.getLeft() != null && parent.getLeft().getData() == data) {
                isLeft = true;
            }
            Node subParent = null;
            if (target.getMiddle() != null) {
                while (target.getMiddle() != null) {
                    subParent = target;
                    target = target.getMiddle();
                }
                subParent.setMiddle(null);
                return true;
            } else if (target.getLeft() != null) {
                if (target.getRight() == null
                        || target.getLeft().getRight() == null) {
                    if (isLeft) {
                        parent.setLeft(target.getLeft());
                    } else {
                        parent.setRight(target.getLeft());
                    }
                    return true;
                } else {
                    subParent = target;
                    target = target.getLeft();
                    while (target.getRight() != null) {
                        subParent = target;
                        target = target.getRight();
                    }
                    subParent.setRight(null);
                    if (isLeft) {
                        parent.getLeft().setValue(target.getData());
                    } else {
                        parent.getRight().setValue(target.getData());
                    }
                    return true;
                }
            } else {
                if (isLeft) {
                    parent.setLeft(target.getRight());
                } else {
                    parent.setRight(target.getRight());
                }
                return true;
            }
        }
    }

    public void print() {
        inOrderTraverse(root);
    }

    private void inOrderTraverse(Node root) {
        if (root != null) {
            inOrderTraverse(root.left);
            inOrderTraverse(root.middle);
            System.out.println(String.valueOf(root.getData()) + ' ');
            inOrderTraverse(root.right);
        }
    }

    public static class Node {
        private int data;
        private Node left = null;
        private Node right = null;
        private Node middle = null;

        public Node(int data) {
            this.data = data;
        }

        public void setValue(int data) {
            this.data = data;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void setMiddle(Node middle) {
            this.middle = middle;
        }

        public int getData() {
            return data;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public Node getMiddle() {
            return middle;
        }
    }

    public static void main(String args[]) {
        Tree tree = new Tree();
        tree.insert(10);
        tree.print();
        System.out.println(" ");
        tree.delete(10);
        tree.print();
    }
}