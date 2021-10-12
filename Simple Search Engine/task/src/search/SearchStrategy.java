package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public abstract class SearchStrategy {
    ArrayList<String> arrayList;
    HashMap<String,ArrayList<Integer>> wordList;
    String search;
    public void setList(ArrayList<String> arrayList, HashMap<String, ArrayList<Integer>> wordList, String search){
        this.arrayList = arrayList;
        this.wordList = wordList;
        this.search = search;
        doSearch();
    }
    abstract void doSearch();
}

class SearchAnyStrategy extends SearchStrategy {

    @Override
    void doSearch() {

        String[] searchWordList = search.split(" ");
        ArrayList<Integer> arr = new ArrayList<>();
        for (String s : searchWordList) {
            arr.addAll(wordList.get(s.toLowerCase(Locale.ROOT)));
        }
        List<Integer> disArr = arr.stream().distinct().collect(Collectors.toList());
        if (!disArr.isEmpty()) {
            System.out.printf("%s persons found:\n", disArr.size());
            for (Integer integer : disArr) {
                System.out.println(arrayList.get(integer));
            }
        } else {
            System.out.println("No matching people found.");
        }
    }
}

class SearchAllStrategy extends SearchStrategy {

    @Override
    void doSearch() {

        String[] searchWordList = search.split(" ");
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();

        for (String value : searchWordList) {
            ArrayList<Integer> list = wordList.get(value.toLowerCase(Locale.ROOT));
            if(list != null)
                arr.add(list);
        }

        if(arr.isEmpty()) {
            System.out.println("No matching people found.");
            return;
        }

        List<Integer> disArr = arr.get(0);

        for (ArrayList<Integer> integers : arr) {
            disArr.retainAll(integers);
        }

        if (!disArr.isEmpty()) {
            System.out.printf("%s persons found:", disArr.size());
            for (Integer integer : disArr) {
                System.out.println(arrayList.get(integer));
            }
        }

    }
}

class SearchNoneStrategy extends SearchStrategy {

    @Override
    void doSearch() {
        String[] searchWordList = search.split(" ");
        ArrayList<Integer> arr = new ArrayList<>();
        for (String s : searchWordList) {
            arr.addAll(wordList.get(s.toLowerCase(Locale.ROOT)));
        }

        ArrayList<String> noneList = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            if(!arr.contains(i))
            {
                noneList.add(arrayList.get(i));
            }
        }

        if (!noneList.isEmpty()) {
            System.out.printf("%s persons found:\n", noneList.size());
            for (String str : noneList) {
                System.out.println(str);
            }
        } else {
            System.out.println("No matching people found.");
        }
    }
}