package com.model;

import java.util.ArrayList;

public class OfferedCourses {
	
	public String term;
	public ArrayList<String> courseCodes;
	
	
	public OfferedCourses(String term, ArrayList<String> courseCodes) {
		super();
		this.term = term;
		this.courseCodes = courseCodes;
	}
	
	
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public ArrayList<String> getCourseCodes() {
		return courseCodes;
	}
	public void setCourseCodes(ArrayList<String> courseCodes) {
		this.courseCodes = courseCodes;
	}
	
	
	
	
	
	
	

}
