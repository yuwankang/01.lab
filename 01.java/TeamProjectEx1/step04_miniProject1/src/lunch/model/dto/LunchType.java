package lunch.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LunchType {
    //음식 종류
    private String foodType;

    //음식점1
    private String restaurant1;

    //음식점 2
    private String restaurant2;

    //음식점3
    private String restaurant3;

    @Override
    public String toString() {
        return String.format("음식 종류: %s, 맛집 1: %s, 맛집 2: %s, 맛집 3: %s",
                foodType, restaurant1, restaurant2, restaurant3);
    }
}