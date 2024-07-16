package model.domain;

import lombok.Builder;

@Builder
public class Person {
	private String id;
	private int pw;
	private String address;
	
	//public Person() {}
}
