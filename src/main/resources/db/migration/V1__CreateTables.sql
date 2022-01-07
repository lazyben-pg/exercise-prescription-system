CREATE TABLE user
(
    id bigint primary key auto_increment,
    username varchar(20) unique,
    encrypted_password varchar(100)
);

CREATE TABLE prescription
(
    id bigint primary key auto_increment,
    userid bigint,
    questionnaire_id bigint,
    userinfo_id bigint,
    created_at datetime
);

CREATE TABLE userinfo
(
    id bigint primary key auto_increment,
    userid bigint,
    sexual int,
    age int,
    height double,
    weight double,
    muscle_mass double,
    lean_body_mass double,
    fat_weight double,
    fat_percentage double,
    body_mass_index double,
    weight_control double,
    standard_weight double,
    basal_metabolic_rate double,
    stature double,
    created_at datetime
);

CREATE TABLE questionnaire
(
    id bigint primary key auto_increment,
    userid bigint,
    q1 varchar(5),
    q2 varchar(5),
    q3 varchar(5),
    q4 varchar(5),
    q5 varchar(5),
    q6 varchar(5),
    q7 varchar(5),
    q8 varchar(5),
    q9 varchar(5),
    q10 varchar(5),
    q11 varchar(5),
    q12 varchar(5),
    q13 varchar(5),
    q14 varchar(5),
    created_at datetime,
    symptom int
);

insert into user (username, encrypted_password) values ('admin', '$2a$10$v0Y1rN.15Ddji2soT7ojy.Ha6T7xyFZzvZEWUlSWXmUxLCg91wBFi')