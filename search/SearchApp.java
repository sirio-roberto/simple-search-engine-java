package search;

import java.util.*;

public class SearchApp {
    private final Scanner scan = new Scanner(System.in);
    private final String DATA_TYPE = "people";
    private final Set<String> data;

    public SearchApp() {
        data = new LinkedHashSet<>();
    }

    public void run() {
        new AddDataCommand("add").execute();
        System.out.println();
        new SearchDataCommand("search").execute();
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
            System.out.printf("Enter the number of %s:\n", DATA_TYPE);
            int numOfRecords = Integer.parseInt(scan.nextLine());

            System.out.printf("Enter all %s:\n", DATA_TYPE);
            for (int i = 0; i < numOfRecords; i++) {
                data.add(scan.nextLine());
            }
        }
    }

    private class SearchDataCommand extends Command {

        public SearchDataCommand(String name) {
            super(name);
        }

        @Override
        void execute() {
            System.out.println("Enter the number of search queries:");
            int numOfSearch = Integer.parseInt(scan.nextLine());

            for (int i = 0; i < numOfSearch; i++) {
                System.out.printf("\nEnter data to search %s:\n", DATA_TYPE);
                String wordToSearch = scan.nextLine();

                List<String> foundData = data.stream()
                        .filter(d -> d.toLowerCase().contains(wordToSearch.toLowerCase()))
                        .toList();

                if (foundData.isEmpty()) {
                    System.out.printf("No matching %s found.\n", DATA_TYPE);
                } else {
                    System.out.printf("\nFound %s:\n", DATA_TYPE);
                    foundData.forEach(System.out::println);
                }
            }
        }
    }
}
