package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                    addTask();
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
        System.out.println(ConsoleColors.RED + "Bye, bye!");



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

    public static void addTask() {
        System.out.println("add");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please add task description");
        String description = scanner.nextLine();
        System.out.println("Please add task due date with format yyyy-MM-dd");
        String dateString = scanner.nextLine();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        while (true) {
            try {
                Date date = format.parse(dateString);
                String resultDate = format.format(date);
                if(dateString.equals(resultDate)) {
                    break;
                }
            } catch (ParseException | IllegalArgumentException e) {

            }
            System.out.println("Wrong format. Please add task due date with format yyyy-MM-dd");
            dateString = scanner.nextLine();
        }

        System.out.println("Is your task is important: true/false ?");
        String important = scanner.nextLine();
        while(true) {
            if(important.equalsIgnoreCase("true") || important.equalsIgnoreCase("false")) {
                break;
            }
            System.out.println("Is your task is important: true/false ?");
            important = scanner.nextLine();
        }
        System.out.println("dodaje nowe zadanie");
        String[] task = {description, dateString, important};
        tasks = ArrayUtils.add(tasks, task);

    }

    public static void removeTask() {
        System.out.println("remove");
        System.out.println("Please select number remove.");

        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                String line = scanner.nextLine();
                int indexToRemove = Integer.parseInt(line);
                if(indexToRemove >= 0) {
                    tasks = ArrayUtils.remove(tasks, indexToRemove);
                    System.out.println("Value was successfully deleted.");
                    break;
                }

            } catch (NumberFormatException | IndexOutOfBoundsException e) {
            }
            System.out.println("Incorrect argument passed. Please give number greater or equal 0");
        }

    }

    public static void listTask(){
        System.out.println("list");
        for (int i = 0; i < tasks.length; i++) {
            String[] task = tasks[i];
            System.out.println(i+" : " + task[0] + " "+ task[1] + " " + task[2]);

        }
    }


}
