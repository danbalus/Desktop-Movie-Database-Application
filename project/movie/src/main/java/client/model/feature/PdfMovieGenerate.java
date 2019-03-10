package client.model.feature;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import client.model.entity.Movie;

public class PdfMovieGenerate implements Raport {

    private static String FILE;//= "c:/Users/DanB/Downloads/aaaa/fisrt.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 25,
            Font.BOLD, BaseColor.RED);
    private static Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD, BaseColor.BLUE);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    // private ArrayList<Movie> movieList;//nu merge declarat u list, conflict

    public PdfMovieGenerate(String file) {
        FILE = file;
    }

    public void start(ArrayList<Movie> movieList) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addTitlePage(document);
            String path = findPath();
            addContent(document, movieList, path);
            document.close();
            // fileChooser();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private static void addMetaData(Document document) {
        document.addTitle("movie raport PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Dan B");
        document.addCreator("Dan B");
    }

    private static void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("Movie List", redFont));


        preface.add(new Paragraph(
                "This document contain a list of all movies from the application ",
                smallBold));

        addEmptyLine(preface, 1);

        document.add(preface);
        // Start a new page
        //-------------------------------->>>>>> document.newPage();
    }

    private String findPath() {
        File file = new File("./src/main/resources/stuff/HIMYM.jpg");
        String path = file.getPath();
        return path;

    }

    private static void addContent(Document document, ArrayList<Movie> movieList, String path)
            throws DocumentException, IOException {
        Anchor anchor = new Anchor("List of movies", catFont);
        //anchor.setName("First Chapter");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Table with movies and actors", subFont);
        //Paragraph preface = new Paragraph();
        // addEmptyLine(preface, 5);
        addEmptyLine(subPara, 5);
        Section subCatPart = catPart.addSection(subPara);

        Image img = Image.getInstance(path);
        //document.add(new Paragraph("Sample 1: This is simple image demo."));
        img.setAbsolutePosition(20f, 25f);
        document.add(img);

        createTable(subCatPart, movieList);

        // now add all this to the document
        document.add(catPart);

        Paragraph preface = new Paragraph("Table with movies and actors", subFont);
        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph(
                "Report generated by: " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));
        addEmptyLine(preface, 1);

    }

    private static void createTable(Section subCatPart, ArrayList<Movie> movieList)
            throws BadElementException, IOException {
        PdfPTable table = new PdfPTable(5);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("Name", blueFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Genre", blueFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Info", blueFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Rate", blueFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Actors", blueFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        for (Movie movie : movieList) {
            table.addCell(movie.getName() + "");//smecherie daca i null
            table.addCell(movie.getGenre() + "");
            table.addCell(movie.getTitle() + "");
            table.addCell(movie.getRating() + "");
           // table.addCell(movie.getActorsNameAsStringList() + "");
        }

        subCatPart.add(table);

    }

    private static void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }


}