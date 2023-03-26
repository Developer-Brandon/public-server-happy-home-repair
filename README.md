## 로컬에서 돌려보시길 원하시는 경우 아래의 글들을 읽어주세요.

### 1. MySQL이 설치되어 있지 않으신 경우는 아래의 노션 페이지에서 설치법을 살펴봐 주세요.

> https://pond-play-786.notion.site/MySQL-d7b37429823b41a9bc89805fa627dc92

### 2. 그 후 계정 생성을 위해 아래의 명령어를 MySQL에서 실행시켜 주세요.

> create user 'testUser'@'localhost' identified by '1111';

> grant all privileges on *.* to 'testUser'@'localhost';

### 3. 그 후 스키마를 생성해주세요.

> create schema if not exists happy_home_repair;

### 4. 아래와 같은 사진이 나오면 빌드에 성공한 것입니다.

> <img src="https://dk-projects-images.s3.ap-northeast-2.amazonaws.com/%EC%9D%B4%EB%AF%B8%EC%A7%80+387.png"  width="500" height="250">
