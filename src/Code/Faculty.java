package Code;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Faculty {
    
     public void insertUpdateDeleteFaculty(char operation, Integer id, String program, String course, String tname, String fname, String gender,
        String bdate, String email, String mobileno, String address)
     {
        Connection con = MyConnection.getConnection();
         
        PreparedStatement ps;    

        //i for insert
        if (operation == 'i') {
            try {
                ps = con.prepareStatement("insert into faculty(Program, Course, FacultyName, FatherName, Gender, DOB, Email, MobileNo, Address) values(?,?,?,?,?,?,?,?,?)");
                ps.setString(1, program);
                ps.setString(2, course);
                ps.setString(3, tname);
                ps.setString(4, fname);
                ps.setString(5, gender);
                ps.setString(6, bdate);
                ps.setString(7, email);
                ps.setString(8, mobileno);
                ps.setString(9, address);

                if (ps.executeUpdate() > 0) {
                    String message = "<html><b style='font-size: 12px;'>New Faculty Added Successfully...</b></html>";
                    JOptionPane.showMessageDialog(null, message);
                }
            } catch (SQLException e) {
                String errorMessage = "<html><b style='font-size: 12px;'>" + e.getMessage() + "</b></html>";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        // 'u' for update
        if (operation == 'u') {
            try {
                ps = con.prepareStatement("UPDATE faculty SET Program=?, Course=?, FacultyName=?, FatherName=?, Gender=?, DOB=?, Email=?, MobileNo=?, Address=? WHERE id=?");
                ps.setString(1, program);
                ps.setString(2, course);
                ps.setString(3, tname);
                ps.setString(4, fname);
                ps.setString(5, gender);
                ps.setString(6, bdate);
                ps.setString(7, email);
                ps.setString(8, mobileno);
                ps.setString(9, address);
                ps.setInt(10, id);

                if (ps.executeUpdate() > 0) {
                    String message = "<html><b style='font-size: 12px;'>Faculty Data Updated Successfully...</b></html>";
                    JOptionPane.showMessageDialog(null, message);
                }
            } catch (SQLException e) {
                String errorMessage = "<html><b style='font-size: 12px;'>" + e.getMessage() + "</b></html>";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
            }
        }


          // 'd' for delete
        if(operation == 'd')
        {
           try{
               ps = con.prepareStatement("Delete FROM faculty WHERE id=?");
               ps.setInt(1, id);

                if (ps.executeUpdate() > 0)
                {
                    String message = "<html><b style='font-size: 12px;'>Faculty Data Deleted Successfully...</b></html>";
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
     
     
      public void fillFacultyJTable(JTable table, String valueToSearch)
    {
         Connection con = MyConnection.getConnection();
         //Search Record
         try{
               String query = "SELECT * FROM faculty WHERE CONCAT(id, ' ', Program, ' ', Course, ' ', FacultyName, ' ', FatherName, ' ', Gender, ' ', DOB, ' ', Email, ' ', MobileNo, ' ', Address) LIKE ?";

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
                
                  // Clear existing rows from the table
                    //model.setRowCount(0);
        
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
    
     public boolean isCourseAssigned(Integer id, String course) {
          boolean isExist = false;
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;

        try {
//            String query = "SELECT * FROM faculty WHERE Course = ?";
//            ps = con.prepareStatement(query);
//            ps.setString(1, course);
//
//            ResultSet rs = ps.executeQuery();
//            return rs.next(); // If a row is returned, the course is already assigned

            if (id == null)
            {
                ps = con.prepareStatement("SELECT 1 FROM faculty WHERE Course = ?");
                ps.setString(1, course);
            } 
            else
            {
                ps = con.prepareStatement("SELECT 1 FROM faculty WHERE Course = ? AND id <> ?");
                ps.setString(1, course);
                ps.setInt(2, id);
            }

            ResultSet rs = ps.executeQuery();

            isExist = rs.next();
        }
        catch(SQLException e)
        {
              String errorMessage = "<html><b style='font-size: 12px;'>"+e.getMessage()+"</b></html>";
              JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
              
        }
        return isExist;
}
      
     public boolean isEmailExist(Integer id, String email) {

        boolean isExist = false;
        Connection con = MyConnection.getConnection();

        PreparedStatement ps;
        try {
            
            if(id == null)
            {
                ps = con.prepareStatement("select 1 FROM faculty where Email=?");
                ps.setString(1, email);
            }
            else
            {
                ps = con.prepareStatement("select 1 FROM faculty where Email=? AND id <> ?");
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
                ps = con.prepareStatement("select 1 FROM faculty where MobileNo=?");
                ps.setString(1, mobileno);
            }
            else
            {
                ps = con.prepareStatement("select 1 FROM faculty where MobileNo=? AND id <> ?");
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

   
}
