## fisa 3기 미니프로젝트

  - 기간 : 2024-07-12 
  - 팀원 : 강유완, 곽병찬, 구동길, 김상민
  - 목적 : 수업 실습 코드 분석 및 활용
    
***

### 데이터 구조
1. team
- ("1번 ", "1팀", "조원1이름", "조원2 이름","조원3 이름","조원4 이름", "양식")
- ("2번 ","2팀", "조원1이름", "조원2 이름","조원3 이름","조원4 이름", "중식")
- ("3번 ","3팀", "조원1이름", "조원2 이름","조원3 이름","조원4 이름", "일식")
- ("4번 ","4팀", "조원1이름", "조원2 이름","조원3 이름","조원4 이름", "한식")
- ("5번 ","5팀", "조원1이름", "조원2 이름","조원3 이름","조원4 이름", "패스트푸드")

2. food
- ("양식", "파스타, 피자")
- ("중식", "짜장면, 짬뽕, 탕수육")
- ("일식", "초밥, 라멘, 돈부리")
- ("한식", "칼국수, 김치찌개")
- ("패스트푸드", "롯데리아, 맥도날드, 맘스터치")
 
3. lunchType
 - ("양식", "달리 181", "레지아노","안농")
 - ("중식", "모범반점", "샤오바오 ","안농")
 - ("일식", "소마카츠", "오카와리","안농")
 - ("한식", " 이선생", "김둘레순대국 ","안농")
 - ("패스트푸드", "맥도날드",	"맘스터치","안농")

***

### 실행 순서
1. Project 생성
2. 모든 Project 검색
3. '1번' Project 검색
4. '1번' Project의 팀 순서 변경(수정) 후 해당 Project 검색
5. '1번' Project 삭제 후 삭제한 Project 존재 여부 검색
 
 ***
 ### StartView 클래스
```
+-------------------+             +--------------------------------+
|      Team         |<------------|       StartView                |
+-------------------+             +--------------------------------+
| -teamName: String |             | +main(args: String[]): void    |
| -members: String[]|             +--------------------------------+
+-------------------+

+-------------------+             +-------------------------------+
|      Food         |<------------|       StartView                |
+-------------------+             +-------------------------------+
| -type: String     |             | +main(args: String[]): void   |
| -menu: String     |             +-------------------------------+
+-------------------+

+-------------------+             +-------------------------------+
|    LunchType      |<------------|       StartView                |
+-------------------+             +-------------------------------+
| -type: String     |             | +main(args: String[]): void   |
| -places: String[] |             +-------------------------------+
+-------------------+

+-------------------+             +-------------------------------+
|  LunchProject     |<------------|       StartView                |
+-----------------------+         +-------------------------------+
| -name: String         |         | +main(args: String[]): void   |
| -type: String         |         +-------------------------------+
| -team: Team           |
| -food: Food           |
| -lunchType: LunchType |
| -startTime: String    |
| -endTime: String      |
| -remarks: String      |
+-------------------+

+----------------------+                                     +-------------------------------+
|LunchProjectController|<------------------------------------|       StartView               |
+--------------------------------------------------------+   +-------------------------------+
| +getInstance(): LunchProjectController                 |
| +TeamProjectInsert(project: LunchProject): void        |
| +getTeamProjectsList(): void                           |
| +getTeamProject(name: String): void                    |
| +teamProjectUpdate(name: String, newTeam: Team): void  |
| +teamProjectDelete(name: String): void                 |
+--------------------------------------------------------+

```
 


