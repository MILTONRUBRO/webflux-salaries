package br.com.devmos.webfluxsalaries.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
	
	private String code;
	private int age;
	private String gender;
	private String educationLevel;
	private String jobTitle;
	private Double yearsOfExperience;
	private Double salary;
}
