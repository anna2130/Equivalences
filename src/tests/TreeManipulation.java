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
	public void testAndSimplification() {
		FormationTree tree = compiler.compile("p&p");
		r.applyAndSimplification(tree, 0, 0);
		assertEquals("p&p: ", tree.toString(), "0-0: p");
	}
	
	@Test
	public void testAndSimplificationComplex() {
		FormationTree tree = compiler.compile("(!q->r)&(!q->r)");
		r.applyAndSimplification(tree, 0, 0);
		assertEquals("(!q->r)&(!q->r): ", tree.toString(), "0-0: -> (0-1: ! (0-2: q), 1-1: r)");
	}
	
	@Test
	public void testAndSimplificationComplex2() {
		FormationTree tree = compiler.compile("((p->q)&(p->q))&q");
		r.applyAndSimplification(tree, 0, 1);
		assertEquals("(!q->r)&(!q->r): ", tree.toString(), "0-0: & (0-1: -> (0-2: p, 1-2: q), 1-1: q)");
	}

//	@Test
//	public void testAndLeftSimplification() {
//		FormationTree tree = compiler.compile("q&p");
//		r.applyAndLeftSimplification(tree, 0, 0);
//		assertEquals("q&p: ", tree.toString(), "0-0: q");
//	}
//	
//	@Test
//	public void testAndLeftSimplificationComplex() {
//		FormationTree tree = compiler.compile("(!q->r)&p");
//		r.applyAndLeftSimplification(tree, 0, 0);
//		assertEquals("(!q->r)&p: ", tree.toString(), "0-0: -> (0-1: ! (0-2: q), 1-1: r)");
//	}
//	
//	@Test
//	public void testAndRightSimplification() {
//		FormationTree tree = compiler.compile("q&p");
//		r.applyAndRightSimplification(tree, 0, 0);
//		assertEquals("q&p: ", tree.toString(), "0-0: p");
//	}
//	
//	@Test
//	public void testAndRightSimplificationComplex() {
//		FormationTree tree = compiler.compile("p&(!q->r)");
//		r.applyAndRightSimplification(tree, 0, 0);
//		assertEquals("p&(!q->r): ", tree.toString(), "0-0: -> (0-1: ! (0-2: q), 1-1: r)");
//	}
	
	@Test
	public void testAndRightAssociativity() {
		FormationTree tree = compiler.compile("(p&q)&r");
		r.applyAndRightAssociativity(tree, 0, 0);
		assertEquals("(p&q)&r: ", tree.toString(), "0-0: & (0-1: p, 1-1: & (2-2: q, 3-2: r))");
	}

	@Test
	public void testAndRightAssociativityComplex() {
		FormationTree tree = compiler.compile("t|((!p&q)&(r->s))");
		r.applyAndRightAssociativity(tree, 1, 1);
		assertEquals("t|((!p&q)&(r->s)): ", tree.toString(), "0-0: | (0-1: t, 1-1: & (2-2: ! (4-3: p), 3-2: & (6-3: q, 7-3: -> (14-4: r, 15-4: s))))");
	}
	
	@Test
	public void testAndLeftAssociativity() {
		FormationTree tree = compiler.compile("p&(q&r)");
		r.applyAndLeftAssociativity(tree, 0, 0);
		assertEquals("p&(q&r): ", tree.toString(), "0-0: & (0-1: & (0-2: p, 1-2: q), 1-1: r)");
	}

	@Test
	public void testAndLeftAssociativityComplex() {
		FormationTree tree = compiler.compile("((r->s)&(!p&q))|t");
		r.applyAndLeftAssociativity(tree, 0, 1);
		assertEquals("((r->s)&(!p&q))|t: ", tree.toString(), "0-0: | (0-1: & (0-2: & (0-3: -> (0-4: r, 1-4: s), 1-3: ! (2-4: p)), 1-2: q), 1-1: t)");
	}
	
}
