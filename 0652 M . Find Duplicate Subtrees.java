// 652. Find Duplicate Subtrees
// https://leetcode.com/problems/find-duplicate-subtrees/description/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    Map<String, Integer> subtreeCount;
    List<TreeNode> duplicates;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        subtreeCount = new HashMap<>();
        duplicates = new ArrayList<>();
        serializeSubtree(root);
        return duplicates;
    }

    private String serializeSubtree(TreeNode node) {
        if (node == null) {
            return "#";
        }
        String serialized = node.val + "," + serializeSubtree(node.left) + "," + serializeSubtree(node.right);
        subtreeCount.put(serialized, subtreeCount.getOrDefault(serialized, 0) + 1);
        if (subtreeCount.get(serialized) == 2) {
            duplicates.add(node);
        }
        return serialized;
    }
}

class Solution 
{
    HashMap<String, Integer> map= new HashMap<>();//String -- frequency//it store the string at every instant when we visit parent after visiting its children //it also calculates the frequency of the String in the tree
    ArrayList<TreeNode> res= new ArrayList<>();//it contain the list of dublicate nodes 
    
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) 
    {
        Mapper(root);
        return res;//returning the list containing a node of dublicate subtree
    }
    
    public String Mapper(TreeNode root)
    {//we are doing postorder traversal because we want to first deal with children and then the parent 
        if(root == null)//when we reach to the null ,we return N to tell that i am null and unique 
            return "N";
        
        String left= Mapper(root.left);//recursing down the left subtree and knowing about the left child //LEFT
        String right= Mapper(root.right);//recursing down the hright subtree and knowing abou the right child //RIGHT
        
        //ROOT
        String curr= root.val +" "+left +" "+ right;//after knowing about the left and right children//parent forms their own string //space is added to disinguish the string of same reapeatating root value ex- 11 N , 1 1N
        
        map.put(curr, map.getOrDefault(curr, 0)+ 1);//counting the frequency of string 
        
        if(map.get(curr) == 2)//only the dublicate string node are added to the ArrayList 
            res.add(root);
    
        return curr;//returning to the parent to that i am present, and here is my string with the informationn of my left and right child 
    }
}//Please do Upvote, it helps a lot 
