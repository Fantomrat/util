package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DataSplit {

    private ArrayList<String> strings = new ArrayList<>();
    private ArrayList<String> integers = new ArrayList<>();
    private ArrayList<String> floats = new ArrayList<>();

    public DataSplit(ArrayList<String> inFiles) {

        ArrayList<String> numbers = new ArrayList<>();

        for (String file : inFiles) {
            for (String text : readFile(file)) {
                if (Pattern.matches("^[+-]?(\\d*[.,]?\\d+)(([Ee][-+]?\\d+)?|([fd])?)$",text)) {
                    numbers.add(text);
                }
                else {
                    strings.add(text);
                }
            }
        }

        for (String number : numbers) {
            double d = Double.parseDouble(number.replace(',', '.'));
            if (d%1 != 0) {
                floats.add(number);
            }
            else {
                integers.add(number);
            }
        }

    }

    private ArrayList<String> readFile(String path) {
        ArrayList<String> fileContent = new ArrayList<>();

        try {
            File file = new File(path);
            Scanner reader = new Scanner(file, "utf-8");
            while (reader.hasNext()) {
                fileContent.add(reader.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File "+ path +" not found");
        }
        return fileContent;
    }

    public ArrayList<String> getStrings() {
        return strings;
    }

    public ArrayList<String> getIntegers() {
        return integers;
    }

    public ArrayList<String> getFloats() {
        return floats;
    }
}
