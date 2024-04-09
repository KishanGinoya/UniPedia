package Code;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Student {
    
    public void insertUpdateDeleteStudent(char operation, Integer id, String sname, String fname, int eno, String gender,
        String bdate, String email, String mobileno,int sem, String course, String address)
    {
        Connection con = MyConnection.getConnection();

        // 'i' for insert
        if(operation == 'i')
        {
           try{
               PreparedStatement ps = con.prepareStatement("INSERT INTO student (StudentName, FatherName, EnrollmentNo, Gender, DOB, Email, MobileNo,Semester, Course, Address) VALUES (?,?,?,?,?,?,?,?,?,?)");
               ps.setString(1, sname);
               ps.setString(2, fname);
               ps.setInt(3, eno);
               ps.setString(4, gender);
               ps.setString(5, bdate);
               ps.setString(6, email);
               ps.setString(7, mobileno);
               ps.setInt(8, sem);
               ps.setString(9, course);
               ps.setString(10, address);

               if(ps.executeUpdate() > 0)
               {
                   String message = "<html><b style='font-size: 12px;'>New Student Added Successfully...</b></html>";
                   JOptionPane.showMessageDialog(null, message);
               }
           }
           catch(SQLException e)
           {
              String errorMessage = "<html><b style='font-size: 12px;'>"+e.getMessage()+"</b></html>";
              JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
           }
        }
        
        // 'u' for update
        if(operation == 'u')
        {
           try{
               PreparedStatement ps = con.prepareStatement("UPDATE student SET StudentName=?, FatherName=?, EnrollmentNo=?, Gender=?, DOB=?, Email=?, MobileNo=?, Semester=?, Course=?, Address=? WHERE id=?");
               ps.setString(1, sname);
               ps.setString(2, fname);
               ps.setInt(3, eno);
               ps.setString(4, gender);
               ps.setString(5, bdate);
               ps.setString(6, email);
               ps.setString(7, mobileno);
               ps.setInt(8, sem);
               ps.setString(9, course);
               ps.setString(10, address);
               ps.setInt(11, id);

                if (ps.executeUpdate() > 0)
                {
                    String message = "<html><b style='font-size: 12px;'>Student Data Updated Successfully...</b></html>";
                    JOptionPane.showMessageDialog(null, message);
                }
           }
           catch(SQLException e)
           {
              String errorMessage = "<html><b style='font-size: 12px;'>"+e.getMessage()+"</b></html>";
              JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
           }
        }
        
         // 'd' for delete
        if(operation == 'd')
        {
           try{
               PreparedStatement ps = con.prepareStatement("Delete FROM student WHERE id=?");
               ps.setInt(1, id);

                if (ps.executeUpdate() > 0)
                {
                    String message = "<html><b style='font-size: 12px;'>Student Data Deleted Successfully...</b></html>";
                    JOptionPane.showMessageDialog(null, message);
                }
           }
           catch(SQLException e)
           {
              String errorMessage = "<html><b style='font-size: 12px;'>"+e.getMessage()+"</b></html>";
              JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
           }
        }
        
    }

    public void fillStudentJTable(JTable table, String valueToSearch)
    {
         Connection con = MyConnection.getConnection();
         //Search Record
         try{
               String query = "SELECT * FROM student WHERE CONCAT(id, ' ', StudentName, ' ', FatherName, ' ', EnrollmentNo, ' ', Gender, ' ', DOB, ' ', Email, ' ', MobileNo, ' ', Semester, ' ', Course, ' ', Address) LIKE ?";

               // String query = "SELECT * FROM student";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, "%" + valueToSearch + "%");
         
                ResultSet rs = ps.executeQuery();
                
                DefaultTableModel model = (DefaultTableModel)table.getModel();
               
                // Add columns to the model
                /*
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    model.addColumn(rs.getMetaData().getColumnName(i));
                }*/
                
                /*
                Object[] row;
                
                while(rs.next()){
                    row = new Object[10];
                    row[0] = rs.getInt(1);
                    row[1] = rs.getString(2);
                    row[2] = rs.getString(3);
                    row[3] = rs.getInt(4);
                    row[4] = rs.getString(5);
                    row[5] = rs.getString(6);
                    row[6] = rs.getString(7);
                    row[7] = rs.getString(8);
                    row[8] = rs.getString(9);
                    row[9] = rs.getString(10);
                
                 model.addRow(row);
                }*/
                
                // Add rows to the model
                
                while (rs.next()) {
                    Object[] row = new Object[rs.getMetaData().getColumnCount()];
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        row[i - 1] = rs.getObject(i);
                    }
                    model.addRow(row);
                }

                // Set the model to the JTable
               //table.setModel(model);

           }
           catch(SQLException e)
           {
              String errorMessage = "<html><b style='font-size: 12px;'>"+e.getMessage()+"</b></html>";
              JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
           }
    }
    
    public boolean isEmailExist(Integer id, String email) {

        boolean isExist = false;
        Connection con = MyConnection.getConnection();

        PreparedStatement ps;
        try {
            
            if(id == null)
            {
                ps = con.prepareStatement("select 1 FROM student where Email=?");
                ps.setString(1, email);
            }
            else
            {
                ps = con.prepareStatement("select 1 FROM student where Email=? AND id <> ?");
                ps.setString(1, email);
                ps.setInt(2, id);
            }
         
            ResultSet rs = ps.executeQuery();

            isExist = rs.next();

        } catch (SQLException e) {

            String errorMessage = "<html><b style='font-size: 12px;'>Your E-mail Already Registered..</b></html>";
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
        }

        return isExist;
    }
            
     public boolean isMobileNoExist(Integer id, String mobileno) {

        boolean isExist = false;
        Connection con = MyConnection.getConnection();

        try {
            PreparedStatement ps;
            if(id == null)
            {
                ps = con.prepareStatement("select 1 FROM student where MobileNo=?");
                ps.setString(1, mobileno);
            }
            else
            {
                ps = con.prepareStatement("select 1 FROM student where MobileNo=? AND id <> ?");
                ps.setString(1, mobileno);
                ps.setInt(2, id);
            }
          
            ResultSet rs = ps.executeQuery();

            isExist = rs.next();

        } catch (SQLException e) {

            String errorMessage = "<html><b style='font-size: 12px;'>Your Mobile No is Already Registered..</b></html>";
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
        }

        return isExist;
    }

    public boolean isEnrollmentNoExist(Integer id, Integer eno) {

        boolean isExist = false;
        Connection con = MyConnection.getConnection();

        try {
            PreparedStatement ps;
            if(id == null)
            {
                ps = con.prepareStatement("select 1 FROM student where EnrollmentNo=?");
                ps.setInt(1, eno);
            }
            else
            {
                ps = con.prepareStatement("select 1 FROM student where EnrollmentNo=? AND id <> ?");
                ps.setInt(1, eno);
                ps.setInt(2, id);
            }
          
            ResultSet rs = ps.executeQuery();

            isExist = rs.next();

        } catch (SQLException e) {

            String errorMessage = "<html><b style='font-size: 12px;'>Your Mobile No is Already Registered..</b></html>";
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
        }

        return isExist;
    }

}
