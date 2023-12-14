package com.devlach.java_job_interview.algorithms.tree;

public class SymmetricTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.left.right = new TreeNode(3);
        root2.right = new TreeNode(2);
        root2.right.right = new TreeNode(3);


        System.out.println("isSymmetric(root) = " + isSymmetric(root));
        System.out.println("isSymmetric(root2) = " + isSymmetric(root2));
    }


    public static boolean isSymmetric(TreeNode root) {
        return root == null ||
                isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode left, TreeNode right) {
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;

        return left.val == right.val &&
                isMirror(left.left, right.right) &&
                isMirror(right.left, left.right);
    }

}
