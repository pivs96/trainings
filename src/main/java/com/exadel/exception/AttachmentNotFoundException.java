package com.exadel.exception;

public class AttachmentNotFoundException extends RuntimeException {
    private String attachId;

    public AttachmentNotFoundException(String attachId) {
        this.attachId = attachId;
    }

    public String getAttachId() {
        return attachId;
    }
}
