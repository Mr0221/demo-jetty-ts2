package BTree;

public class Node {
    private NodeDate date;
    private Node ptLeftNode;
    private Node ptRightNode;

    public Node(final NodeDate date, final Node ptLeftNode, final Node ptRightNode) {
        this.date = date;
        this.ptLeftNode = ptLeftNode;
        this.ptRightNode = ptRightNode;
    }
    public NodeDate getDate() {
        return date;
    }
    public void setDate(final NodeDate date) {
        this.date = date;
    }
    public Node getPtLeftNode() {
        System.out.println(ptLeftNode.getDate().getDate());
        return ptLeftNode;
    }
    public void setPtLeftNode(final Node ptLeftNode) {
        this.ptLeftNode = ptLeftNode;
    }
    public Node getPtRightNode() {
        System.out.println(ptRightNode.getDate().getDate());
        return ptRightNode;
    }
    public void setPtRightNode(final Node ptRightNode) {
        this.ptRightNode = ptRightNode;
    }


}
