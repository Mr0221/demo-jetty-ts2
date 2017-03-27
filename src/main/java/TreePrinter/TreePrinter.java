package TreePrinter;

import java.util.*;


public class TreePrinter {
	public class TreeNode {
	    int val = 0;
	    TreeNode left = null;
	    TreeNode right = null;
	    public TreeNode(int val) {
	        this.val = val;
	    }
	}
	//123
    public int[][] printTree(TreeNode root) {
        
        if(root==null) return null;
        LinkedList<TreeNode> quenen=new LinkedList<TreeNode>();
        ArrayList<Integer> arr= new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> ans= new ArrayList<ArrayList<Integer>>();
        TreeNode last=root;
        TreeNode nlast=null;
        TreeNode temp=null;
        quenen.add(last);
        while(!quenen.isEmpty()){
            temp=quenen.poll();
            arr.add(temp.val);
            if(temp.left!=null){
                quenen.add(temp.left);
                nlast=temp.left;
            }
            if(temp.right!=null){
                quenen.add(temp.right);
                nlast=temp.right;
            }
            if(temp==last){
                ans.add(arr);
                arr=new ArrayList<Integer>();
                last=nlast;
            }
        }
        
        int[][] a=new int[ans.size()][];
        for(int i=0;i<ans.size();i++){
          a[i]=new int[ans.get(i).size()];
            for(int j=0;j<ans.get(i).size();j++){
                a[i][j]=ans.get(i).get(j);
            }
        }
        
      return a;  
    }
}