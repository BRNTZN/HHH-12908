package org.hibernate.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tree {

	@Id
	private Long id;

	@OneToMany(mappedBy = "tree", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	private List<Branch> branches;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		for (Branch branch : branches) {
			branch.setTree(this);
		}
		this.branches = branches;
	}
}
