package com.devlach.java_job_interview.algorithms.tree;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

    /**
     * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.
     * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
     * Output: [3,9,20,null,null,15,7]
     * Example 2:
     *
     * Input: inorder = [-1], postorder = [-1]
     * Output: [-1]
     */
    static int postIndex = 0;
    static Map<Integer, Integer> inorderMap = new HashMap<>();
    public static TreeNode buildTree(int[] inorder, int[] postorder){
      postIndex = postorder.length - 1;
      for (int i =0; i < inorder.length; i++) {
          inorderMap.put(inorder[i], i);
      }
      return buildTreeHelper(postorder, 0, inorder.length - 1);
    }

    private static TreeNode buildTreeHelper(int[] postorder, int start, int end){
        if(start > end) return null;
        TreeNode root = new TreeNode(postorder[postIndex--]);
        int inorderIndex = inorderMap.get(root.val);

        root.right = buildTreeHelper(postorder, inorderIndex + 1, end);
        root.left = buildTreeHelper(postorder, start, inorderIndex - 1 );

        return root;
    }
}
