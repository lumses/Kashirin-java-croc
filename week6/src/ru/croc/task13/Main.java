package ru.croc.task13;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

class FilterComm implements BlackListFilter{
    @Override
    public void filterComments(List<String> comments, Set<String> blackList){
        List<String> RemoveAfter = new ArrayList<>();
        for(String element : comments) {
            for (String h: blackList) {
                if (element.contains(h)) {
                    RemoveAfter.add(element);
                }
            }
        }
        comments.removeAll(RemoveAfter);
        // comments.removeIf(i -> blackList.contains(i)); я не понял, почему нельзя написать вот так :(
    } 
}
class Main {
    public static void main(String[] args) {
        List<String> comm = new ArrayList<>();
        comm.add("one");
        comm.add("two");
        comm.add("three");
        comm.add("Hello!\n");
        comm.add("four");
        comm.add("Hey! wee");
        comm.add("five");
        comm.add("Bye!, Goodbye!");
        comm.add("six");
        comm.add("seven");
        comm.add("eight");
        comm.add("ey trash \n,;sddsg");
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
        blackList_1.add("trash");
        FilterComm b = new FilterComm();
        b.filterComments(comm,blackList_1);
        for (int i = 0; i < comm.size(); i++) {
            System.out.print(comm.get(i) + " ");
        }
        System.out.println();
    }
}
