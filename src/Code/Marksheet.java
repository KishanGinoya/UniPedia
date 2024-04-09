package Code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class Marksheet {

    public int sem = 0;
    public String program = "";
    public String sname = "";
    public String fname = "";
    public String gender = "";
    public String mobileno = "";
    public String email = "";
    public int enr = 0;

    public boolean findId(Integer enr) {
        boolean isExist = false;
        try {
            Connection con = MyConnection.getConnection();
            PreparedStatement pr = con.prepareStatement("select * from student where EnrollmentNo=?");
            pr.setInt(1, enr);

            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                isExist = true;
                sem = rs.getInt("Semester");
                program = rs.getString("Course");
                sname = rs.getString("StudentName");
                fname = rs.getString("FatherName");
                gender = rs.getString("Gender");
                mobileno = rs.getString("MobileNo");
                email = rs.getString("Email");
                this.enr = rs.getInt("EnrollmentNo");
            } else {
                String errorMessage = "<html><b style='font-size: 12px;'>Enrollment No. Doesn't exist...</b></html>";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException e) {
            String errorMessage = "<html><b style='font-size: 12px;'>" + e.getMessage() + "</b></html>";
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
        }
        return isExist;
    }

    public boolean findScoreId(Integer enr) {
        boolean isExist = false;
        try {
            Connection con = MyConnection.getConnection();
            PreparedStatement pr = con.prepareStatement("select * from score where enrollementno=?");
            pr.setInt(1, enr);

            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                isExist = true;

            } else {
                String errorMessage = "<html><b style='font-size: 12px;'>Students Marks not enter..</b></html>";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException e) {
            String errorMessage = "<html><b style='font-size: 12px;'>" + e.getMessage() + "</b></html>";
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
        }
        return isExist;
    }
    public boolean findMarksheetId(Integer enr) {
        boolean isExist = false;
        try {
            Connection con = MyConnection.getConnection();
            PreparedStatement pr = con.prepareStatement("select * from marksheet where enrollmentno=?");
            pr.setInt(1, enr);

            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                isExist = true;

            }

        } catch (SQLException e) {
            String errorMessage = "<html><b style='font-size: 12px;'>" + e.getMessage() + "</b></html>";
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
        }
        return isExist;
    }
    public int totalm = 100,passm = 40,totalOfMarks = 0;
    
    String c1 = "",c2 = "",c3 = "",c4 = "";
    int mc1 = 0,mc2 = 0,mc3 = 0,mc4 = 0;
    public char fGrade=0;

    public void fillMarkSheetJTable(JTable table) {
        Connection con = MyConnection.getConnection();

        PreparedStatement ps;

        try {

            ps = con.prepareStatement("select * from coursedetails where program=? and semester=?");
            ps.setString(1, program);
            ps.setInt(2, sem);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c1 = rs.getString("c1");
                c2 = rs.getString("c2");
                c3 = rs.getString("c3");
                c4 = rs.getString("c4");
            }
        } catch (SQLException e) {
            String errorMessage = "<html><b style='font-size: 12px;'>" + e.getMessage() + "</b></html>";
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
        }
        try {
            PreparedStatement ps2;
            ps2 = con.prepareStatement("select * from score where enrollementno=?");
            ps2.setInt(1, enr);

            ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()) {
                mc1 = rs2.getInt("c1m");
                mc2 = rs2.getInt("c2m");
                mc3 = rs2.getInt("c3m");
                mc4 = rs2.getInt("c4m");
            }
        } catch (SQLException e) {
            String errorMessage = "<html><b style='font-size: 12px;'>" + e.getMessage() + "</b></html>";
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
        }
        totalOfMarks = mc1 + mc2 + mc3 + mc4;
        int pr=totalOfMarks/4;
        
        fGrade=getGrade(pr);
        
        // Check if any subject grade is 'R', then set final grade to 'R'
        if (getGrade(mc1) == 'R' || getGrade(mc2) == 'R' || getGrade(mc3) == 'R' || getGrade(mc4) == 'R') {
            fGrade = 'R';
        }
    
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{c1, totalm, passm, mc1, getGrade(mc1)});
        model.addRow(new Object[]{c2, totalm, passm, mc2, getGrade(mc2)});
        model.addRow(new Object[]{c3, totalm, passm, mc3, getGrade(mc3)});
        model.addRow(new Object[]{c4, totalm, passm, mc4, getGrade(mc4)});
        table.setModel(model);
    }

    public char getGrade(int mark) {
        char grade = 0;
        if (mark < 40) {
            grade = 'R';
        } else if (mark >= 40 && mark < 60) {
            grade = 'D';
        } else if (mark >= 60 && mark < 80) {
            grade = 'C';
        } else if (mark >= 80 && mark < 90) {
            grade = 'B';
        } else if (mark >= 90 && mark < 100) {
            grade = 'A';
        } 

        return grade;
    }
}
