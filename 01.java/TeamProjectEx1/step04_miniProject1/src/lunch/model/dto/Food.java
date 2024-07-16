package lunch.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Food {
    private String food;
    private String menu;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("음식 종류 : ");
        builder.append(food);
        builder.append(", menu : ");
        builder.append(menu);

        return builder.toString();  // String으로 반환 메소드 호출
    }
}
