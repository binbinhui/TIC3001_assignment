package sg.edu.nus.comp.tic3001.kwic_assignment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.Writer;
import java.util.Scanner;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static sg.edu.nus.comp.tic3001.kwic_assignment.Main.FileName.*;

public class Search {
    static List<String> fileNames = new ArrayList<>();
    static List<String> searchWords = new ArrayList<>();
    static boolean isSearch = false;

    static void searchFiles(int index) {
        Input input = new Input();
        
        System.out.print(searchWords);
        try {
            fileNames = Files.readAllLines(Paths.get(String.valueOf(new File(folder(listOfFiles(index))))));
            System.out.println("filename is "+ fileNames);
            int anyChange = Files.readAllLines(Paths.get(folder(output(index)))).size();
            for (int fileIndex = 0; fileIndex < fileNames.size(); fileIndex++) {
                Main.runTest(fileNames.get(fileIndex), 0, fileIndex);
            }
            if (anyChange == Files.readAllLines(Paths.get(folder(output(index)))).size()) UI.noWordsFound(searchWords);
        } catch (IOException e) {
            System.out.println("index not found");

        }
    }
}