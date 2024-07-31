package step11_myArt;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq_id", allocationSize = 50, initialValue = 1)
@Entity
public class Emp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq") 
	@Column(name="empno")
	private Long empNo;
	
	@NonNull
	@Column(name="ename", length=20)
	private String eName;
	
	@NonNull
	@Column(name="job", length=20)
	private String job;
	
	@NonNull
	@Column(name="mgr", length = 20)
	private Long manager;
	
	@NonNull
	@Column(name="hiredate")
	private Date hireDate;
	
	@NonNull
	@Column(name="sal")
	private Long salary;
	
	@NonNull
	@Column(name="comm")
	private Long comm;
	
	@NonNull
	@OneToOne
	@JoinColumn(name="DEPTNO")
	private Dept deptNo;
}