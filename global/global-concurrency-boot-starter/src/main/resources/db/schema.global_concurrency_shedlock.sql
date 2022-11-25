--
DROP TABLE IF EXISTS global_concurrency_shedlock;
CREATE TABLE global_concurrency_shedlock  (
  `name` varchar(64) NOT NULL,
  `lock_until` timestamp NOT NULL,
  `locked_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `locked_by` varchar(255) NOT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COMMENT = 'global_concurrency_shedlock';