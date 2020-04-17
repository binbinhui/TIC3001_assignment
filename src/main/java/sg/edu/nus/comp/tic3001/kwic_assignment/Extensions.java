package sg.edu.nus.comp.tic3001.kwic_assignment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static sg.edu.nus.comp.tic3001.kwic_assignment.Main.FileName.*;

class Extensions {
    static List<String> inputWords = new ArrayList<>();
    static List<String> ignoredWords = new ArrayList<>();
    static List<String> requiredWords = new ArrayList<>();
    static boolean wordsToIgnoreIncluded = true;
    static boolean requiredWordsIncluded = true;
    static boolean haveError = false;
    static Stream<String> stream;

    static void updateAllWords(int index) {
        try {
            inputWords = Files.readAllLines(Paths.get(String.valueOf(new File(folder(input(index))))));
            stream = Files.lines(Paths.get(folder(ignored(index))));
            ignoredWords = stream.map(String::toLowerCase).collect(Collectors.toList());
            stream = Files.lines(Paths.get(folder(required(index))));
            requiredWords = stream.map(String::toLowerCase).collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("index not found");

        }
    }

    static void checkAllFiles() {
        for (int index = 0; index <= 5; ++index) {
            updateAllWords(index);
            checkIfFileIsEmpty(inputWords, input(index), output(index));
            checkIfFileIsEmpty(wordsToIgnoreIncluded, ignoredWords, ignored(index));
            checkIfFileIsEmpty(requiredWordsIncluded, requiredWords, required(index));
        }
    }

    static boolean checkIfFileIsEmpty(Boolean isIncluded, List<String> words, String fileName) {
        if (isIncluded && words.isEmpty()) {
            if (fileName.contains("Ignored")) UI.ignoreFileIsEmpty(fileName);
            else if (fileName.contains("Required")) UI.requiredFileIsEmpty(fileName);
            return true;
        } else return false;
    }

    static boolean checkIfFileIsEmpty(List<String> words, String inputFileName, String outputFileName) {
        if (words.isEmpty()) {
            UI.inputFileIsEmpty(inputFileName, outputFileName);
            return true;
        } else return false;
    }

    static void checkForMultipleWords(List<String> words, String fileName) {
        for (String word : words) {
            if (word.split(" ").length != 1) {
                haveError = true;
                UI.multipleWord(word, fileName);
                break;
            }
        }
    }

    static void checkForDuplicateWords(int index) {
        for (String word : ignoredWords) {
            if (requiredWords.contains(word)) {
                haveError = true;
                UI.duplicateWord(word, ignored(index), required(index));
                break;
            }
        }
    }
}
