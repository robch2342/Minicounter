package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;

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
        result = main.addera(0.5, 0.5);
        assertEquals(1, result, errorMargin);
        result = main.addera(2, 0.5);
        assertEquals(2.5, result, errorMargin);
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
    public void toInfinityAndBeyond() {
        double result = main.addera(Double.MAX_VALUE, Double.MAX_VALUE);
        assertEquals(Double.POSITIVE_INFINITY, result, errorMargin);
        result = main.subtrahera(-Double.MAX_VALUE, Double.MAX_VALUE);
        assertEquals(Double.NEGATIVE_INFINITY, result, errorMargin);
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

    @Test(expected = Exception.class)
    public void parseShouldTrowException() throws Exception {
        main.parse("a+4");
    }

    @Test
    public void identifyOperatorsTrue() {
        String operators = "+-*/^";
        for (int i = 0; i < operators.length(); i++) {
            assertTrue(main.isOperator(operators.charAt(i)));
        }
    }

    @Test
    public void identifyOperatorsFalse() {
        //Bygg en sträng med alla tecken förutom operatorerna vi ska stödja.
        CharsetEncoder ce = Charset.forName(Charset.defaultCharset().name()).newEncoder();
        StringBuilder result = new StringBuilder();
        for (char c = 0; c < Character.MAX_VALUE; c++) {
            if (ce.canEncode(c) && c != '+' && c != '-' && c != '*' && c != '/' && c != '^') {
                result.append(c);
            }
        }
        String notOperators = result.toString();

        //Testa alla tecken i strängen.
        for (int i = 0; i < notOperators.length(); i++) {
            assertTrue(!main.isOperator(notOperators.charAt(i)));
        }
    }

    @Test
    public void identifyOperatorsTrueString() {
        String operators = "+-*/^";
        for (int i = 0; i < operators.length(); i++) {
            assertTrue(main.isOperator("" + operators.charAt(i)));
        }
    }

    @Test
    public void identifyOperatorsFalseString() {
        //Bygg en sträng med alla tecken förutom operatorerna vi ska stödja.
        CharsetEncoder ce = Charset.forName(Charset.defaultCharset().name()).newEncoder();
        StringBuilder result = new StringBuilder();
        for (char c = 0; c < Character.MAX_VALUE; c++) {
            if (ce.canEncode(c) && c != '+' && c != '-' && c != '*' && c != '/' && c != '^') {
                result.append(c);
            }
        }
        String notOperators = result.toString();

        //Testa alla tecken i strängen.
        for (int i = 0; i < notOperators.length(); i++) {
            assertTrue(!main.isOperator("" + notOperators.charAt(i)));
        }
    }

    //subtraktion

    @Test
    public void subtraheraHeltal() {
        double result = main.subtrahera(5, 2);
        assertEquals(3, result, errorMargin);
    }

    @Test
    public void subtraheraNegativa() {
        double result = main.subtrahera(-5, -2);
        assertEquals(-3, result, errorMargin);
    }

    @Test
    public void subtraheraDecimaltal() {
        double result = main.subtrahera(4.5, 2.6);
        assertEquals(1.9, result, errorMargin);
        result = main.subtrahera(4.5, 2.5);
        assertEquals(2, result, errorMargin);
        result = main.subtrahera(4, 2.5);
        assertEquals(1.5, result, errorMargin);
    }

    @Test(expected = Exception.class)
    public void subtraheraMedBokstaver() throws Exception {
        main.parse("4-b");
    }


    @Test
    public void divideraMedHeltal() {
        double result = main.dividera(8, 2);
        assertEquals(4, result, 0);
    }

    @Test
    public void divideraMedNegativaTal() {
        double result = main.dividera(-8, -2);
        assertEquals(4, result, 0);
    }

    @Test
    public void divideraMedDecimalTal() {
        double result = main.dividera(4.5, 0.5);
        assertEquals(9, result, 0);
    }

    @Test(expected = Exception.class)
    public void divideraMedBokstaver() throws Exception {
        main.parse("4/b");
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
        assertEquals(4, result, 0);
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
        assertEquals(5, result, 0);
    }

    @Test
    public void divideraMedDecimalKvotParse() {
        double result = 0;
        try {
            result = main.parse("10/4");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(2.5, result, 0);
    }

    @Test
    public void splitInput() {
        ArrayList<String> facit = new ArrayList<String>();
        assertEquals(0, main.splitInput("").size());
        facit.add("4.3789");
        facit.add("+");
        facit.add("56");
        facit.add("*");
        facit.add("-");
        facit.add("foo");
        facit.add("/");
        facit.add("bar");
        ArrayList<String> result = main.splitInput("   4.3789+56 *-foo/bar  ");
        for (int i = 0; i < facit.size(); i++) {
            assertEquals(facit.get(i), result.get(i));
        }
    }

    @Test
    public void correctNegativeNumbers() {
        ArrayList<String> facit = new ArrayList<String>();
        facit.add("-2.0");
        facit.add("-");
        facit.add("-13.0");
        facit.add("*");
        facit.add("-7.0");
        ArrayList<String> result = main.correctNegativeNumbers(main.splitInput("-2 - -13 * - --7"));
        for (int i = 0; i < facit.size(); i++) {
            assertEquals(facit.get(i), result.get(i));
        }
    }

    @Test
    public void parseMultipleOperators() throws Exception {
        assertEquals(42, main.parse("1+2*30-19"), errorMargin);
    }

    @Test(expected = Exception.class)
    public void parseSyntaxError() throws Exception {
        main.parse("*47");
    }

    @Test(expected = Exception.class)
    public void parseSyntaxError2() throws Exception {
        main.parse("47/");
    }

    @Test(expected = Exception.class)
    public void parseSyntaxError3() throws Exception {
        main.parse("foobar");
    }

    @Test(expected = Exception.class)
    public void parseSyntaxError4() throws Exception {
        main.parse("14 78");
    }

    @Test(expected = Exception.class)
    public void parseSyntaxError5() throws Exception {
        main.parse("-");
    }

    @Test
    public void parseEmptySyntaxError() {
        try {
            main.parse("");
            assertTrue(false);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "empty list");
        }
    }

    @Test
    public void parseLastoperator() throws Exception{
        double result = main.parse("last -13*5");
        assertEquals(-65, result, errorMargin);
        result = main.parse("-10 * last+50-690");
        assertEquals(10, result, errorMargin);
        result = main.parse("last");
        assertEquals(10, result, errorMargin);
    }

    //Upphöjt

    @Test
    public void upphojttillheltal() throws Exception {
        double result = main.upphojtTill(2, 3);
        assertEquals(8, result, errorMargin);
        result = main.upphojtTill(0, 0);
        assertEquals(Double.NaN, result, errorMargin);
        result = main.upphojtTill(1, 50000);
        assertEquals(1, result, errorMargin);
    }

    @Test
    public void upphojttillnegativatal() throws Exception {
        double result = main.upphojtTill(-2, -13);
        assertEquals(-0.000122070312, result, errorMargin);
        result = main.upphojtTill(-0, -0);
        assertEquals(Double.NaN, result, errorMargin);
        result = main.upphojtTill(-1, 0);
        assertEquals(1, result, errorMargin);
        result = main.upphojtTill(-1, -50000);
        assertEquals(1, result, errorMargin);
    }

    @Test
    public void upphojttilldecimaltal() throws Exception {
        double result = main.upphojtTill(1.5, 2.0);
        assertEquals(2.25, result, errorMargin);
        result = main.upphojtTill(1.0, 3.0);
        assertEquals(1.0, result, errorMargin);
        result = main.upphojtTill(15.1555, 2.0);
        assertEquals(229.68918025, result, errorMargin);
        result = main.upphojtTill(-1.43, -10.00);
        assertEquals(0.0279665971, result, errorMargin);
    }

    @Test(expected = Exception.class)
    public void upphojtilldecialtalerror() throws Exception {
        double result = main.upphojtTill(1.5, 2.4);

    }
    @Test(expected = Exception.class)
    public void upphöjrillParse() throws Exception{
        double result = 0;
            result = main.parse("a ^ 2");
        }
    @Test(expected = Exception.class)
    public void upphöjrillParse2() throws Exception{
        double result = 0;
        result = main.parse("2 ^ a");
    }
    @Test
    public void upphöjrillParse3() throws Exception{
        double result = 0;
        result = main.parse("2 ^ 2");
        assertEquals(4, result, errorMargin);
        result = main.parse("24 ^ 4");
        assertEquals(331776, result, errorMargin);
        result = main.parse("0.4 ^ 2");
        assertEquals(0.16, result, errorMargin);
        result = main.parse("-2 ^ -2");
        assertEquals(0.25, result, errorMargin);
    }
}
