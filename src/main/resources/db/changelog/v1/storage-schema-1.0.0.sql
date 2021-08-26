--liquibase formatted sql

--changeset shazam2morrow:1629485080271
--comment: create table for storing information about storage type
create table storage_type
(
    id   smallint    not null,
    name varchar(20) not null,
    constraint storage_type_pk primary key (id),
    constraint storage_type_name_uk unique (name)
);

--changeset shazam2morrow:1629485193621
--comment: insert new type "local" into storage type table
insert into storage_type(id, name)
values (1, 'local');

--changeset shazam2morrow:1627126385425
--comment: create table for storing information about uploaded file
create table uploaded_file
(
    id            integer       not null,
    size          integer       not null,
    storage       smallint      not null,
    slug          varchar(32)   not null,
    checksum      varchar(40)   not null,
    mime_type     varchar(255)  not null,
    original_name varchar(255)  not null,
    url           varchar(2000) not null,
    is_deleted    boolean       not null default false,
    uploaded_at   timestamptz   not null default now(),
    deleted_at    timestamptz,
    constraint uploaded_file_pk primary key (id),
    constraint uploaded_file_url_uk unique (url),
    constraint uploaded_file_slug_uk unique (slug),
    constraint uploaded_file_size_positive check (size > 0),
    constraint uploaded_file_storageId_fk foreign key (storage) references storage_type (id)
);

--changeset shazam2morrow:1627128384374
--comment: create sequence generator for uploaded_file table
create sequence uploaded_file_seq as bigint increment by 1 minvalue 1 no maxvalue start with 1;