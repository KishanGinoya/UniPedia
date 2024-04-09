package Code;

import java.awt.Desktop;
import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.*;
import javax.swing.JTable;

public class MarksheetPDF {
    
    public static final Font TITLE_FONT = new Font(FontFamily.HELVETICA, 24, Font.BOLD, new BaseColor(0, 128, 0));
    private static final Font SUBTITLE_FONT = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
    private static final Font NORMAL_FONT = new Font(Font.FontFamily.HELVETICA, 12);
    private static final Font BOLD_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

    public void generatePDFMarksheet(int enrollmentNo,String sname, String fname, String gender, String mobileno, String email,
        String program, int sem, int totalMarks, char finalGrade, JTable table) {
        Document document = new Document();

    try {
        // Get the downloads folder path
        String downloadsPath = System.getProperty("user.home") + "/Downloads/";

        // Set the file path and name to the downloads folder
        String fileName = downloadsPath + "marksheet_" + sname + ".pdf";
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));

        //String fileName = "marksheet_" + sname + ".pdf";
        //PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
        
        document.open();
        
        // Add header with logo and title
        PdfPTable header = new PdfPTable(1);

        Image logo = Image.getInstance("D:\\2024_BCA6C3X022_UniPedia_JAVA\\Code\\E-Learning App\\src\\icons\\Untitled.jpg");
        PdfPCell logoCell = new PdfPCell(logo, true);
        logoCell.setBorder(Rectangle.NO_BORDER);
        logoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        logoCell.setFixedHeight(60); // Set the height of the cell
        header.addCell(logoCell);
           
        // Add header table to the document
        document.add(header);

        // Add title in center
        Paragraph title = new Paragraph("Student Mark Sheet", TITLE_FONT);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
            
         // Add enrollment number
        //document.add(new Paragraph("Enrollment No: " + enrollmentNo));
        
        // Add content to the PDF using a table
        PdfPTable infoTable = new PdfPTable(2);
        infoTable.setWidthPercentage(80);
        infoTable.setSpacingBefore(20f);
        infoTable.setSpacingAfter(20f);

        // Add student information to the table
        addTableHeader(infoTable, "Student Information", 30f);
        addTableRow(infoTable, "Enrollment No", Integer.toString(enrollmentNo), 30f);
        addTableRow(infoTable, "Name", sname, 30f);
        addTableRow(infoTable, "Father's Name", fname, 30f);
        addTableRow(infoTable, "Gender", gender, 30f);
        addTableRow(infoTable, "Mobile No", mobileno, 30f);
        addTableRow(infoTable, "Email", email, 30f);
        addTableRow(infoTable, "Program", program, 30f);
        addTableRow(infoTable, "Semester", Integer.toString(sem), 30f);

        document.add(infoTable);

        // Add mark sheet details to the PDF using the provided JTable
        PdfPTable markSheetTable = getJTableAsPdfTable(table);
        markSheetTable.setWidthPercentage(100); // Set table width to 100%

       
              
        // Set the height of all cells in the table
        for (int i = 0; i < markSheetTable.getRows().size(); i++) {
            for (int j = 0; j < markSheetTable.getNumberOfColumns(); j++) {
                PdfPCell cell = markSheetTable.getRow(i).getCells()[j];
                cell.setFixedHeight(35); // Set the height of the cell to 35 points
            }
        }
    
        // Iterate over each cell in all columns
        for (int i = 0; i < markSheetTable.getRows().size(); i++) {
            for (int j = 0; j < markSheetTable.getNumberOfColumns(); j++) {
                PdfPCell cell = markSheetTable.getRow(i).getCells()[j];
                cell.setHorizontalAlignment(Element.ALIGN_CENTER); // Set horizontal alignment to center
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Set vertical alignment to middle
            }
        }

        // Adjust the width of the subject column (assuming it's the first column)
        float[] columnWidths = {3, 1, 1, 1, 1}; // Adjust the width of the first column (subject column)
        markSheetTable.setWidths(columnWidths);

        // Add "Mark Sheet Details" text with the specified font
        Font markSheetDetailsFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Paragraph markSheetDetails = new Paragraph("Mark Sheet Details:", markSheetDetailsFont);
        markSheetDetails.setAlignment(Element.ALIGN_CENTER);
        document.add(markSheetDetails);

