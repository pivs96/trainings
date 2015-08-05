package com.exadel.export;

import com.exadel.model.entity.training.Training;
import com.exadel.model.entity.user.User;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PdfExporter {
    public static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
    public static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL, BaseColor.RED);
    public static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    public static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);

    public static void addMetaDataAndTitlePage(Document document, User creator, String title, String time) throws DocumentException, MalformedURLException {
        document.addTitle(title);
        document.addSubject("Export user's statistics");
        document.addKeywords("statistics, controlling, attendance");
        document.addAuthor(creator.getName() + " " + creator.getSurname());
        document.addCreator("Exadel trainings");

        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph(title, catFont));

        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Report generated by: " + creator.getName() + " " + creator.getSurname() + ", " + time, smallBold));
        addEmptyLine(preface, 3);
        preface.add(new Paragraph("Export user's statistics", smallBold));

        addEmptyLine(preface, 8);

        Chunk exadelLink = new Chunk("Exadel home", redFont);
        exadelLink.setAction(new PdfAction(new URL("http://www.exadel.com/")));
        preface.add(new Paragraph(exadelLink));

        Chunk link = new Chunk("User's list", redFont);
        link.setAction(new PdfAction(new URL("http://localhost:9000/#/users/")));
        preface.add(new Paragraph(link));

        document.add(preface);
        document.newPage();
    }

    public static void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }

    public static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
