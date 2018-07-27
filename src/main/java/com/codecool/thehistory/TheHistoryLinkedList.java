package com.codecool.thehistory;

import java.util.*;

public class TheHistoryLinkedList implements TheHistory {
    /**
     * This implementation should use a String LinkedList so don't change that!
     */
    private List<String> wordsLinkedList = new LinkedList<>();

    @Override
    public void add(String text) {
        wordsLinkedList = new LinkedList(Arrays.asList(text.split(" ")));
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        List<String> removingWord = new LinkedList<>();
        removingWord.add(wordToBeRemoved);
        wordsLinkedList.removeAll(removingWord);
    }

    @Override
    public int size() {
        return wordsLinkedList.size();
    }

    @Override
    public void clear() {
        wordsLinkedList = new LinkedList<>();
    }

    @Override
    public void replaceOneWord(String from, String to) {
        ListIterator it = wordsLinkedList.listIterator();
        while (it.hasNext()) {
            if (from.equals(it.next())) {
                it.remove();
                it.add(to);
            }
        }
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        ListIterator iterator = wordsLinkedList.listIterator();
        while (iterator.hasNext()) {
            boolean findFullMatch = true;
            int offset = 0;
            if (iterator.next().equals(fromWords[0])) {
                for (int i = 1; i < fromWords.length; i++) {
                    offset++;
                    if (iterator.hasNext() && !iterator.next().equals(fromWords[i])) {
                        findFullMatch = false;
                        iterator.previous();
                        break;
                    }
                    if (!iterator.hasNext() && i < fromWords.length - 1) {
                        findFullMatch = false;
                    }
                }
            } else {
                findFullMatch = false;
            }
            if (findFullMatch) {
                for (String fromWord : fromWords) {
                    iterator.previous();
                    iterator.remove();
                }
                for (String toWord : toWords) {
                    iterator.add(toWord);
                }
            }
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsLinkedList) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }

}
