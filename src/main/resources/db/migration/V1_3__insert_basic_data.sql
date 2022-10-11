# 1. 공지사항 테이블
delete from web_app_test.tb_notice;
insert into web_app_test.tb_notice(title, content)
values ('테스트_제목1', '테스트_내용1')
     ,('테스트_제목2', '테스트_내용2')
     ,('테스트_제목3', '테스트_내용3')
     ,('테스트_제목4', '테스트_내용4')
     ,('테스트_제목5', '테스트_내용5')
     ,('테스트_제목6', '테스트_내용6');

###################################################################################

# 2. 자주하는 질문 테이블
delete from web_app_test.tb_faq;
insert into web_app_test.tb_faq(title, content)
values ('테스트_제목1', '테스트_내용1')
     ,('테스트_제목2', '테스트_내용2')
     ,('테스트_제목3', '테스트_내용3')
     ,('테스트_제목4', '테스트_내용4')
     ,('테스트_제목5', '테스트_내용5')
     ,('테스트_제목6', '테스트_내용6');

###################################################################################

# 3. 블로그 게시물 테이블
delete from web_app_test.tb_from_post_type;
insert into web_app_test.tb_from_post_type(type) value('MAIN');

insert into web_app_test.tb_blog_posting_info(
    f_t_no, title, img_src, posted_reg_dt
) values(
    1, '제목_테스트', '이미지경로_테스트', current_timestamp()
)
       ,        (
    1, '제목_테스트2', '이미지경로_테스트2', current_timestamp()
);

###########################################################################################################

# 4.기본 골짜 데이터들
delete from web_app_test.tb_repair_type;
insert into web_app_test.tb_repair_type(r_type_order, title, explanation)
values (1, 'PAINT', '친환경 페인트')
     , (2, 'WINDOW', '창호수리 방충망')
     , (3, 'DOOR', '문짝, 문틀')
     , (4, 'MIDDLE_DOOR', '중문, 포켓도어')
     , (5, 'ELSE', '환풍기,건반, 건조대');

delete from web_app_test.tb_repair_location;
insert into web_app_test.tb_repair_location(r_location_order, name)
values (1, 'SEOUL')
     , (2, 'INCHEON')
     , (3, 'KYEONG_KI_DO');

delete from web_app_test.tb_repair_state;
insert into web_app_test.tb_repair_state(name, explanation)
values ('APPLY', '접수')
     , ('FIXING', '수리진행')
     , ('NONE_FIXING', '수리미진행')
     , ('FINISH', '완료');
