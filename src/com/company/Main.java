package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    double result = 0;

    public static void main(String[] args) {
        Main main = new Main();
        main.prompt();
    }

    public void prompt() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Minicounter 0.13");
        String input = "";
        while (!input.equals("exit")) {
            System.out.print("> ");
            input = scanner.nextLine();
            try {
                result = parse(input);
                System.out.println(result);
            } catch (Exception e){
                System.out.println("Luser error: Fingers are too fat.");
            }
        }
        System.out.println("Hej då");
    }

    public double parse(String input) throws Exception{
        String word1 = "";
        char operator = 'u';
        String word2= "";
        for (int i = 0; i < input.length(); i++) {
            if (isOperator(input.charAt(i))){
                operator = input.charAt(i);
                if(word1.equals("")) {
                    word1 = word2;
                    word2 = "";
                }
            } else {
                word2 += input.charAt(i);
            }
        }
        System.out.println(word1 + " " + word2);
        double res;
        try {
            if (operator == '+') {
                res = addera(Double.valueOf(word1), Double.valueOf(word2));
            }
            if (operator == '/') {
                res = dividera(Double.valueOf(word1), Double.valueOf(word2));
            }
            if (operator == '-') {
                //res = subtrahera(Double.valueOf(word1), Double.valueOf(word2));
            }
            else {
                throw new Exception();
            }

        } catch (Exception e) {
            throw e;
        }
        return res;
    }

    public boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public ArrayList<String> splitInput(String input) {
        ArrayList<String> res = new ArrayList<>();
        String word = "";
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                res.add(word);
                word = "";
            } else if (isOperator(input.charAt(i))) {
                if (!word.equals("")) {
                    res.add(word);
                    word = "";
                }
                res.add("" + input.charAt(i));
            }
        }
        return res;
    }

    public String parseNextWord(String input) {
        return "";
    }

    public int indexOfMatchingParenthesis(String input) {
        for (int i = 0; i < input.length(); i++) {

        }
        return 0;
    }

    public double addera(double a, double b) {
        return a + b;
    }

    public double multiplicera(double a, double b) {
        return a * b;
    }

    public double dividera(double a, double b) {
        return a/b;

    }
}
