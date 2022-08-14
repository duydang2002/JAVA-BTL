package Login;
import javax.swing.*;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.*;
import java.sql.*;
public class chat{
public static void main(String[] args) {
    String url = "jdbc:mysql://localhost:3306/javadatabase";
    String user = "root";
    String password = "";
    String sql = "SELECT  `cusMessage`, `ID`, `Sender` FROM `tblchat` WHERE `Tên Đăng Nhập` = ?";
    String sqlChat = "Insert Into `tblchat`(`Tên đăng nhập`, `cusMessage`, `Sender`) VALUES (?,?,?)";
    try (Connection conn = DriverManager.getConnection(url, user, password)){
        System.out.println("ket noi thanh cong");
        System.out.println(conn.getCatalog());
        PreparedStatement PS = conn.prepareStatement(sql);
        PS.setString(1, "duy");
      
        ResultSet rs = PS.executeQuery();
        while(rs.next()){
            
            if (rs.getString(3).equals("Boss")){
            System.out.println("Boss: "+ rs.getString(1));
        }
            else System.out.println(("Customer: "+ rs.getString(1)));
        }
    
    }
    catch(Exception E){

    }

}
    
}