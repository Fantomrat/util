package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String resultPath = "";
        String prefix = "";
        boolean addingMode = false;
        int statictic = 0;
        ArrayList<String> inFiles = new ArrayList<String>();

        if (args.length == 0) {
            System.out.println("There aro no arguments.");
            System.out.println("Use '-h' to get info");
            System.exit(0);
        }

        for (int i = 0; i < args.length; i++) {

            switch (args[i]) {
                case ("-h"):
                    helpInfoOutput();
                    System.exit(0);
                case ("-o"):
                    resultPath = args[i + 1];
                    i++;
                    break;
                case ("-p"):
                    prefix = args[i + 1];
                    i++;
                    break;

                case ("-a"):
                    addingMode = true;
                    break;

                case ("-s"):
                    statictic = 1;
                    break;

                case ("-f"):
                    statictic = 2;
                    break;

                default:
                    inFiles.add(args[i]);
                    break;


            }

        }

        DataSplit data = new DataSplit(inFiles);

        FileCreation file = new FileCreation(resultPath, addingMode);

        int stringElements = file.createFile(prefix, "strings", data.getStrings());
        int integerElements = file.createFile(prefix, "integers", data.getIntegers());
        int floatElements = file.createFile(prefix, "floats", data.getFloats());


        if (statictic > 0) {
            int totalElements = stringElements + integerElements + floatElements;

            System.out.println("Count of elements adden to '" + prefix + "strings.txt': " + stringElements);
            System.out.println("Count of elements adden to '" + prefix + "integers.txt': " + integerElements);
            System.out.println("Count of elements adden to '" + prefix + "floats.txt': " + floatElements);
            System.out.println("Count of elements adden to all files: " + totalElements);

            if (statictic == 2) {
                System.out.println();
                ArrayList<String> numbers = new ArrayList<>(data.getIntegers());
                numbers.addAll(data.getFloats());

                double max = Double.parseDouble(numbers.get(0).replace(',', '.'));
                double min = Double.parseDouble(numbers.get(0).replace(',', '.'));
                double sum = 0;
                double mid;
                int maxString = data.getStrings().get(0).length();
                int minString = data.getStrings().get(0).length();

                for (String number_s : numbers) {
                    double number = Double.parseDouble(number_s.replace(',', '.'));

                    if (max < number) {
                        max = number;
                    }
                    if (min > number) {
                        min = number;
                    }

                    sum += number;

                }

                mid = sum / numbers.size();



                for (String string : data.getStrings()) {

                    int length = string.length();

                    if (length > maxString) {
                        maxString = length;
                    }
                    if (length < minString) {
                        minString = length;
                    }

                }


                System.out.println("Max value: " + max);
                System.out.println("Min value: " + min);
                System.out.println("Sum: " + sum);
                System.out.println("Middle value: " + mid);
                System.out.println("Max string size: " + maxString);
                System.out.println("Min string size: " + minString);

            }
        }



    }

    private static void helpInfoOutput() {
        String fileName = new java.io.File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName();
        String info = """
        Usage:
            java -jar %s <options> <files>
            
            -h display help message
            -o <result path> sets the output file path
            -p <file prefix> sets the file prefix
            -a switch to the mode of adding to existing files
            -s display short statistics
            -f display full statistics
        """.formatted(fileName);
        System.out.println(info);
    }
}