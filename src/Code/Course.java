package Code;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Course {

    public void insertUpdateDeleteCourse(char operation, Integer id, String program, Integer sem, String c1, String c2, String c3, String c4) {

        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        if (operation == 'i') {
            try {
                ps = con.prepareStatement("insert into coursedetails(program,semester,c1,c2,c3,c4) values(?,?,?,?,?,?)");
                ps.setString(1, program);
                ps.setInt(2, sem);
                ps.setString(3, c1);
                ps.setString(4, c2);
                ps.setString(5, c3);
                ps.setString(6, c4);

                if (ps.executeUpdate() > 0) {
                    String message = "<html><b style='font-size: 12px;'>New Course Added Successfully...</b></html>";
                    JOptionPane.showMessageDialog(null, message);
                }
            } catch (SQLException e) {
                String errorMessage = "<html><b style='font-size: 12px;'>" + e.getMessage() + "</b></html>";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (operation == 'u') {
            try {
                ps = con.prepareStatement("update coursedetails set program=?,semester=?,c1=?,c2=?,c3=?,c4=? where id=?");
                ps.setString(1, program);
                ps.setInt(2, sem);
                ps.setString(3, c1);
                ps.setString(4, c2);
                ps.setString(5, c3);
                ps.setString(6, c4);
                ps.setInt(7, id);

                if (ps.executeUpdate() > 0) {
                    String message = "<html><b style='font-size: 12px;'>Course Data Updated..</b></html>";
                    JOptionPane.showMessageDialog(null, message);
                }

            } catch (SQLException e) {
                String errorMessage = "<html><b style='font-size: 12px;'>" + e.getMessage() + "</b></html>";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (operation == 'd') {
            try {
                ps = con.prepareStatement("delete from coursedetails where id=?");
                ps.setInt(1, id);

//                ps.setInt(1, id);
                if (ps.executeUpdate() > 0) {
                    String message = "<html><b style='font-size: 12px;'>Course Deleted..</b></html>";
                    JOptionPane.showMessageDialog(null, message);
                }
            } catch (SQLException e) {
                String errorMessage = "<html><b style='font-size: 12px;'>" + e.getMessage() + "</b></html>";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

//    public boolean isCourseExist(Integer id, String courseName) {
//
//        boolean isExist = false;
//        Connection con = MyConnection.getConnection();
//
//        try {
//            PreparedStatement ps;
//            if(id == null)
//            {
//                ps = con.prepareStatement("select 1 FROM course where c_name=?");
//                ps.setString(1, courseName);
//            }
//            else
//            {
//                ps = con.prepareStatement("select 1 FROM course where c_name=? AND c_id <> ?");
//                ps.setString(1, courseName);
//                ps.setInt(2, id);
//            }
//           
//
//            ResultSet rs = ps.executeQuery();
//
//            isExist = rs.next();
//
//        } catch (SQLException e) {
//
//            String errorMessage = "<html><b style='font-size: 12px;'>Runtime Error occuring Please check your details</b></html>";
//            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
//        }
//
//        return isExist;
//    }
//    
    public void fillStudentJTable(JTable table) {
        Connection con = MyConnection.getConnection();

        PreparedStatement ps;

        try {
            ps = con.prepareStatement("select * from coursedetails");
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
            String errorMessage = "<html><b style='font-size: 12px;'>Runtime Error occuring Please check your details</b></html>";
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public int getCourseId(String courseLabel) {
        int courseId = 0;

        Connection con = MyConnection.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement("select * from course where c_name=?");
            ps.setString(1, courseLabel);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                courseId = rs.getInt("c_id");
            }

        } catch (SQLException e) {

            String errorMessage = "<html><b style='font-size: 12px;'>Runtime Error occuring Please check your details</b></html>";
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
        }

        return courseId;
    }

//    public void fillCourseCombo(JComboBox combo)
//    {
//      
//         Connection con=MyConnection.getConnection();
//        
//        PreparedStatement ps;
//        
//        try{
//            ps=con.prepareStatement("select * from course");
//            ResultSet rs=ps.executeQuery();
//            
//            
//            //Add rows to the model
//            while(rs.next()){
//                combo.addItem(rs.getString(2));   
//            }
//            
//        }
//        catch(SQLException e){
//            String errorMessage = "<html><b style='font-size: 12px;'>Runtime Error occuring Please check your details</b></html>";
//            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
    public boolean checkProgramSem(char operation, int courseId, String program, Integer sem, String c1, String c2, String c3, String c4) {
        boolean isExit = false;
        if (operation == 'u') {
            try {
                Connection con = MyConnection.getConnection();

                //PreparedStatement pr = con.prepareStatement("select * from coursedetails where program=? and semester=? and c1=? and c2=? and c3=? and c4=?");
                  
                PreparedStatement pr = con.prepareStatement("SELECT * FROM coursedetails WHERE program=? AND semester=? AND id <> ?");
                pr.setString(1, program);
                pr.setInt(2, sem);
                pr.setInt(3, courseId);

                ResultSet rs = pr.executeQuery();
                if (rs.next()) {
                    isExit = true;
                }

            } catch (SQLException e) {
                String errorMessage = "<html><b style='font-size: 12px;'>Runtime Error occuring Please check your details</b></html>";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
            }
            
        }
        if (operation == 'i') {
            try {
                Connection con = MyConnection.getConnection();

                PreparedStatement pr = con.prepareStatement("select * from coursedetails where program=? and semester=?");

                pr.setString(1, program);
                pr.setInt(2, sem);
                
                ResultSet rs = pr.executeQuery();
                if (rs.next()) {
                    isExit = true;
                }

            } catch (SQLException e) {
                String errorMessage = "<html><b style='font-size: 12px;'>Runtime Error occuring Please check your details</b></html>";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
            }
            
        }
        return isExit;
    }

}
