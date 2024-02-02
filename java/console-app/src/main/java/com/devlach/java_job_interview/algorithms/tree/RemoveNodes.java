package com.devlach.java_job_interview.algorithms.tree;

import java.util.*;

public class RemoveNodes {

    public static List<TreeNode> deleteNodes(TreeNode root, Set<Integer> toDelete) {
        List<TreeNode> result = new ArrayList<>();
        if (root == null) return result;
        delete(root, toDelete, result, true);
        return result;
    }

    private static TreeNode delete(TreeNode node, Set<Integer> toDelete, List<TreeNode> result, boolean isRoot) {
        if (node == null) return null;
        boolean deleted = toDelete.contains(node.val);
        if (isRoot && !deleted) result.add(node);

        if (!deleted) {
            node.left = delete(node.left, toDelete, result, false);
            node.right = delete(node.right, toDelete, result, false);
        } else {
            if (node.left != null) delete(node.left, toDelete, result, true);
            if (node.right != null) delete(node.right, toDelete, result, true);
            return null;
        }

        return node;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        Set<Integer> toDelete = new HashSet<>(Arrays.asList(3, 5));
        List<TreeNode> remainingTrees = deleteNodes(root, toDelete);

        for (TreeNode tree : remainingTrees) {
            printTree(tree);
            System.out.println();
        }
    }

    private static void printTree(TreeNode node) {
        if (node != null) {
            System.out.print(node.val + " ");
            printTree(node.left);
            printTree(node.right);
        }
    }
}
