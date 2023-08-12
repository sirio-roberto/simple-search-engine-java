package search;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < args.length - 1; i++) {
            if ("--data".equals(args[i])) {
                SearchApp app = new SearchApp(args[i + 1]);
                app.run();
                break;
            }
        }
    }
}
