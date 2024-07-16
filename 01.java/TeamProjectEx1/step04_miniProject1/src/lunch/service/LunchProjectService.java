package lunch.service;

import lunch.model.dto.Food;
import lunch.model.dto.LunchProject;
import lunch.model.dto.Team;

import java.util.ArrayList;
import java.util.List;

public class LunchProjectService {
    // Singleton pattern
    private static final LunchProjectService instance = new LunchProjectService();
    private final List<LunchProject> teamProjectList = new ArrayList<>();

    private LunchProjectService() {}

    public static LunchProjectService getInstance() {
        return instance;
    }

    /**
     * 모든 Project 검색
     *
     * @return 모든 Project 목록
     */
    public List<LunchProject> getTeamProjectsList() {
        return new ArrayList<>(teamProjectList);
    }

    /**
     * Project 이름으로 검색 - 해당 Project 반환
     *
     * @param projectName 프로젝트 이름
     * @return 검색된 프로젝트
     */
    public LunchProject getTeamProject(String projectName) {
        for (LunchProject project : teamProjectList) {
            if (project != null && project.getTeamName().equals(projectName)) {
                return project;
            }
        }
        return null;
    }

    /**
     * 새로운 Project 추가
     *
     * @param project 저장할 새로운 프로젝트
     * @throws Exception 프로젝트 이름이 이미 존재할 경우
     */
    public void teamProjectInsert(LunchProject project) throws Exception {
        if (getTeamProject(project.getTeamName()) != null) {
            throw new Exception("해당 프로젝트 이름은 이미 존재합니다. 다시 확인하세요.");
        }
        teamProjectList.add(project);
    }

    /**
     * 프로젝트의 팀 정보 수정
     *
     * @param projectName 프로젝트 이름
     * @param team 수정할 팀 정보
     * @throws Exception 프로젝트 또는 팀 정보가 존재하지 않을 경우
     */
    public void teamProjectUpdate(String projectName, Team team) throws Exception {
        LunchProject project = getTeamProject(projectName);
        if (project == null) {
            throw new Exception("프로젝트 이름을 다시 확인하세요.");
        }
        if (team == null) {
            throw new Exception("수정할 팀 정보가 누락되었습니다. 다시 확인하세요.");
        }
        project.setProjectTeam(team);
    }

    /**
     * 프로젝트의 음식 정보 수정
     *
     * @param projectName 프로젝트 이름
     * @param food 수정할 음식 정보
     * @throws Exception 프로젝트가 존재하지 않을 경우
     */
    public void foodProjectUpdate(String projectName, Food food) throws Exception {
        LunchProject project = getTeamProject(projectName);
        if (project == null) {
            throw new Exception("프로젝트 이름을 다시 확인하세요.");
        }
        project.setProjectFood(food);
    }

    /**
     * 프로젝트 삭제
     *
     * @param projectName 삭제할 프로젝트 이름
     * @throws Exception 프로젝트가 존재하지 않을 경우
     */
    public void teamProjectDelete(String projectName) throws Exception {
        LunchProject project = getTeamProject(projectName);
        if (project == null) {
            throw new Exception("프로젝트 이름을 다시 확인하세요.");
        }
        teamProjectList.remove(project);
    }
}
