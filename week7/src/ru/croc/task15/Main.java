package ru.croc.task15;

import java.util.*;
import java.util.function.Predicate;

public interface BlackListFilter<T> {

    default ArrayList<T> filterComments(Iterable<T> comments, Predicate<T> blacklist) {
        ArrayList<T> filterComments = new ArrayList<>();
        for (T x : comments) {
                if(!blacklist.test(x)) {
                    filterComments.add(x);
                }
        }
        return filterComments;
    }
}