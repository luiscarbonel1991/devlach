package com.devlach.java_job_interview.algorithms.tree;



public class ContructBinaryTree {

    /**
     * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.
     * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
     * Output: [3,9,20,null,null,15,7]
     */

    public static void main(String[] args) {
        var inorder = new int[]{9,3,15,20,7};
        var postorder = new int[]{9,15,7,20,3};
        var tree = new ContructBinaryTree();
        var result = tree.buildTree(inorder, postorder);
        System.out.println("result = " + result);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode buildTree(int[] inorder, int[] postorder, int inStart, int inEnd, int postStart, int postEnd){
        if(inStart > inEnd || postStart > postEnd) return null;
        int rootValue = postorder[postEnd];
        TreeNode root = new TreeNode(rootValue);
        int k = 0;
        for(int i = 0; i < inorder.length; i++){
            if(inorder[i] == rootValue){
                k = i;
                break;
            }
        }
        root.left = buildTree(inorder, postorder, inStart, k - 1, postStart, postStart + k - (inStart + 1));
        root.right = buildTree(inorder, postorder, k + 1, inEnd, postStart + k - inStart, postEnd - 1);
        return root;
    }
}
