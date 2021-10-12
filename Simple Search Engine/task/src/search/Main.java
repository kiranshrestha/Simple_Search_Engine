package search;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        SearchEngine searchEngine = new SearchEngine(s);

        File file = new File(args[1]);
        searchEngine.readPeopleDataFromFile(file);
        searchEngine.showMenu();

    }
}
