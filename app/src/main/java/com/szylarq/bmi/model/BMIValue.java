package com.szylarq.bmi.model;

import com.szylarq.bmi.utils.BMIStatus;

public class BMIValue {

    private double mass;
    private double height;

    public BMIValue(double mass, double height) {

        if(mass <= 0 || height <=0){

            throw new IllegalArgumentException();
        }

        this.mass = mass;
        this.height = height;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double calculateBmi(boolean isMetricUnits){

        return isMetricUnits ? calculateBmiMetric(getMass(), getHeight()) : calculateBmiImperial(getMass(), getHeight());
    }
    private double calculateBmiMetric(double mass, double height){

        return mass/Math.pow(height/100, 2);
    }

    private double calculateBmiImperial(double mass, double height) {

        return 703.0 * mass / Math.pow(height, 2);
    }

    public static BMIStatus getBmiStatus(double bmiValue){

        if (bmiValue >= 30){

            return BMIStatus.OBESE;
        }
        else if(bmiValue >= 25){

            return BMIStatus.OVERWEIGHT;
        }
        else if(bmiValue >= 18.5){

            return BMIStatus.NORMAL;
        }
        else{
            return BMIStatus.UNDERWEIGHT;
        }
    }
}
