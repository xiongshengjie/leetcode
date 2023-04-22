package cn.xcloude.leetcode;

/**
 * leetcode 543
 * 二叉树的直径
 */
public class DiameterOfBinaryTree {
    public int diameterOfBinaryTree(TreeNode root) {
        return nodeCountOfDiameter(root).getMaxDiameter() - 1;
    }

    private DiameterDecider nodeCountOfDiameter(TreeNode node) {
        if (node == null) {
            return DiameterDecider.EMPTY;
        }

        DiameterDecider leftDiameterDecider = nodeCountOfDiameter(node.left);
        DiameterDecider rightDiameterDecider = nodeCountOfDiameter(node.right);

        // 当前节点最大直径由 左节点最大直径(不通过当前节点), 右几点最大直径(不通过当前节点) 以及 通过当前节点的最大直径 决定
        int maxDiameter = Math.max(leftDiameterDecider.getMaxHeight() + rightDiameterDecider.getMaxHeight() + 1,
                Math.max(leftDiameterDecider.getMaxDiameter(), rightDiameterDecider.getMaxDiameter()));
        // 当前节点最大高度由 左右节点较大的高度 + 1
        int maxHeight = Math.max(leftDiameterDecider.getMaxHeight(), rightDiameterDecider.getMaxHeight()) + 1;
        return new DiameterDecider(maxDiameter, maxHeight);
    }

    private static final class DiameterDecider {
        private static final DiameterDecider EMPTY = new DiameterDecider(0, 0);

        private int maxDiameter;
        private int maxHeight;

        public DiameterDecider(int maxDiameter, int maxHeight) {
            this.maxDiameter = maxDiameter;
            this.maxHeight = maxHeight;
        }

        public int getMaxDiameter() {
            return maxDiameter;
        }

        public void setMaxDiameter(int maxDiameter) {
            this.maxDiameter = maxDiameter;
        }

        public int getMaxHeight() {
            return maxHeight;
        }

        public void setMaxHeight(int maxHeight) {
            this.maxHeight = maxHeight;
        }
    }
}
