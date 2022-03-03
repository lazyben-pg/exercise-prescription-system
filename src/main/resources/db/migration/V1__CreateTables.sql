create TABLE user
(
    id bigint primary key auto_increment,
    username varchar(20) unique,
    encrypted_password varchar(100)
);

create TABLE prescription
(
    id bigint primary key auto_increment,
    userid bigint,
    questionnaire_id bigint,
    userinfo_id bigint,
    created_at datetime
);

create TABLE userinfo
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
    heart_rate_rest double,
    created_at datetime
);

create TABLE questionnaire
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

create TABLE pingpong_prescription
(
    id bigint primary key auto_increment,
    userid bigint,
    forehand_attack varchar(30),
    backhand_scoop_pass varchar(30),
    backhand_push varchar(30),
    fast_loop_drive varchar(30),
    high_spin_loop_drive varchar(30),
    race varchar(30),
    created_at datetime
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into user (username, encrypted_password) values ('luohongde', '$2a$10$v0Y1rN.15Ddji2soT7ojy.Ha6T7xyFZzvZEWUlSWXmUxLCg91wBFi');
insert into user (username, encrypted_password) values ('tanming', '$2a$10$v0Y1rN.15Ddji2soT7ojy.Ha6T7xyFZzvZEWUlSWXmUxLCg91wBFi');
insert into user (username, encrypted_password) values ('zhousha', '$2a$10$v0Y1rN.15Ddji2soT7ojy.Ha6T7xyFZzvZEWUlSWXmUxLCg91wBFi');
insert into user (username, encrypted_password) values ('shanghang', '$2a$10$v0Y1rN.15Ddji2soT7ojy.Ha6T7xyFZzvZEWUlSWXmUxLCg91wBFi');
insert into user (username, encrypted_password) values ('ruxu', '$2a$10$v0Y1rN.15Ddji2soT7ojy.Ha6T7xyFZzvZEWUlSWXmUxLCg91wBFi');
insert into user (username, encrypted_password) values ('zhuyeqing', '$2a$10$v0Y1rN.15Ddji2soT7ojy.Ha6T7xyFZzvZEWUlSWXmUxLCg91wBFi');
insert into user (username, encrypted_password) values ('lishichao', '$2a$10$v0Y1rN.15Ddji2soT7ojy.Ha6T7xyFZzvZEWUlSWXmUxLCg91wBFi');
insert into user (username, encrypted_password) values ('shixuejiao', '$2a$10$v0Y1rN.15Ddji2soT7ojy.Ha6T7xyFZzvZEWUlSWXmUxLCg91wBFi');
insert into user (username, encrypted_password) values ('gunian', '$2a$10$v0Y1rN.15Ddji2soT7ojy.Ha6T7xyFZzvZEWUlSWXmUxLCg91wBFi');
insert into user (username, encrypted_password) values ('xiongling', '$2a$10$v0Y1rN.15Ddji2soT7ojy.Ha6T7xyFZzvZEWUlSWXmUxLCg91wBFi');

insert into userinfo (userid, sexual, age, height, weight, muscle_mass, lean_body_mass, fat_weight, fat_percentage, body_mass_index, weight_control, standard_weight, basal_metabolic_rate, stature, heart_rate_rest, created_at)
values (1, 0, 24, 174.0, 87.3, 32.8, 57.8, 29.5, 33.8, 28.8, -21.5, 65.8, 1619.0, 6.0, 61.0, now());
insert into userinfo (userid, sexual, age, height, weight, muscle_mass, lean_body_mass, fat_weight, fat_percentage, body_mass_index, weight_control, standard_weight, basal_metabolic_rate, stature, heart_rate_rest, created_at)
values (2, 0, 27, 169.0, 66.8, 30.6, 54.1, 12.7, 19.0, 13.4, -4.5, 62.3, 1539.0, 1.0, 59.0, now());
insert into userinfo (userid, sexual, age, height, weight, muscle_mass, lean_body_mass, fat_weight, fat_percentage, body_mass_index, weight_control, standard_weight, basal_metabolic_rate, stature, heart_rate_rest, created_at)
values (3, 1, 24, 167.0, 48.0, 20.2, 37.3, 10.7, 22.2, 17.2, 10.2, 58.2, 1176.0, 1.0, 63.0, now());
insert into userinfo (userid, sexual, age, height, weight, muscle_mass, lean_body_mass, fat_weight, fat_percentage, body_mass_index, weight_control, standard_weight, basal_metabolic_rate, stature, heart_rate_rest, created_at)
values (4, 0, 27, 179.5, 66.7, 28.3, 50.7, 16.0, 24.0, 20.7, 2.9, 69.6, 1465.0, 3.0, 62.0, now());
insert into userinfo (userid, sexual, age, height, weight, muscle_mass, lean_body_mass, fat_weight, fat_percentage, body_mass_index, weight_control, standard_weight, basal_metabolic_rate, stature, heart_rate_rest, created_at)
values (5, 0, 22, 184.0, 73.9, 31.4, 56.2, 17.7, 24.0, 21.8, -1.1, 72.8, 1584.0, 3.0, 61.0, now());
insert into userinfo (userid, sexual, age, height, weight, muscle_mass, lean_body_mass, fat_weight, fat_percentage, body_mass_index, weight_control, standard_weight, basal_metabolic_rate, stature, heart_rate_rest, created_at)
values (6, 0, 23, 169.5, 56.7, 25.8, 46.4, 10.3, 18.1, 19.1, 5.9, 62.65, 1372.0, 4.0, 54.0, now());
insert into userinfo (userid, sexual, age, height, weight, muscle_mass, lean_body_mass, fat_weight, fat_percentage, body_mass_index, weight_control, standard_weight, basal_metabolic_rate, stature, heart_rate_rest, created_at)
values (8, 1, 23, 158.0, 51.5, 20.0, 36.9, 14.6, 28.4, 20.6, 1.3, 52.8, 1167.0, 4.0, 60.0, now());
insert into userinfo (userid, sexual, age, height, weight, muscle_mass, lean_body_mass, fat_weight, fat_percentage, body_mass_index, weight_control, standard_weight, basal_metabolic_rate, stature, heart_rate_rest, created_at)
values (7, 0, 23, 163.0, 57.4, 26.1, 46.5, 10.9, 19.0, 21.6, 0.7, 58.1, 1374.0, 4.0, 67.0, now());
insert into userinfo (userid, sexual, age, height, weight, muscle_mass, lean_body_mass, fat_weight, fat_percentage, body_mass_index, weight_control, standard_weight, basal_metabolic_rate, stature, heart_rate_rest, created_at)
values (9, 1, 24, 155.5, 52.4, 18.6, 35.0, 17.4, 33.3, 21.7, -1.1, 51.3, 1126.0, 3.0, 58.0, now());
insert into userinfo (userid, sexual, age, height, weight, muscle_mass, lean_body_mass, fat_weight, fat_percentage, body_mass_index, weight_control, standard_weight, basal_metabolic_rate, stature, heart_rate_rest, created_at)
values (10, 1, 23, 164.0, 51.9, 20.7, 38.4, 13.5, 26.0, 19.3, 4.5, 56.4, 1199.0, 4.0, 59.0, now());

insert into questionnaire (userid, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, symptom, created_at)
values (1, 'B', 'C', 'C', 'B', 'B', 'C', 'C', 'B', 'D', 'B', 'C', 'B', 'B', 'C', 19, now());
insert into questionnaire (userid, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, symptom, created_at)
values (2, 'B', 'C', 'C', 'B', 'B', 'C', 'C', 'B', 'D', 'B', 'C', 'B', 'B', 'C', 19, now());
insert into questionnaire (userid, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, symptom, created_at)
values (3, 'B', 'C', 'C', 'B', 'B', 'C', 'C', 'B', 'D', 'B', 'C', 'B', 'B', 'C', 19, now());
insert into questionnaire (userid, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, symptom, created_at)
values (4, 'B', 'C', 'C', 'B', 'B', 'C', 'B', 'B', 'D', 'B', 'C', 'B', 'B', 'C', 5, now());
insert into questionnaire (userid, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, symptom, created_at)
values (5, 'B', 'C', 'C', 'B', 'B', 'C', 'C', 'B', 'D', 'B', 'C', 'B', 'B', 'C', 19, now());
insert into questionnaire (userid, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, symptom, created_at)
values (6, 'B', 'C', 'C', 'B', 'B', 'C', 'C', 'B', 'D', 'B', 'C', 'B', 'B', 'C', 19, now());
insert into questionnaire (userid, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, symptom, created_at)
values (7, 'B', 'C', 'C', 'B', 'B', 'C', 'C', 'B', 'D', 'B', 'C', 'B', 'B', 'C', 19, now());
insert into questionnaire (userid, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, symptom, created_at)
values (8, 'B', 'C', 'C', 'B', 'B', 'C', 'C', 'B', 'D', 'B', 'C', 'B', 'B', 'C', 19, now());
insert into questionnaire (userid, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, symptom, created_at)
values (9, 'B', 'C', 'C', 'B', 'B', 'C', 'C', 'B', 'D', 'B', 'C', 'B', 'B', 'C', 19, now());
insert into questionnaire (userid, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, symptom, created_at)
values (10, 'B', 'C', 'C', 'B', 'B', 'C', 'C', 'B', 'D', 'B', 'C', 'B', 'B', 'C', 19, now());