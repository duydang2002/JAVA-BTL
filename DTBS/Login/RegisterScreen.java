package Login;
import javax.swing.*;

import Hashing.Hashing;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.*;
public class RegisterScreen extends JFrame implements ActionListener{
    JLabel userNameLabel = new JLabel("User Name:");
    JTextField userNamTextField = new JTextField(30);
    JLabel hotenLabel= new JLabel("Enter Name: ");
    JTextField hotenTextField = new JTextField(30);
    JLabel EmailLabel = new JLabel("Email: (format @gmail.com)");
    JTextField EmailtextField = new JTextField(30);
    JLabel passwordLabel = new JLabel("Password");
    JPasswordField passwordField = new JPasswordField(30);
    JLabel repasswordJLabel = new JLabel("Confirm Password: ");
    JPasswordField repasswordField = new JPasswordField(30);
    JLabel sdtLabel= new JLabel("Phone Number");
    JTextField stdtexTextField = new JTextField(10);
    JLabel addressLabel = new JLabel("Address:");
    JTextField addressTextField = new JTextField(30);
    JButton regisButton = new JButton("Register");
    Hashing passDB = new Hashing();
    public RegisterScreen(){
        this.setLayout(new GridLayout(15,1));

            regisButton.addActionListener(this);
            this.add(hotenLabel);
            this.add(hotenTextField);
            this.add(EmailLabel);
            this.add(EmailtextField);
            this.add(userNameLabel);
            this.add(userNamTextField);
            this.add(passwordLabel);
            this.add(passwordField);
            this.add(repasswordJLabel);
            this.add(repasswordField);
            this.add(sdtLabel);
            this.add(stdtexTextField);
            this.add(addressLabel);
            this.add(addressTextField);
            this.add(regisButton);
            pack();
            this.setVisible(true);
    }
    @Override
    public void actionPerformed (ActionEvent e){
        String email =EmailtextField.getText().toLowerCase();
        String passwordf = String.valueOf(passwordField.getPassword());
        String name = hotenTextField.getText();
        String address = addressTextField.getText();
        String phoneNum =  stdtexTextField.getText();
        String userName = userNamTextField.getText();
        char [] passtoString = passwordf.toCharArray(); 
        char [] userNametoString = userName.toCharArray();
        int countNumbersPass = 0;
        int countUpperCasePass =0;
        int countNumbersUserName = 0;

        if (e.getSource()==regisButton){
            
            String url = "jdbc:mysql://localhost:3306/javadatabase";
            String user = "root";
            String password = "";
            String sqlInsert = "INSERT INTO `tblseller`(`Tên đăng nhập`, `Password`, `Họ và tên`,`Địa Chỉ` ,`SĐT`,`Số tiền đã mua`,`Tiền đã nạp`,`email`) VALUES (?,?,?,?,?,?,?,?)";
            String check = "SELECT * FROM `tblseller` where `Tên đăng Nhập`=? OR `SĐT`=? OR `Email`=?" ; 
            // String selectAll = "SELECT * FROM `tblseller`";
            boolean emailcheck =false;
            boolean phonecheck = false;
            boolean userNameCheck = false;
            // check the numbers
            for (int i = 0;i<passwordf.length();i++){
                if (Character.isDigit(passtoString[i])){
                   countNumbersPass++;
                }
                if (Character.isUpperCase(passtoString[i]))
                    countUpperCasePass++;
            }
            for (int i =0 ; i<userName.length();i++){
                if (Character.isDigit(userNametoString[i])){
                    countNumbersUserName++;
                }
            }
            
            try(Connection conn= DriverManager.getConnection(url, user, password)){
            System.out.println("ket noi thanh cong");
            System.out.println(conn.getCatalog());
            PreparedStatement stmt = conn.prepareStatement(check);
            stmt.setString(1, userName);
            stmt.setString(2, phoneNum);
            stmt.setString(3,email);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                 if (rs.getString(1).equals(userName)){
                    userNameCheck = true;}
                 if (rs.getString(5).equals(phoneNum)){
                    phonecheck = true;
                 }
                 if (rs.getString(9).equals(email)){
                    emailcheck= true;
                 }
                 
                // System.exit(0);

            }
            // change

         
            if (name.isEmpty() || email.isEmpty() || phoneNum.isEmpty() || address.isEmpty() || passwordf.isEmpty() || userName.isEmpty()){
                JOptionPane.showMessageDialog(this,
                "Please enter all fields",
                "Try again",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (userName.length()<8 || countNumbersUserName<2){
                JOptionPane.showMessageDialog(this,
                "User name must has 8 character or above and at least 2 number ",
                "Try again",
                JOptionPane.ERROR_MESSAGE);
        return;
            }
            if (userNameCheck){
                JOptionPane.showMessageDialog(this,
                "User name is used!",
                "Try again",
                JOptionPane.ERROR_MESSAGE);
        return;
            }
            if (!checkNumOfPass()){
                JOptionPane.showMessageDialog(this,
                "Password must contain 8 character or above",
                "Try again",
                JOptionPane.ERROR_MESSAGE);
        return;
            }

            if (!checkPass() ){
                JOptionPane.showMessageDialog(this,
                "Confirm Password does not match",
                "Try again",
                JOptionPane.ERROR_MESSAGE);
        return;
        }
        if (countNumbersPass<2){
            JOptionPane.showMessageDialog(this,
            "Not enough numbers!",
            "Try again",
            JOptionPane.ERROR_MESSAGE);
    return;

        }
        if (countUpperCasePass<1){
            JOptionPane.showMessageDialog(this,
            "Not enough Uppercase!",
            "Try again",
            JOptionPane.ERROR_MESSAGE);
    return;
        }
             
            if (!checkEmail()){
                JOptionPane.showMessageDialog(this,
                "Email not Correct!",
                "Try again",
                JOptionPane.ERROR_MESSAGE);
        return;
            }

            if (emailcheck){
                JOptionPane.showMessageDialog(this,
                "Email is used!",
                "Try again",
                JOptionPane.ERROR_MESSAGE);
        return;
            }
            if (phonecheck|| phoneNum.length()<10 || !String.valueOf(phoneNum.charAt(0)).equals("0") ){
                System.out.println( String.valueOf(phoneNum.charAt(0)));
                System.out.println(String.valueOf(phoneNum.charAt(0)).equals("0"));
                JOptionPane.showMessageDialog(this,
                "Phone Number is used or incorrect phone number!",
                "Try again",
                JOptionPane.ERROR_MESSAGE);
        return;
            }
     System.out.println(passDB.Hashing(passwordf));
                    // select all student
                    stmt = conn.prepareStatement(sqlInsert);
                    stmt.setString(1,userName);
                    stmt.setString(2,passDB.Hashing(passwordf));
                    stmt.setString(3,name);
                    stmt.setString(4, address);
                    stmt.setString(5,phoneNum);
                    stmt.setInt(6, 0);
                    stmt.setInt(7, 0);
                    stmt.setString(8    ,email);
                    stmt.execute();

                   JOptionPane.showMessageDialog(this, "<HTML> Đăng Kí thành công <HTML>");
                    stmt.close();
                    conn.close();
                    this.dispose();
                 
                 } 
                 catch(Exception E)
                 {
                     E.printStackTrace();
                 }
    } 
    
        
    }
    private boolean checkPass(){
        if (String.valueOf(passwordField.getPassword()).equals(String.valueOf(repasswordField.getPassword())) ) return true;
        else return false;
    }
    private boolean checkNumOfPass(){
        if (String.valueOf(passwordField.getPassword()).length()>=8) return true;
        else return false;
    }
    private boolean checkEmail(){
      String email=  EmailtextField.getText();
      if (email.length() <=10) return false;  
      if (email.substring(email.length()-10, email.length()).equals("@gmail.com")) return true;
        else return false;
    }

}
