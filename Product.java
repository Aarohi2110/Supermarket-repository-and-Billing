package pro1;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import javax.swing.JOptionPane;

public class Product extends javax.swing.JFrame {

   
    public Product() {
        initComponents();
        Connect();
        LoadProductNo();
    }
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/product","root","");
            System.out.println("Connect");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        
         try {
            // TODO add your handling code here:
            String pname=txtname.getText();
            String price=txtprice.getText();
            String qty=txtqty.getText();
            String pid= txtpid.getSelectedItem().toString();
            
            pst=con.prepareStatement("update products set pname=?, price=?, qty=? where id=? ");
            pst.setString(1, pname);
            pst.setString(2, price);
            pst.setString(3, qty);
            pst.setString(4, pid);
            int k=pst.executeUpdate();
            if(k==1){
                JOptionPane.showMessageDialog(this,"Record Updatedd");
                txtname.setText("");
                txtprice.setText("");
                txtqty.setText("");
                txtname.requestFocus();
                LoadProductNo();
            }
            else{
               JOptionPane.showMessageDialog(this,"Record Failed"); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }                                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        try {
            // TODO add your handling code here:
            String pname=txtname.getText();
            String price=txtprice.getText();
            String qty=txtqty.getText();
           
            pst=con.prepareStatement("insert into products(pname,price,qty)values(?,?,?)");
            pst.setString(1, pname);
            pst.setString(2, price);
            pst.setString(3, qty);
            int k=pst.executeUpdate();
            if(k==1){
                JOptionPane.showMessageDialog(this,"Record Addedd");
                txtname.setText("");
                txtprice.setText("");
                txtqty.setText("");
                txtname.requestFocus();
                LoadProductNo();
            }
            else{
               JOptionPane.showMessageDialog(this,"Record Failed"); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }                                        

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        txtname.setText("");
                txtprice.setText("");
                txtqty.setText("");
                txtname.requestFocus();
                LoadProductNo();
        
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
    
            String pid= txtpid.getSelectedItem().toString();
            
            pst=con.prepareStatement("delete from products where id=? ");
          
            pst.setString(1, pid);
            int k=pst.executeUpdate();
            if(k==1){
                JOptionPane.showMessageDialog(this,"Record Deletedd");
                txtname.setText("");
                txtprice.setText("");
                txtqty.setText("");
                txtname.requestFocus();
                LoadProductNo();
            }
            else{
               JOptionPane.showMessageDialog(this,"Record Failed"); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }                                        

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        try {
            // TODO add your handling code here:

            String pid= txtpid.getSelectedItem().toString();
            pst=con.prepareStatement("select * from products where id=?");
            pst.setString(1,pid);
            rs=pst.executeQuery();
            if(rs.next()==true){
                txtname.setText(rs.getString(2));
                txtprice.setText(rs.getString(3));
                txtqty.setText(rs.getString(4));
                LoadProductNo();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }

    }                         
