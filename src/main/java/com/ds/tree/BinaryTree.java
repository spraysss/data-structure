package com.ds.tree;

import java.util.Stack;

public class BinaryTree {

    private BiNode root;

    public BinaryTree(BiNode root) {
        this.root = root;
    }


    private static int i = 0;//递归时记录字符串的位置

    /**
     * @param s 前序遍历字符串，#代表空指针
     * @return 通过前序遍历产生的二叉树
     */
    public static BinaryTree createBiTree(String s) {
        System.out.println("使用前序序列: "+s+" 创建二叉树");
        i = 0;
        return new BinaryTree(BinaryTree.createBiTreeNode(s));
    }


    private static BiNode createBiTreeNode(String s) {
        BiNode node = null;
        if (s.charAt(i) != '#') {//字符不为# ，往下递归
            node = new BiNode(s.charAt(i));
            i++;
            node.lChlid = createBiTreeNode(s);
            node.rChild = createBiTreeNode(s);


        } else {// 遇到# 字符串向后偏移一位
            i++;
        }
        return node;

    }

    private
    static class BiNode {

        char data;
        BiNode lChlid;
        BiNode rChild;

        public BiNode(char data) {
            this.data = data;
        }
    }

    /**
     * 先序遍历递归实现
     */

    public void perOrderTraverseRecursive() {
        System.out.println("先序遍历:");
        perOrderTraverseRecursive(root);
        System.out.println();
    }

    private void perOrderTraverseRecursive(BiNode node) {
        if (node != null) {
            visit(node);
            perOrderTraverseRecursive(node.lChlid);
            perOrderTraverseRecursive(node.rChild);
        }
    }

    /**
     * 中序遍历递归实现
     */

    public void inOrderTraverseRecursive() {
        System.out.println("中序遍历:");
        inOrderTraverseRecursive(root);
        System.out.println();
    }

    private void inOrderTraverseRecursive(BiNode node) {

        if (node != null) {
            inOrderTraverseRecursive(node.lChlid);
            visit(node);
            inOrderTraverseRecursive(node.rChild);
        }
    }


    /**
     * 后序遍历递归实现
     */
    public void postOrderTraverseRecursive() {
        System.out.println("后序遍历:");
        postOrderTraverseRecursive(root);
        System.out.println();
    }

    private void postOrderTraverseRecursive(BiNode node) {
        if (node != null) {
            postOrderTraverseRecursive(node.lChlid);
            postOrderTraverseRecursive(node.rChild);
            visit(node);
        }

    }

    /**
     * 使用Stack实现的非递归前序遍历
     */

    public void preOrderTraverse() {
        System.out.println("非递归前序遍历:");
        Stack<BiNode> nodeStack = new Stack<>();
        BiNode node = root;
        while (!nodeStack.isEmpty() || node != null) {
            while (node != null) {
                visit(node);
                nodeStack.push(node);
                node = node.lChlid;

            }
            if (!nodeStack.isEmpty()) {
                node = nodeStack.pop();
                node = node.rChild;
            }
        }
        System.out.println();
    }

    /**
     * 使用Stack实现的非递归中序遍历
     */

    public void inOrderTraverse() {

        System.out.println("非递归中序遍历:");
        Stack<BiNode> nodeStack = new Stack<>();
        BiNode node = root;
        while (!nodeStack.isEmpty() || node != null) {
            while (node != null) {
                nodeStack.push(node);
                node = node.lChlid;

            }
            if (!nodeStack.isEmpty()) {
                node = nodeStack.pop();
                visit(node); //访问根结点
                node = node.rChild;
            }
        }
        System.out.println();
    }


    /**
     * 使用双栈实现二叉树的后序遍历
     */
    public void postOrderTraverseWithDoubleStack() {
        System.out.println("使用双栈实现的非递归后序遍历:");
        BiNode node = root;
        if (node != null) {
            Stack<BiNode> s1 = new Stack<>();
            Stack<BiNode> s2 = new Stack<>();
            s1.push(root);
            while (!s1.isEmpty()) {
                node = s1.pop();
                s2.push(node);
                if (node.lChlid != null) {
                    s1.push(node.lChlid);
                }
                if (node.rChild != null) {
                    s1.push(node.rChild);
                }
            }
            while (!s2.isEmpty()) {
                visit(s2.pop());
            }
        }

        System.out.println();
    }


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
        binaryTree.postOrderTraverseWithDoubleStack();
    }
}
