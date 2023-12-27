CREATE DATABASE IF NOT EXISTS `gym`;
USE `gym`;

CREATE TABLE IF NOT EXISTS `user`
(
    `id`         bigint NOT NULL AUTO_INCREMENT,
    `email`      varchar(255) DEFAULT NULL,
    `first_name` varchar(255) DEFAULT NULL,
    `last_name`  varchar(255) DEFAULT NULL,
    `password`   varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);



CREATE TABLE IF NOT EXISTS `exercise`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `muscle_group`
(
    `id`     bigint NOT NULL AUTO_INCREMENT,
    `muscle` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);



CREATE TABLE IF NOT EXISTS `predefined_plan`
(
    `id`                bigint NOT NULL AUTO_INCREMENT,
    `name`              varchar(255)  DEFAULT NULL,
    `repeat_cycle`      int           DEFAULT NULL,
    `workout_per_cycle` int           DEFAULT NULL,
    `description`       varchar(3000) DEFAULT NULL,
    PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `user_plan`
(
    `id`                 bigint NOT NULL AUTO_INCREMENT,
    `active`             bit(1)       DEFAULT NULL,
    `created_date`       datetime(6)  DEFAULT NULL,
    `name`               varchar(255) DEFAULT NULL,
    `user_id`            bigint NOT NULL,
    `predefined_plan_id` bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_user_plan_user` (`user_id`),
    KEY `fk_user_plan_predefined_plan` (`predefined_plan_id`),
    CONSTRAINT `fk_user_plan_predefined_plan` FOREIGN KEY (`predefined_plan_id`) REFERENCES `predefined_plan` (`id`),
    CONSTRAINT `fk_user_plan_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);




CREATE TABLE IF NOT EXISTS `workout_category`
(
    `id`       bigint NOT NULL AUTO_INCREMENT,
    `category` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);



CREATE TABLE IF NOT EXISTS `exercise_category`
(
    `id`                  bigint NOT NULL AUTO_INCREMENT,
    `workout_category_id` bigint NOT NULL,
    `exercise_id`         bigint NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_exercise_category_workout_category` (`workout_category_id`),
    KEY `fk_exercise_category_exercise` (`exercise_id`),
    CONSTRAINT `fk_exercise_category_workout_category` FOREIGN KEY (`workout_category_id`) REFERENCES `workout_category` (`id`),
    CONSTRAINT `fk_exercise_category_exercise` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`id`)
);


CREATE TABLE IF NOT EXISTS `exercise_muscle_group`
(
    `id`              bigint NOT NULL AUTO_INCREMENT,
    `exercise_id`     bigint NOT NULL,
    `muscle_group_id` bigint NOT NULL,
    `target`          tinyint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_exercise_muscle_group_exercise` (`exercise_id`),
    KEY `fk_exercise_muscle_group_muscle_group` (`muscle_group_id`),
    CONSTRAINT `fk_exercise_muscle_group_muscle_group` FOREIGN KEY (`muscle_group_id`) REFERENCES `muscle_group` (`id`),
    CONSTRAINT `fk_exercise_muscle_group_exercise` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`id`),
    CONSTRAINT `exercise_muscle_group_chk_1` CHECK ((`target` between 0 and 1))
);


CREATE TABLE IF NOT EXISTS `predefined_plan_category`
(
    `id`                          bigint NOT NULL AUTO_INCREMENT,
    `predefined_plan_category_id` bigint NOT NULL,
    `workout_category_id`         bigint NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_predefined_plan_category_predefined_plan_category` (`predefined_plan_category_id`),
    KEY `fk_predefined_plan_category_workout_category` (`workout_category_id`),
    CONSTRAINT `fk_predefined_plan_category_workout_category` FOREIGN KEY (`workout_category_id`) REFERENCES `workout_category` (`id`),
    CONSTRAINT `fk_predefined_plan_category_predefined_plan_category` FOREIGN KEY (`predefined_plan_category_id`) REFERENCES `predefined_plan_schedule` (`id`)
);


CREATE TABLE IF NOT EXISTS `predefined_plan_schedule`
(
    `id`                 bigint NOT NULL AUTO_INCREMENT,
    `predefined_plan_id` bigint NOT NULL,
    `schedule_name`      varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_predefined_plan_schedule_predefined_plan` (`predefined_plan_id`),
    CONSTRAINT `fk_predefined_plan_schedule_predefined_plan` FOREIGN KEY (`predefined_plan_id`) REFERENCES `predefined_plan` (`id`)
);




CREATE TABLE IF NOT EXISTS `user_plan_category_schedule`
(
    `id`                  bigint NOT NULL AUTO_INCREMENT,
    `day_order`           int DEFAULT NULL,
    `user_id`             bigint NOT NULL,
    `user_plan_id`        bigint NOT NULL,
    `workout_category_id` bigint NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_user_plan_category_schedule_user` (`user_id`),
    KEY `fk_user_plan_category_schedule_user_plan` (`user_plan_id`),
    KEY `fk_user_plan_category_schedule_workout_category` (`workout_category_id`),
    CONSTRAINT `fk_user_plan_category_schedule_user_plan` FOREIGN KEY (`user_plan_id`) REFERENCES `user_plan` (`id`),
    CONSTRAINT `fk_user_plan_category_schedule_workout_category` FOREIGN KEY (`workout_category_id`) REFERENCES `workout_category` (`id`),
    CONSTRAINT `fk_user_plan_category_schedule_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);


CREATE TABLE IF NOT EXISTS `workout_history`
(
    `id`                             bigint NOT NULL AUTO_INCREMENT,
    `workout_date`                   datetime(6) DEFAULT NULL,
    `user_id`                        bigint NOT NULL,
    `user_plan_category_schedule_id` bigint NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_workout_history_user` (`user_id`),
    KEY `fk_workout_history_user_plan_category_schedule` (`user_plan_category_schedule_id`),
    CONSTRAINT `fk_workout_history_user_plan_category_schedule` FOREIGN KEY (`user_plan_category_schedule_id`) REFERENCES `user_plan_category_schedule` (`id`),
    CONSTRAINT `fk_workout_history_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

