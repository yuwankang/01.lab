/* 실행 프로세스
 * 
 * StartView -> Controller -> Service -> DAO -> DB
 * -> DAO -> Service -> Controller -> 정상view
 * 								   -> 실패view
 * Controller : 정상? 비정상? 구분
 * 		조건문 또는 예외처리					
 */
package view;

import controller.Controller;

public class StartView {

	public static void main(String[] args) {
		//모든 사람의 정보 요청이라 가정
		Controller.allView();

	}

}
