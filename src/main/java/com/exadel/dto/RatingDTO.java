package com.exadel.dto;

public class RatingDTO {
    private int rating;
    private long trainingId;
    private long userId;

    public RatingDTO() {
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(long trainingId) {
        this.trainingId = trainingId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RatingDTO{" +
                "rating=" + rating +
                ", trainingId=" + trainingId +
                ", userId=" + userId +
                '}';
    }
}
