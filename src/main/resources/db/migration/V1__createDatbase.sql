create table brand (
       id bigint generated by default as identity,
        brand_name varchar(200),
        primary key (id)
    );

create table car (
       id bigint generated by default as identity,
        chassis_number varchar(200),
        color varchar(255),
        model varchar(255),
        name varchar(255),
        year_factory integer,
        year_model integer,
        brand_id bigint,
        engine_id bigint,
        primary key (id));

create table engine (
       id bigint generated by default as identity,
        capacity double,
        code varchar(255),
        hp integer,
        primary key (id));

alter table brand add constraint UK_hsu7w3m7wxvplg49ip7g0v5rr unique (brand_name);

alter table car add constraint UK_bb17i5x0hdmbvwvybelkj1ht2 unique (chassis_number);

alter table car add constraint FKj1mws2ruu9q6k2sa4pwlxthxn foreign key (brand_id) references brand;

alter table car add constraint FKnednv54lgu9rfucgemr5eal0j foreign key (engine_id) references engine;