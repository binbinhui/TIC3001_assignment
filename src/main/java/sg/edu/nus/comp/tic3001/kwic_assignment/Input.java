package sg.edu.nus.comp.tic3001.kwic_assignment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static sg.edu.nus.comp.tic3001.kwic_assignment.Main.FileName.input;

class Input {
    private static Scanner in = new Scanner(System.in);
    private static boolean isExit = false;

    static void getInput() {
        String line = in.nextLine();
        String command = getCommandWord(line);
        switch (command) {
            case "1":
                Search.isSearch = true;
                Extensions.wordsToIgnoreIncluded = false;
                Extensions.requiredWordsIncluded = false;
                UI.toggledOff();
                UI.enterSearch();
                getSearchInput();
                break;
            case "2":
                Extensions.checkAllFiles();
                Main.runTest(input(0), 0,0);
                break;
            case "3":
                Extensions.checkAllFiles();
                for (int index = 1; index <= 5; ++index) Main.runTest(input(index), index,0);
                break;
            case "4":
                Extensions.wordsToIgnoreIncluded = !Extensions.wordsToIgnoreIncluded;
                UI.optionToggled(Extensions.wordsToIgnoreIncluded);
                break;
            case "5":
                Extensions.requiredWordsIncluded = !Extensions.requiredWordsIncluded;
                UI.optionToggled(Extensions.requiredWordsIncluded);
                break;
            case "q":
                isExit = true;
                UI.exit();
                break;
            default:
                UI.invalidCommand();
                break;
        }
        if (!isExit) UI.enterCommand();
    }

    static void getSearchInput() {
        String line = in.nextLine();
        String command = getCommandWord(line);
        switch (command) {
            case "q":
                isExit = true;
                UI.exit();
                break;
            default:
                Search.searchWords.add(command);
                UI.showSearchWords(Search.searchWords);
                Search.searchFiles(0);
                break;
        }
        if (!isExit) UI.enterSearch();
        else System.exit(0);
    }

    static String getCommandWord(String input) {
        return input.trim().split(" ")[0];
    }

    void readFile(Lines lines, File file) throws IOException {
        for (String line : Files.readAllLines(Paths.get(String.valueOf(file)))) {
            if (line.equals("\n")) continue;
            if (line.contains(".")) {
                String[] sentences = line.split("[.]");
                for (String sentence : sentences) lines.insert(sentence.trim());
            } else lines.insert(line);
        }
    }
}
