package parser;

import static org.junit.Assert.*;

import org.junit.Test;

public class CompilerTest {

	@Test
	public void testCompiler() {
		Compiler compiler = new Compiler();
		compiler.compile("100+2*34\n");
		
	}

}
