package org.hibernate.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Branch{

	@Id
	private Long id;

	@ManyToOne
	private Tree tree;

	@OneToMany(mappedBy = "branch", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	private List<Leaf> leaves;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tree getTree() {
		return tree;
	}

	public void setTree(Tree tree) {
		this.tree = tree;
	}

	public List<Leaf> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<Leaf> leaves) {
		for (Leaf leaf : leaves) {
			leaf.setBranch(this);
		}
		this.leaves = leaves;
	}
}
