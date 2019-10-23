create table USERS
(
    USERNAME varchar(20) not null,
    PASSWORD varchar(100) not null,
    ENABLED boolean not null default false,
    primary key(USERNAME)
);

CREATE TABLE AUTHORITIES (
    USERNAME VARCHAR(50) NOT NULL,
    AUTHORITY VARCHAR(50) NOT NULL,
    FOREIGN KEY (USERNAME) REFERENCES users(USERNAME)
);

CREATE UNIQUE INDEX ix_auth_username
    on authorities (username,authority);

insert into USERS(USERNAME, PASSWORD, ENABLED) VALUES ('admin', '$2a$10$0kK1BHGt7omO12kksqBM/eSdvq2YuZT2fJjVdUtAMKYP9xwTVtnea', true); -- password
insert into USERS(USERNAME, PASSWORD, ENABLED) VALUES ('user', '$2a$10$0kK1BHGt7omO12kksqBM/eSdvq2YuZT2fJjVdUtAMKYP9xwTVtnea', true); -- password
insert into USERS(USERNAME, PASSWORD, ENABLED) VALUES ('ruben', '$2a$10$0kK1BHGt7omO12kksqBM/eSdvq2YuZT2fJjVdUtAMKYP9xwTVtnea', true); -- password
insert into USERS(USERNAME, PASSWORD, ENABLED) VALUES ('arne', '$2a$10$0kK1BHGt7omO12kksqBM/eSdvq2YuZT2fJjVdUtAMKYP9xwTVtnea', true); -- password
insert into USERS(USERNAME, PASSWORD, ENABLED) VALUES ('coach', '$2a$10$0kK1BHGt7omO12kksqBM/eSdvq2YuZT2fJjVdUtAMKYP9xwTVtnea', true); -- password

insert into AUTHORITIES(USERNAME, AUTHORITY) VALUES ('admin', 'ROLE_ADMIN');
insert into AUTHORITIES(USERNAME, AUTHORITY) VALUES ('user', 'ROLE_USER');
insert into AUTHORITIES(USERNAME, AUTHORITY) VALUES ('ruben', 'ROLE_USER');
insert into AUTHORITIES(USERNAME, AUTHORITY) VALUES ('arne', 'ROLE_USER');
insert into AUTHORITIES(USERNAME, AUTHORITY) VALUES ('coach', 'ROLE_ADMIN');