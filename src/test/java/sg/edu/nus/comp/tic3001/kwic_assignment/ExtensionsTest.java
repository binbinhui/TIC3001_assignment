package sg.edu.nus.comp.tic3001.kwic_assignment;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

public class ExtensionsTest extends TestCase {
	
	@Test
	public void test() {
		
		Extensions exten = new Extensions();
		List<String> sup = Arrays.asList("sup1", "sup2", "sup3");
		boolean output = exten.checkIfFileIsEmpty(true, sup, "hihi");
		//System.out.print(output);
		assertEquals(false, output);
	}  
	@Test
	public void test2() {
		
		Extensions exten = new Extensions();
		List<String> sup = Arrays.asList("sup1", "sup2", "sup3");
		boolean output = exten.checkIfFileIsEmpty(sup, "haha", "hihi");
		//System.out.print(output);
		assertEquals(false, output);
	}

}
