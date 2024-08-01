package step04.onetomany;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.junit.Test;

import util.DBUtil;

public class Step04RunTest {
	//step03 - 이미 존재하는 table과 데이터가 있다는 가정의 실습
	// 중요 - Member 기준으로 Team 정보 확인
	// 부모 - Team, 자식 - Member
	@Test
	public void step03Test() {
		EntityManager em = DBUtil.getEntityManager();
		
		Member4 m = em.find(Member4.class, 1L);
		
		/* 
		 * select member4x0_.member_id as member_i1_0_0_, 
		 * 			member4x0_.name as name2_0_0_, 
		 * 			member4x0_.team_id as team_id3_0_0_, 
		 * 			team4x1_.team_id as team_id1_1_1_, 
		 * 			team4x1_.team_name as team_nam2_1_1_ 
		 * from ce_member member4x0_, Team4 team4x1_ 
		 * where member4x0_.team_id=team4x1_.team_id(+) and member4x0_.member_id=?
		 *
		 *- step04 Member 클래스에 추가 설정 
		 *	: @ManyToOne(fetch = FetchType.LAZY)
		 *	: 설정의 장점
		 *		- Member 데이터 검색시에 Team join 실행 여부 결정
		 *		- 실제 Team정보를 필요로 하지 않는 로직인 경우 Member만 검색
		 *		- 이미 검색된 member의 Team 정보 검색시점에 Team 검색
		 *		- 결론 : join 문장 아님, 필요시에만 추가 검색
		 *		- 고찰
		 *			join이 빠를지? select 두번 하는게 빠를지?
		 *			select 두번한다의 의미는 DB접속 2번 즉 connection 두번...생성 및 활용 및 반환
		 *		- 참고 : step02 단계는 team 검색
		 *				team table에 member관련 컬럼을 보유하고 있지 않음
		 *				fetch = FetchType.LAZY 관련 없음 
		 *
		 * select member4x0_.member_id as member_i1_0_0_, 
		 * 			member4x0_.name as name2_0_0_, 
		 * 			member4x0_.team_id as team_id3_0_0_ 
		 * from ce_member member4x0_ 
		 * where member4x0_.member_id=?
		 * 
		 * -- 팀 정보 요청시 추가 select 문장 실행
		 * select member4x0_.member_id as member_i1_0_0_, 
		 * member4x0_.name as name2_0_0_, 
		 * member4x0_.team_id as team_id3_0_0_ 
		 * from ce_member member4x0_ 
		 * where member4x0_.member_id=?
		 * 
		 * select team4x0_.team_id as team_id1_1_0_,
		 * 		  team4x0_.team_name as team_nam2_1_0_ 
		 * from Team4 team4x0_ 
		 * where team4x0_.team_id=?
		 * 
		 */
		System.out.println(m.getName());
		System.out.println(m.getTeamId().getTeamName());//? 
		
		em.close();
		em = null;
	}
	
	
	
	//step02 - 이미 존재하는 table과 데이터가 있다는 가정의 실습
	// 중요 : Team 정보 기준으로 Member 정보 확인 
	// persistence.xml none으로 설정후 진행 
	// select 로직	
	/*
	 * step01번 실습 - 단순 팀 이름 검색 
	 * Team4 - @OneToMany(mappedBy = "teamId")
	 * Member4 - @OneToOne
	 * Team4 pk값으로 검색 
	 * 
	 * select team4x0_.team_id as team_id1_1_0_, team4x0_.team_name as team_nam2_1_0_ 
	 * from Team4 team4x0_ 
	 * where team4x0_.team_id=?
	 * 
	 * step02번 실습 - 팀에 소속된 첫번째 팀원 이름 검색 
	 * Team4 - @OneToMany(mappedBy = "teamId")
	 * Member4 - @OneToOne
	 * 
	 * select team4x0_.team_id as team_id1_1_0_, 
	 * 		  team4x0_.team_name as team_nam2_1_0_ 
	 * from Team4 team4x0_ where team4x0_.team_id=?
	 * 
	 * select members0_.team_id as team_id3_0_0_, 
	 * 		  members0_.member_id as member_i1_0_0_, 
	 * 		  members0_.member_id as member_i1_0_1_, 
	 * 		  members0_.name as name2_0_1_, 
	 * 		  members0_.team_id as team_id3_0_1_ 
	 * from ce_member members0_ 
	 * where members0_.team_id=?
	
	 * step03 - Member4의 @OneToOne -> @ManyToOne 으로 변경
	 * 
	 * select team4x0_.team_id as team_id1_1_0_, 
	 * 		  team4x0_.team_name as team_nam2_1_0_ 
	 * from Team4 team4x0_ 
	 * where team4x0_.team_id=?
	 * 
	 * select members0_.team_id as team_id3_0_0_, 
	 * 		  members0_.member_id as member_i1_0_0_, 
	 * 		  members0_.member_id as member_i1_0_1_, 
	 * 		  members0_.name as name2_0_1_, 
	 * 		  members0_.team_id as team_id3_0_1_
	 * from ce_member members0_ 
	 * where members0_.team_id=?
	 * 
	 * 
	 */
//	@Test
	public void step02Test() {
		EntityManager em = DBUtil.getEntityManager();
		
		//step01 - team 검색
		// 1 - 정수(int, 32bit) / Team4의 pk 변수는 long(64bit)
		// int -> long으로 자동 형변환(기본 타입의 기질)
		// Integer 와 Long 객체타입 자동형변환 불가 
		// 1 -> new Integer(1) 자동변환
		// 문제는 Integer가 아닌 Long 타입 필요 따라서 Long객체로 변환 가능한 값 표현
		// long 값 표현시 l or L
		Team4 team = em.find(Team4.class, 1L);  
		System.out.println(team.getTeamName());	
		
		//step02 - 팀원명 검색
		//이미 알고 있는  team_id값으로 member table검색 
		List<Member4> all = team.getMembers();
		System.out.println(all.get(0).getName());
		System.out.println(all.get(1).getName());
		
		//step03 - Member4의 @OneToOne -> @ManyToOne 으로 변경
		
		
		em.close();
		em = null;
	}
	
	
	//step01 - 데이터 저장만
//	@Test
	public void step01Test() {
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = DBUtil.getEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			Team4 t1 = new Team4("축구1팀");
			em.persist(t1);
			em.persist(new Team4("배구1팀"));
			
			em.persist(new Member4("손흥민", t1));  
			em.persist(new Member4("김연경", t1));  
			em.persist(new Member4("박찬호", t1));  
			
			tx.commit();
			
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			if(em != null) {
				em.close();
				em = null;
			}
		}
		
	}
}



