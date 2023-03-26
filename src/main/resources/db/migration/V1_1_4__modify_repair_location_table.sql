# 1. 수리 지역 테이블 수정
alter table happy_home_repair.tb_repair_location add explanation varchar(20) default '' not null after name;
alter table happy_home_repair.tb_repair_location modify mod_dt datetime default (CURRENT_TIMESTAMP) not null comment '수정날짜' after explanation;

# 2.
delimiter $$

drop procedure if exists modify_table_data $$
create procedure modify_table_data()
begin
    if((select explanation
                  from happy_home_repair.tb_repair_location
                  where name = 'SEOUL') = '') then
    update happy_home_repair.tb_repair_location
    set explanation = '서울'
    where name = 'SEOUL';
    end if;

    if((select explanation
                   from happy_home_repair.tb_repair_location
                   where name = 'INCHEON') = '') then
        update happy_home_repair.tb_repair_location
        set explanation = '인천'
        where name = 'INCHEON';
    end if;

    if((select explanation
                   from happy_home_repair.tb_repair_location
                   where name = 'KYEONG_KI_DO') = '') then
        update happy_home_repair.tb_repair_location
        set explanation = '경기도'
        where name = 'KYEONG_KI_DO';
    end if;
end $$

call modify_table_data() $$

delimiter ;
