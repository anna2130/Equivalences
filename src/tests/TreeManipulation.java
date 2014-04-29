package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import treeBuilder.Compiler;
import treeBuilder.FormationTree;
import treeManipulation.RuleApplicator;

public class TreeManipulation {

	Compiler compiler;
	RuleApplicator r;
	
	@Before 
	public void method() {
		compiler = new Compiler();
		r = new RuleApplicator();
	}
	
	@Test
	public void testAndCommutativity() {
		FormationTree tree = compiler.compile("q&p");
		r.applyCommutativity(tree, 0, 0);
		assertEquals("q&p: ", tree.toString(), "0-0: & (0-1: p, 1-1: q)");
	}

	@Test
	public void testAndCommutativityComplex() {
		FormationTree tree = compiler.compile("(!q->r)&(p|s)");
		r.applyCommutativity(tree, 0, 0);
		assertEquals("(!q->r)&(p|s): ", tree.toString(), "0-0: & (0-1: | (0-2: p, 1-2: s), 1-1: -> (2-2: ! (4-3: q), 3-2: r))");
	}

	@Test
	public void testAndLeftSimplification() {
		FormationTree tree = compiler.compile("q&p");
		r.applyAndLeftSimplification(tree, 0, 0);
		assertEquals("q&p: ", tree.toString(), "0-0: q");
	}
	
	@Test
	public void testAndLeftSimplificationComplex() {
		FormationTree tree = compiler.compile("(!q->r)&p");
		r.applyAndLeftSimplification(tree, 0, 0);
		assertEquals("(!q->r)&p: ", tree.toString(), "0-0: -> (0-1: ! (0-2: q), 1-1: r)");
	}
	
	@Test
	public void testAndRightSimplification() {
		FormationTree tree = compiler.compile("q&p");
		r.applyAndRightSimplification(tree, 0, 0);
		assertEquals("q&p: ", tree.toString(), "0-0: p");
	}
	
	@Test
	public void testAndRightSimplificationComplex() {
		FormationTree tree = compiler.compile("p&(!q->r)");
		r.applyAndRightSimplification(tree, 0, 0);
		assertEquals("p&(!q->r): ", tree.toString(), "0-0: -> (0-1: ! (0-2: q), 1-1: r)");
	}

	@Test
	public void testAndRightAssociativity() {
		FormationTree tree = compiler.compile("(p&q)&r");
		r.applyAndRightAssociativity(tree, 0, 0);
		assertEquals("(p&q)&r: ", tree.toString(), "0-0: & (0-1: p, 1-1: & (2-2: q, 3-2: r))");
	}
	
	@Test
	public void testAndLeftAssociativity() {
		FormationTree tree = compiler.compile("p&(q&r)");
		r.applyAndLeftAssociativity(tree, 0, 0);
		assertEquals("p&(q&r): ", tree.toString(), "0-0: & (0-1: & (0-2: p, 1-2: q), 1-1: r)");
	}
}
