--liquibase formatted sql

--changeset shazam2morrow:1629023273090
--comment: create table for storing restaurant type
create table restaurant_type
(
    id   smallint    not null,
    name varchar(10) not null,
    constraint restaurant_type_pk primary key (id),
    constraint restaurant_type_name_uk unique (name)
);

--changeset shazam2morrow:1629023359214
--comment: insert restaurant types in the table
insert into restaurant_type(id, name)
values (1, 'shop'),
       (2, 'chain'),
       (3, 'restaurant');

--changeset shazam2morrow:1627491879454
--comment: create table for storing information about restaurant
create table restaurant
(
    id              integer        not null,
    type            smallint       not null,
    slug            varchar(32)    not null,
    title           varchar(255)   not null,
    address         varchar(255)   not null,
    rating          smallint       not null default 0,
    created_at      timestamptz    not null default now(),
    updated_at      timestamptz    not null default now(),
    is_active       boolean        not null default false,
    average_receipt numeric(21, 4) not null default 0.0000,
    aliases         text[]         not null default array []::text[],
    images          text[]         not null default array []::text[],
    location        geometry,
    short_title     varchar(100),
    banner_url      varchar(2000),
    description     varchar(4096),
    constraint restaurant_pk primary key (id),
    constraint restaurant_slug_uk unique (slug),
    constraint restaurant_average_receipt_positive check (average_receipt >= 0),
    constraint restaurant_type_fk foreign key (type) references restaurant_type (id),
    constraint restaurant_rating_positive_percent check (rating >= 0 and rating <= 100)
);

--changeset shazam2morrow:1627491934035
--comment: create sequence generator for restaurant table
create sequence restaurant_seq as bigint increment by 1 minvalue 1 no maxvalue start with 1;

--changeset shazam2morrow:1628266278702
--comment: create table for storing day of a week
create table week_day
(
    id   smallint    not null,
    name varchar(10) not null,
    constraint week_day_pk primary key (id),
    constraint week_day_name_uk unique (name)
);

--changeset shazam2morrow:1628266560847
--commment: insert week days in the table
insert into week_day(id, name)
values (1, 'monday'),
       (2, 'tuesday'),
       (3, 'wednesday'),
       (4, 'thursday'),
       (5, 'friday'),
       (6, 'saturday'),
       (7, 'sunday');

--changeset shazam2morrow:1628267092585
--comment: create table for storing restaurant schedule
create table restaurant_schedule
(
    id              integer  not null,
    restaurant_id   integer  not null,
    end_week_day    smallint not null,
    start_week_day  smallint not null,
    end_at_hour     smallint not null default 0,
    start_at_hour   smallint not null default 0,
    end_at_minute   smallint not null default 0,
    start_at_minute smallint not null default 0,
    constraint restaurant_schedule_pk primary key (id),
    constraint restaurant_schedule_uk unique (restaurant_id, start_week_day, end_week_day),
    constraint restaurant_schedule_end_at_hour_valid check (end_at_hour >= 0 and end_at_hour < 24),
    constraint restaurant_schedule_end_week_day_fk foreign key (end_week_day) references week_day (id),
    constraint restaurant_schedule_end_at_minute_valid check (end_at_minute >= 0 and end_at_minute < 60),
    constraint restaurant_schedule_start_at_hour_valid check (start_at_hour >= 0 and start_at_hour < 24),
    constraint restaurant_schedule_restaurantId_fk foreign key (restaurant_id) references restaurant (id),
    constraint restaurant_schedule_start_week_day_fk foreign key (start_week_day) references week_day (id),
    constraint restaurant_schedule_start_at_minute_valid check (start_at_minute >= 0 and start_at_minute < 60)
);

--changeset shazam2morrow:1628267196432
--comment: create sequence generator for restaurant_schedule table
create sequence restaurant_schedule_seq as bigint increment by 1 minvalue 1 no maxvalue start with 1;

