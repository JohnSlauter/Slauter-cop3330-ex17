package org.example;

/*
 *  UCF COP3330 Fall 2021 Assignment 1 Solution
 *  Copyright 2021 John Slauter
 */

import java.util.Scanner;

public class App 
{
    private static final double BAC_limit = 0.08;

    public static void main( String[] args )
    {

        Scanner s = new Scanner(System.in);

        String temp;

        double a_d_ratio, ounces, weight, hours_past, BAC;

        System.out.print("Enter a 1 if you are male or a 2 if you are female: ");

        temp = verify_num(s);

        a_d_ratio = String_to_Int(temp) == 1 ? 0.73 : 0.66;

        System.out.print("How many ounces of alcohol did you have? ");

        temp = verify_num(s);

        ounces = String_to_Double(temp);

        System.out.print("What is your weight, in pounds? ");

        temp = verify_num(s);

        weight = String_to_Double(temp);

        System.out.print("How many hours has it been since your last drink? ");

        temp = verify_num(s);

        hours_past = String_to_Double(temp);

        BAC = ounces * 5.14 / weight * a_d_ratio - 0.015 * hours_past;

        temp = String.format("Your BAC is %f\n%s", BAC, BAC > BAC_limit ? "It is not legal for you to drive."
                        : "It is legal for you to drive.");

        System.out.print(temp);

        s.close();

    }

    private static String verify_num(Scanner s){

        String in = s.nextLine();

        int flag = 0;

        if(in.equals(".")){

            flag = 1;

        }

        for(int i = 0; i < in.length(); i++){

            if(in.charAt(i) < '0' || in.charAt(i) > '9'){

                if(in.charAt(i) == '.' && flag < 1){

                    flag++;

                    continue;

                }

                System.out.print("Please enter a number. ");

                return verify_num(s);

            }

        }

        return in;

    }

    private static int String_to_Int(String num){

        int res = 0;

        for(int i = 0; i < num.length(); i++){

            res += (num.charAt(i)-'0')*pow(10, num.length()-1-i);

        }

        return res;

    }

    private static double String_to_Double(String num){

        double res = 0.0;

        int decimal_index = num.indexOf("."), flag = 1;

        if(decimal_index == -1){

            return(String_to_Int(num));

        }

        for(int i = 0; i < num.length(); i++) {

            if (i == decimal_index) {

                flag = 0;

                continue;

            }

            res += (num.charAt(i) - '0') * pow(10, decimal_index - flag - i);

        }

        return res;

    }

    private static double pow(int base, int exponent){

        if(exponent < 0){

            pow_neg_exp(base, -1*exponent);

        }

        int res = 1;

        for(int i = 0; i < exponent; i++){

            res *= base;

        }

        return res;

    }

    private static double pow_neg_exp(int base, int exponent){

        double res = 1;

        for(int i = 0; i < exponent; i++){

            res /= base;

        }

        return res;

    }

}
