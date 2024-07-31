package step02.onetoonejoin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor 
@Getter
@Setter
@ToString

@SequenceGenerator(name = "member_seq", sequenceName = "member_seq_id", 
				   allocationSize = 50, initialValue = 1)
//@Entity
public class Member2 {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq") 
	@Column(name="member_id")
	private long memberId;
	
	@NonNull
	@Column(length = 20) //컬럼 사이즈 20byte
	private String name;
	
	@NonNull
	@Column(name="team_id")
	private long teamId;
	
}
