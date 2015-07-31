package com.exadel.dto;

import com.exadel.model.entity.training.Attachment;

public class AttachmentDTO {
    private long id;
    private String name;
    private String link;
    private long trainingId;

    public AttachmentDTO(Attachment attachment) {
        this.id = attachment.getId();
        this.name = attachment.getName();
        this.link = attachment.getLink();
        this.trainingId = attachment.getTraining().getId();
    }

    public AttachmentDTO() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(long trainingId) {
        this.trainingId = trainingId;
    }
}
