/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import Classes.Appointment;
import com.itextpdf.text.Chunk;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * A handler to export the upcoming events within the following the following
 * week of a {@link Classes.User}
 * @author patricia
 */
public class ExportToPdfHandler {

    final static String PATH = "DocumentsTest.pdf";
    final static String IMG = "src/icons/Mascot500x400.png";
    private static final Font plain_text = FontFactory.getFont(FontFactory.HELVETICA, 12);
    private static final Font headlineFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 36);
    private static final Font secondHeadlineFont = FontFactory.getFont(FontFactory.HELVETICA, 18);
    private static final Font footnote = FontFactory.getFont(FontFactory.HELVETICA, 8);
    private static final Font tableContent = FontFactory.getFont(FontFactory.COURIER, 10);
    /**
     * Function to convert all upcoming events of an user of the following week
     * into a pdf file
     * @param appointments
     * @return Document of {@link Appointment} of a {@link Classes.User}
     */
    public static Document convertToPdf(ArrayList<Appointment> appointments){
        Document document = new Document(PageSize.A4);
        try{
        
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(setPathToSave()));

        Image image = Image.getInstance(IMG);
        //Properties set
        writer.setPdfVersion(PdfWriter.VERSION_1_7);
        writer.setTagged();
        writer.setViewerPreferences(PdfWriter.DisplayDocTitle);
        //Adds title to the doc
        document.open();
        document.addTitle("Weekly Schedule");
        //Add author to the doc
        document.addAuthor("~My TimeLine Owned and created by Patricia Bombik");
        Chunk imageChunk = new Chunk(image, 0, 0, true);
        document.add(createHeadline("Your Weekly Schedule"));
        document.add(new Paragraph(" "));
        Paragraph paragraph = new Paragraph();

        paragraph.setIndentationLeft(20);
        paragraph.add(imageChunk);
        document.add(paragraph);
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(createSecondHeadline("First, there's an overview of your appointments:"));

        
        document.add(createFootNote("ordered by Date."));
        document.add(new Paragraph(" "));
        document.add(createAllAppointments(appointments));
        PdfPTable outerTable = new PdfPTable(2);
        PdfPCell overhead = new PdfPCell(new Phrase(""));
        outerTable.setSplitRows(false);
        outerTable.setWidthPercentage(100);
        outerTable.setHorizontalAlignment(0);
        overhead.setColspan(2);
        overhead.setHorizontalAlignment(1);
        overhead.setBorder(Rectangle.NO_BORDER);
        outerTable.addCell(overhead);

        PdfPCell table1 = new PdfPCell();
        PdfPCell table2 = new PdfPCell();
        for (int i = 0; i < appointments.size(); i++) {
            float padding = 0;
            table1.setPadding(padding);
            table2.setPadding(padding);
            if (i % 2 == 0) {
                table1 = new PdfPCell(returnAppointmentDetails(appointments.get(i)));
                outerTable.addCell(table1);
            } else {
                table2 = new PdfPCell(returnAppointmentDetails(appointments.get(i)));
                outerTable.addCell(table2);
            }
        }
        if (appointments.size() % 2 != 0) {
            outerTable.addCell("");
        }
        
        document.newPage();
        paragraph = new Paragraph();
        paragraph.setIndentationLeft(20);
        paragraph.setIndentationRight(20);

        paragraph.add(outerTable);
        document.add(paragraph);
        document.close();
        
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(ExportToPdfHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return document;
    }
    /**
     * Returns a Table Containing certain informations of {@link Appointment}
     * of a user
     * @param allEvents
     * @return {@link Paragraph} containing the table of {@link Appointment}
     * @throws DocumentException 
     */
    public static Paragraph createAllAppointments(ArrayList<Appointment> allEvents) throws DocumentException {
        Paragraph p = new Paragraph();
        p.setIndentationLeft(20);
        PdfPTable appointmenttable = new PdfPTable(3);
        appointmenttable.setWidths(new float[]{80, 250, 200});
        //appointmenttable.setWidthPercentage(70);
        //Set Headlines 
        PdfPCell headlineID = new PdfPCell(new Phrase("Event ID"));
        PdfPCell headlineName = new PdfPCell(new Phrase("Event Name"));
        PdfPCell headlineDate = new PdfPCell(new Phrase("Event Date"));
        //Set Headline Alignement
        headlineID.setHorizontalAlignment(Element.ALIGN_CENTER);
        headlineName.setHorizontalAlignment(Element.ALIGN_CENTER);
        headlineDate.setHorizontalAlignment(Element.ALIGN_CENTER);
        //Add Headlines of Table
        appointmenttable.addCell(headlineID);
        appointmenttable.addCell(headlineName);
        appointmenttable.addCell(headlineDate);

        appointmenttable.setHorizontalAlignment(Element.ALIGN_LEFT);
        allEvents.forEach(Appointment -> {
            PdfPCell EventID = new PdfPCell(new Phrase(String.valueOf(Appointment.getID())));
            PdfPCell EventName = new PdfPCell(new Phrase(Appointment.getName()));
            PdfPCell EventDate = new PdfPCell(new Phrase(Appointment.getDateTime().toLocalDate().toString()));
            EventID.setHorizontalAlignment(Element.ALIGN_CENTER);
            EventDate.setHorizontalAlignment(Element.ALIGN_CENTER);
            appointmenttable.addCell(EventID);
            appointmenttable.addCell(EventName);
            appointmenttable.addCell(EventDate);
        });
        p.add(appointmenttable);
        return p;
    }
    /**
     * Creates a headline within the Document
     * @param headlineString
     * @return {@link Paragraph} containing a headline
     */
    public static Paragraph createHeadline(String headlineString) {

        Paragraph headliner = new Paragraph();
        headliner.setFont(headlineFont);
        headliner.setAlignment(Element.ALIGN_CENTER);
        Chunk headline = new Chunk(headlineString);
        headline.setUnderline(0.1f, -2f);
        headliner.add(headline);

        return headliner;
    }
    /**
     * Creates a sub headling withing the Document
     * @param secondHeadlineString
     * @return {@link Paragraph} containing a sub headline
     */
    public static Paragraph createSecondHeadline(String secondHeadlineString) {

        Paragraph secondHeadline = new Paragraph();
        secondHeadline.setFont(secondHeadlineFont);
        secondHeadline.setIndentationLeft(20);
        Chunk headline = new Chunk(secondHeadlineString);
        //headline.setUnderline(0.1f, -2f);
        secondHeadline.add(headline);

        return secondHeadline;
    }
    /**
     * returns a footnote which is contained in the document
     * @param footNoteString
     * @return {@link Paragraph} resembling a footnote
     */
    public static Paragraph createFootNote(String footNoteString) {
        Paragraph footnote = new Paragraph();
        footnote.setFont(ExportToPdfHandler.footnote);
        footnote.setIndentationLeft(20);
        Chunk foot = new Chunk(footNoteString);
        footnote.add(foot);

        return footnote;
    }
    /**
     * Returns a {@link PdfPTable} containing all information of an 
     * {@link Appointment}
     * @param app
     * @return {@link PdfPTable}
     */
    public static PdfPTable returnAppointmentDetails(Appointment app) {
        PdfPTable apptable = new PdfPTable(2);
        apptable.addCell(getCell("ID:", PdfPCell.ALIGN_LEFT));
        apptable.addCell(getCell(String.valueOf(app.getID()), 0));
        apptable.addCell(getCell("Name:", PdfPCell.ALIGN_LEFT));
        apptable.addCell(getCell(String.valueOf(app.getName()), 0));
        apptable.addCell(getCell("Host:", PdfPCell.ALIGN_LEFT));
        apptable.addCell(getCell(String.valueOf(app.getHost()), 0));
        apptable.addCell(getCell("Date:", PdfPCell.ALIGN_LEFT));
        apptable.addCell(getCell(String.valueOf(app.getDateTime().toLocalDate()), 0));
        apptable.addCell(getCell("Location:", PdfPCell.ALIGN_LEFT));
        apptable.addCell(getCell(String.valueOf(app.getLocation()), 0));
        apptable.addCell(getCell("Duration:", PdfPCell.ALIGN_LEFT));
        apptable.addCell(getCell(String.valueOf(app.getDuration()), 0));
        apptable.addCell(getCell("Priority:", PdfPCell.ALIGN_LEFT));
        apptable.addCell(getCell(String.valueOf(app.getPriority()), 0));
        return apptable;
    }
    /**
     * returns a {@link PdfPCell} which is formatted in a certain way
     * @param text which is contained in that cell
     * @param alignment of the text within that cell
     * @return a {@link PdfPCell} 
     */
    public static PdfPCell getCell(String text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text, tableContent));
        cell.setPadding(0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPadding(3f);
        return cell;
    }
    /**
     * Sets the path where the document of the Weekly Schedule will be stored 
     * on your device
     * @return {@link File} containing the document
     */
    public static File setPathToSave() {
        String folder = "";
        String schedule = "\\Weekly Schedule.pdf";
        try {
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ExportToPdfHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(""));
        chooser.setApproveButtonText("Save");
        chooser.setDialogTitle("Where to save your Weekly Schedule?");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            folder = chooser.getSelectedFile().toString();
            folder += schedule;
        }
        
        return new File(folder);
    }
}
