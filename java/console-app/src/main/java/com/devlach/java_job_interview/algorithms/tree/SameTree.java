package com.devlach.java_job_interview.algorithms.tree;

public class SameTree {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        t1.right = new TreeNode(3);

        TreeNode t2 = new TreeNode(1);
        t2.left = new TreeNode(2);
        t2.right = new TreeNode(3);

        // Should be true
        System.out.println("isSameTree(t1, t2) = " + isSameTree(t1, t2));

        // Should be false
        System.out.println("isSameTree(t1, t2) = " + isSameTree(t1, new TreeNode(1)));
    }

    public static boolean isSameTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;

        return t1.val == t2.val &&
                isSameTree(t1.left, t2.left) &&
                isSameTree(t1.right, t2.right);
    }
}
