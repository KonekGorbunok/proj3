package org.test3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static ArrayList<String> number =
            new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));

    public static void main(String[] args) throws IOException {
        System.out.println("Введите строку:");
        try (BufferedReader str = new BufferedReader(new InputStreamReader(System.in))){
            String line = str.readLine();
            if(isValidCase(line)){
                if (isSnakeCase(line)){
                    System.out.println(toCamelCase(line));
                } else if (isCamelCase(line)) {
                    System.out.println(toSnakeCase(line));
                } else {
                    System.out.println("Ни Camel, ни case.");
                }
            } else {
                System.out.println("Error!");
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static boolean isValidCase(String str){
        //проверка на пустую строку
        if (str.trim().isEmpty()){
            return false;
        //проверка на первый символ в верхнем регистре
        } else if (Character.isUpperCase(str.charAt(0))) {
            return  false;
        }
        //наименование не может начинаться с цифры.
        for (String s : number){
            if (str.startsWith(s)){
                return false;
            }
        }
        //проверка на 2 символа в верхнем регистре или подчеркивания подчеркивания подряд
        boolean isUpperCase = false;
        int countUpperCase = 0;
        boolean isUnderlining = false;
        int countUnderlining = 0;
        for (char ch : str.toCharArray()){
            if (Character.isUpperCase(ch) && isUpperCase){
                return false;
            } else if (Character.isUpperCase(ch)){
                isUpperCase = true;
                countUpperCase++;
            } else {
                isUpperCase = false;
            }
            if (ch == '_' && isUnderlining){
                return false;
            } else if (ch == '_') {
                isUnderlining = true;
                countUnderlining++;
            } else {
                isUnderlining = false;
            }
        }
        if (countUnderlining > 0 && countUpperCase > 0){
            return false;
        }
        return true;
    }
    public static boolean isSnakeCase (String snakeCase){
        if (!snakeCase.contains(String.valueOf('_'))) {
            return false;
        }
        return true;
    }
    public static boolean isCamelCase (String camelCase){
        boolean upperCase = false;
        for (char c : camelCase.toCharArray()){
            if (Character.isUpperCase(c) && upperCase){
                return false;
            } else if (Character.isUpperCase(c)) {
                upperCase = true;
            } else {
                upperCase = false;
            }
        }
        return true;
    }
    public static String toCamelCase (String snakeCase){
        StringBuilder builder = new StringBuilder();
        boolean upperCase = false;
        for (char c : snakeCase.toCharArray()){
            if (c == '_'){
                upperCase = true;
            } else if (upperCase) {
                builder.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }
    public static String toSnakeCase (String camelCase){
        StringBuilder builder = new StringBuilder();
        for (char c : camelCase.toCharArray()){
            if (Character.isUpperCase(c)){
                builder.append('_').append(Character.toLowerCase(c));
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }
}