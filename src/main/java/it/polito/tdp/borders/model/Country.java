package it.polito.tdp.borders.model;

import java.util.Objects;

public class Country {
	
	private String stateAbb;
	private int cCode;
	private String stateName;
	
	public Country(String stateAbb, int cCode, String stateName) {
		super();
		this.stateAbb = stateAbb;
		this.cCode = cCode;
		this.stateName = stateName;
	}

	public String getStateAbb() {
		return stateAbb;
	}

	public void setStateAbb(String stateAbb) {
		this.stateAbb = stateAbb;
	}

	public int getcCode() {
		return cCode;
	}

	public void setcCode(int cCode) {
		this.cCode = cCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@Override
	public String toString() {
		return "Country [stateAbb=" + stateAbb + ", cCode=" + cCode + ", stateName=" + stateName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cCode, stateAbb, stateName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return cCode == other.cCode && Objects.equals(stateAbb, other.stateAbb)
				&& Objects.equals(stateName, other.stateName);
	}
	
	
}
