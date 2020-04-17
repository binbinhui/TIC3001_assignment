package sg.edu.nus.comp.tic3001.kwic_assignment;

import java.io.File;
import java.io.IOException;

import static sg.edu.nus.comp.tic3001.kwic_assignment.Main.FileName.*;
import static sg.edu.nus.comp.tic3001.kwic_assignment.Extensions.*;
import static sg.edu.nus.comp.tic3001.kwic_assignment.Search.isSearch;

public class Main {
    public static void main(String[] args) {
        UI.welcomeMessage();
        UI.enterCommand();
    }

    public static class FileName {
        public static String folder(String fileName) {
            int index = Integer.parseInt(fileName.replaceAll("\\D+", ""));
            return "TIC3001-Assignment3-testcases/Test" + index + "/" + fileName;
        }

        public static String input(int index) {
            return "Titles" + index + ".txt";
        }

        public static String output(int index) {
            return "OurOutput" + index + ".txt";
        }

        public static String ignored(int index) {
            return "Ignored" + index + ".txt";
        }

        public static String required(int index) {
            return "Required" + index + ".txt";
        }

        public static String listOfFiles(int index) {
            return "listOfFiles" + index + ".in";
        }
    }

    public static void runTest(String fileName, int index, int fileIndex) {
    	
    	//System.out.println("filename, index, fileIndex is " + fileName +" " + index +" " + fileIndex +" ");
    	
        Lines lines = new Lines();
        Lines shiftedLines = new Lines();
        Input input = new Input();
        Output output = new Output();

        CircularShift circularShift = new CircularShift(shiftedLines);
        Alphabetizer alphabetizer = new Alphabetizer();

        lines.addObserver(circularShift);
        shiftedLines.addObserver(alphabetizer);
        
        

        if(!isSearch) {
            haveError = false;
            updateAllWords(index);
            checkForMultipleWords(ignoredWords, folder(ignored(index)));
            checkForMultipleWords(requiredWords, folder(required(index)));
            checkForDuplicateWords(index);
            if (haveError) return;
        }

        try {
            input.readFile(lines, new File(folder(fileName)));
            if (!isSearch) output.writeFile(shiftedLines, new File(folder(output(index))), fileName, isSearch);
            else if (isSearch && fileIndex == 0) output.writeFile(shiftedLines, new File(folder(output(index))), fileName, isSearch);
            else output.appendFile(shiftedLines, new File(folder(output(index))), fileName);
            if (fileIndex == 0) UI.fileUpdated(output(index));
        } catch (IOException e) {
            System.out.println("index not found");
        }
    }
}