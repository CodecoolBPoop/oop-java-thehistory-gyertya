package com.codecool.thehistory;

import java.util.*;

public class TheHistoryArrayList implements TheHistory {
    /**
     * This implementation should use a String ArrayList so don't change that!
     */
    private List<String> wordsArrayList = new ArrayList<>();

    @Override
    public void add(String text) {
        wordsArrayList = new ArrayList<>(Arrays.asList(text.split("\\s+")));
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        List<String> removingWord = new ArrayList<>();
        removingWord.add(wordToBeRemoved);
        wordsArrayList.removeAll(removingWord);
    }

    @Override
    public int size() {
        return wordsArrayList.size();
    }

    @Override
    public void clear() {
        wordsArrayList = new ArrayList<>();
    }

    @Override
    public void replaceOneWord(String from, String to) {
        Collections.replaceAll(wordsArrayList, from, to);
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        int counter = 0;
        String indexes = "";
        for (int wordIndex = 0; wordIndex < size(); wordIndex++) {
            if (wordsArrayList.get(wordIndex).equals(fromWords[0])) {
                for (int i = 0; i < fromWords.length; i++) {
                    if (wordIndex + i >= size() || !wordsArrayList.get(wordIndex + i).equals(fromWords[i])) {
                        break;
                    }
                    if (i == fromWords.length - 1) {
                        counter++;
                        indexes += wordIndex + " ";
                        wordIndex += fromWords.length - 1;

                    }
                }
            }
        }

        Common common = new Common();
        int[] indexesInt = common.getIndexesFromWord(indexes);
        for (int i = counter - 1; i >= 0; i--) {
            for (int j = 0; j < fromWords.length; j++) {
                wordsArrayList.remove(indexesInt[i]);
            }
            for (int j = toWords.length - 1; j >= 0; j--) {
                wordsArrayList.add(indexesInt[i], toWords[j]);
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsArrayList) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }

}
