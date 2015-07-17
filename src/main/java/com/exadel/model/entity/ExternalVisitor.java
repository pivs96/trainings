package com.exadel.model.entity;

import java.util.List;

/**
 * Created by Виктория on 13.07.2015.
 */
public class ExternalVisitor extends User {
    private List<Training> visitingTrainings;

    /*public ExternalVisitor(String id, String name, String surname, String phone, String email, List<Training> visitingTrainings) {
        super(id, name, surname, phone, email, UserRole.USER);
        this.visitingTrainings = visitingTrainings;
    }*/

    public ExternalVisitor() {
        super();
    }

    /*public ExternalVisitor(ExternalVisitor externalVisitor) {
        super(externalVisitor.getId(), externalVisitor.getName(), externalVisitor.getSurname(),
                externalVisitor.getPhone(), externalVisitor.getEmail(), UserRole.USER);
        this.visitingTrainings = new ArrayList<>();
    }*/

    public List<Training> getVisitingTrainings() {
        return visitingTrainings;
    }

    public void setVisitingTrainings(List<Training> visitingTrainings) {
        this.visitingTrainings = visitingTrainings;
    }
}
