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
    public void parseIntegerAddition() {
        double result = main.parse("4+19");
        assertEquals(23, result, errorMargin);
    }

    @Test
    public void parseFloatAddition() {
        double result = main.parse("4.20+19");
        assertEquals(23.20, result, errorMargin);
    }
}