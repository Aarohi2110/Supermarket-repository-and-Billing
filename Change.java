package pro1;


public class Change extends javax.swing.JFrame {

    
    public Change() {
        initComponents();
    }

    public Change(int sum) {
        initComponents();
        total.setText(String.valueOf(sum));
    }                                                           

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        String amt=txtamt.getText();
        txtchange.setText(String.valueOf(Integer.parseInt(amt)-Integer.parseInt(total.getText())));
    }                                        
