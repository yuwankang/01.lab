package lunch.model.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LunchProject {
	private String TeamName;
	/** 프로젝트 고유 이름 : lunch- 프로젝트 구분 데이터 */
	private String food;
	/** 팀  : 팀번호 , 팀원명, 팀원명 정보 */
	private Team projectTeam;
	/** 음식 */
	private Food projectFood;
	/** 점심 타입 */
	private LunchType LunchType;
	/** 프로젝트 시작일 */
	private String startDate;
	/** 프로젝트 종료일 */
	private String endDate;
	/** 진행되는 project의 상세 내용 */
	private String Detail;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("팀 순서 : ");
		builder.append(TeamName);
		builder.append("음식종류 : ");
		builder.append(food);
		builder.append(", 팀 : ");
		builder.append(projectTeam);
		builder.append(", 음식 : ");
		builder.append(projectFood);
		builder.append(", 음식 종류 : ");
		builder.append(LunchType);
		builder.append(", 점심시간 시작 : ");
		builder.append(startDate);
		builder.append(", 점심시간 종료 : ");
		builder.append(endDate);
		builder.append(", 기타 내용 : ");
		builder.append(Detail);
		return builder.toString();
	}
}