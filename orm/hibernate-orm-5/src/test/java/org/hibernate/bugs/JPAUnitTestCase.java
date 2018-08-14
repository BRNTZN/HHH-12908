package org.hibernate.bugs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.entity.Branch;
import org.hibernate.entity.Leaf;
import org.hibernate.entity.Tree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	public void hhh123Test() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		// Do stuff...

		Leaf leaf1 = new Leaf();
		leaf1.setId(Long.valueOf(1));

		Leaf leaf2 = new Leaf();
		leaf2.setId(Long.valueOf(2));

		Branch branch = new Branch();
		branch.setId(Long.valueOf(3));
		branch.setLeaves(Arrays.asList(leaf1, leaf2));

		Tree tree = new Tree();
		tree.setId(Long.valueOf(4));
		tree.setBranches(Arrays.asList(branch));

		entityManager.persist(tree);

		entityManager.getTransaction().commit();

		entityManager.close();

		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Tree foundTree = entityManager.find(Tree.class, tree.getId());

		assertEquals(1, foundTree.getBranches().size());


		entityManager.close();
	}
}
