### 일정 관리 앱 만들기
## 프로젝트 소개
일정 등록, 일정 조회 (전체, 단건), 일정 수정, 일정 삭제가 가능한 **일정 관리** Apply 프로그램 개발 프로젝트

---
## 스프링 베이직반 과제 (Posteman API 삭제 실행 첨부)
<img width="741" height="699" alt="image" src="https://github.com/user-attachments/assets/007afd97-1528-4179-be91-1aea16628eb9" />


---
## 프로젝트 구조 설명

### 1. API 명세서
|   기능    |  Method   | URL                                      | request  | response |
|:--------:|:---------:|:-----------------------------------------|:---------|:--------------|
|  일정 등록   |   POST    | /user/scheduler                          |          |               |
|  일정 조회   |    GET    | /user/scheduler/{schedulerId}/{userName} |          |               |
| 일정 단건 조회 |    GET   | /user/scheduler/{schedulerId}/{userName} |          |               |
|  일정 수정   |    PUT    | /user/scheduler/{schedulerId}            |          |               |
|  일정 삭제   |  DELETE   | /user/scheduler/{schedulerId}            |          |               |

### 2. ERD

### 3. 메인 프로젝트 구조
    📁 src/
     └── 📁 main/
          └── 📁 java/
               └── 📁 com/
                   └── 📁 controller/
                   └── 📁 dto/
                   └── 📁 entity/
                   └── 📁 repository/
                   └── 📁 service/
                   └── ScaduleApplication.java

### 4. 사용 언어
- Java
- Spring
- MySQL
- Lombok

---
## 프로젝트 목표
- ```3 Layer Architectur```에 따라 각 Layer 목적에 맞게 개발
- CRUD 필수 기능을 구현하고 Postman 으로 정상 테스트 구현할 수 있다
- 어노테이션을 통해 스프링을 활용할 수 있다

### ◆ 필수 프로젝트

#### ■ Lv.0 : API 명세 및 ERD 작성
- root (최상위) 경로의 ```README.md``` 에 작성 및 첨부
#### ■ Lv.1 : 일정 생성
- 일정 제목, 일정 내용, 작성자명, 비밀번호, 작성/수정일 첨부
- 단, 작성/수정일은 날짜와 시간을 모두 포함
- API 응답에 비밀번호 제외
#### ■ Lv.2 : 일정 조회 (전체, 단건)
- 전체 : 작성자명을 기준으로 등록된 일정 목록 전체 조회
- 수정일 기준 내림차순 정렬
- API 응답에 비밀번호 제외
#### ■ Lv.3 : 선택 일정 수정
- 일정 제목, 작성자명 만 수정 가능
- 수정 요청 시, 비밀번호 함께 전달
- 작성일은 변경할 수 없으며, 수정일은 수정 완료 시, 수정한 시점으로 변경
- API 응답에 비밀번호 제외
#### ■ Lv.4 : 선택 일정 삭제
- 삭제 요청 시, 비밀번호 함께 전달
