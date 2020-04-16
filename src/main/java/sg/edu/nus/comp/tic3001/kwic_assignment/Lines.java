package sg.edu.nus.comp.tic3001.kwic_assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

class Lines extends Observable {

    private List<String> lines = new ArrayList<>();

    void insert(String line) {
        lines.add(line);
        setChanged();
        notifyObservers(new LinesEvent(line));
    }
    void delete(int index) {
        lines.remove(index);
    }
    String get(int index) {
        return lines.get(index);
    }
    List<String> all() {
        return lines;
    }
}
