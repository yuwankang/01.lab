package step01.nonjoin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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

//@Entity
public class Member1 {

	@Id
	@GeneratedValue
	@Column(name="member_id")
	private long memberId;
	
	@NonNull
	@Column(length = 20)
	private String name;
	
	@NonNull
	@Column(name="team_id")
	private long teamId;
	
}
/*
create table Member1 (
	member_id number(19,0) not null, 
	name varchar2(20), 
	team_id number(19,0), 
	primary key (member_id)
);
 */
