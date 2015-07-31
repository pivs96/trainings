package com.exadel.exception;

public class EntryNotFoundException extends RuntimeException {

    private String entryId;

    public EntryNotFoundException(String entryId) {
        this.entryId = entryId;
    }

    public String getEntryId() {
        return entryId;
    }
}
