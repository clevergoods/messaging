CREATE SEQUENCE public.messages_seq START 101;
CREATE SEQUENCE public.messages_statistics_seq START 101;
CREATE SEQUENCE public.message_type_seq START 101;
CREATE SEQUENCE public.author_seq START 101;

SELECT setval('messages_seq', 101);
SELECT setval('messages_statistics_seq', 101);


CREATE TABLE message_type (
    id bigint DEFAULT nextval('public.message_type_seq'::regclass) NOT NULL,
    type_name varchar(20) NOT NULL
);

CREATE TABLE author (
    id bigint DEFAULT nextval('public.author_seq'::regclass) NOT NULL,
    author_name varchar(20) NOT NULL
);

CREATE TABLE messages (
    id bigint DEFAULT nextval('public.messages_seq'::regclass) NOT NULL,
    uuid varchar(50) NOT NULL,
    type_id  bigint NOT NULL,
    author_id bigint NOT NULL,
    statistics_id bigint,
    data text DEFAULT NULL,
    organization varchar(50) NOT NULL,
    metainformation text NOT NULL
);

CREATE TABLE messages_statistics (
id bigint DEFAULT nextval('public.messages_statistics_seq'::regclass) NOT NULL,
    received_date timestamp without time zone not null,
    processing_time_ms bigint,
    processed_date timestamp without time zone,
    process_name varchar(50)
);
ALTER TABLE ONLY public.messages ADD CONSTRAINT message_uuid_unique_constraint UNIQUE (uuid);
ALTER TABLE ONLY public.messages ADD CONSTRAINT messages_pk PRIMARY KEY (id);

ALTER TABLE ONLY public.messages_statistics ADD CONSTRAINT messages_statistics_pk PRIMARY KEY (id);

ALTER TABLE ONLY public.message_type ADD CONSTRAINT messages_type_pk PRIMARY KEY (id);
ALTER TABLE ONLY public.message_type ADD CONSTRAINT message_type_name_unique_constraint UNIQUE (type_name);

ALTER TABLE ONLY public.author ADD CONSTRAINT author_pk PRIMARY KEY (id);
ALTER TABLE ONLY public.author ADD CONSTRAINT author_name_unique_constraint UNIQUE (author_name);

ALTER TABLE ONLY public.messages ADD CONSTRAINT messages_to_message_type_fk FOREIGN KEY (type_id) REFERENCES public.message_type(id) ON UPDATE RESTRICT ON DELETE RESTRICT;
ALTER TABLE ONLY public.messages ADD CONSTRAINT messages_to_author_fk FOREIGN KEY (author_id) REFERENCES public.author(id) ON UPDATE RESTRICT ON DELETE RESTRICT;
