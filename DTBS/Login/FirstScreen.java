import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class FirstScreen extends JFrame implements ActionListener{
JLabel choiceJLabel = new JLabel("You Are :");
JButton ownerButton = new JButton("Owner");
JButton customerButton = new JButton("Customer");

public FirstScreen(){
    this.setLayout(new GridLayout(3,1));
    ownerButton.addActionListener(this);
    customerButton.addActionListener(this);
    this.add(choiceJLabel);
    this.add(ownerButton);
    this.add(customerButton);
    this.setVisible(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();    
}

@Override
public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    if (e.getSource()==ownerButton){

    }
    if (e.getSource()== customerButton){
        LoginCustomer a = new LoginCustomer();
    }
    
}

}