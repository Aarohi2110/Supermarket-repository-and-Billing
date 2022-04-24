package pro1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Bill extends javax.swing.JFrame {

    public Bill() {
        initComponents();
        Connect();
        LoadProductNo();
        billheader();
    }
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    int sum=0;
  
    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                con=DriverManager.getConnection("jdbc:mysql://localhost/product","root","");
                System.out.print("Connect");
            } catch (SQLException ex) {
                Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void LoadProductNo(){
        try {
            pst=con.prepareStatement("select id from products");
            rs=pst.executeQuery();
            txtpid.removeAllItems();
            while(rs.next()){
                txtpid.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    private void billheader(){
        txtbill.setText("====================================="+"\n"+
                        "\t"+"Company Name"+"\n"+
                        "Contact No- xxxxxxxxxx"+"\n"
                         +"Address"+"\n"+
                       "======================================"+"\n"
        + "Items\t"+"Price\t"+"Quantity\t"+"Total\n");
        
    }
    
                          

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        String sm=String.valueOf(sum);
        txtbill.setText(
        txtbill.getText()+"\n"+"Grand Total: \t"+sm
            );
    }                                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        
            txtbill.setText(
          txtbill.getText()+txtname.getText()+"\t"+txtprice.getText()+"\t"+txtqty.getText()+"\t"+txttotal.getText()+ "\n"
            );
               txtname.setText("");
                txtprice.setText("");
                txtqty.setText("");
                txttotal.setText("");
                txtname.requestFocus();
                LoadProductNo();
    
    }                                        
                                     

    private void txtqtyActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
        String pid=txtpid.getSelectedItem().toString();
        if(pid!=null){
  
        int qty=Integer.parseInt(txtqty.getText());
        int price=Integer.parseInt(txtprice.getText());
        int ini_qty = 0;
        try {
            pst=con.prepareStatement("select * from products where id=?");
            pst.setString(1, pid);
            rs=pst.executeQuery();
            if(rs.next()==true){
                ini_qty=Integer.parseInt(rs.getString(4));
            }
            LoadProductNo();
        } catch (SQLException ex) {
            Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
        }
        int final_qty= (ini_qty-qty);
          
        if(final_qty>=0){
        try {
            pst=con.prepareStatement("update products set qty=? where id=? ");
            pst.setString(1,String.valueOf(final_qty));
            pst.setString(2, pid);
            int k=pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else{
            JOptionPane.showMessageDialog(this,"Insufficient Quantity");
        }
        int total=qty*price;
        sum=sum+total;
        txttotal.setText(String.valueOf(total));
        ini_qty=0;
        final_qty=0;
        }
        
    }                                      

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
        String pid= txtpid.getSelectedItem().toString();
        try {
            // TODO add your handling code here:
            
            pst=con.prepareStatement("select * from products where id=?");
            pst.setString(1,pid);
            rs=pst.executeQuery();
            if(rs.next()==true){
                txtname.setText(rs.getString(2));
                txtprice.setText(rs.getString(3));
                LoadProductNo();
        
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }                                      

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        new Bill().setVisible(false);
        new Change(sum).setVisible(true);
    }                                        
