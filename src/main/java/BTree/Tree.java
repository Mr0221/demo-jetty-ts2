package BTree;

public class Tree {
    private final Node root;

    public Node getRoot() {
        return root;
    }
    public Tree(){
        this.root = new Node(new NodeDate(1), null, null);
        final Node n2 = new Node(new NodeDate(2), null, null);
        final Node n3 = new Node(new NodeDate(3), null, null);
        final Node n4 = new Node(new NodeDate(4), null, null);
        this.root.setPtLeftNode(n2);
        n2.setPtLeftNode(n4);
        this.root.setPtRightNode(n3);

    }
    public void addNode(final Node node){

    }
    public void overTree(final Node startNode){
        if(startNode != null){
            System.out.println( startNode.getDate().getDate() );
            if(startNode.getPtLeftNode() != null){
                overTree(startNode.getPtLeftNode());
            }
            if(startNode.getPtRightNode() != null){
                overTree(startNode.getPtRightNode());
            }
        }
    }
}
