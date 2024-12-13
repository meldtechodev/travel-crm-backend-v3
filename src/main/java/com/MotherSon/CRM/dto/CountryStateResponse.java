package com.MotherSon.CRM.dto;
 
import java.util.List;
 
import com.MotherSon.CRM.models.State;
 
public class CountryStateResponse {
	
	 private List<State> states;
	    private long totalStates;
		public List<State> getStates() {
			return states;
		}
		public void setStates(List<State> states) {
			this.states = states;
		}
		public long getTotalStates() {
			return totalStates;
		}
		public void setTotalStates(long totalStates) {
			this.totalStates = totalStates;
		}
		public CountryStateResponse(List<State> states, long totalStates) {
			super();
			this.states = states;
			this.totalStates = totalStates;
		}

}