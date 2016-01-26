package com.company;

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
        String word2= "";
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+'){
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
            res = addera(Double.valueOf(word1), Double.valueOf(word2));
        } catch (Exception e) {
            throw e;
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
}
