package com.exadel.controller;

import com.exadel.model.entity.training.Entry;
import com.exadel.model.entity.training.Training;
import com.exadel.model.entity.user.*;
import com.exadel.service.AbsenteeService;
import com.exadel.service.EntryService;
import com.exadel.service.UserService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.exadel.export.PdfExporter.*;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping("/files/export")
public class ExportStatController {
    /*@Autowired
    private TrainingService trainingService;*/
    @Autowired
    private UserService userService;
    @Autowired
    private EntryService entryService;
    @Autowired
    private AbsenteeService absenteeService;

    private static String FILE =  System.getProperty("user.dir") + File.separator;
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HHmmss");
    private static SimpleDateFormat prefaceformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

   /* @RequestMapping(value = "/training", method = RequestMethod.GET)
    public boolean getTrainingPdf(@RequestParam String trainingId) {
        Training training = trainingService.getTrainingById(trainingId);
        long userId = userService.getCurrentId();
        User creator = userService.getUserById(userId);

        ExportTrainingToPdf.createPdf(training, creator);
        return true;
    }*/

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public void getUserPdf(@RequestParam String userId) {
        User user = userService.getUserById(userId);

        long creatorId = userService.getCurrentId();
        User creator = userService.getUserById(creatorId);

        try {
            Document document = new Document();
            Date creatingDate = new Date();
            String time = format.format(creatingDate);
            String name = user.getName() + " " + user.getSurname();
            PdfWriter.getInstance(document, new FileOutputStream(FILE + "Statistics of "
                    + name + " " + time + ".pdf"));

            document.open();
            addMetaDataAndTitlePage(document, creator, name, prefaceformat.format(creatingDate));
            addContent(document, user);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addContent(Document document, User user) throws DocumentException {
        int chapterNumber = 1;

        if (addVisitingTrainings(document, user, chapterNumber))
            chapterNumber++;

        Anchor anchor = new Anchor("First Chapter", catFont);
        anchor.setName("First Chapter");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Subcategory 1", subFont);
        Section subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Hello"));

        subPara = new Paragraph("Subcategory 2", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Paragraph 1"));
        subCatPart.add(new Paragraph("Paragraph 2"));
        subCatPart.add(new Paragraph("Paragraph 3"));

        // add a list
        createList(subCatPart);
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 5);
        subCatPart.add(paragraph);

        // now add all this to the document
        document.add(catPart);

        // Next section
        anchor = new Anchor("Second Chapter", catFont);
        anchor.setName("Second Chapter");

        // Second parameter is the number of the chapter
        catPart = new Chapter(new Paragraph(anchor), 2);

        subPara = new Paragraph("Subcategory", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("This is a very important message"));

        // now add all this to the document
        document.add(catPart);

    }

    private boolean addVisitingTrainings(Document document, User user, int chapterNumber) throws DocumentException {
        List<Training> visitingTrainings;

        if (user.getRole() == UserRole.EXTERNAL_VISITOR)
            visitingTrainings = ((ExternalVisitor)user).getVisitingTrainings();
        else if (user.getRole() == UserRole.EMPLOYEE)
            visitingTrainings = ((Employee)user).getVisitingTrainings();
        else
            return false;

        Anchor anchor = new Anchor("Visited trainings", catFont);
        anchor.setName("Visited trainings");
        Chapter chapter = new Chapter(new Paragraph(anchor), chapterNumber);

        Paragraph emptyLine = new Paragraph();
        addEmptyLine(emptyLine, 2);
        chapter.add(emptyLine);

        PdfPTable trainingsTable = new PdfPTable(3);

        PdfPCell cell = new PdfPCell(new Phrase("Name"));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        trainingsTable.addCell(cell);

//        cell = new PdfPCell(new Phrase("Begin"));
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        trainingsTable.addCell(cell);
//
//        cell = new PdfPCell(new Phrase("End"));
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        trainingsTable.addCell(cell);

        cell = new PdfPCell(new Phrase("Absences"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        trainingsTable.addCell(cell);

        cell = new PdfPCell(new Phrase("All"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        trainingsTable.addCell(cell);
        trainingsTable.setHeaderRows(1);

        List<Paragraph> trainingParagraphs = new ArrayList<>();
        List<List<String>> days = new ArrayList<>();
        List<List<String>> reasons = new ArrayList<>();

        for (Training training : visitingTrainings) {
            trainingsTable.addCell(training.getName());

            trainingParagraphs.add(new Paragraph(training.getName(), subFont));

            List<String> trainingDays = new ArrayList<>();
            List<String> trainingReasons = new ArrayList<>();
            List<Entry> entries = entryService.getAllEntriesByTrainingId(training.getId());
            int absentCount = 0;

            for (Entry entry : entries) {
                Absentee absentee = absenteeService.getAbsentee(user.getId(), entry.getId());
                if (absentee != null) {
                    absentCount++;
                    trainingDays.add(format.format(entry.getBeginTime()));
                    trainingReasons.add(absentee.getReason());
                }
            }

            days.add(trainingDays);
            reasons.add(trainingReasons);

            trainingsTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            trainingsTable.addCell(String.valueOf(absentCount));
            trainingsTable.addCell(String.valueOf(entries.size()));

            trainingsTable.setHorizontalAlignment(Element.ALIGN_LEFT);
        }

        chapter.add(trainingsTable);

        for (int i = 0; i < trainingParagraphs.size(); i++) {
            Paragraph paragraph = trainingParagraphs.get(i);
            Section subSection = chapter.addSection(paragraph);

            addEmptyLine(emptyLine, 2);
            subSection.add(emptyLine);

            PdfPTable absenceTable = new PdfPTable(2);
            PdfPCell absenceCell = new PdfPCell(new Phrase("Day"));
            absenceCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            absenceTable.addCell(absenceCell);

            absenceCell = new PdfPCell(new Phrase("Absence reason"));
            absenceCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            absenceTable.addCell(absenceCell);

            List<String> dayStrings = days.get(i);
            List<String> absenceStrings = reasons.get(i);
            for (int j = 0; j < dayStrings.size(); j++) {
                String day = dayStrings.get(j);
                String reason = absenceStrings.get(j);

                absenceTable.addCell(day);
                absenceTable.addCell(reason);
            }

            subSection.add(absenceTable);
        }

        /*subParagraph = new Paragraph("Subcategory 2", subFont);
        subSection = chapter.addSection(subParagraph);
        subSection.add(new Paragraph("Paragraph 1"));
        subSection.add(new Paragraph("Paragraph 2"));
        subSection.add(new Paragraph("Paragraph 3"));

        // add a list
        createList(subSection);
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 5);
        subSection.add(paragraph);*/
        document.add(chapter);
        return true;
    }
}
