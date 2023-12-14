package com.devlach.java_job_interview.algorithms.tree;

public class InvertBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        invertTree(root);

        RuntimeException ex = new RuntimeException("Should be equals");
        if(root.val != 2) throw ex;
        if(root.left.val != 3) throw ex;
        if(root.right.val != 1) throw ex;

    }

    public static TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        TreeNode left = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = left;
        return root;
    }
}
