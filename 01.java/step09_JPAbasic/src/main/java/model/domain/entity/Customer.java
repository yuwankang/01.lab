package model.domain.entity;

import javax.persistence.Column;  
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor  
@RequiredArgsConstructor
@Getter
@Setter
@ToString  

@Entity  
public class Customer {
	@Id   
//	@GeneratedValue(strategy = GenerationType.IDENTITY)   //mysql auto_increment 적용
	@GeneratedValue		//oracle용 sequence 적용
	private int no;	
	
	@NonNull 
	@Column(unique = true, nullable = false)  
	private String id;
	
	@NonNull
	@Column(nullable = false, name = "fisaname")
	private String name;
	
	@NonNull
	@Column(nullable = false)
	private int age;
	
}





