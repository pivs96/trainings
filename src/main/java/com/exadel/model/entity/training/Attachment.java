package com.exadel.model.entity.training;

import com.exadel.dto.AttachmentDTO;

import javax.persistence.*;

@Entity
@Table(name = "attachments")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String link;

    @ManyToOne
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

    public Attachment() {
    }

    public Attachment(AttachmentDTO attachmentDTO) {
        this.id = attachmentDTO.getId();
        this.name = attachmentDTO.getName();
        this.link = attachmentDTO.getLink();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
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

}