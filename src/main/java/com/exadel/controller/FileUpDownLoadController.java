package com.exadel.controller;

import com.exadel.model.entity.FileLoadStatus;
import com.exadel.model.entity.training.Attachment;
import com.exadel.service.AttachmentService;
import com.exadel.service.TrainingService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/files")
public class FileUpDownLoadController {

    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private TrainingService trainingService;

    private static final String path = System.getProperty("user.dir") + File.separator + "Attachments";

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public FileLoadStatus handleFileUpload(@RequestParam String trainingId,
                                           @RequestBody MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                File dir = new File(path + File.separator + trainingId);
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

                Attachment attachment = new Attachment();

                attachment.setName(file.getOriginalFilename());
                attachment.setTraining(trainingService.getTrainingById(trainingId));
                attachmentService.addAttachmentFile(attachment);

                stream.write(bytes);
                stream.close();
                return FileLoadStatus.UPLOAD_SUCCESS;
            } catch (Exception e) {
                return FileLoadStatus.UPLOAD_FAIL;
            }
        } else {
            return FileLoadStatus.UPLOAD_EMPTY_FAIL;
        }
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public FileLoadStatus handleFileDownload(@RequestParam String id, HttpServletRequest request,
                                             HttpServletResponse response) {
        Attachment attachment = attachmentService.getAttachmentById(id);
        String filePath = path + File.separator + attachment.getTraining().getId()
                + File.separator + attachment.getName();
        File downloadFile = new File(filePath);

        ServletContext context = request.getServletContext();
        String mimeType = context.getMimeType(filePath);

        return downloadFile(downloadFile, mimeType, response);
    }

    public static FileLoadStatus downloadFile(File downloadFile, String mimeType, HttpServletResponse response) {
        FileInputStream inputStream = null;
        OutputStream outStream = null;

        try {
            inputStream = new FileInputStream(downloadFile);

            response.setContentLength((int) downloadFile.length());
            response.setContentType(mimeType);

            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment;filename=\"%s\"", downloadFile.getName());
            response.setHeader(headerKey, headerValue);

            outStream = response.getOutputStream();
            IOUtils.copy(inputStream, outStream);
        } catch (Exception e) {
            return FileLoadStatus.DOWNLOAD_FAIL;
        } finally {
            try {
                if (null != inputStream)
                    inputStream.close();
                if (null != outStream)
                    outStream.close();
            } catch (IOException e) {
                return FileLoadStatus.DOWNLOAD_CLOSING_FAIL;
            }
            return FileLoadStatus.DOWNLOAD_SUCCESS;
        }
    }
}
