
package lunch.model.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Team {
	// 팀번호
	private String no;
	
	//이름
	private String name1;
	
	private String name2;
	
	private String name3;
	
	private String name4;
	
	private String food;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("팀 번호 : ");
		builder.append(no);
		builder.append(", 팀원명 : ");
		builder.append(name1);
		builder.append(", 팀원명 : ");
		builder.append(name2);
		builder.append(", 팀원명 : ");
		builder.append(name3);
		builder.append(", 팀원명 : ");
		builder.append(name4);
		builder.append(", 음식 종류 : ");
		builder.append(food);
		return builder.toString();  //String으로 반환 메소드 호출
	}
	
	
}