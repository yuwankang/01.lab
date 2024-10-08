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

@Entity
public class Team1 {

	@Id //pk 
	@GeneratedValue //sequence
	@Column(name = "team_id") //team_id
	private long teamId;//team_id 언더바는 상수 아니면 쓰지 않는다.
	
	//Team1(String teamName){}
	@NonNull
	@Column(name="team_name", length = 20)
	private String teamName;
	
}

/*
create sequence hibernate_sequence start with 1 increment by 1
create table Team1 (
	team_id number(19,0) not null, 
	team_name varchar2(20), 
	primary key (team_id)
);
*/