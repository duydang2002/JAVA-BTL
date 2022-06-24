import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.*;
public class RegisterScreen extends JFrame implements ActionListener{
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
        this.setLayout(new GridLayout(13,1));

            regisButton.addActionListener(this);
            this.add(hotenLabel);
            this.add(hotenTextField);
            this.add(EmailLabel);
            this.add(EmailtextField);
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
        if (e.getSource()==regisButton){
            String url = "jdbc:mysql://localhost:3306/javadatabase";
            String user = "root";
            String password = "";
            String sqlInsert = "INSERT INTO `tblseller`(`Email`, `Password`, `Họ và tên`,`Địa Chỉ` ,`SĐT`,`Số tiền đã mua`,`Tiền đã nạp`) VALUES (?,?,?,?,?,?,?)";
            String check = "SELECT * FROM `tblseller` where email=? AND SĐT=? "; 
            // String selectAll = "SELECT * FROM `tblseller`";
            boolean emailcheck =false;
            boolean phonecheck = false;

            try(Connection conn= DriverManager.getConnection(url, user, password)){
            System.out.println("ket noi thanh cong");
            System.out.println(conn.getCatalog());
            PreparedStatement stmt = conn.prepareStatement(check);
            stmt.setString(1, email);
            stmt.setString(2, phoneNum);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                 if (rs.getString(1).equals(email)  ) {
                    emailcheck = true;}
                 if (rs.getString(5).equals(phoneNum)){
                    phonecheck = true;
                 }
                 
                // System.exit(0);

            }
            // change

         
            if (name.isEmpty() || email.isEmpty() || phoneNum.isEmpty() || address.isEmpty() || passwordf.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                "Please enter all fields",
                "Try again",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!checkPass()){
                JOptionPane.showMessageDialog(this,
                "Confirm Password does not match",
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
            if (phonecheck){
                JOptionPane.showMessageDialog(this,
                "Phone Number is used!",
                "Try again",
                JOptionPane.ERROR_MESSAGE);
        return;
            }
                      System.out.println(passDB.Hashing(passwordf));
                    // select all student
                    stmt = conn.prepareStatement(sqlInsert);
                    stmt.setString(1, email);
                    stmt.setString(2,passDB.Hashing(passwordf));
                    stmt.setString(3,name);
                    stmt.setString(4, address);
                    stmt.setString(5,phoneNum);
                    stmt.setInt(6, 0);
                    stmt.setInt(7, 0);
                    stmt.execute();
                    // break;
                    // get data from table 'student'
                   JOptionPane.showMessageDialog(this, "<HTML> Đăng Kí thành công <HTML>");
                    stmt.close();
                    conn.close();
                    this.dispose();
                    Login back = new Login();
                 } 
                 catch(Exception E)
                 {
              
                    // E.printStackTrace();
                 }
    } 
    
        
    }
    private boolean checkPass(){
        if (String.valueOf(passwordField.getPassword()).equals(String.valueOf(repasswordField.getPassword()))) return true;
        else return false;
    }
    private boolean checkEmail(){
      String email=  EmailtextField.getText();
        if (email.substring(email.length()-10, email.length()).equals("@gmail.com")) return true;
        else return false;
    }
}
