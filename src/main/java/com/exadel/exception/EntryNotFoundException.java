package com.exadel.exception;

public class EntryNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String entryId;

    public EntryNotFoundException(String entryId) {
        this.entryId = entryId;
    }

    public String getEntryId() {
        return entryId;
    }
}