--changeset shazam2morrow:1628784424144
--comment: create table for storing category
create table category
(
    id         integer      not null,
    slug       varchar(32)  not null,
    title      varchar(255) not null,
    sort_order smallint     not null default 0,
    is_active  boolean      not null default false,
    created_at timestamptz  not null default now(),
    updated_at timestamptz  not null default now(),
    icon_url   varchar(2000),
    constraint category_pk primary key (id),
    constraint category_slug_uk unique (slug),
    constraint category_sort_order_positive check (sort_order >= 0)
);

--changeset shazam2morrow:1628873622201
--comment: create sequence generator for category table
create sequence category_seq as bigint increment by 1 minvalue 1 no maxvalue start with 1;

--changeset shazam2morrow:1628784657369
--comment: create table for storing restaurant category
create table restaurant_category
(
    id            integer not null,
    category_id   integer not null,
    restaurant_id integer not null,
    constraint restaurant_category_pk primary key (id),
    constraint restaurant_category_uk unique (restaurant_id, category_id),
    constraint restaurant_category_categoryId_fk foreign key (category_id) references category (id),
    constraint restaurant_category_restaurantId_fk foreign key (restaurant_id) references restaurant (id)
);

--changeset shazam2morrow:1628873677404
--comment: create sequence generator for restaurant_category table
create sequence restaurant_category_seq as bigint increment by 1 minvalue 1 no maxvalue start with 1;

--changeset shazam2morrow:1628966454124
--comment: create table for storing restaurant menu
create table restaurant_menu
(
    id            integer      not null,
    restaurant_id integer      not null,
    slug          varchar(32)  not null,
    title         varchar(255) not null,
    sort_order    smallint     not null default 0,
    is_active     boolean      not null default false,
    created_at    timestamptz  not null default now(),
    updated_at    timestamptz  not null default now(),
    constraint restaurant_menu_pk primary key (id),
    constraint restaurant_menu_slug_uk unique (slug),
    constraint restaurant_menu_restaurantId_fk foreign key (restaurant_id) references restaurant (id)
);

--changeset shazam2morrow:1628969737118
--comment: create sequence generator for restaurant_menu table
create sequence restaurant_menu_seq as bigint increment by 1 minvalue 1 no maxvalue start with 1;

--changeset shazam2morrow:1628966666813
--comment: create table for storing menu group
create table menu_group
(
    id         integer      not null,
    menu_id    integer      not null,
    slug       varchar(32)  not null,
    title      varchar(255) not null,
    sort_order smallint     not null default 0,
    is_active  boolean      not null default false,
    created_at timestamptz  not null default now(),
    updated_at timestamptz  not null default now(),
    icon_url   varchar(2000),
    constraint menu_group_pk primary key (id),
    constraint menu_group_slug_uk unique (slug),
    constraint menu_group_sort_order_positive check (sort_order >= 0),
    constraint menu_group_menuId_fk foreign key (menu_id) references restaurant_menu (id)
);

--changeset shazam2morrow:1628970055710
--comment: create sequence generator for menu_group table
create sequence menu_group_seq as bigint increment by 1 minvalue 1 no maxvalue start with 1;

--changeset shazam2morrow:1628967299862
--comment: create table for storing menu item
create table menu_item
(
    id           integer        not null,
    group_id     integer        not null,
    slug         varchar(32)    not null,
    title        varchar(255)   not null,
    calories     integer        not null default 0,
    sort_order   integer        not null default 0,
    cooking_time smallint       not null default 0,
    price        numeric(21, 4) not null default 0.000,
    is_active    boolean        not null default false,
    created_at   timestamptz    not null default now(),
    updated_at   timestamptz    not null default now(),
    banner_url   varchar(2000),
    description  varchar(4096),
    constraint menu_item_pk primary key (id),
    constraint menu_item_slug_uk unique (slug),
    constraint menu_item_price_positive check (price >= 0),
    constraint menu_item_calories_positive check (calories >= 0),
    constraint menu_item_cooking_time_positive check (cooking_time >= 0),
    constraint menu_item_groupId_fk foreign key (group_id) references menu_group (id)
);

--changeset shazam2morrow:1628970328553
--comment: create sequence generator for menu_item
create sequence menu_item_seq as bigint increment by 1 minvalue 1 no maxvalue start with 1;
