package probono.view;

import probono.controller.TalentDonationProjectController;
import probono.model.dto.Beneficiary;
import probono.model.dto.Donator;
import probono.model.dto.TalentDonationProject;
import probono.model.dto.TalentDonationType;

import java.util.Arrays;
import java.util.List;

public class StartView {

    public static void main(String[] args) {

        // Donators
        List<Donator> donators = Arrays.asList(
            new Donator(7369, "김의사", "kimdoc@company.com", "슈바이처 프로젝트"),
            new Donator(7156, "신예능", "shin@company.com", "오드리햅번 프로젝트"),
            new Donator(8012, "이레사", "lee@company.com", "마더테레사 프로젝트"),
            new Donator(7777, "박메너", "parkdoc@company.com", "슈바이처 프로젝트")
        );

        // Beneficiaries
        List<Beneficiary> beneficiaries = Arrays.asList(
            new Beneficiary(100, "김연약", "010-111-1111", "슈바이처 프로젝트"),
            new Beneficiary(101, "박아트", "010-222-2222", "오드리햅번 프로젝트"),
            new Beneficiary(105, "이건강", "010-555-5555", "마더테레사 프로젝트"),
            new Beneficiary(103, "맘아픔", "010-333-3333", "슈바이처 프로젝트")
        );

        // Talent Donation Types
        List<TalentDonationType> talentDonationTypes = Arrays.asList(
            new TalentDonationType("슈바이처 프로젝트", "의료, 보건, 건강과 관련된 분야",
                "의사, 한의사, 수의사, 스포츠 마사지, 수지침, 이혈, 발마사지 등 의료 활동이나 의료 활동을 위한 후원, 보건, 의료 활동 보조, 대체의학 요법, 보건위생, 응급 처치등"),
            new TalentDonationType("오드리햅번 프로젝트", "문화, 예술 관련 분야",
                "예술가, 문화관련 프로그램 제공, 전시ㆍ관람 등 기회제공, 사진, 영상, 디자인, 메이크업, 마술, 모델, 활용 캠페인 등"),
            new TalentDonationType("마더테레사 프로젝트", "저소득층 및 사회복지 분야",
                "사회복지 관련 시설기관 봉사 및 후원, 독거노인 돌봄, 그룹홈, 쉼터 지원등"),
            new TalentDonationType("키다리아저씨 프로젝트", "멘토링, 상담, 교육, 결연 분야",
                "결연, 상담, 멘토, 독서ㆍ학습지도 및 교육기회 제공, 장학지원, 심리상담 등 멘토링, 상담, 교육, 결연분야"),
            new TalentDonationType("헤라클레스 프로젝트", "체육, 기능, 기술 관련 분야",
                "체육활동 및 교육, 집수리 봉사, 운전, 배송, 엔지니어링, 기술 제공 및 자문등")
        );

        // Talent Donation Projects
        List<TalentDonationProject> projects = Arrays.asList(
            new TalentDonationProject("01슈바이처", donators.get(0), beneficiaries.get(0), talentDonationTypes.get(0), 
                "2024-11-31", "2024-12-03", "아토피 무상 치료"),
            new TalentDonationProject("02오드리햅번", donators.get(1), beneficiaries.get(1), talentDonationTypes.get(1), 
                "2024-11-31", "2024-12-03", "예술가와의 만남"),
            new TalentDonationProject("03마더테레사", donators.get(2), beneficiaries.get(2), talentDonationTypes.get(2), 
                "2024-11-31", "2024-12-03", "독거 노인 식사 제공")
        );

        // Controller
        TalentDonationProjectController controller = TalentDonationProjectController.getInstance();

        System.out.println("*** 01. Project 생성 ***");
        projects.forEach(controller::donationProjectInsert);

        System.out.println("\n*** 02. 모든 Project 검색 ***");
        controller.getDonationProjectsList();

        System.out.println("\n*** 03. '01슈바이처' Project 검색 ***");
        controller.getDonationProject("01슈바이처");

        System.out.println("\n*** 04. '01슈바이처' Project의 기부자 변경(수정) 후 해당 Project 검색 ***");
        // Update project donator
        controller.donationProjectUpdate("01슈바이처", donators.get(3));
        controller.getDonationProject("01슈바이처");

        System.out.println("\n*** 05. '01슈바이처' Project 삭제 후 삭제한 Project 존재 여부 검색 ***");
        // Delete project
        controller.donationProjectDelete("01슈바이처");
        controller.getDonationProject("01슈바이처");
    }
}
