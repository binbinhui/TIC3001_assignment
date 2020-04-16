package sg.edu.nus.comp.tic3001.kwic_assignment;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

public class SearchTest extends TestCase {

    List<String> outPut = new ArrayList<>();
   

	@Test
	public void test() {
		String[] array = new String[] {"CourseTitles0.txt","", "Science Computer", "Science Life","",""};
	
		
		Main main = new Main();
		Search search = new Search();
		Extensions exten = new Extensions();
		
		search.isSearch = true;
		search.searchWords.add("science");
		exten.wordsToIgnoreIncluded = false;
		exten.requiredWordsIncluded = false;
		exten.haveError = false;
		
		String string = "CourseTitles0.txt";
		int index = 0;
		int fileIndex = 00;
		
		main.runTest(string, index, fileIndex);
		
		//File file = new File("./TIC3001-Assignment3-testcases/Test0/OurOutput0.txt");  

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"./TIC3001-Assignment3-testcases/Test0/OurOutput0.txt"));
			String line = reader.readLine();
			while (line != null) {
				
				if(line == "") {
					
				}
				else {
				outPut.add(line);
				//System.out.println(line);
				// read next line
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		for(int i = 0; i < outPut.size(); i ++) {
			
			assertEquals(outPut.get(i), array[i]);
			
		}
			

		
		
	}

}
