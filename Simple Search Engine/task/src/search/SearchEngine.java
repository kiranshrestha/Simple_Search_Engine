package search;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class SearchEngine {
    Scanner s;
    ArrayList<String> arrayList;
    HashMap<String,ArrayList<Integer>> wordList;

    public SearchEngine(Scanner s) {
        this.s = s;
        arrayList = new ArrayList<>();
        wordList = new HashMap<>();
    }



    public void readPeopleDataFromFile(File file) {
       try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
           int count = 0;
           while (true) {
               String line = bufferedReader.readLine();
               if(line == null) {
                   break;
               }
               arrayList.add(line);
               String[] words = line.split(" ");

               for (String word : words) {
                   String key = word.toLowerCase(Locale.ROOT);
                   if (wordList.containsKey(key)) {
                       wordList.get(key).add(count);
                   } else {
                       ArrayList<Integer> value = new ArrayList<>();
                       value.add(count);
                       wordList.put(key, value);
                   }
               }
               count++;
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    void showMenu() {
        System.out.println(
                "=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit");
        int input = s.nextInt();
        s.nextLine();
        System.out.println();

        switch (input) {
            case 1 : {
                SearchStrategy searchStrategy = null;
                System.out.println("Select a matching strategy: ALL, ANY, NONE");

                String type = s.nextLine();

                switch (type) {
                    case "ANY" : {
                        searchStrategy = new SearchAnyStrategy();
                        break;
                    }
                    case "ALL" : {
                        searchStrategy = new SearchAllStrategy();
                        break;
                    }
                    case "NONE" : {
                        searchStrategy = new SearchNoneStrategy();
                        break;
                    }
                }

                System.out.println("Enter a name or email to search all suitable people.");
                String search = s.nextLine();
                System.out.println();
                if (searchStrategy!= null)
                    searchStrategy.setList(arrayList, wordList, search);

                System.out.println();
                showMenu();
                break;
            }
            case 2 : {
                System.out.println("=== List of people ===");
                arrayList.forEach(System.out::println);
                System.out.println();
                showMenu();
                break;
            }
            case 0 : {
                System.out.println("Bye!");
                break;
            }
            default: {
                System.out.println("Incorrect option! Try again.");
                showMenu();
            }
        }
    }
}