        // Add some space between the main table and the "Total Marks" and "Final Grade" section
        addEmptyLine(document, 1); // Add one empty line for spacing

        // Add the mark sheet table to the document
        document.add(markSheetTable);

       // Add some space between the main table and the "Total Marks" and "Final Grade" section
        addEmptyLine(document, 1); // Add two empty lines for spacing

        // Create a table for Total Marks and Final Grade
        PdfPTable gradeTableNew = new PdfPTable(2);
        gradeTableNew.setWidthPercentage(100);

        Font labelFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD); // Font for labels
        
        // Add Total Marks and its value to the first cell
        PdfPCell totalMarksCellNew = new PdfPCell(new Phrase("Total Marks: " + totalMarks + " / 400", labelFont));
        totalMarksCellNew.setBorder(Rectangle.NO_BORDER);
        totalMarksCellNew.setPaddingLeft(40);
        gradeTableNew.addCell(totalMarksCellNew);

        // Add Final Grade and its value to the second cell
        PdfPCell finalGradeCellNew = new PdfPCell(new Phrase("Final Grade: " + finalGrade, labelFont));
        finalGradeCellNew.setHorizontalAlignment(Element.ALIGN_RIGHT);
        finalGradeCellNew.setBorder(Rectangle.NO_BORDER);
        finalGradeCellNew.setPaddingRight(30);
        gradeTableNew.addCell(finalGradeCellNew);

        // Add the grade table to the document
        document.add(gradeTableNew);

        // Close the document
        document.close();

        // Open the PDF file
        Desktop.getDesktop().open(new File(fileName));

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void addTitle(Document document, String title, BaseColor color) throws DocumentException {
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD, color);
        Paragraph paragraph = new Paragraph(title, titleFont);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        addEmptyLine(document, 1);
        document.add(paragraph);
        addEmptyLine(document, 1);
    }

    private static void addTableHeader(PdfPTable table, String header, float cellHeight) {
        PdfPCell cell = new PdfPCell(new Phrase(header, SUBTITLE_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setMinimumHeight(cellHeight);
        table.addCell(cell);
    }

    private static void addTableRow(PdfPTable table, String key, String value, float cellHeight) {
        PdfPCell keyCell = new PdfPCell(new Phrase(key, BOLD_FONT));
        keyCell.setMinimumHeight(20f); // Adjust height as needed
        keyCell.setHorizontalAlignment(Element.ALIGN_LEFT); // Align key text to the left
        keyCell.setPaddingLeft(5f); // Adjust left padding as needed
        keyCell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Vertically align text to the middle
        keyCell.setMinimumHeight(cellHeight);
        table.addCell(keyCell);

        PdfPCell valueCell = new PdfPCell(new Phrase(value, NORMAL_FONT));
        valueCell.setMinimumHeight(20f); // Adjust height as needed
        valueCell.setHorizontalAlignment(Element.ALIGN_LEFT); // Align value text to the left
        valueCell.setPaddingLeft(5f); // Adjust left padding as needed
        valueCell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Vertically align text to the middle
        valueCell.setMinimumHeight(cellHeight);
        table.addCell(valueCell);
    }


    private static void addEmptyLine(Document document, float spacing) throws DocumentException {
        Paragraph paragraph = new Paragraph(" ");
        paragraph.setSpacingBefore(spacing * 10);
        document.add(paragraph);
    }

    private static PdfPTable getJTableAsPdfTable(JTable jTable) {
        PdfPTable pdfTable = new PdfPTable(jTable.getColumnCount());

        // Add headers
        for (int i = 0; i < jTable.getColumnCount(); i++) {
            pdfTable.addCell(new Phrase(jTable.getColumnName(i), BOLD_FONT)); // Set header font
        }

        // Add content
        for (int row = 0; row < jTable.getRowCount(); row++) {
            for (int column = 0; column < jTable.getColumnCount(); column++) {
                pdfTable.addCell(new Phrase(jTable.getValueAt(row, column).toString(), NORMAL_FONT)); // Set content font
            }
        }

        return pdfTable;
    }
    
    
}

