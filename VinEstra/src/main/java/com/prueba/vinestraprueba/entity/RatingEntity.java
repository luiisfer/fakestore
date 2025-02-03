package com.prueba.vinestraprueba.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class RatingEntity {
    private double rate;
    private int count;

    public RatingEntity(double rate, int count) {
        this.rate = rate;
        this.count = count;
    }

    public RatingEntity() {
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
