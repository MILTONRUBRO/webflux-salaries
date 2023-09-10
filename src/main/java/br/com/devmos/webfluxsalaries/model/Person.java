package br.com.devmos.webfluxsalaries.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "persons")
public class Person {
	
    @Id
    private String id = new ObjectId().toString();
    
    @Indexed(unique = true, background = true)
    private String code;
	private int age;
	private String gender;
	private String educationLevel;
	private String jobTitle;
	private Double yearsOfExperience;
	private Double salary;
	
	public Person(String code, int age, String gender, String educationLevel, String jobTitle, Double yearsOfExperience,
			Double salary) {
		this.code = code;
		this.age = age;
		this.gender = gender;
		this.educationLevel = educationLevel;
		this.jobTitle = jobTitle;
		this.yearsOfExperience = yearsOfExperience;
		this.salary = salary;
	}
	
	
	

}
