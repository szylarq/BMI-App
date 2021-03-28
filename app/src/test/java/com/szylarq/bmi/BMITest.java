package com.szylarq.bmi;

import com.szylarq.bmi.model.BMIValue;
import com.szylarq.bmi.utils.Utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class BMITest {

    @Test
    public void roundDoubleValue() {

        assertEquals(Double.valueOf(2.4), Double.valueOf(Utils.roundDoubleValue(2.444444)));
        assertEquals(Double.valueOf(2.5), Double.valueOf(Utils.roundDoubleValue(2.456789)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateBmiInvalidValues() {

        BMIValue bmiValue = new BMIValue(0.0,0.0);
    }

    @Test
    public void calculateBmiValidValuesMetric() {

        BMIValue bmiValue = new BMIValue(100,200);

        assertEquals(Double.valueOf(25.0), Double.valueOf(Utils.roundDoubleValue(bmiValue.calculateBmi(true))));

        bmiValue = new BMIValue(80, 165);

        assertEquals(Double.valueOf(29.4), Double.valueOf(Utils.roundDoubleValue(bmiValue.calculateBmi(true))));
    }

    @Test
    public void calculateBmiValidValuesImperial() {

        BMIValue bmiValue = new BMIValue(220.46,78.74);

        assertEquals(Double.valueOf(25.0), Double.valueOf(Utils.roundDoubleValue(bmiValue.calculateBmi(false))));

        bmiValue = new BMIValue(176.37, 64.96);

        assertEquals(Double.valueOf(29.4), Double.valueOf(Utils.roundDoubleValue(bmiValue.calculateBmi(false))));
    }
}