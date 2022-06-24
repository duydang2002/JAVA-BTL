import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.*;
public class Customer extends JFrame implements ActionListener  {
    JLabel name = new JLabel("Name :");
    JTextField nameTextField = new JTextField(30);
    JLabel addressJLabel = new JLabel("Address");
    JTextField addressTextField = new JTextField(30);
    JLabel amount = new JLabel("Money paid:");
    JTextField amounTextField =new JTextField(30);
    JLabel money = new JLabel("Money left:");
    JTextField moneyTextField =new JTextField(30);
    JButton addMoney = new JButton("Deposit");
    JFrame addmoneyFrame = new JFrame("Depositing: ");
    JLabel addAmount = new JLabel("Add Amount");
    JTextField addAmountField = new JTextField(30);
    String email="";
    public Customer(User user){
        email=user.email;
        this.setLayout(new GridLayout(8,1));
        addMoney.addActionListener(this);
        nameTextField.setText(user.name);
        nameTextField.setEditable(false);
        addressTextField.setText(user.address);
        addressTextField.setEditable(false);
        amounTextField.setText(String.valueOf(user.Paid)+"VND");
        amounTextField.setEditable(false);
        moneyTextField.setText(String.valueOf(user.Money+"VND"));
        moneyTextField.setEditable(false);
        this.add(name);
        this.add(nameTextField);
        this.add(addressJLabel);
        this.add(addressTextField);
        this.add(amount);
        this.add(amounTextField);
        this.add(money);
        this.add(moneyTextField);
        this.setVisible(true);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    public void actionPerformed(ActionEvent e)  {
        String url = "jdbc:mysql://localhost:3306/javadatabase";
            String user = "root";
            String password = "";
            User user2 = null;
            String sql = "update `tblseller` set `Tiền đã nạp`=? where email=?";
        if (e.getSource()==addMoney){
            try (Connection conn = DriverManager.getConnection(url, user, password)){
                PreparedStatement PS = conn.prepareStatement(sql);
                PS.setInt(1, Integer.parseInt( addAmountField.getText()));
                PS.setString(2,email);
            }
            catch(Exception E){

            }
        }
    }
    
}
