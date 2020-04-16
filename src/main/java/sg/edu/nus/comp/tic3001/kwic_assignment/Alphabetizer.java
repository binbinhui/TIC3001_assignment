package sg.edu.nus.comp.tic3001.kwic_assignment;


import java.util.*;

class Alphabetizer implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        Lines lines = (Lines) o;
        LinesEvent event = (LinesEvent) arg;
        lines.all().sort(String.CASE_INSENSITIVE_ORDER);
    }
}