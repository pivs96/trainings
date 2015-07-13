package com.exadel.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Training {
    private String id;
    private String name;
    private String place;
    private Date date;
    private Date beginTime;
    private Date endTime;
    private String trainer;
    private String targetAudience;
    private double rating;
    private int numberOfFeedbacks;
    private int maxNumber;
    private int currentNumber;

    public Training(String id, String name, String place, Date date, Date beginTime, Date endTime, String trainer, String targetAudience, double rating, int numberOfFeedbacks, int maxNumber, int currentNumber) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.date = date;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.trainer = trainer;
        this.targetAudience = targetAudience;
        this.rating = rating;
        this.numberOfFeedbacks = numberOfFeedbacks;
        this.maxNumber = maxNumber;
        this.currentNumber = currentNumber;
    }

    public Training() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public int getNumberOfFeedbacks() {
        return numberOfFeedbacks;
    }

    public void setNumberOfFeedbacks(Integer numberOfFeedbacks) {
        this.numberOfFeedbacks = numberOfFeedbacks;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(Integer maxNumber) {
        this.maxNumber = maxNumber;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(Integer currentNumber) {
        this.currentNumber = currentNumber;
    }
}
