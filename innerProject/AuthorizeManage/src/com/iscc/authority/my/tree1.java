package com.iscc.authority.my;

import  javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.awt.event.*;
//import java.awt.event.ActionListener;
 
import javax.swing.tree.*;
import javax.swing.event.*;
import javax.swing.SwingUtilities;
 

public class tree1 extends JFrame  implements TreeSelectionListener,MouseListener,ItemListener, ActionListener{
    
    JPopupMenu jpopupMenu1 = new JPopupMenu();
    JMenuItem jmenuItem1,jmenuItem2,jmenuItem3,jmenuItem4;
    
    JTree tree;
    tree1()
    {       
        Container con=getContentPane();   
        setSize(300,250);
        setVisible(true);
        jmenuItem1 = new JMenuItem("��Ӻ���");
        jmenuItem2 = new JMenuItem("ɾ������");
        jmenuItem3 = new JMenuItem("�鿴��Ϣ");
        jmenuItem4 = new JMenuItem("����");
        jmenuItem1.addMouseListener(this);
        jmenuItem2.addMouseListener(this);
        jmenuItem3.addMouseListener(this);
        jmenuItem4.addMouseListener(this);
        jpopupMenu1.add(jmenuItem1);
        jpopupMenu1.add(jmenuItem2);
        jpopupMenu1.add(jmenuItem3);
        jpopupMenu1.add(jmenuItem4);
        jmenuItem1.addActionListener(this);
        jmenuItem2.addActionListener(this);
        jmenuItem3.addActionListener(this);
        jmenuItem4.addActionListener(this);
        //Container con1=getContentPane();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        DefaultMutableTreeNode root=new DefaultMutableTreeNode("�ҵĺ���");
        DefaultMutableTreeNode t1=new DefaultMutableTreeNode("��ѧͬѧ");
        DefaultMutableTreeNode t2=new DefaultMutableTreeNode("����ͬѧ");
        DefaultMutableTreeNode t3=new DefaultMutableTreeNode("����ͬѧ");
        

        final DefaultMutableTreeNode t1_1=new DefaultMutableTreeNode("lkj");
        
        final DefaultMutableTreeNode t1_2=new DefaultMutableTreeNode("����");
        DefaultMutableTreeNode t1_3=new DefaultMutableTreeNode("����");
        DefaultMutableTreeNode t2_1=new DefaultMutableTreeNode("dfsa");
        DefaultMutableTreeNode t2_2=new DefaultMutableTreeNode("ggasd");
        DefaultMutableTreeNode t2_3=new DefaultMutableTreeNode("adf");
        DefaultMutableTreeNode t3_1=new DefaultMutableTreeNode("dsfgre");
        DefaultMutableTreeNode t3_2=new DefaultMutableTreeNode("sdfgrewr");
        DefaultMutableTreeNode t3_3=new DefaultMutableTreeNode("er");
        

        root.add(t1);
        
        root.add(t2);
        root.add(t3);
        t1.add(t1_1);
        t1.add(t1_2);
        t1.add(t1_3);
        t2.add(t2_1);
        t2.add(t2_2);
        t2.add(t2_3);
        t3.add(t3_1);
        t3.add(t3_2);
        t3.add(t3_3);
        tree=new JTree(root);
        JScrollPane scrollpane=new JScrollPane(tree);       
        con.add(scrollpane);
        tree.addTreeSelectionListener(this);
        tree.add(jpopupMenu1);
        //ʵ��
        
        
        tree.addMouseListener(new   MouseAdapter()   
        {    
         final TreePath visiblePath = new TreePath(getTreeModel().getPathToRoot(t1_2)); 
                public   void   mousePressed(MouseEvent   event)   
                {   
                   int   selRow   =   tree.getRowForLocation(event.getX(),   event.getY());  
                   TreePath   selPath   =   tree.getPathForLocation(event.getX(),   event.getY());  
                   if (selPath==null){
                    
                   }
                   
                   else if(selPath.equals(visiblePath))   {   

                        if(((event.getModifiers()   &   InputEvent.BUTTON3_MASK)!=0)   &&   (tree.getSelectionCount()>0))   
                        {   
                              showmenu(event.getX(),event.getY());   
                        }   
                }   
                  
        }  
        }
      );  
        
        
  }   
    private DefaultTreeModel getTreeModel(){
     return (DefaultTreeModel)tree.getModel();
   } 
   
    public void showmenu(int   x,int   y){
       jpopupMenu1.show(tree,x,y);     
    }
    public void itemStateChanged(ItemEvent e)
    {       
    }
    
    public void valueChanged(TreeSelectionEvent e)
    {
    }
    
    public void actionPerformed(ActionEvent e)
    {
        System.out.println("dfa");
         if(e.getSource()==jmenuItem1)
          {
          System.out.println("you click 1");
          }
         else if(e.getSource()==jmenuItem4)
          {
          System.out.println("you click 4");
          } 
         else if(e.getSource()==jmenuItem3)
          {
          System.out.println("you click 3");
          }
        
    }
    
    public void mousePressed(MouseEvent e) {
        /*
        int mods=e.getModifiers();
//        ����Ҽ�
        if((mods&InputEvent.BUTTON3_MASK)!=0){
//        �����˵�
        jpopupMenu1.show(this,e.getX(),e.getY());
        */
        //if(e.getSource()==jmenuItem11)
        
        
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseClicked(MouseEvent e){
     System.out.println("###################");
    }
    
        //void menuItem1_actionPerformed(ActionEvent e) {}
//        �˵��¼�  
    
    public static void main(String args[])
    {
        tree1 m=new tree1();
        m.validate();
        //System.out.println("dfa");
        
        
    }
 
}

