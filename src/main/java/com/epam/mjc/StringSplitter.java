package com.epam.mjc;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public static List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> resultList = new ArrayList<>();
        resultList.add(source);

        for (String delimiter : delimiters) {
            List<String> tempList = new ArrayList<>();
            for (String str : resultList) {
                tempList.addAll(Arrays.stream(str.split(delimiter))
                        .filter(s -> !s.isBlank())
                        .collect(Collectors.toList()));
            }
            resultList = tempList;
        }
        return resultList;
    }
}


