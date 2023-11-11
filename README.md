# 편킹 - 비즈니스 로직 서버
> 편의점 할인 정보 통합 조회 시스템인 편킹의 백엔드 비즈니스 로직을 구현하는 모듈입니다. 
> 데이터 수집 모듈이 수집한 데이터를 사용해 안드로이드 어플리케이션에 기능을 제공합니다.
> 상품 인식 모듈과 어플리케이션 사이에서 두 모듈을 간접적으로 연결합니다.

## 주요 담당 기능
### 추천 할인 상품 목록 제공
> 사용자의 어플리케이션 사용 기록을 분석하여 사용자의 취향을 파악하고, 이를 이용해 적절한 추천 행사 상품을 제공합니다.
>
>[![Notion](https://img.shields.io/badge/추천알고리즘_설계기-%23000000.svg?style=for-the-badge&logo=notion&logoColor=white)](https://robinjoon.notion.site/81ea5ddd83f04e6eb81cdc16124c620f?pvs=4)
### 할인 정보 조회
> 현재 진행중인 할인 상품 목록을 제공합니다. 상품명을 기준으로 검색할 수 있습니다.
### 리뷰 작성 및 조회
> 특정 상품에 대한 짧은 평인 리뷰 기능을 제공합니다.
### 개인정보 관리
> 회원가입 및 개인정보 수정 기능을 제공합니다.
## 기술 스택
### 언어
![Java](https://img.shields.io/badge/java_11-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
### 프레임워크
#### 메인
![Spring](https://img.shields.io/badge/spring_boot_2.7.10-%236DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white)
#### ORM
![Spring](https://img.shields.io/badge/spring_data_jpa-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
### 데이터베이스
![MariaDB](https://img.shields.io/badge/MariaDB_10-003545?style=for-the-badge&logo=mariadb&logoColor=white) 