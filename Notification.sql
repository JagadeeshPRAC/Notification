CREATE TABLE `event_master` (
  `etm_id` int UNIQUE PRIMARY KEY NOT NULL,
  `etm_name` varchar(255),
  `etm_status` smallInt
);

CREATE TABLE `notification_template` (
  `nt_id` int UNIQUE PRIMARY KEY NOT NULL,
  `nt_body` text,
  `nt_channel` int,
  `nt_status` smallInt
);

CREATE TABLE `event_notification_mapping` (
  `etnm_id` int UNIQUE PRIMARY KEY NOT NULL,
  `etnm_etm_id` int,
  `etnm_nt_id` int,
  `etnm_status` smallInt
);

CREATE TABLE `channel` (
  `cl_id` int UNIQUE PRIMARY KEY NOT NULL,
  `cl_name` varchar(255)
);

ALTER TABLE `notification_template` ADD FOREIGN KEY (`nt_channel`) REFERENCES `channel` (`cl_id`);

ALTER TABLE `event_notification_mapping` ADD FOREIGN KEY (`etnm_etm_id`) REFERENCES `event_master` (`etm_id`);

ALTER TABLE `event_notification_mapping` ADD FOREIGN KEY (`etnm_nt_id`) REFERENCES `notification_template` (`nt_id`);