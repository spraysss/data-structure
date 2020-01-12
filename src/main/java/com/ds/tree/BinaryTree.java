package com.ds.tree;

import java.util.Stack;

public class BinaryTree {

    private BiNode root;

    public BinaryTree(BiNode root) {
        this.root = root;
    }

    static class BiNode {

        char data;
        BiNode left;
        BiNode right;

        public BiNode(char data) {
            this.data = data;
        }
    }

    private static int i = 0;//Record string position on recursion

    /**
     * @param s preOrder string represent of Binary Tree， # mean nil
     * @return Binary Tree construct by the preOrder string
     */
    public static BinaryTree createBiTree(String s) {
        System.out.println("createBiTree with preOrder String : " + s);
        i = 0;
        return new BinaryTree(BinaryTree.createBiTreeNode(s));
    }


    private static BiNode createBiTreeNode(String s) {
        BiNode node = null;
        if (s.charAt(i) != '#') {// char is not #， do recursion
            node = new BiNode(s.charAt(i));
            i++; // when successful create a BiNode ,increase string index
            node.left = createBiTreeNode(s);
            node.right = createBiTreeNode(s);


        } else {// Character # encountered, increase  string index
            i++;
        }
        return node;

    }


    /**
     * Recursive implementation of preOrder traversal
     */

    public void perOrderTraverseRecursive() {
        System.out.println("perOrderTraverseRecursive:");
        perOrderTraverseRecursive(root);
        System.out.println();
    }

    private void perOrderTraverseRecursive(BiNode node) {
        if (node != null) {
            visit(node);
            perOrderTraverseRecursive(node.left);
            perOrderTraverseRecursive(node.right);
        }
    }

    /**
     * Recursive implementation of inOrder traversal
     */

    public void inOrderTraverseRecursive() {
        System.out.println("inOrderTraverseRecursive:");
        inOrderTraverseRecursive(root);
        System.out.println();
    }

    private void inOrderTraverseRecursive(BiNode node) {

        if (node != null) {
            inOrderTraverseRecursive(node.left);
            visit(node);
            inOrderTraverseRecursive(node.right);
        }
    }


    /**
     * Recursive implementation of postOrder traversal
     */
    public void postOrderTraverseRecursive() {
        System.out.println("postOrderTraverseRecursive:");
        postOrderTraverseRecursive(root);
        System.out.println();
    }

    private void postOrderTraverseRecursive(BiNode node) {
        if (node != null) {
            postOrderTraverseRecursive(node.left);
            postOrderTraverseRecursive(node.right);
            visit(node);
        }

    }

    /**
     * Stack implementation of preOrder traversal
     */

    public void preOrderTraverse() {
        System.out.println("preOrder Traverse use Stack:");
        Stack<BiNode> nodeStack = new Stack<>();
        BiNode node = root;
        while (!nodeStack.isEmpty() || node != null) {
            while (node != null) {
                visit(node);
                nodeStack.push(node);
                node = node.left;

            }
            if (!nodeStack.isEmpty()) {
                node = nodeStack.pop();
                node = node.right;
            }
        }
        System.out.println();
    }

    /**
     * Stack implementation of inOrder traversal
     */

    public void inOrderTraverse() {

        System.out.println("inOrder Traverse use Stack:");
        Stack<BiNode> nodeStack = new Stack<>();
        BiNode node = root;
        while (!nodeStack.isEmpty() || node != null) {
            while (node != null) {
                nodeStack.push(node);
                node = node.left;

            }
            if (!nodeStack.isEmpty()) {
                node = nodeStack.pop();
                visit(node); //访问根结点
                node = node.right;
            }
        }
        System.out.println();
    }

    /**
     * Stack implementation of postOrder traversal
     */
    public void postOrderTraverse() {
        System.out.println("postOrder Traverse use Stack:");
        BiNode lastVisit = root;
        if (lastVisit != null) {
            Stack<BiNode> stack = new Stack<>();
            stack.push(lastVisit);
            BiNode top;
            while (!stack.isEmpty()) {
                top = stack.peek();
                //the stack top node has left child ,before we put it in stack we should Exclude the following case:
                //case 1:  node = topNode.left this means we just visit the stack top node's left node  last time, put again will cause duplicate  ,So we need to eliminate this situation，therefore node = !topNode.left
                //case 2:  node = topNode.right  this means we just visit the stack top node's right node, Now that you have visited the right node, you must have visited the left node  ,So we also need to eliminate this situation，therefore node = !topNode.right
                if (top.left != null && lastVisit != top.left && lastVisit != top.right) {
                    stack.push(top.left);
                 //the stack top node has right child ,before we put it in stack we should Exclude the following case:
                 //node = topNode.right  this means we just visit the stack top node's right node last time, put again will cause duplicate  ,So we need to eliminate this situation，therefore node = !topNode.right
                 //node == topNode.right is ok ,because this means we just visit the stack top node's left node ,now it time to deal with the right subtree
                } else if (top.right != null && lastVisit != top.right) {
                    stack.push(top.right);
                //it's time to visit the stack's top node ,because it's left and right node is null or we have visit it's left subtree and right subtree
                } else {
                    visit(stack.pop());
                    lastVisit = top;
                }
            }
        }
        System.out.println();
    }

    /**
     * Double Stack implementation of postOrder traversal
     */
    public void postOrderTraverseWithDoubleStack() {
        System.out.println("postOrderTraverse use Double Stack:");
        BiNode node = root;
        if (node != null) {
            Stack<BiNode> s1 = new Stack<>();
            Stack<BiNode> s2 = new Stack<>();
            s1.push(root);
            while (!s1.isEmpty()) {
                node = s1.pop();
                s2.push(node);
                if (node.left != null) {
                    s1.push(node.left);
                }
                if (node.right != null) {
                    s1.push(node.right);
                }
            }
            while (!s2.isEmpty()) {
                visit(s2.pop());
            }
        }

        System.out.println();
    }

    public void levelOrderTraverse() {

    }

    public BiNode search(char data) {
        return null;
    }

    public BiNode delete(char data) {
        return null;
    }

    /**
     * visit the node
     *
     * @param node
     */
    private void visit(BiNode node) {
        System.out.print(node.data + " ");
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = createBiTree("AB##DE##C#F##");
        binaryTree.perOrderTraverseRecursive();
        binaryTree.inOrderTraverseRecursive();
        binaryTree.postOrderTraverseRecursive();
        binaryTree.preOrderTraverse();
        binaryTree.inOrderTraverse();
        binaryTree.postOrderTraverse();
        binaryTree.postOrderTraverseWithDoubleStack();
    }
}
