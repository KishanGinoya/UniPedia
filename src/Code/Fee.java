package Code;

import java.sql.*;
import javax.swing.JOptionPane;

public class Fee {
    public int sem = 0;
    public String program = "";
    public void insertFee(char operation, Integer id, int eno, String sname, int sem, String program,
        String ReceiptNo, String ReceiptDate, String PaymentType, int fee, String words)
    {
        Connection con = MyConnection.getConnection();
         
        PreparedStatement ps; 
        
        if (operation == 'i') {
            try {
                
                if (enrollmentExists(eno)) {
                    String message = "<html><b style='font-size: 12px;'>Enrollment No. already exists!</b></html>";
                    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
                    return; // Exit the method if enrollment exists
                }
                 
                ps = con.prepareStatement("insert into fee(EnrollmentNo, StudentName, Semester, Program, ReceiptNo, ReceiptDate, PaymentType, TotalFee, Words) values(?,?,?,?,?,?,?,?,?)");
                ps.setInt(1, eno);
                ps.setString(2, sname);
                ps.setInt(3, sem);
                ps.setString(4, program);
                ps.setString(5, ReceiptNo);
                ps.setString(6, ReceiptDate);
                ps.setString(7, PaymentType);
                ps.setInt(8, fee);
                ps.setString(9, words);

                if (ps.executeUpdate() > 0) {
                    String message = "<html><b style='font-size: 12px;'>Fee Successfully paid...</b></html>";
                    JOptionPane.showMessageDialog(null, message);
                }
            } catch (SQLException e) {
                String errorMessage = "<html><b style='font-size: 12px;'>" + e.getMessage() + "</b></html>";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    public boolean enrollmentExists(int enrollmentNo) 
    {
        Connection con = MyConnection.getConnection();
        String query = "SELECT EnrollmentNo FROM fee WHERE EnrollmentNo = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, enrollmentNo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // If a record is found, enrollment exists
            }
        }
        catch (SQLException e)
        {
                String errorMessage = "<html><b style='font-size: 12px;'>" + e.getMessage() + "</b></html>";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
                 return false;
        }
    }
}
