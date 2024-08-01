package step04.onetomany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

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

@SequenceGenerator(name = "team4_seq", sequenceName = "team4_seq_id",
				   initialValue = 1, allocationSize = 50)
@Entity
public class Team4 {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
					generator = "team4_seq")
	@Column(name = "team_id")
	private long teamId;
	
	@NonNull
	@Column(name="team_name", length = 20)
	private String teamName;
	
	// select * from team,member where team.team_id=member.team_id;
	
	// join을 위해서 entity에만 구현하는 변수
	@OneToMany(mappedBy = "teamId") //Member4에서 Team에 조인된 변수명 매핑
	public List<Member4> members = new ArrayList<>();
	
	
	
	
	
}
