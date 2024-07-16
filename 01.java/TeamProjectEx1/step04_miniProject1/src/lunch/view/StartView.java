package lunch.view;
import lunch.controller.LunchProjectController;
import lunch.model.dto.Food;
import lunch.model.dto.LunchProject;
import lunch.model.dto.LunchType;
import lunch.model.dto.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StartView {
    public static void main(String[] args) {
        // 팀 정보
        Team team1 = new Team("1팀", "BTS", "아이유", "블랙핑크", "김범수", "양식");
        Team team2 = new Team("2팀", "손흥민", "황희찬", "이강인", "김민재", "중식");
        Team team3 = new Team("3팀", "유재석", "박명수", "정형돈", "정준하", "일식");
        Team team4 = new Team("4팀", "강호동", "이수근", "김종민", "은지원", "한식");
        Team team5 = new Team("5팀", "강유완", "곽병찬", "구동길", "김상민", "패스트푸드");

        // 음식 종류
        List<Food> foodList = new ArrayList<>();
        foodList.add(new Food("양식", "파스타, 피자"));
        foodList.add(new Food("중식", "짜장면, 짬뽕, 탕수육"));
        foodList.add(new Food("일식", "초밥, 라멘, 돈부리"));
        foodList.add(new Food("한식", "칼국수, 김치찌개"));
        foodList.add(new Food("패스트푸드", "롯데리아, 맥도날드, 맘스터치"));

        // 가게 목록
        List<LunchType> lunchTypeList = new ArrayList<>();
        lunchTypeList.add(new LunchType("양식", "달리 181", "레지아노", "안농"));
        lunchTypeList.add(new LunchType("중식", "모범반점", "샤오바오 ", "안농"));
        lunchTypeList.add(new LunchType("일식", "소마카츠", "오카와리", "안농"));
        lunchTypeList.add(new LunchType("한식", " 이선생", "김둘레순대국 ", "안농"));
        lunchTypeList.add(new LunchType("패스트푸드", "맥도날드", "맘스터치", "안농"));

        // 랜덤 생성기
        Random random = new Random();

        // 프로젝트 생성
        LunchProject[] projects = new LunchProject[5];
        projects[0] = createRandomProject("1번", team1, foodList, lunchTypeList, random);
        projects[1] = createRandomProject("2번", team2, foodList, lunchTypeList, random);
        projects[2] = createRandomProject("3번", team3, foodList, lunchTypeList, random);
        projects[3] = createRandomProject("4번", team4, foodList, lunchTypeList, random);
        projects[4] = createRandomProject("5번", team5, foodList, lunchTypeList, random);

        // 데이터 구성 후 서비스 로직 실행
        LunchProjectController controller = LunchProjectController.getInstance();

        System.out.println("*** 01. Project 생성 ***");
        // 저장 시도시 이미 존재할 경우 예외 발생, 미 존재시 정상 저장 실행
        for (LunchProject project : projects) {
            controller.teamProjectInsert(project);
        }

        System.out.println("\n*** 02. 모든 Project 검색 ***");
        controller.getTeamProjectsList();

        System.out.println("\n*** 03. '1번' Project 검색 ***");
        controller.getTeamProject("1번");

        // 한식 변경하기
        System.out.println("\n*** 04. '1번' Project의 팀순서 변경(수정) 후 해당 Project 검색 ***");
        controller.teamProjectUpdate("1번", team5);
        controller.getTeamProject("1번");

        // 한식 삭제하기
        System.out.println("\n*** 05. '1번' Project 삭제 후 삭제한 Project 존재 여부 검색 ***");
        controller.teamProjectDelete("1번");
        controller.getTeamProject("1번");
    }

    private static LunchProject createRandomProject(String projectId, Team team, List<Food> foodList, List<LunchType> lunchTypeList, Random random) {
        int foodIndex = random.nextInt(foodList.size());
        Food food = foodList.get(foodIndex);

        LunchType lunchType = null;
        for (LunchType lt : lunchTypeList) {
            if (lt.getFoodType().equals(food.getFood())) {  // Food 객체의 getFood() 사용
                lunchType = lt;
                break;
            }
        }

        String comment = switch (food.getFood()) {  // Food 객체의 getFood() 사용
            case "양식" -> "Délicieux";
            case "중식" -> "好吃";
            case "일식" -> "おいしい";
            case "한식" -> "맛있다.";
            case "패스트푸드" -> "Delicious";
            default -> "";
        };

        return new LunchProject(projectId, food.getFood(), team, food, lunchType, "13:00~", "14:00", comment);  // Food 객체의 getFood() 사용
    }
}
