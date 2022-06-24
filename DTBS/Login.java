import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import java.awt.font.TextAttribute;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

public class Login extends JFrame implements ActionListener, MouseListener {
    JPanel Login =new JPanel();
    JLabel loginLabel = new JLabel("Email:");
    JLabel passwordLabel = new JLabel("Password:");
    JButton loginButton= new JButton("Login");
    JTextField AccountField= new JTextField(20);
    JPasswordField passworField = new JPasswordField(20);
    JLabel checkJLabel = new JLabel("If you dont have an account click here to Register");
    public Login(){
        this.setLayout(new BorderLayout());

        Login.setLayout(new GridLayout(6,1));
        setVisible(true);
        checkJLabel.setForeground(Color.BLUE);
        loginButton.addActionListener(this);
        checkJLabel.addMouseListener(this);

        Login.add(loginLabel);
        Login.add(AccountField);
        Login.add(passwordLabel);
        Login.add(passworField);
        Login.add(checkJLabel);
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
            User user2 = null;
            ValidatePassword VP = new ValidatePassword();
            // User user;
            // String sqlInsert = "INSERT INTO `tblseller`(`Email`, `Password`, `Họ và tên`, `SĐT`,`Số tiền đã mua`) VALUES (?,?,?,?,?)";
            String sql = "SELECT * FROM `tblseller` WHERE Email=?";
    
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

                        if (VP.ValidatePassword(String.valueOf(passworField.getPassword()), rs.getString(2))){
                            // System.out.println("Dang Nhap");
                    
                        user2 = new User();
                        user2.name = rs.getString(3);
                        user2.email = rs.getString(1);
                        user2.phone = rs.getString(5);
                        user2.address = rs.getString(4);
                        user2.password = rs.getString(2);
                        user2.Paid = rs.getInt(6);
                        user2.Money= rs.getInt(7);
                        JOptionPane.showMessageDialog(this, " Đăng Nhập Thành công", "Message", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                    if (user2 != null) {
                        this.dispose();
                        // Login.dispose();
                   Customer cus = new Customer(user2);
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


    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        this.dispose();
           RegisterScreen a = new RegisterScreen();
        
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    Font font = checkJLabel.getFont();
    Map attributes = font.getAttributes();
    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        // checkJLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        checkJLabel.setFont(font.deriveFont(attributes));
        
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub

       attributes.put(TextAttribute.UNDERLINE, -1);
       checkJLabel.setFont(font.deriveFont(attributes));
    }

    
}
