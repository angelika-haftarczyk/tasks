package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.FileReader;
import java.io.FileWriter;
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
        saveCsv();

        System.out.println(ConsoleColors.RED + "Bye, bye!");


    }
    // wyświetlanie opcji wyboru
    public static void printOption() {
        System.out.println(ConsoleColors.BLUE + "Please select an option: ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("add" + "\nremove" + "\nlist" + "\nexit");
    }
// nowy sposób wczytywania
    public static String[][] readCsv(String fileName) {
        String[][] result = new String[0][];
        try {
            FileReader reader = new FileReader(fileName);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(", ");
                result = ArrayUtils.add(result, words);
            }
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    // stary sposób wczytywania
//    public static String[][] readCsv(String fileName) {
//        StringBuilder sb = new StringBuilder();
//        try {
//            FileReader reader = new FileReader(fileName);
//            Scanner scanner = new Scanner(reader);
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                sb.append(line).append("\n");
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//        String content = sb.toString();
//        String[] lines = content.split("\n");
//        String[][] result = new String[lines.length][3];
//        for (int i = 0; i < lines.length; i++) {
//            String[] line = lines[i].split(", ");
//            result[i] = line;
//        }
//        return result;
//    }

    //dodawanie zadań
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
                if(dateString.equals(resultDate)) { //porówanie np. czy ktoś nie podał błędnych wartości np. 13 miesiąca
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
        System.out.println("Added new task");
        String[] task = {description, dateString, important};
        tasks = ArrayUtils.add(tasks, task);

    }
// usuwanie zadań
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

    //wyświetlanie listy zadań
    public static void listTask(){
        System.out.println("list");
        for (int i = 0; i < tasks.length; i++) {
            String[] task = tasks[i];
            System.out.println(i+" : " + task[0] + " "+ task[1] + " " + task[2]);

        }
    }

    //zapisywanie i zastępowanie starej listy zadań
    public static void saveCsv() {
        try (FileWriter fileWriter = new FileWriter("tasks.csv", false)){
            for (int i = 0; i < tasks.length; i++) {
                String[] task = tasks[i];
                String description = task[0];
                String date = task[1];
                String important = task[2];
                fileWriter.append(description).append(", ").append(date).append(", ").append(important).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
