package com.devlach.java_job_interview.algorithms.tree;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    /**
     * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
     * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * Output: [3,9,20,null,null,15,7]
     * Example 2:
     *
     * Input: preorder = [-1], inorder = [-1]
     * Output: [-1]
     */
    static int preIndex = 0;
    static Map<Integer, Integer> inorderMap = new HashMap<>();
    public static TreeNode buildTree(int[] preorder, int[] inorder){
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, 0, inorder.length - 1);
    }

    private static TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd){
        if(preStart > preEnd) return null;
        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTreeHelper(preorder, preStart, inorderMap.get(rootVal) - 1);
        root.right = buildTreeHelper(preorder, inorderMap.get(rootVal) + 1, preEnd);
        return root;
    }
}
