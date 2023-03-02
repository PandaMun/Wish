### 삼성 SW 청년 아카데미 1학기 관통프로젝트 : Wish(Where Is My Home)[부동산 실거래 매매가 조회 사이트]

---

### 1. 기획 배경 및 목표

- **프로젝트 목표 및 사용 데이터(Project Objectives And Usage Data)**
    - **프로젝트 목표**
        1. Web Architecture을 이해하고 활용하여 Web Project를 설계하고 구현할수 있다.
        2. Spring Framework, JPA, Vue.js를 이해하고 활용할수 있다.
        3. OAuth로그인, REST API등의 기술을 활용하여 MSA를 도입해본다.
        4. 지역별 코로나 현황등을 추가한다.
    - **사용 데이터**
        1. 국토 교통부 아파트/ 연립 다세대 실거래가 자료 - 공공 데이터 포탈
        2. 법정동코드 - 행정표준코드관리시스템
        3. 환경 지도 점검데이터 - 서울 열린 데이터 광장
        4. 상권정보 - 공공 데이터 포탈
        5. 코로나 선별 진료소 현황 - 공공 데이터 포탈
        6. 국민 안심 병원 목록 - 공공 데이터 포탈

### 2. 프로젝트 요구사항

- **기능적 요구사항 및 비 기능적 요구사항(Fuctional And Non-Functional Requirements)**
    - **기능적 요구사항**
        1. [필수] 주택 실거래가 정보 수집 : 국토 교통부의 실거래가 정보를 활용하여 데이터 가공후, 데이터 베이스에 저장한다.
        2. [필수] 주택 실거래가 검색 : 주택 실거래가 정보를 원하는 검색 정보에 따라 검색 결과 데이터 제공
        3. [필수] 회원 관리 : 회원정보 가입, 수정, 검색
        4. [필수] 로그인 관리 : 로그인/ 로그아웃/ 비밀번호 찾기
        5. [추가] 관심지역 정보 관리 : 관심지역을 설정하여 언제든지 그 지역의 정보를 우선적으로 검색할수 있는 기능을 제공한다.
        6. [추가] 주변 탐방 정보 수집 : 공공 데이터 포털의 상권정보를 활용하여 데이터베이스에 저장
        7. [추가] 주변 탐방 업종 정보 검색 : 관련 동네 업종정보를 지도를 통해 제공
        8. [추가] 주변 환경 정보 수집 및 검색 : 서울 열린데이터광장의 환경지도 점검 정보를 활용하여 데이터 가공후, 데이터베이스에 저장하고, 녹지 정보, 폐수배출, 대기 배출과 같은 동네 환경정보를 지도를 통해 제공한다.
        9. [추가] 주택 관련 뉴스 정보 크롤링 : 주택 및 부동산과 관련된 최근 뉴스 정보를 크롤링하여 제공한다.
        10. [추가] 공지사항 관리 : 공지사항 등록, 수정, 삭제, 검색 기능을 제공한다.

    - **비 기능적 요구사항**
        1. 공공데이터의 정확성 : 사용하는 공공 데이터 API에 대해 정확성이 검증되어야 한다.
        2. 가용성 : 언제나 어떤 디바이스로든 서비스가 가능해야 한다.
        3. 응답성 : 사용자의 검색에 대한 결과를 빠르게 응답해야한다.
        4. 사용자 편의성 : 사용자가 웹 사이트에 대한 사전지식이 없더라도, 사용하기 편리해야한다.
        5. MSA : REST API 및 O-AUTH를 적용하여 MSA에 맞게 설계해야한다.


### 3. 개발환경 및 시스템 구조

- **개발 언어 및 프레임 워크(Development Language and Framework)**
    - JDK 11
    - JavaScript
    - IntelliJ Ultimate
    - Spring Boot
    - JPA
    - Vue.js
    - MySQL


### 4. 프로젝트 설계

- **유스케이스 다이어그램(UseCase Diagram)**

    ![UseCaseDiagram](https://user-images.githubusercontent.com/61276800/208332217-24bc6914-c124-42b0-b878-045785818165.png)


- **클래스 다이어그램(Class Diagram)**

    ![ClassDiagram](https://user-images.githubusercontent.com/61276800/208332459-341f602d-d68e-4899-9ae1-d12f300abbf1.png)


- **ER 다이어그램(Entity Relation Diagram)**

    ![ERD](https://user-images.githubusercontent.com/61276800/208332416-adb6f511-5310-4f4e-892b-66a6ff4ac9d4.png)



### 5 . 사용자 인터페이스 구현

- 메인 화면

    ![Main](https://user-images.githubusercontent.com/61276800/208332498-4545a576-56b8-4938-b60e-72162c59b715.png)



- 로그인 화면

    ![Login](https://user-images.githubusercontent.com/61276800/208332531-57586235-fdbd-4dec-a499-87b88ef3b42f.png)



- 검색창에 위치 검색시 자동완성

    ![MainSearch](https://user-images.githubusercontent.com/61276800/208332578-5e7b5d10-10e5-457c-9165-380369e32ecb.png)


- 원하는 지역 검색후 이동시 해당하는 아파트 마커 출력

    ![Search](https://user-images.githubusercontent.com/61276800/208332639-dc990bda-5836-4ca0-861f-3e378bca9fd0.png)



- 지도를 드래그하여 이동하면 타 지역의 아파트 마커 출력

    ![MoveMap](https://user-images.githubusercontent.com/61276800/208332674-0a452492-c388-4a4e-8d66-2520e75b90e7.png)



- 마커 클릭시 아파트 상세정보 출력

    ![SearchDetail](https://user-images.githubusercontent.com/61276800/208332713-8fb81505-0bcb-44c8-93b2-09a635afab3c.png)



- 공지사항 화면

    ![Notice](https://user-images.githubusercontent.com/61276800/208332756-e89c9c06-bf2b-49af-a8db-27016710c858.png)



- QnA 화면

    ![QnA](https://user-images.githubusercontent.com/61276800/208332769-46aeb74b-6da6-4730-b52b-143845da324d.png)


- 아파트 즐겨찾기 페이지

    ![Favorite](https://user-images.githubusercontent.com/61276800/208332835-1b93bcdd-7c05-4241-ab15-2fff7c3bd096.png)

- MyPage

    ![MyPage](https://user-images.githubusercontent.com/61276800/208332872-d71d7406-3708-4b34-9f94-301a34f1652c.png)
