package ru.croc.task13;

import java.util.*;

class FilterComm implements BlackListFilter{
    @Override
    public void filterComments(List<String> comments, Set<String> blackList){
        List<String> RemoveAfter = new ArrayList<>();
        for(String element : comments) {
            if (blackList.contains(element)) {
                RemoveAfter.add(element);
            }
        }
        comments.removeAll(RemoveAfter);
    } 
}
class Main {
    public static void main(String[] args) {
        List<String> comm = new ArrayList<>();
        comm.add("one");
        comm.add("two");
        comm.add("three");
        comm.add("Hello!");
        comm.add("four");
        comm.add("Hey!");
        comm.add("five");
        comm.add("Bye!");
        comm.add("six");
        comm.add("seven");
        comm.add("eight");
        comm.add("nine");
        comm.add("ten");
        Set<String> blackList = new HashSet<>();
        FilterComm a = new FilterComm();
        a.filterComments(comm,blackList);
        System.out.println("Test #1 with empty blackList");
        for (int i = 0; i < comm.size(); i++) {
            System.out.print(comm.get(i) + " ");
        }
        System.out.println();
        System.out.println("Test #2 with non-empty blackList");
        Set<String> blackList_1 = new HashSet<>();
        blackList_1.add("Hello!");
        blackList_1.add("Hey!");
        blackList_1.add("Bye!");
        FilterComm b = new FilterComm();
        b.filterComments(comm,blackList_1);
        for (int i = 0; i < comm.size(); i++) {
            System.out.print(comm.get(i) + " ");
        }
        System.out.println();
    }
}