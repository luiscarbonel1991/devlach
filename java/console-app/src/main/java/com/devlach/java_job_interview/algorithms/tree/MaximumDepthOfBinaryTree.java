package com.devlach.java_job_interview.algorithms.tree;

public class MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        if( maxDepth(root) != 3 ) throw new RuntimeException("Max Depth Should be 3");
    }


    public static int maxDepth(TreeNode node){
        if(node == null) return 0;
        int maxDepthLeft = maxDepth(node.left);
        int maxDepthRight = maxDepth(node.right);
        return Math.max(maxDepthLeft, maxDepthRight) + 1;
    }
}
