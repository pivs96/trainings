package com.exadel.controller;

import com.exadel.model.entity.feedback.TrainingFeedback;
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

import javax.servlet.http.HttpServletResponse;

import static com.exadel.export.PdfExporter.*;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class ExportStatController {
    @Autowired
    private UserService userService;
    @Autowired
    private EntryService entryService;
    @Autowired
    private AbsenteeService absenteeService;

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HHmmss");
    private static SimpleDateFormat prefaceFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static DecimalFormat doubleFormat = new DecimalFormat("#.##");
    private static final String path = System.getProperty("user.dir");

    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public void getUserPdf(@RequestParam String userId, HttpServletResponse response) {
        User user = userService.getUserById(userId);

        long creatorId = userService.getCurrentId();
        User creator = userService.getUserById(creatorId);

        try {
            Document document = new Document();
            Date creatingDate = new Date();
            String time = format.format(creatingDate);
            String name = "Statistics of " + user.getName() + " " + user.getSurname();

            String filePath = path + File.separator + name + " " + time + ".pdf";
            File downloadFile = new File(filePath);

            PdfWriter.getInstance(document, new FileOutputStream(downloadFile));

            document.open();
            addMetaDataAndTitlePage(document, creator, name, prefaceFormat.format(creatingDate));
            addContent(document, user);
            document.close();

            FileUpDownLoadController.downloadFile(downloadFile, "application/pdf", response);
            downloadFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addContent(Document document, User user) throws DocumentException {
        int chapterNumber = 1;
        com.itextpdf.text.List summaryContent = new com.itextpdf.text.List(false, false, 15);

        if (addVisitingTrainings(document, user, chapterNumber, summaryContent))
            chapterNumber++;

        if (addMentoringTrainings(document, user, chapterNumber, summaryContent))
            chapterNumber++;

        if (chapterNumber != 0)
            addSummary(document, summaryContent, chapterNumber);
    }

    private boolean addVisitingTrainings(Document document, User user, int chapterNumber, com.itextpdf.text.List summaryContent) throws DocumentException {
        List<Training> trainings;
        if (user.getRole() == UserRole.EXTERNAL_VISITOR)
            trainings = ((ExternalVisitor) user).getVisitingTrainings();
        else if (user.getRole() == UserRole.EMPLOYEE)
            trainings = ((Employee) user).getVisitingTrainings();
        else
            return false;

        document.newPage();
        Anchor anchor = new Anchor("Visiting trainings", catFont);
        anchor.setName("Visiting trainings");
        Chapter chapter = new Chapter(new Paragraph(anchor), chapterNumber);

        Paragraph emptyLine = new Paragraph();
        addEmptyLine(emptyLine, 1);
        chapter.add(emptyLine);

        List<String> headers = Arrays.asList("Name", "Absences", "All");
        PdfPTable trainingsTable = createTableWithHeader(headers);
        chapter.add(trainingsTable);

        List<String> absenceHeaders = Arrays.asList("Day", "Absence reason");
        int allAbsenceCount = 0;
        int lecturesCount = 0;

        for (Training training : trainings) {
            List<String> trainingDays = new ArrayList<>();
            List<String> trainingReasons = new ArrayList<>();
            List<Entry> entries = entryService.getAllEntriesByTrainingId(training.getId());
            int absentCount = 0;

            for (Entry entry : entries) {
                Absentee absentee = absenteeService.getAbsentee(user.getId(), entry.getId());
                if (absentee != null) {
                    absentCount++;
                    trainingDays.add(dateFormat.format(entry.getBeginTime()));
                    trainingReasons.add(absentee.getReason());
                }
            }

            lecturesCount += entries.size();
            allAbsenceCount += absentCount;

            if (absentCount > 0) {
                Paragraph paragraph = new Paragraph(training.getName(), subFont);
                addEmptyLine(paragraph, 1);
                Section subSection = chapter.addSection(paragraph);

                PdfPTable absenceTable = createTableWithHeader(absenceHeaders);

                for (int j = 0; j < trainingDays.size(); j++) {
                    absenceTable.addCell(trainingDays.get(j));
                    absenceTable.addCell(trainingReasons.get(j));
                }

                subSection.add(absenceTable);
            }

            trainingsTable.addCell(training.getName());
            trainingsTable.addCell(String.valueOf(absentCount));
            trainingsTable.addCell(String.valueOf(entries.size()));
        }


        double percent = (double) allAbsenceCount / lecturesCount * 100;
        summaryContent.add(new ListItem("Lectures attended: " + lecturesCount));
        summaryContent.add(new ListItem("Lectures skipped: " + allAbsenceCount
                + " (" + doubleFormat.format(percent) + "%)"));

        document.add(chapter);
        return true;
    }

    private PdfPTable createTableWithHeader(List<String> headers) {
        PdfPTable table = new PdfPTable(headers.size());

        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
        }

        table.setHeaderRows(1);
        return table;
    }

    private boolean addMentoringTrainings(Document document, User user, int chapterNumber, com.itextpdf.text.List summaryContent) throws DocumentException {
        List<Training> mentoringTrainings;
        if (user.getRole() != UserRole.EXTERNAL_VISITOR)
            mentoringTrainings = ((ExternalTrainer) user).getMentoringTrainings();
        else
            return false;

        document.newPage();
        Anchor anchor = new Anchor("Mentoring trainings", catFont);
        anchor.setName("Mentoring trainings");
        Chapter chapter = new Chapter(new Paragraph(anchor), chapterNumber);

        Paragraph emptyLine = new Paragraph();
        addEmptyLine(emptyLine, 1);
        chapter.add(emptyLine);

        List<String> headers = Arrays.asList("Name", "Lectures mentored", "Responses");
        PdfPTable trainingsTable = createTableWithHeader(headers);
        chapter.add(trainingsTable);

        List<String> feedbackHeaders = Arrays.asList("Day", "Feedback", "Positive");
        int eventsSum = 0;
        int feedbackSum = 0;
        int positiveFeedbackSum = 0;

        for (Training training : mentoringTrainings) {
            trainingsTable.addCell(training.getName());

            int eventsCount = training.getEntries().size();
            eventsSum += eventsCount;
            trainingsTable.addCell(String.valueOf(eventsCount));

            int feedbackCount = training.getFeedbacks().size();
            feedbackSum += feedbackCount;
            trainingsTable.addCell(String.valueOf(feedbackCount));

            if (feedbackCount > 0) {
                Paragraph paragraph = new Paragraph(training.getName(), subFont);
                addEmptyLine(paragraph, 1);
                Section subSection = chapter.addSection(paragraph);
                PdfPTable feedbackTable = createTableWithHeader(feedbackHeaders);
                subSection.add(feedbackTable);

                for (TrainingFeedback feedback : training.getFeedbacks()) {
                    feedbackTable.addCell(dateFormat.format(feedback.getDate()));
                    feedbackTable.addCell(feedback.getOtherInfo());

                    if (feedback.isStudyWithTrainer()) {
                        feedbackTable.addCell("+");
                        positiveFeedbackSum++;
                    }
                    else
                        feedbackTable.addCell("-");
                }
            }
        }

        summaryContent.add(new ListItem("Lectures mentored: " + eventsSum));
        summaryContent.add(new ListItem("Feedbacks (as trainer) received: " + feedbackSum));
        double percent = (double) positiveFeedbackSum / feedbackSum * 100;
        summaryContent.add(new ListItem("Positive feedbacks (as trainer) received: "
                + positiveFeedbackSum + " (" + doubleFormat.format(percent) + "%)"));

        document.add(chapter);
        return true;
    }

    private void addSummary(Document document, com.itextpdf.text.List summaryContent, int chapterNumber) throws DocumentException {
        document.newPage();
        Anchor anchor = new Anchor("Summary", catFont);
        anchor.setName("Summary");
        Chapter chapter = new Chapter(new Paragraph(anchor), chapterNumber);
        chapter.add(summaryContent);
        document.add(chapter);
    }
}