package Login;
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
public class UpdateImage extends JFrame implements ActionListener {
    JTextField txtFileName= new JTextField(100);
    JLabel lbImage = new JLabel("");
    JButton attachButton = new JButton("Attach");
    JButton summitButton = new JButton("Submit");
    static File f;
    public UpdateImage(){
        txtFileName.setText("");
        lbImage.setIcon(null);
        attachButton.addActionListener(this);
        summitButton.addActionListener(this);
        this.setLayout(new GridLayout(4,1));
        this.add(lbImage);
        this.add(txtFileName);
        this.add(attachButton);
        this.add(summitButton);
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
        Image getAbsolutePath = null;
        ImageIcon icon = new ImageIcon(fileName);
        Image image = icon.getImage().getScaledInstance(lbImage.getWidth(), lbImage.getHeight(), Image.SCALE_SMOOTH);
        lbImage.setIcon(icon);
        }
        if (e.getSource()== summitButton){
            String url = "jdbc:mysql://localhost:3306/javadatabase";
            String user = "root";
            String password = "";
            try(Connection conn= DriverManager.getConnection(url, user, password)){
                System.out.println("ket noi thanh cong");
                System.out.println(conn.getCatalog());
                String Image = txtFileName.getText();
                Image = Image.replace("//", "////");
                String upDateImageSql = " INSERT INTO `tblnews`(`NewsImage`) VALUES (?)";
                PreparedStatement Ps = conn.prepareStatement(upDateImageSql);
                FileInputStream fis = new FileInputStream(f);
                Ps.setBinaryStream(1, fis,fis.available());
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
