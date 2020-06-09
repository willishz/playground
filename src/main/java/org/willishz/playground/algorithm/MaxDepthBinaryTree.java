package org.willishz.playground.algorithm;

import org.willishz.playground.util.JacksonUtil;

/**
 * 二叉树最大深度
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 */
public class MaxDepthBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, new TreeNode(9, null, null), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        MaxDepthBinaryTree.maxDepth(root);
        System.out.println(JacksonUtil.bean2Json(s));
    }

    /**
     * 递归
     *
     * @param s
     * @return
     */
    public static void maxDepth(TreeNode node) {
        if () {

        }
    }
}
