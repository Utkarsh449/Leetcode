/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    TreeNode ans;
    private int maxDepth = -1;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if(root == null){
            return null;
        }
        dfs(root, 0);
        return ans;
    }

    public int dfs(TreeNode node, int depth){
        if(node == null){
            maxDepth = Math.max(maxDepth, depth);
            return depth;
        }
        int leftMaxDepth = dfs(node.left, depth+1);
        int rightMaxDepth = dfs(node.right, depth+1);
        if(leftMaxDepth == rightMaxDepth && leftMaxDepth == maxDepth){
            ans = node;
        }
        return Math.max(leftMaxDepth, rightMaxDepth);
    }
}