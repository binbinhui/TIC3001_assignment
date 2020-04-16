package sg.edu.nus.comp.tic3001.kwic_assignment;

import java.util.List;

public class UI {
    static void welcomeMessage() {
        System.out.println("\033[33m---Welcome to the KWIC Circulator 3 !---");
        System.out.println("To run the search function      | type \033[0m1\033[33m");
        System.out.println("To run internal test (Test0)    | type \033[0m2\033[33m");
        System.out.println("To run Test1 - Test5            | type \033[0m3\033[33m");
        System.out.println("To toggle WordsToIgnore ON/OFF  | type \033[0m4\033[33m");
        System.out.println("To toggle RequiredWords ON/OFF  | type \033[0m5\033[33m");
        System.out.println("To exit the program             | type \033[0mq\033[0m");
    }

    static void enterCommand() {
        System.out.print("\033[0mWordsToIgnore: " + stateOfOption(Extensions.wordsToIgnoreIncluded));
        System.out.println("\t RequiredWords: " + stateOfOption(Extensions.requiredWordsIncluded));
        System.out.print("Your command? ");
        Input.getInput();
    }
    static void enterSearch() {
        System.out.print("\033[0mWordsToIgnore: " + stateOfOption(Extensions.wordsToIgnoreIncluded));
        System.out.println("\t RequiredWords: " + stateOfOption(Extensions.requiredWordsIncluded));
        System.out.print("Enter a word to search: ");
        Input.getSearchInput();
    }

    public static void showSearchWords(List<String> searchWords) {
        System.out.print("Current search word(s): ");
        for (String word: searchWords) System.out.print(word + " ");
       // System.out.println("showSearchWords");
    }
    public static void noWordsFound(List<String> searchWords) {
        System.out.print("No matches found for the word '" + searchWords.get(searchWords.size() - 1) + "'.\nPlease try searching again.\n");
    }

    static String stateOfOption(Boolean option) {
        if (option) return "ON";
        else return "OFF";
    }

    static void optionToggled(boolean option) {
        if (option == sg.edu.nus.comp.tic3001.kwic_assignment.Extensions.wordsToIgnoreIncluded) {
            System.out.println("WordsToIgnore has been toggled to " + stateOfOption(Extensions.wordsToIgnoreIncluded) + ".");
        } else if (option == Extensions.requiredWordsIncluded) {
            System.out.println("RequiredWords has been toggled to " + stateOfOption(Extensions.requiredWordsIncluded) + ".");
        }
    }
    static void toggledOff() {
            System.out.println("WordsToIgnore and RequiredWords has been toggled OFF during search.");
    }

    static void red(String input) {
        System.out.println("\033[31m" + input + "\033[0m");
    }

    static String grey(String input) {
        return "\033[0m" + input + "\033[31m";
    }

    static String yellow(String input) {
        return "\033[33m" + input + "\033[31m";
    }

    static void invalidCommand() {
        red("Please enter a valid command.");
    }

    static void exit() {
        System.out.println("\033[33mThank you for using the KWIC Circulator. Goodbye!");
    }

    static void fileUpdated(String outputName) {
        System.out.println("\033[33m" + outputName + " has been updated.\033[0m");
    }

    static void duplicateWord(String word, String ignore, String required) {
        red("[Error] Output text files were not updated because the word '" + yellow(word) + "' is found in both " + grey(ignore) + " and " + grey(required) + ".\n\t\t" +
                "Please remove '" + yellow(word) + "' from the " + grey(ignore) + " / " + grey(required) + ".");
    }

    static void multipleWord(String word, String fileName) {
        red("[Error] Output text files were not updated because " + grey(fileName) + " has the line '" + yellow(word) + "', which has more than ONE word.\n\t\t" +
                "Please modify '" + yellow(word) + "' in the " + grey(fileName) + " to contain only ONE word.");
    }

    static void ignoreFileIsEmpty(String fileName) {
        System.out.println(yellow("[Warning] ") + grey(fileName) + yellow(" is empty. You may still proceed, but no words will be ignored.\n\t\t  " +
                "You should modify ") + grey(fileName) + yellow(" to avoid this, or toggle ") + grey("WordsToIgnore") + yellow(" to ") + grey("OFF."));
    }

    static void requiredFileIsEmpty(String fileName) {
        System.out.println(yellow("[Warning] ") + grey(fileName) + yellow(" is empty. You may still proceed, but RequiredWords will be turned OFF for this case.\n\t\t  " +
                "You should modify ") + grey(fileName) + yellow(" to avoid this, or toggle ") + grey("RequiredWords") + yellow(" to ") + grey("OFF."));
    }

    static void inputFileIsEmpty(String inputFileName, String outputFileName) {
        System.out.println(yellow("[Warning] ") + grey(inputFileName) + yellow(" is empty. You may still proceed, but ") + grey(outputFileName) + yellow(" will come out empty.\n\t\t  " +
                "You should modify ") + grey(inputFileName) + yellow(" to avoid this."));
    }
}
