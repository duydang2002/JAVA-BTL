package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.*;
// import java.io.FileOutputStream;
import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.xdevapi.PreparableStatement;

public class ReadImage {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/javadatabase";
        String user = "root";
        String password = "";
        String SqlSelect = "Select * from `tblnews` where `NewsName` = ? ";

        try (Connection conn = DriverManager.getConnection(url, user, password)){
            // SqlSelect.lastIndexOf("\\")
            File f= new File("C:\\Users\\T590\\OneDrive\\Documents\\Java\\"+"Image.png");
            FileOutputStream output = new FileOutputStream(f);

            PreparedStatement Ps = conn.prepareStatement(SqlSelect);
            Ps.setString(1,"initialize");
            ResultSet rs = Ps.executeQuery();
            while (rs.next()){
                InputStream input = rs.getBinaryStream  ("NewsImage");
                System.out.println("Reading NewsImage form database");
                System.out.println(SqlSelect);
                byte [] buffer = new byte[1024];
                while (input.read(buffer)>0){
                    output.write(buffer);
                }
                System.out.println("\n saved to file" + f.getAbsolutePath());
                System.out.println("Completed");
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
