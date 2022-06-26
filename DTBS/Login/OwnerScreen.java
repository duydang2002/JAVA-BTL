package Login;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class OwnerScreen extends JFrame {
    private JLabel st = new JLabel("Updating!!!");
    
public OwnerScreen(Owner o){
    this.add(st);
    this.setVisible(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
}
