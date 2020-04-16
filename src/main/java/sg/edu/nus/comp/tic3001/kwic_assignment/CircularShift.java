package sg.edu.nus.comp.tic3001.kwic_assignment;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static sg.edu.nus.comp.tic3001.kwic_assignment.Extensions.*;
import static sg.edu.nus.comp.tic3001.kwic_assignment.Main.FileName.folder;
import static sg.edu.nus.comp.tic3001.kwic_assignment.Main.FileName.output;
import static sg.edu.nus.comp.tic3001.kwic_assignment.Search.*;

class CircularShift implements Observer {

    private Lines shiftedLines;

    CircularShift(Lines shiftedLines) {
        this.shiftedLines = shiftedLines;
    }

    @Override
    public void update(Observable obs, Object arg) {
        LinesEvent event = (LinesEvent) arg;

        List<String> result = new LinkedList<>();
        List<String> words = new ArrayList<>(Arrays.asList(event.getLine().split(" ")));

        int last = words.size() - 1;
        String firstWord;

        for (int i = 0; i < words.size(); ++i) {
            words.add(0, words.remove(last));
            firstWord = words.get(0).toLowerCase();

            if (!requiredWordsIncluded || requiredWords.contains(firstWord) || requiredWords.size() == 0)
                if (!wordsToIgnoreIncluded || !ignoredWords.contains(firstWord))
                    if (!isSearch || searchWords.contains(firstWord))
                        result.add(arrToString(words));
        }
        for (String shift : result) shiftedLines.insert(shift);
    }

    private String arrToString(List<String> arr) {
        StringBuilder builder = new StringBuilder();
        for (String node : arr) {
            builder.append(node);
            builder.append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
