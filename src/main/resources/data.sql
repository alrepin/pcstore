-- drop all objects;
create table "pcstore_field"
(
    "id"          BIGINT auto_increment
        primary key,
    "data_type"   INTEGER,
    "is_constant" BOOLEAN,
    "name"        CHARACTER VARYING(255),
    "value"       CHARACTER VARYING(255)
);

create table "pcstore_product_type"
(
    "id"   BIGINT auto_increment
        primary key,
    "name" CHARACTER VARYING(255)
);

create table "pcstore_product"
(
    "id"              BIGINT auto_increment
        primary key,
    "leftovers"       INTEGER,
    "manufacturer"    CHARACTER VARYING(255),
    "price"           NUMERIC(38, 2),
    "serial_number"   CHARACTER VARYING(255),
    "product_type_id" BIGINT,
    constraint "FKey5r4v76aibje9dpxcnuxabn9"
        foreign key ("product_type_id") references "pcstore_product_type"
);

create table "pcstore_product_fields"
(
    "product_id" BIGINT not null,
    "field_id"   BIGINT not null,
    primary key ("product_id", "field_id"),
    constraint "FKb6rjwk74oraqk079yw76c87fd"
        foreign key ("product_id") references "pcstore_product",
    constraint "FKpt62q1d3fi3iou9iik7gx8pao"
        foreign key ("field_id") references "pcstore_field"
);

create table "pcstore_product_type_fields"
(
    "product_type_id" BIGINT not null,
    "field_id"        BIGINT not null,
    primary key ("product_type_id", "field_id"),
    constraint "FKktlrdpcqulkb6decjmm6ph00v"
        foreign key ("field_id") references "pcstore_field",
    constraint "FKrb0eh6dllr5h69e7s41k6m9w4"
        foreign key ("product_type_id") references "pcstore_product_type"
);

INSERT INTO PUBLIC."pcstore_product_type" ("name")
VALUES ('HDD');

INSERT INTO PUBLIC."pcstore_product_type" ("name")
VALUES ('Monitor');

INSERT INTO PUBLIC."pcstore_product_type" ("name")
VALUES ('PC');

INSERT INTO PUBLIC."pcstore_product_type" ("name")
VALUES ('Laptop');

INSERT INTO PUBLIC."pcstore_field" ("data_type", "is_constant", "name", "value")
VALUES (100, false, 'capacity', null);

INSERT INTO PUBLIC."pcstore_field" ("data_type", "is_constant", "name", "value")
VALUES (100, true, 'diagonal', '13');

INSERT INTO PUBLIC."pcstore_field" ("data_type", "is_constant", "name", "value")
VALUES (100, true, 'diagonal', '14');

INSERT INTO PUBLIC."pcstore_field" ("data_type", "is_constant", "name", "value")
VALUES (100, true, 'diagonal', '15');

INSERT INTO PUBLIC."pcstore_field" ("data_type", "is_constant", "name", "value")
VALUES (100, true, 'diagonal', '17');

INSERT INTO PUBLIC."pcstore_field" ("data_type", "is_constant", "name", "value")
VALUES (200, true, 'form_factor', 'Desktop');

INSERT INTO PUBLIC."pcstore_field" ("data_type", "is_constant", "name", "value")
VALUES (200, true, 'form_factor', 'Nettop');

INSERT INTO PUBLIC."pcstore_field" ("data_type", "is_constant", "name", "value")
VALUES (200, true, 'form_factor', 'Monoblock');

INSERT INTO PUBLIC."pcstore_product_type_fields" ("product_type_id", "field_id")
VALUES (1, 1);

INSERT INTO PUBLIC."pcstore_product_type_fields" ("product_type_id", "field_id")
VALUES (2, 2);

/*INSERT INTO PUBLIC."pcstore_product_type_fields" ("product_type_id", "field_id")
VALUES (2, 3);

INSERT INTO PUBLIC."pcstore_product_type_fields" ("product_type_id", "field_id")
VALUES (2, 4);

INSERT INTO PUBLIC."pcstore_product_type_fields" ("product_type_id", "field_id")
VALUES (2, 5);*/

INSERT INTO PUBLIC."pcstore_product_type_fields" ("product_type_id", "field_id")
VALUES (3, 6);

/*INSERT INTO PUBLIC."pcstore_product_type_fields" ("product_type_id", "field_id")
VALUES (3, 7);

INSERT INTO PUBLIC."pcstore_product_type_fields" ("product_type_id", "field_id")
VALUES (3, 8);*/

INSERT INTO PUBLIC."pcstore_product_type_fields" ("product_type_id", "field_id")
VALUES (4, 2);

/*INSERT INTO PUBLIC."pcstore_product_type_fields" ("product_type_id", "field_id")
VALUES (4, 3);

INSERT INTO PUBLIC."pcstore_product_type_fields" ("product_type_id", "field_id")
VALUES (4, 4);

INSERT INTO PUBLIC."pcstore_product_type_fields" ("product_type_id", "field_id")
VALUES (4, 5);*/
