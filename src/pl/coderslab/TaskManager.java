package pl.coderslab;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TaskManager {

    static String[][] tasks;

    public static void main(String[] args) {
        System.out.println(ConsoleColors.BLUE + "Please select an option: ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("add" + "\nremove" + "\nlist" + "\nexit");

        tasks = readCsv("tasks.csv");


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
}
