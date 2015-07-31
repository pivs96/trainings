package com.exadel.dto;

import com.exadel.model.entity.training.Attachment;

public class AttachmentDTO {
    private long id;
    private String type;
    private String name;
    private String link;
    private long entryId;

    public AttachmentDTO(Attachment attachment) {
        this.id = attachment.getId();
        this.type = attachment.getType();
        this.name = attachment.getName();
        this.link = attachment.getLink();
        this.entryId = attachment.getEntry().getId();
    }

    public AttachmentDTO() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public long getEntryId() {
        return entryId;
    }

    public void setEntryId(long entryId) {
        this.entryId = entryId;
    }
}
