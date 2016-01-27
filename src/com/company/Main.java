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
        System.out.print("> ");
        input = scanner.nextLine();
        while (!input.equals("exit")) {
            try {
                result = parse(input);
                System.out.println(result);
            } catch (Exception e){
                if (e.getMessage().equals("empty list")) {
                    System.out.println("Write something, you twat!");
                } else {
                    System.out.println("Syntax error: " + e.getMessage());
                }
            }
            System.out.print("> ");
            input = scanner.nextLine();
        }
        System.out.println("Hej då");
    }

    public ArrayList<String> correctNegativeNumbers(ArrayList<String> input) {
        boolean done = false;
        while (!done) {
            done = true;
            for (int i = input.size() - 1; i >= 0; i--) {
                if (input.get(i).equals("-") && (i == 0 || isOperator(input.get(i - 1)))) {
                    input.set(i + 1, Double.toString(-Double.valueOf(input.get(i + 1))));
                    input.remove(i);
                    done = false;
                    break;
                }
            }
        }
        return input;
    }

    public double parse(String input) throws Exception {

        ArrayList<String> expr = correctNegativeNumbers(splitInput(input));

        //Kolla om listan är tom.
        if (expr.size() == 0) {
            throw new Exception("empty list");
        }

        boolean done = false;

        //Räkna ut alla upphöjningar.
        while (!done) {
            done = true;
            //System.out.println(expr);
            for (int i = 0; i < expr.size(); i++) {
                if (expr.get(i).equals("^")) {
                    try{
                        expr.set(i, Double.toString(upphojttill(
                                Double.valueOf(expr.get(i - 1)),
                                Double.valueOf(expr.get(i + 1)))));
                        expr.remove(i + 1);
                        expr.remove(i - 1);
                        done = false;
                        break;
                    } catch (Exception e) {
                        throw new Exception(buildErrorMessage(expr, i));
                    }
                }
            }
        }

        done = false;

        //Räkna ut alla multiplikationer och divisioner först.
        //Loopen repeterar tills det inte finns några fler uträkningar att göra.
        while (!done) {
            //Sätt done till true så att loopen avslutas om vi inte gör något den här körningen.
            done = true;

            //System.out.println(expr);

            //Loopa igenom varje element i listan.
            for (int i = 0; i < expr.size(); i++) {
                //Kolla om det här elementet är en multiplikationsoperator.
                if (expr.get(i).equals("*")) {
                    try{
                        //Sätt det här elementet till produkten av föregående och nästa element.
                        expr.set(i, Double.toString(multiplicera(
                                Double.valueOf(expr.get(i - 1)),
                                Double.valueOf(expr.get(i + 1)))));
                        //Ta bort föregående och nästa element.
                        expr.remove(i + 1);
                        expr.remove(i - 1);
                        //Vi gjorde något den här körningen, så vi vill köra igenom en gång till.
                        done = false;
                        //Längden på listan har ändrats. Säkrast att bryta for-loopen och börja om.
                        break;
                    } catch (Exception e) {
                        throw new Exception(buildErrorMessage(expr, i));
                    }
                } else if (expr.get(i).equals("/")) {
                    try{
                        expr.set(i, Double.toString(dividera(
                                Double.valueOf(expr.get(i - 1)),
                                Double.valueOf(expr.get(i + 1)))));
                        expr.remove(i + 1);
                        expr.remove(i - 1);
                        done = false;
                        break;
                    } catch (Exception e) {
                        throw new Exception(buildErrorMessage(expr, i));
                    }
                }
            }
        }

        done = false;

        //Räkna ut alla additioner och subtraktioner.
        while (!done) {
            done = true;
            //System.out.println(expr);
            for (int i = 0; i < expr.size(); i++) {
                if (expr.get(i).equals("+")) {
                    try{
                        expr.set(i, Double.toString(addera(
                                Double.valueOf(expr.get(i - 1)),
                                Double.valueOf(expr.get(i + 1)))));
                        expr.remove(i + 1);
                        expr.remove(i - 1);
                        done = false;
                        break;
                    } catch (Exception e) {
                        throw new Exception(buildErrorMessage(expr, i));
                    }
                } else if (expr.get(i).equals("-")) {
                    try {
                        expr.set(i, Double.toString(subtrahera(
                                Double.valueOf(expr.get(i - 1)),
                                Double.valueOf(expr.get(i + 1)))));
                        expr.remove(i + 1);
                        expr.remove(i - 1);
                        done = false;
                        break;
                    } catch (Exception e) {
                        throw new Exception(buildErrorMessage(expr, i));
                    }
                }
            }
        }

        //Kolla att vi fick en lista med en enda sträng.
        if (expr.size() == 1) {
            try{
                return Double.valueOf(expr.get(0));
            } catch (Exception e) {
                throw new Exception(input);
            }
        }
        //Annars är det syntax error.
        throw new Exception();
    }

    public String buildErrorMessage(ArrayList<String> input, int errorIndex) {
        String msg = "near";
        for (int i = 0; i < input.size(); i++) {
            msg += " " + input.get(i);
            if (i == errorIndex) {
                msg += "ˇ";
            }
        }
        return msg;
    }

    public boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    public boolean isOperator(String s) {
        if (s.length() == 1) {
            return isOperator(s.charAt(0));
        }
        return false;
    }

    public ArrayList<String> splitInput(String input) {
        ArrayList<String> res = new ArrayList<String>();
        String word = "";
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                if (!word.equals("")) {
                    res.add(word);
                    word = "";
                }
            } else if (isOperator(input.charAt(i))) {
                if (!word.equals("")) {
                    res.add(word);
                    word = "";
                }
                res.add("" + input.charAt(i));
            } else {
                word += input.charAt(i);
            }
        }
        if (!word.equals("")) {
            res.add(word);
        }
        return res;
    }

    public double addera(double a, double b) {
        return a + b;
    }

    public double subtrahera(double a, double b) {
        return a - b;
    }

    public double multiplicera(double a, double b) {
        return a * b;
    }

    public double dividera(double a, double b) {
        return a/b;

    }

    public double upphojttill(double a, double b) throws Exception{
        long exp = Math.round(b);
        double res = 1;
        if (b != 0 && b % exp != 0) {
            throw new Exception();
        } else if (exp > 0) {
            for (long i = 0; i < exp; i++) {
                res *= a;
            }
        }  else if (exp < 0) {
            for (long i = 0; i > exp; i--) {
                res /= a;
            }
        } else {
            return 1;
        }
        return res;
    }
}
