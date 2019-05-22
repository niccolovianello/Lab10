package it.polito.tdp.porto.model;

public class Adiacenza {
	
	private Author a1;
	private Author a2;
	private Paper paper;
	
	public Adiacenza(Author a1, Author a2, Paper paper) {
		this.a1 = a1;
		this.a2 = a2;
		this.paper = paper;
	}

	public Author getA1() {
		return a1;
	}

	public void setA1(Author a1) {
		this.a1 = a1;
	}

	public Author getA2() {
		return a2;
	}

	public void setA2(Author a2) {
		this.a2 = a2;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	@Override
	public String toString() {
		return "Adiacenza [a1=" + a1 + ", a2=" + a2 + ", paper=" + paper + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + ((a2 == null) ? 0 : a2.hashCode());
		result = prime * result + ((paper == null) ? 0 : paper.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adiacenza other = (Adiacenza) obj;
		if (a1 == null) {
			if (other.a1 != null)
				return false;
		} else if (!a1.equals(other.a1))
			return false;
		if (a2 == null) {
			if (other.a2 != null)
				return false;
		} else if (!a2.equals(other.a2))
			return false;
		if (paper == null) {
			if (other.paper != null)
				return false;
		} else if (!paper.equals(other.paper))
			return false;
		return true;
	}

	
}
