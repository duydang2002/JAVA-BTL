package Login;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.*;
import java.util.Random;
public class UpdateImage extends JFrame implements ActionListener {
    JTextField txtFileName= new JTextField(100);
    JLabel lbImage = new JLabel("");
    JButton attachButton = new JButton("Attach");
    JButton summitButton = new JButton("Submit News");
    JButton summitButton2 = new JButton("Submit Bestseller");
    static File f;
    public UpdateImage(){
        txtFileName.setText("");
        lbImage.setIcon(null);
        attachButton.addActionListener(this);
        summitButton.addActionListener(this);
        summitButton2.addActionListener(this);
        this.setLayout(new GridLayout(4,1));
        this.add(lbImage);
        this.add(txtFileName);
        this.add(attachButton);
        this.add(summitButton);
        this.add(summitButton2);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==attachButton){
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        f = chooser.getSelectedFile();
        String fileName = f.getAbsolutePath();
        txtFileName.setText(fileName);
        ImageIcon i = new ImageIcon(f.getAbsolutePath());
        lbImage.setIcon(i);
        }
        if (e.getSource()== summitButton){
            String url = "jdbc:mysql://localhost:3306/javadatabase";
            String user = "root";
            String password = "";
            Random ran = new Random();
            try(Connection conn= DriverManager.getConnection(url, user, password)){
                System.out.println("ket noi thanh cong");
                System.out.println(conn.getCatalog());
                String Image = txtFileName.getText();
                System.out.println(f.getAbsolutePath());
                Image = Image.replace("//", "////");
                String upDateImageSql = " INSERT INTO `tblnews`(`Newsname`,`Image`) VALUES (?,?)";
                PreparedStatement Ps = conn.prepareStatement(upDateImageSql);
                FileInputStream fis = new FileInputStream(f);
                
                Ps.setBinaryStream(2, fis,fis.available());
                Ps.setString(1, f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("\\")+1));
                Ps.execute();
                JOptionPane.showMessageDialog(this, "Update Complete", "Successfull", JOptionPane.OK_CANCEL_OPTION);
            }
            catch(Exception E)
            {
               System.out.println("ST wrong!!");
               E.printStackTrace();
            }
        }
        if (e.getSource()==summitButton2){
            String url = "jdbc:mysql://localhost:3306/javadatabase";
            String user = "root";
            String password = "";
            Random ran = new Random();
            try(Connection conn= DriverManager.getConnection(url, user, password)){
                System.out.println("ket noi thanh cong");
                System.out.println(conn.getCatalog());
                String Image = txtFileName.getText();
                Image = Image.replace("//", "////");
                String upDateImageSql = " INSERT INTO `tblBestSeller`(`Name`,`Image`,`Quote`,`Price`) VALUES (?,?,?,?)";
                PreparedStatement Ps = conn.prepareStatement(upDateImageSql);
                FileInputStream fis = new FileInputStream(f);
         
                Ps.setBinaryStream(2, fis,fis.available());
                Ps.setString(1, f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("/")+1));
                Ps.setString(3, "ABCXYZ");
                Ps.setInt(4, 2);
                Ps.execute();
                JOptionPane.showMessageDialog(this, "Update Complete", "Successfull", JOptionPane.OK_CANCEL_OPTION);
            }
            catch(Exception E)
            {
               System.out.println("ST wrong!!");
               E.printStackTrace();
            }
        }
    }   
    public static void main(String[] args) {
      UpdateImage a=  new UpdateImage();
    }
}
