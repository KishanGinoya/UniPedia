package Code;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Score {

    public int sem = 0;
    public String program = "";
    
    public String c1="",c2="",c3="",c4="";

    public void insertUpdateDeleteScore(char operation, Integer id, String program, Integer sem, Integer enr, Integer c1m, Integer c2m, Integer c3m, Integer c4m) {

        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        if (operation == 'i') {
            try {
                ps = con.prepareStatement("insert into score(program,semester,enrollementno,c1m,c2m,c3m,c4m) values(?,?,?,?,?,?,?)");
                ps.setString(1, program);
                ps.setInt(2, sem);
                ps.setInt(3, enr);
                ps.setInt(4, c1m);
                ps.setInt(5, c2m);
                ps.setInt(6, c3m);
                ps.setInt(7, c4m);

                if (ps.executeUpdate() > 0) {
                    String message = "<html><b style='font-size: 12px;'>Score Added Successfully...</b></html>";
                    JOptionPane.showMessageDialog(null, message);
                }
            } catch (SQLException e) {
                String errorMessage = "<html><b style='font-size: 12px;'>" + e.getMessage() + "</b></html>";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (operation == 'u') {
            try {
                ps = con.prepareStatement("update score set program=?,semester=?,enrollementno=?,c1m=?,c2m=?,c3m=?,c4m=? where id=?");
                ps.setString(1, program);
                ps.setInt(2, sem);
                ps.setInt(3, enr);
                ps.setInt(4, c1m);
                ps.setInt(5, c2m);
                ps.setInt(6, c3m);
                ps.setInt(7, c4m);
                ps.setInt(8, id);

                if (ps.executeUpdate() > 0) {
                    String message = "<html><b style='font-size: 12px;'>Score Data Updated..</b></html>";
                    JOptionPane.showMessageDialog(null, message);
                }

            } catch (SQLException e) {
                String errorMessage = "<html><b style='font-size: 12px;'>" + e.getMessage() + "</b></html>";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (operation == 'd') {
            try {
                ps = con.prepareStatement("delete from score where id=?");
                ps.setInt(1, id);

//                ps.setInt(1, id);
                if (ps.executeUpdate() > 0) {
                    String message = "<html><b style='font-size: 12px;'>Score Deleted..</b></html>";
                    JOptionPane.showMessageDialog(null, message);
                }
            } catch (SQLException e) {
                String errorMessage = "<html><b style='font-size: 12px;'>" + e.getMessage() + "</b></html>";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
            }
        }

    }

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

    public boolean checkMarksSem(char operation, Integer id, String program, Integer sem, Integer enr, Integer c1m, Integer c2m, Integer c3m, Integer c4m) {
        boolean isExit = false;
        if (operation == 'u') {
            try {
                Connection con = MyConnection.getConnection();

                PreparedStatement pr = con.prepareStatement("select * from score where program=? and semester=? and enrollementno=? and c1m=? and c2m=? and c3m=? and c4m=? AND id <> ?");

                pr.setString(1, program);
                pr.setInt(2, sem);
                pr.setInt(3, enr);
                pr.setInt(4, c1m);
                pr.setInt(5, c2m);
                pr.setInt(6, c3m);
                pr.setInt(7, c4m);
                pr.setInt(8, id);

                ResultSet rs = pr.executeQuery();
                if (rs.next()) {
                    isExit = true;
                }

            } catch (SQLException e) {
                String errorMessage = "<html><b style='font-size: 12px;'>" + e.getMessage() + "</b></html>";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
            }

        }
        if (operation == 'i') {
            try {
                Connection con = MyConnection.getConnection();

                PreparedStatement pr = con.prepareStatement("select * from score where program=? and semester=? and enrollementno=?");

                pr.setString(1, program);
                pr.setInt(2, sem);
                pr.setInt(3, enr);

                ResultSet rs = pr.executeQuery();
                if (rs.next()) {
                    isExit = true;
                }

            } catch (SQLException e) {
                String errorMessage = "<html><b style='font-size: 12px;'>" + e.getMessage() + "</b></html>";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
            }

        }
        return isExit;
    }

    public void fillStudentJTable(JTable table) {
        Connection con = MyConnection.getConnection();

        PreparedStatement ps;

        try {
            ps = con.prepareStatement("select * from score");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            //Add rows to the model
            while (rs.next()) {
                Object[] row = new Object[rs.getMetaData().getColumnCount()];

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }
            //set the model to the JTable
            table.setModel(model);

        } catch (SQLException e) {
            String errorMessage = "<html><b style='font-size: 12px;'>" + e.getMessage() + "</b></html>";
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void getSubject() {
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
    }
    public boolean findCourse(Integer sem,String program) {
        boolean isExist = false;
        try {
            Connection con = MyConnection.getConnection();
            PreparedStatement pr = con.prepareStatement("select * from coursedetails where program=? and semester=?");
            pr.setString(1, program);
            pr.setInt(2, sem);

            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                isExist = true;

            } else {
                String errorMessage = "<html><b style='font-size: 12px;'>Course not provided..</b></html>";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException e) {
            String errorMessage = "<html><b style='font-size: 12px;'>" + e.getMessage() + "</b></html>";
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
        }
        return isExist;
    }

}
