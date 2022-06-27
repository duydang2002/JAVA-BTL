package Login;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import Hashing.Hashing;
import Hashing.ValidatePassword;

import java.awt.font.TextAttribute;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OwnerLogin extends JFrame implements ActionListener {
    JPanel Login =new JPanel();
    JLabel loginLabel = new JLabel("User Name:");
    JLabel passwordLabel = new JLabel("Password:");
    JButton loginButton= new JButton("Login");
    JTextField AccountField= new JTextField(20);
    JPasswordField passworField = new JPasswordField(20);
    public OwnerLogin(){
        this.setLayout(new BorderLayout());

        Login.setLayout(new GridLayout(5,1));
        setVisible(true);
   
        loginButton.addActionListener(this);

        Login.add(loginLabel);
        Login.add(AccountField);
        Login.add(passwordLabel);
        Login.add(passworField);
        Login.add(loginButton);
        
        this.add(Login,BorderLayout.CENTER);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == loginButton){
            String url = "jdbc:mysql://localhost:3306/javadatabase";
            String user = "root";
            String password = "";
            Owner owner = null;
            ValidatePassword VP = new ValidatePassword();
            // User user;
            // String sqlInsert = "INSERT INTO `tblseller`(`Email`, `Password`, `Họ và tên`, `SĐT`,`Số tiền đã mua`) VALUES (?,?,?,?,?)";
            String sql = "SELECT * FROM `tblowner` WHERE `Tên Đăng nhập`=?";
    
                 try(Connection conn= DriverManager.getConnection(url, user, password)){
                    System.out.println("ket noi thanh cong");
                    System.out.println(conn.getCatalog());
                    
                    // select all student
                    PreparedStatement  stmt = conn.prepareStatement(sql);
                    stmt.setString(1, AccountField.getText().toLowerCase());
                    Hashing passHashing = new Hashing();
                    System.out.println( passHashing.Hashing(String.valueOf(passworField.getPassword())));
              
                    ResultSet rs = stmt.executeQuery();
               
                    if (rs.next()) {
                        System.out.println(rs.getString(4));

                        if (VP.ValidatePassword(String.valueOf(passworField.getPassword()), rs.getString(4))){     
                            System.out.println("??");                                
                            owner = new Owner();
                            owner.Revenue= rs.getInt(1);
                            owner.userName=rs.getString(2);
                            owner.Name=rs.getString(3);
                        JOptionPane.showMessageDialog(this, " Đăng Nhập Thành công", "Message", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                    if (owner != null) {
                        this.dispose();
                        // Login.dispose();
                   OwnerScreen own = new OwnerScreen(owner);
                    }
                    else {
                        JOptionPane.showMessageDialog(this, "Tên Đăng Nhập hoặc mật khẩu không đúng", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    // change

                    stmt.close();
                    conn.close();
                 } 
                 catch(Exception E)
                 {
                    System.out.println("ST wrong!!");
                    E.printStackTrace();
                 }
                }
    }

}
