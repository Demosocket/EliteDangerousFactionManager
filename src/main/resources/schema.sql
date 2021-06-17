drop table if exists elite.tasks;
drop table if exists elite.influence;
drop table if exists elite.systems;
drop table if exists elite.users;

create table elite.users
(
    id            bigserial not null
        constraint users_pkey
            primary key,
    enabled       boolean,
    hash_password varchar(255),
    user_role     varchar(255),
    username      varchar(255)
);

alter table elite.users
    owner to postgres;

create table elite.systems
(
    id                   bigserial not null
        constraint systems_pkey
            primary key,
    expansion_date       date,
    faction              varchar(255),
    name                 varchar(255),
    number               integer,
    orbit_large          integer,
    orbit_large_control  integer,
    orbit_medium         integer,
    orbit_medium_control integer,
    planet_base          integer,
    planet_base_control  integer,
    planet_large         integer,
    planet_large_control integer,
    population           bigint,
    primary_economy      varchar(255),
    secondary_economy    varchar(255)
);

alter table elite.systems
    owner to postgres;

create table elite.influence
(
    id         bigserial not null
        constraint influence_pkey
            primary key,
    date       date,
    influence  integer,
    state      varchar(255),
    systems_id bigint
        references systems
);

alter table elite.influence
    owner to postgres;

create table elite.tasks
(
    id               bigserial not null
        constraint tasks_pkey
            primary key,
    combat_task      varchar(255),
    date             date,
    do_nothing       varchar(255),
    election_task    varchar(255),
    message_header   varchar(255),
    task_1           varchar(255),
    task_2           varchar(255),
    task_description varchar(255),
    user_id          bigint
        references users
);

alter table elite.tasks
    owner to postgres;