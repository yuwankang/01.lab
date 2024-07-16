package controller;

import java.util.ArrayList;

import model.PeopleDAO;
import model.domain.PeopleDTO;
import view.EndView;

public class Controller {
	//모든 사람 정보 요청 및 처리 기능
	public static void allView() {
		ArrayList<PeopleDTO> all = PeopleDAO.getAllPeople();
		EndView.printAll(all);//PeopleDTO들이 저장된 ArrayList
	}
	//수정...
}
