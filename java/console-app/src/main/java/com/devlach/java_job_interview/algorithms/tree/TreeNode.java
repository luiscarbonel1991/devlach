package com.devlach.java_job_interview.algorithms.tree;

import java.util.*;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

    TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode insert(TreeNode node, int val) {
        if(node == null) {
            return new TreeNode(val);
        }

        if(val < node.val) {
            node.left = insert(node.left, val);
        } else {
            node.right = insert(node.right, val);
        }
        return node;
    }

    public List<Integer> preorderTraversal(TreeNode node) {
        List<Integer> result = new LinkedList<>();
        if(node == null) return result;

        Stack<TreeNode> nodes = new Stack<>();
        nodes.push(node);
        while (!nodes.isEmpty()) {
            TreeNode root = nodes.pop();
            result.add(root.val);
            if(root.right != null) {
                nodes.push(root.right);
            }
            if(root.left != null) {
                nodes.push(root.left);
            }
        }
        return result;
    }

    public List<Integer> inorderTraversal(TreeNode node) {
        List<Integer> result = new LinkedList<>();
        if(node == null) return result;
        Stack<TreeNode> nodes = new Stack<>();
        TreeNode currentNode = node;
        while (!nodes.isEmpty() || currentNode != null) {
            while (currentNode != null) {
                nodes.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = nodes.pop();
            result.add(currentNode.val);
            currentNode = currentNode.right;
        }

        return result;
    }

    public List<Integer> preorderTraversalR(TreeNode node) {
        List<Integer> result = new LinkedList<>();
        preorderTraversalR(node, result);
        return result;
    }
    private void preorderTraversalR(TreeNode root, List<Integer> builderNodes) {
        if(root == null) return;
        builderNodes.add(root.val);
        preorderTraversalR(root.left, builderNodes);
        preorderTraversalR(root.right, builderNodes);
    }

    public List<Integer> inorderTraversalR(TreeNode node) {
        List<Integer> result = new LinkedList<>();
        inorderTraversalR(node, result);
        return result;
    }

    private void inorderTraversalR(TreeNode root, List<Integer> builderNodes) {
        if (root == null) return;
        inorderTraversalR(root.left, builderNodes);
        builderNodes.add(root.val);
        inorderTraversalR(root.right, builderNodes);
    }

    public List<Integer> postorderTraversal(TreeNode node) {
        List<Integer> result = new LinkedList<>();

        if(node == null) return result;
        Stack<TreeNode> nodes = new Stack<>();
        nodes.push(node);
        while (!nodes.isEmpty()) {
            TreeNode root = nodes.pop();
            result.add(0, root.val);
            if(root.left != null) {
                nodes.push(root.left);
            }
            if(root.right != null) {
                nodes.push(root.right);
            }
        }
        return result;
    }

public List<Integer> postorderTraversalR(TreeNode node) {
        List<Integer> result = new LinkedList<>();
        postorderTraversalR(node, result);
        return result;
    }

    private void postorderTraversalR(TreeNode root, List<Integer> builderNodes) {
        if(root == null) return;
        postorderTraversalR(root.left, builderNodes);
        postorderTraversalR(root.right, builderNodes);
        builderNodes.add(root.val);
    }


}
