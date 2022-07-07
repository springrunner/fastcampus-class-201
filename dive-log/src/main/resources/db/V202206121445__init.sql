#------------------------
# 버전: V209206121445 
# 변경사항: 프로젝트 초기 스크립
#------------------------

create table dive_resort (
       id bigint not null auto_increment,
        address varchar(255),
        contact_number varchar(255),
        created_date_time datetime(6),
        description varchar(255),
        last_modified_date_time datetime(6),
        name varchar(255),
        owner_name varchar(255),
        primary key (id)
    ) engine=InnoDB;

create table dive_point (
   id bigint not null auto_increment,
    created_date_time datetime(6),
    depth varchar(255),
    description varchar(255),
    last_modified_date_time datetime(6),
    name varchar(255),
    dive_resort_id bigint,
    primary key (id)
) engine=InnoDB;

create table dive_log (
   id bigint not null auto_increment,
    buddy_name varchar(255),
    comment varchar(255),
    created_date_time datetime(6),
    dive_date date,
    entry_time time,
    exit_time time,
    last_modified_date_time datetime(6),
    weather varchar(255),
    dive_point_id bigint,
    primary key (id)
) engine=InnoDB;