package it.polito.tdp.borders.model;

import java.util.Objects;

public class Border {//sarebbe il mio coppiaC
	private Country paese1;
	private Country paese2;
	
	public Border(Country paese1, Country paese2) {
		super();
		this.paese1 = paese1;
		this.paese2 = paese2;
	}

	public Country getPaese1() {
		return paese1;
	}

	public Country getPaese2() {
		return paese2;
	}

	@Override
	public int hashCode() {
		return Objects.hash(paese1, paese2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Border other = (Border) obj;
		return Objects.equals(paese1, other.paese1) && Objects.equals(paese2, other.paese2);
	}
	
}
