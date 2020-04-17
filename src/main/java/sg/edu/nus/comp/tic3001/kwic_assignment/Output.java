package sg.edu.nus.comp.tic3001.kwic_assignment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static sg.edu.nus.comp.tic3001.kwic_assignment.Main.FileName.folder;
import static sg.edu.nus.comp.tic3001.kwic_assignment.Main.FileName.input;

class Output {
    void writeFile(Lines lines, File file, String fileName, boolean isSearch) throws IOException {
    	FileWriter fileWriter = null;
      try {
      	fileWriter = new FileWriter(file);
      	if (lines.all().size() != 0) {
          	
      		if (isSearch) fileWriter.append(fileName).append("\n\n");
            for (String line : lines.all()) {
                fileWriter.append(line).append('\n');
            }
            fileWriter.append('\n');
            fileWriter.flush();
      	}
      }catch (IOException ex) {
          System.out.println("file not found");
      } finally {
      	try {
              if (fileWriter != null)
                  fileWriter.close();
          } catch (IOException e) {
             // handle the exception
          }
      	
      }

    }

    void appendFile(Lines lines, File file, String fileName) throws IOException {
    	FileWriter fileWriter = null;
    	
    	  try {
          //	fileWriter = new FileWriter(file);
          	if (lines.all().size() != 0) {
              List<String> currentLines = Files.readAllLines(Paths.get(file.getPath()));
              fileWriter = new FileWriter(file);
              for (String line : currentLines) {
                  fileWriter.append(line).append('\n');
              }
              fileWriter.append(fileName).append("\n\n");
              for (String line : lines.all()) {
                  fileWriter.append(line).append('\n');
              }
              fileWriter.append('\n');
              fileWriter.flush();
            
         }
          }catch (IOException ex) {
              System.out.println("file not found");

          } finally {
          	try {
                  if (fileWriter != null)
                      fileWriter.close();
              } catch (IOException e) {
                 // handle the exception
              }
          	
          }
    }
    
}
