create table IF NOT EXISTS service
(
  service_id   varchar(50) not null,
  service_name varchar(50) not null,
  created_at   timestamp   not null,
  modified_at  timestamp   not null,
  created_by   varchar(50) not null,
  modified_by  varchar(50) not null,
  primary key (service_id)
);

insert into service (service_id, service_name, created_at, modified_at, created_by, modified_by)
values ('1', '서비스이름', now(), now(), '', '');
