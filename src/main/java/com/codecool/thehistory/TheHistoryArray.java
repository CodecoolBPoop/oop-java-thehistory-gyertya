package com.codecool.thehistory;

public class TheHistoryArray implements TheHistory {

    /**
     * This implementation should use a String array so don't change that!
     */
    private String[] wordsArray = new String[0];

    @Override
    public void add(String text) {
        wordsArray = text.split("\\s+");
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        int count = 0;
        for (String word : wordsArray) {
            if (word == wordToBeRemoved) {
                count++;
            }
        }

        int j = 0;

        String[] tempWordsArray = new String[size() - count + 1];
        for (int i = 0; i < size(); i++) {
            if (wordsArray[i] != (wordToBeRemoved)) {
                tempWordsArray[j++] = wordsArray[i];

            }
        }
        wordsArray = tempWordsArray;
    }

    @Override
    public int size() {
        return wordsArray.length;
    }

    @Override
    public void clear() {
        wordsArray = new String[0];
    }

    @Override
    public void replaceOneWord(String from, String to) {
        int s = size();
//        System.out.println(from + to);
        for (int i = 0; i < s; i++) {
            if (from.equals(wordsArray[i])) {
                wordsArray[i] = to;
            }
        }
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        int counter = 0;
        String indexes = "";
        for (int wordIndex = 0; wordIndex < size(); wordIndex++) {
            if (fromWords[0].equals(wordsArray[wordIndex])) {
                for (int i = 0; i < fromWords.length; i++) {
                    if (wordIndex + i >= size() || !wordsArray[wordIndex + i].equals(fromWords[i])) {
                        break;
                    }
                    if (i == fromWords.length - 1) {
                        counter++;
                        indexes += wordIndex + " ";
                        wordIndex += fromWords.length-1;
                    }
                }
            }
        }

        Common common = new Common();
        int[] indexesInt = common.getIndexesFromWord(indexes);

        String[] tempWordsArray = new String[size() - counter * (fromWords.length - toWords.length)];

        int nextIndex = 0;
        int oldIndex = 0;

        for (int i = 0; i < tempWordsArray.length; i++)
            if (nextIndex < counter && oldIndex == indexesInt[nextIndex]) {
                for (int j = 0; j < toWords.length; j++) {
                    tempWordsArray[i+j] = toWords[j];
                }
                oldIndex += fromWords.length;
                i += toWords.length-1;
                nextIndex++;
            } else {
                tempWordsArray[i] = wordsArray[oldIndex];
                oldIndex++;
            }
        wordsArray = tempWordsArray;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsArray) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }
}
