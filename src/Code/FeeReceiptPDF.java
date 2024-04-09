package Code;

import java.awt.Desktop;
import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FeeReceiptPDF {
    
        private static final Font TITLE_FONT = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
        private static final Font SUBTITLE_FONT = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        private static final Font NORMAL_FONT = new Font(Font.FontFamily.HELVETICA, 12);
        private static final Font BOLD_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

    
        public void generatePDFReceipt(String sname, int eno, String program, int sem, String receiptno,
                                    String receiptdate, String paymenttype, int total, String words) {
        Document document = new Document();

        try {
            // Set the file path and name
            // Get the downloads folder path
            String downloadsPath = System.getProperty("user.home") + "/Downloads/";

            // Set the file path and name to the downloads folder
            String filePath = downloadsPath + "receipt_" + receiptno + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

//            String filePath = "receipt_" + receiptno + ".pdf";
//            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            
            //String filePath = "receipt_" + receiptno + ".pdf";
            //PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

             // Set the file path and name
            //String filePath2 = "receipt_" + receiptno + ".pdf";
            //PdfWriter.getInstance(document, new FileOutputStream(filePath2));
            //document.open();

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
        
             // Add title with custom color
            addTitle(document, "Student Fee Receipt", new BaseColor(0, 128, 0));

            // Add content to the PDF using a table
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(80);
            table.setSpacingBefore(20f);
            table.setSpacingAfter(20f);

            // Add receipt details to the table
            addTableHeader(table, "Receipt Details", 30f);
            addTableRow(table, "Receipt No", receiptno, 30f);
            addTableRow(table, "Date", receiptdate, 30f);

            // Add student information to the table
            addTableHeader(table, "Student Information", 30f);
            addTableRow(table, "Name", sname, 30f);
            addTableRow(table, "Enrollment No", Integer.toString(eno), 30f);
            addTableRow(table, "Program", program, 30f);
            addTableRow(table, "Semester", Integer.toString(sem), 30f);

            // Add payment information to the table
            addTableHeader(table, "Payment Information", 30f);
            addTableRow(table, "Payment Type", paymenttype, 30f);
            addTableRow(table, "Total Amount", Integer.toString(total), 30f);
            addTableRow(table, "Amount in Words", words, 30f);

            document.add(table);

            // Add closing statement
            Paragraph closingStatement = new Paragraph("Thank you for your payment. We appreciate your prompt attention to this matter.");
            closingStatement.setAlignment(Element.ALIGN_CENTER);
            addEmptyLine(document, 1);
            document.add(closingStatement);

            // Close the document
            document.close();

            // Optionally open the PDF file
            Desktop.getDesktop().open(new File(filePath));

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

    private static void addTableHeader(PdfPTable table, String header,  float cellHeight) {
        PdfPCell cell = new PdfPCell(new Phrase(header, SUBTITLE_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setMinimumHeight(cellHeight);
        table.addCell(cell);
    }

    private static void addTableRow(PdfPTable table, String key, String value,  float cellHeight) {
       
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

    private static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }
}
        
