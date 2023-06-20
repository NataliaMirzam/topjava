DELETE FROM meals;
DELETE FROM user_role;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (datetime, description, calories, user_id)
VALUES ('2020-01-30 10:00:00', 'Breakfast', 500, 100000),
       ('2020-01-30 13:00:00', 'Lunch', 1000, 100000),
       ('2020-01-30 20:00:00', 'Dinner', 500, 100000),
       ('2020-01-31 00:00:00', 'Food on boarder', 100, 100000),
       ('2020-01-31 10:00:00', 'Breakfast', 1000, 100000),
       ('2020-01-31 13:00:00', 'Lunch', 500, 100000),
       ('2020-01-31 20:00:00', 'Dinner', 410, 100000),
       ('2015-06-01 14:00:00', 'Admin lunch', 510, 100001),
       ('2015-06-01 21:00:00', 'Admin dinner', 1500, 100001);