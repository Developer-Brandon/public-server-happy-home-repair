# https://www.baeldung.com/database-migrations-with-flyway
# https://devbksheen.tistory.com/entry/Spring-boot-Flyway%EB%A1%9C-%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4-%ED%98%95%EC%83%81%EA%B4%80%EB%A6%AC
# https://www.youtube.com/watch?v=_psxzw9xs_M
# https://velog.io/@backtony/Spring-Flyway-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0
# https://gitee.com/staring/mvcFlyway

# 1. 공지사항 테이블
create table if not exists web_app_test.tb_notice (
    NOTICE_NO INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '공지사항 번호' PRIMARY KEY,
    TITLE VARCHAR(20) NOT NULL COMMENT '공지사항 제목',
    CONTENT TEXT NOT NULL COMMENT '공지사항 내용',
    REG_DT DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '공지사항 등록날짜',
    MOD_DT DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '공지사항 수정날짜'
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT '공지사항';

INSERT INTO web_app_test.tb_notice(TITLE, CONTENT)
VALUES ('테스트_제목1', '테스트_내용1')
     ,('테스트_제목2', '테스트_내용2')
     ,('테스트_제목3', '테스트_내용3')
     ,('테스트_제목4', '테스트_내용4')
     ,('테스트_제목5', '테스트_내용5')
     ,('테스트_제목6', '테스트_내용6');

###################################################################################

# 2. 자주하는 질문 테이블
create table if not exists web_app_test.tb_faq (
     faq_no int unsigned auto_increment comment '자주하는 질문 번호' primary key,
     title varchar(20) not null comment '자주하는 질문 제목',
     content text not null comment '자주하는 질문 내용',
     use_yn enum('y', 'n') default 'y' not null comment '자주하는 질문 사용여부',
     mod_dt datetime default current_timestamp not null comment '자주하는 질문 수정날짜',
     reg_dt datetime default current_timestamp not null comment '자주하는 질문 등록날짜'
) engine=innodb default charset=utf8mb4 comment '자주하는 질문';

insert into web_app_test.tb_faq(title, content)
values ('테스트_제목1', '테스트_내용1')
     ,('테스트_제목2', '테스트_내용2')
     ,('테스트_제목3', '테스트_내용3')
     ,('테스트_제목4', '테스트_내용4')
     ,('테스트_제목5', '테스트_내용5')
     ,('테스트_제목6', '테스트_내용6');

###################################################################################

# 3. 블로그 게시물 테이블
create table if not exists web_app_test.tb_blog_posting_info(
    n_b_p_no int unsigned not null comment '포스팅 번호' auto_increment primary key
    , f_t_no int unsigned not null comment '출처 타입 번호'
    , title text not null comment '포스팅 제목'
    , content text not null comment '포스팅 내용'
    , img_src text not null comment '포스팅 메인사진 풀 이미지 경로'
    , posted_reg_dt datetime not null comment '포스팅 등록날짜'
    , reg_dt datetime not null default current_timestamp comment '등록날짜'
) engine=innodb default charset=utf8mb4 comment '해피홈리페어 네이버 블로그 게시물 정보';


create table if not exists web_app_test.tb_from_post_type(
     f_t_no int unsigned not null comment '포스팅 번호' auto_increment primary key
    , type enum('MAIN') not null default 'MAIN' comment '포스팅 타입'
    , reg_dt datetime not null default current_timestamp comment '등록날짜'
    , mod_dt datetime not null default current_timestamp comment '수정날짜'
    ) engine=innodb default charset=utf8mb4 comment '해피홈리페어 네이버 블로그 게시물 출처 타입';

# 3-1. FK 설정
alter table web_app_test.tb_blog_posting_info add foreign key(f_t_no)
    references web_app_test.tb_from_post_type(f_t_no) on delete cascade;

###########################################################################################################

# 4. 견적문의 시작
create table if not exists web_app_test.tb_repair_apply(
   r_apply_no int unsigned not null comment '견적문의 번호' auto_increment primary key
    , r_type_no int unsigned not null comment '수리종류 번호'
    , r_location_no int unsigned not null comment '수리지역 번호'
    , r_state_no int unsigned not null comment '수리상태 번호'
    , user_type enum('CLIENT') not null default 'CLIENT' comment '고객의 종류'
    , phone_number varchar(15) not null comment '견적문의 핸드폰 번호'
    , agree_or_not enum('Y', 'N') not null default 'Y' comment '견적문의 개인정보 동의 여부'
    , explanation varchar(200) not null comment '견적문의 상세내용'
    , reg_dt datetime not null default current_timestamp comment '견적문의 등록날짜'
    , mod_dt datetime not null default current_timestamp comment '견적문의 수정날짜'
) engine=innodb default charset=utf8mb4 comment '견적문의';

#
create table if not exists web_app_test.tb_repair_type(
    r_type_no int unsigned not null comment '수리종류 번호' auto_increment primary key
    , r_type_order int unsigned not null comment '수리종류 순서'
    , title varchar(30) not null comment '수리종류 제목'
    , explanation text not null comment '수리종류 내용'
    , reg_dt datetime not null default current_timestamp comment '수리종류 등록날짜'
    , mod_dt datetime not null default current_timestamp comment '수리종류 수정날짜'
    ) engine=innodb default charset=utf8mb4 comment '수리종류(타입)';

#
create table if not exists web_app_test.tb_repair_location(
    r_location_no int unsigned not null comment '수리지역 번호' auto_increment primary key
    , r_location_order int unsigned not null comment '수리지역 순서'
    , name varchar(20) not null comment '수리지역 이름'
    , reg_dt datetime not null default current_timestamp comment '수리지역 등록날짜'
    , mod_dt datetime not null default current_timestamp comment '수리지역 수정날짜'
    ) engine=innodb default charset=utf8mb4 comment '수리지역';

#
create table if not exists web_app_test.tb_repair_state(
    r_state_no int unsigned not null comment '수리상태 번호' auto_increment primary key
    , name varchar(20) not null comment '수리상태 이름'
    , explanation text not null comment '수리상태 내용'
    , reg_dt datetime not null default current_timestamp comment '수리상태 등록날짜'
    , mod_dt datetime not null default current_timestamp comment '수리상태 수정날짜'
    ) engine=innodb default charset=utf8mb4 comment '수리상태';

# 4-1. FK 설정
alter table web_app_test.tb_repair_apply add foreign key(r_type_no)
    references web_app_test.tb_repair_type(r_type_no) on delete cascade;

alter table web_app_test.tb_repair_apply add foreign key(r_location_no)
    references web_app_test.tb_repair_location(r_location_no) on delete cascade;

alter table web_app_test.tb_repair_apply add foreign key(r_state_no)
    references web_app_test.tb_repair_state(r_state_no) on delete cascade;
