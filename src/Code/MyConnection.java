package Code;

import java.sql.*;
import javax.swing.JOptionPane;

public class MyConnection {
    
    public static Connection getConnection()
    {
        Connection con = null;
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/javaproject","root","");
        }
        catch(Exception e)
        {
            //System.out.println(e.getMessage());
            String errorMessage = "<html><b style='font-size: 12px;'>Failed to connect to database...</b></html>";
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
        }
        
        return con;
    }
}
