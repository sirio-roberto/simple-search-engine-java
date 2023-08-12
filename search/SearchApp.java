package search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SearchApp {
    private final Scanner scan = new Scanner(System.in);
    private final String DATA_TYPE = "people";
    private final List<String> data;
    private final Map<String, Set<Integer>> invertedIndexMap;
    private final Set<Command> commands;
    private boolean isRunning;
    private final String FILE_NAME;

    public SearchApp(String fileName) {
        data = new ArrayList<>();
        invertedIndexMap = new HashMap<>();
        commands = new HashSet<>();
        FILE_NAME = fileName;

        commands.add(new AddDataCommand("add"));
        commands.add(new SearchDataCommand("search"));
        commands.add(new PrintAllCommand("printAll"));
        commands.add(new ExitCommand("exit"));
    }

    public void run() {
        isRunning = true;
        getCommandByName("add").execute();
        fillInInvertedIndexMap();

        while (isRunning) {
            showMenu();
            String userChoice = scan.nextLine();
            System.out.println();

            Command chosenCommand = getCommandByNumber(userChoice);
            if (chosenCommand != null) {
                chosenCommand.execute();
            } else {
                System.out.println("Incorrect option! Try again.");
            }
            System.out.println();
        }
    }

    private void fillInInvertedIndexMap() {
        for (int i = 0; i < data.size(); i++) {
            for (String word : data.get(i).split(" ")) {
                String lowerWord = word.toLowerCase();
                Set<Integer> values;
                if (invertedIndexMap.containsKey(lowerWord)) {
                    values = invertedIndexMap.get(lowerWord);
                } else {
                    values = new HashSet<>();
                }
                values.add(i);
                invertedIndexMap.put(lowerWord, values);
            }
        }
    }

    private Command getCommandByNumber(String userChoice) {
        return switch (userChoice) {
            case "1" -> getCommandByName("search");
            case "2" -> getCommandByName("printAll");
            case "0" -> getCommandByName("exit");
            default -> null;
        };
    }

    private Command getCommandByName(String name) {
        for (Command command: commands) {
            if (command.name.equals(name)) {
                return command;
            }
        }
        return null;
    }

    private void showMenu() {
        System.out.println("""
                === Menu ===
                1. Find a person
                2. Print all people
                0. Exit""");
    }

    private static abstract class Command {
        private final String name;

        public Command(String name) {
            this.name = name;
        }

        abstract void execute();
    }

    private class AddDataCommand extends Command {

        public AddDataCommand(String name) {
            super(name);
        }

        @Override
        void execute() {
            List<String> fileRows = getFileRows(FILE_NAME);
            data.addAll(fileRows);
        }

        private List<String> getFileRows(String fileName) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                return reader.lines().toList();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return new ArrayList<>();
        }
    }

    private class SearchDataCommand extends Command {

        public SearchDataCommand(String name) {
            super(name);
        }

        @Override
        void execute() {
            System.out.printf("Enter data to search %s:\n", DATA_TYPE);
            String wordToSearch = scan.nextLine();

            if (invertedIndexMap.containsKey(wordToSearch.toLowerCase())) {
                invertedIndexMap.get(wordToSearch.toLowerCase()).forEach(i -> System.out.println(data.get(i)));
            } else {
                System.out.printf("No matching %s found.\n", DATA_TYPE);
            }
        }
    }

    private class PrintAllCommand extends Command {

        public PrintAllCommand(String name) {
            super(name);
        }

        @Override
        void execute() {
            System.out.printf("=== List of %s ===\n", DATA_TYPE);
            data.forEach(System.out::println);
        }
    }

    private class ExitCommand extends Command {

        public ExitCommand(String name) {
            super(name);
        }

        @Override
        void execute() {
            isRunning = false;
            System.out.println("Bye!");
            scan.close();
        }
    }
}
