package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by robin on 2016-01-25.
 */
public class MainTest {
    private Main main;
    private double errorMargin = 0.0000001;

    @Before
    public void setUp() throws Exception {
        main = new Main();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void adderaEttOchTre() {
        double result = main.addera(1, 3);
        assertEquals(4, result, errorMargin);
    }

    @Test
    public void adderaDecimaltal() {
        double result = main.addera(3.14, 4.20);
        assertEquals(7.34, result, errorMargin);
    }

    @Test
    public void multipliceraHeltal() {
        double result = main.multiplicera(1, 3);
        assertEquals(3, result, errorMargin);
        result = main.multiplicera(14, 1);
        assertEquals(14, result, errorMargin);
        result = main.multiplicera(0, 7);
        assertEquals(0, result, errorMargin);
        result = main.multiplicera(9, 0);
        assertEquals(0, result, errorMargin);
        result = main.multiplicera(12, 3);
        assertEquals(36, result, errorMargin);
        result = main.multiplicera(0, 0);
        assertEquals(0, result, errorMargin);
    }

    @Test
    public void multipliceraDecimaltal() {
        double result = main.multiplicera(1.4, 3);
        assertEquals(4.2, result, errorMargin);
        result = main.multiplicera(14, 1.5);
        assertEquals(21, result, errorMargin);
        result = main.multiplicera(0, 7.3);
        assertEquals(0, result, errorMargin);
        result = main.multiplicera(9.14, 0);
        assertEquals(0, result, errorMargin);
        result = main.multiplicera(12.3, 3.5);
        assertEquals(43.05, result, errorMargin);
    }

    @Test
    public void multipliceraNegativaTal() {
        double result = main.multiplicera(1.4, -3);
        assertEquals(-4.2, result, errorMargin);
        result = main.multiplicera(-14, 1.5);
        assertEquals(-21, result, errorMargin);
        result = main.multiplicera(0, -7.3);
        assertEquals(0, result, errorMargin);
        result = main.multiplicera(9.14, -0);
        assertEquals(0, result, errorMargin);
        result = main.multiplicera(-12.3, -3.5);
        assertEquals(43.05, result, errorMargin);
        result = main.multiplicera(-14, 1);
        assertEquals(-14, result, errorMargin);
        result = main.multiplicera(0, -7);
        assertEquals(0, result, errorMargin);
        result = main.multiplicera(-9, 0);
        assertEquals(0, result, errorMargin);
        result = main.multiplicera(-12, -3);
        assertEquals(36, result, errorMargin);
    }

    @Test
    public void parseIntegerAddition() {
        double result = 0;
        try {
            result = main.parse("4+19");
        } catch (Exception e) {

        }
        assertEquals(23, result, errorMargin);
    }

    @Test
    public void parseFloatAddition() {
        double result = 0;
        try {
            result = main.parse("4.20+19");
        } catch (Exception e) {

        }
        assertEquals(23.20, result, errorMargin);
    }

    @Test(expected=Exception.class)
    public void parseShouldTrowException() throws Exception {
        main.parse("a+4");
    }

    @Test
    public void divideraMedHeltal() {
        double result = main.dividera(8, 2);
        assertEquals(4, result, 0);
    }

    @Test
    public void divideraMedNegativaTal() {
        double result = main.dividera(-8, -2);
        assertEquals(-4, result, 0);
    }

    @Test
    public void divideraMedDecimalTal() {
        double result = main.dividera(4.5, 0.5);
        assertEquals(9, result, 0);
    }

    @Test
    public void divideraMedBokstaver() {
        double result = 0;
        try {
            result = main.parse("4/b");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNull(null, result);
    }

    @Test
    public void divideraMedHeltalKvot() {
        double result = main.dividera(8, 2);
        assertEquals(4, result, 0);
    }

    @Test
    public void divideraMedDecimalKvot() {
        double result = main.dividera(10, 4);
        assertEquals(2.5, result, 0);
    }

    @Test
    public void divideraMedHeltalParse() {
        double result = 0;
        try {
            result = main.parse("8/2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(4, result, 0);
    }

    @Test
    public void divideraMedNegativaTalParse() {
        double result = 0;
        try {
            result = main.parse("-8/-2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(-4, result, 0);
    }

    @Test
    public void divideraMedDecimalTalParse() {
        double result = 0;
        try {
            result = main.parse("4.5/0.5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(9, result, 0);
    }

    @Test
    public void divideraMedHeltalKvotParse() {
        double result = 0;
        try {
            result = main.parse("10/2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(4, result, 0);
    }

    @Test
    public void divideraMedDecimalKvotParse() {
        double result = 0;
        try {
            result = main.parse("10/4");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(-4, result, 0);
    }
}