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
            result = parse(input);
            System.out.println(result);
        }
        System.out.println("Hej då");
    }

    public double parse(String input) {
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
        return addera(Double.valueOf(word1), Double.valueOf(word2));
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
}
