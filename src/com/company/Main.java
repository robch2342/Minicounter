package com.company;

import java.util.Scanner;

public class Main {
    double result = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Minicounter 0.13");
        String prompt = "";
        while (!prompt.equals("exit")) {
            System.out.print("> ");
            prompt = scanner.nextLine();
            String[] res = prompt.split(" ");
            for (String s : res) {
                System.out.println(s);
            }
        }
        System.out.println("Hej då");
    }

    public double addera(double a, double b) {
        return a + b;
    }
}
