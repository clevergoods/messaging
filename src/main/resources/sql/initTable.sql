CREATE SEQUENCE public.messages_seq START 101;
CREATE SEQUENCE public.messages_statistics_seq START 101;

CREATE TABLE messages (
    id bigint DEFAULT nextval('public.messages_seq'::regclass) NOT NULL,
    uuid varchar(50) NOT NULL,
    message_type varchar(20) NOT NULL,
    message_from varchar(20) NOT NULL,
    message_content text default NULL,
    message_size integer DEFAULT null,
    meta_information text not null
);

CREATE TABLE messages_statistics (
id bigint DEFAULT nextval('public.messages_statistics_seq'::regclass) NOT NULL,
    message_uuid varchar(50) NOT NULL,
    reseived_time timestamp without time zone not null,
    processing_time_ms integer,
    processed_time timestamp without time zone,
    process_name varchar(50)
);
ALTER TABLE ONLY public.messages
    ADD CONSTRAINT message_uuid_unique_constraint UNIQUE (uuid);
ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_pk PRIMARY KEY (id);
ALTER TABLE ONLY public.messages_statistics
    ADD CONSTRAINT message_statistics_uuid_unique_constraint UNIQUE (message_uuid);
ALTER TABLE ONLY public.messages_statistics
    ADD CONSTRAINT messages_statistics_pk PRIMARY KEY (id);

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_to_messages_statistics_fk FOREIGN KEY (id) REFERENCES public.messages_statistics(id) ON UPDATE RESTRICT ON DELETE RESTRICT;
