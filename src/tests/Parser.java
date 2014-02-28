package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import treeBuilder.Compiler;

public class Parser {

	Compiler compiler;
	
	@Before 
	public void method() {
		compiler = new Compiler();
	}
	
	@Test
	public void testBrackets() {
		String tree = compiler.compile("(a)");
		assertEquals("a&b->a: ", tree, "(prog (expr ( (expr a) )))");
	}
	
	@Test
	public void testNot() {
		String tree = compiler.compile("!a");
		assertEquals("a&b->a: ", tree, "(prog (expr ! (expr a)))");
	}
	
	@Test
	public void testAnd() {
		String tree = compiler.compile("a&b");
		assertEquals("a&b->a: ", tree, "(prog (expr (expr a) & (expr b)))");
	}
	
	@Test
	public void testOr() {
		String tree = compiler.compile("a|b");
		assertEquals("a&b->a: ", tree, "(prog (expr (expr a) | (expr b)))");
	}
	
	@Test
	public void testAtom() {
		String tree = compiler.compile("a");
		assertEquals("a&b->a: ", tree, "(prog (expr a))");
	}
	
	//Testing priority/order of operations
	
	@Test
	public void testAndImplies() {
		String tree = compiler.compile("a&b->a");
		assertEquals("a&b->a: ", tree, "(prog (expr (expr (expr a) & (expr b)) -> (expr a)))");
	}

	@Test
	public void testImpliesAnd() {
		String tree = compiler.compile("a->a&b");
		assertEquals("a&b->a: ", tree, "(prog (expr (expr a) -> (expr (expr a) & (expr b))))");
	}

	@Test
	public void testNotImplies() {
		String tree = compiler.compile("!a->b");
		assertEquals("a&b->a: ", tree, "(prog (expr (expr ! (expr a)) -> (expr b)))");
	}
	
	@Test
	public void testNotBrackets() {
		String tree = compiler.compile("!(a&b)");
		assertEquals("a&b->a: ", tree, "(prog (expr ! (expr ( (expr (expr a) & (expr b)) ))))");
	}
}
