package lunch.controller;

import lunch.model.dto.LunchProject;
import lunch.model.dto.Team;
import lunch.service.LunchProjectService;
import lunch.view.EndView;
import lunch.view.FailView;

public class LunchProjectController {
    private static final LunchProjectController instance = new LunchProjectController();
    private static final LunchProjectService service = LunchProjectService.getInstance();

    private LunchProjectController() {}

    public static LunchProjectController getInstance() {
        return instance;
    }

    /**
     * 모든 Project 검색
     */
    public void getTeamProjectsList() {
        try {
            EndView.projectListView(service.getTeamProjectsList());
        } catch (Exception e) {
            FailView.failViewMessage("프로젝트 목록을 가져오는 데 실패했습니다: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 특정 Project 검색
     * 
     * @param projectName 프로젝트 이름
     */
    public void getTeamProject(String projectName) {
        try {
            EndView.projectView(service.getTeamProject(projectName));
        } catch (Exception e) {
            FailView.failViewMessage("프로젝트를 가져오는 데 실패했습니다: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 새로운 Project 생성
     * 
     * @param project 생성할 프로젝트
     */
    public void teamProjectInsert(LunchProject project) {
        String projectName = project.getTeamName();
        if (projectName != null && !projectName.isEmpty()) {
            try {
                service.teamProjectInsert(project);
                EndView.successMessage("새로운 프로젝트 등록에 성공했습니다.");
            } catch (Exception e) {
                FailView.failViewMessage("프로젝트 등록에 실패했습니다: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            FailView.failViewMessage("입력 부족, 다시 확인하세요.");
        }
    }

    /**
     * 특정 Project의 팀 업데이트
     * 
     * @param projectName 업데이트할 프로젝트의 이름
     * @param team 업데이트할 팀 정보
     */
    public void teamProjectUpdate(String projectName, Team team) {
        try {
            service.teamProjectUpdate(projectName, team);
            EndView.successMessage("프로젝트 업데이트에 성공했습니다.");
        } catch (Exception e) {
            FailView.failViewMessage("프로젝트 업데이트에 실패했습니다: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 특정 Project 삭제
     * 
     * @param projectName 삭제할 프로젝트의 이름
     */
    public void teamProjectDelete(String projectName) {
        try {
            service.teamProjectDelete(projectName);
            EndView.successMessage("프로젝트 삭제에 성공했습니다.");
        } catch (Exception e) {
            FailView.failViewMessage("프로젝트 삭제에 실패했습니다: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
