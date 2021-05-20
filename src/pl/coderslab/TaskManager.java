package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TaskManager {

    static String[][] tasks;

    public static void main(String[] args) {

        tasks = readCsv("tasks.csv");
        printOption();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equals("exit")) {

            switch (input) {
                case "add":
//                    addTask();
                    System.out.println("add");
                    break;
                case "remove":
                     removeTask();
                    System.out.println("remove");
                    break;
                case "list":
                    listTask();
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("Please select a correct option.");
            }
            printOption();
            input = scanner.nextLine();

        }



    }
    public static void printOption() {
        System.out.println(ConsoleColors.BLUE + "Please select an option: ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("add" + "\nremove" + "\nlist" + "\nexit");
    }

    public static String[][] readCsv(String fileName) {
       StringBuilder sb = new StringBuilder();
        try {
            FileReader reader = new FileReader(fileName);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
               String line = scanner.nextLine();
               sb.append(line).append("\n");
//               String[] newLine = line.split(", ");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String content = sb.toString();
        String[] lines = content.split("\n");
        String[][] result = new String[lines.length][3];
        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i].split(", ");
            result[i] = line;
        }
        return result;
    }

    public static void removeTask() {

    }

    public static void listTask(){
        System.out.println("list");
        for (int i = 0; i < tasks.length; i++) {
            String[] task = tasks[i];
            System.out.println(i+" : " + task[0] + " "+ task[1] + " " + task[2]);

        }
    }


}
