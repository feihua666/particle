-- 建表语句sql
DROP TABLE IF EXISTS component_dream_ssq_code_opened;
CREATE TABLE `component_dream_ssq_code_opened` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `ssq_code_id` bigint NOT NULL COMMENT '双色球号码id',
  `opened_date` date NOT NULL COMMENT '开奖日期',
  `opened_phase_year` int NOT NULL COMMENT '开奖期号年份',
  `opened_phase_num` int NOT NULL COMMENT '开奖期号数',
  `opened_week_day` int NOT NULL COMMENT '开奖星期号，从1-7，7代表星期日',
  `opened_phase` varchar(6) NOT NULL COMMENT '开奖期号',
  `opened_red1` int DEFAULT NULL COMMENT '开奖红球1',
  `opened_red2` int DEFAULT NULL COMMENT '开奖红球2',
  `opened_red3` int DEFAULT NULL COMMENT '开奖红球3',
  `opened_red4` int DEFAULT NULL COMMENT '开奖红球4',
  `opened_red5` int DEFAULT NULL COMMENT '开奖红球5',
  `opened_red6` int DEFAULT NULL COMMENT '开奖红球6',
  `win1_num` int DEFAULT NULL COMMENT '中1等奖注数',
  `win1_amount` int DEFAULT NULL COMMENT '中1等奖单注金额，单位元',
  `win1_total_amount` int DEFAULT NULL COMMENT '中1等奖总金额，单位元',
  `win2_num` int DEFAULT NULL COMMENT '中2等奖注数',
  `win2_amount` int DEFAULT NULL COMMENT '中2等奖单注金额，单位元',
  `win2_total_amount` int DEFAULT NULL COMMENT '中2等奖总金额，单位元',
  `win3_num` int DEFAULT NULL COMMENT '中3等奖注数',
  `win3_amount` int DEFAULT NULL COMMENT '中3等奖单注金额，单位元',
  `win3_total_amount` int DEFAULT NULL COMMENT '中3等奖总金额，单位元',
  `win4_num` int DEFAULT NULL COMMENT '中4等奖注数',
  `win4_amount` int DEFAULT NULL COMMENT '中4等奖单注金额，单位元',
  `win4_total_amount` int DEFAULT NULL COMMENT '中4等奖总金额，单位元',
  `win5_num` int DEFAULT NULL COMMENT '中5等奖注数',
  `win5_amount` int DEFAULT NULL COMMENT '中5等奖单注金额，单位元',
  `win5_total_amount` int DEFAULT NULL COMMENT '中5等奖总金额，单位元',
  `win6_num` int DEFAULT NULL COMMENT '中6等奖注数',
  `win6_amount` int DEFAULT NULL COMMENT '中6等奖单注金额，单位元',
  `win6_total_amount` int DEFAULT NULL COMMENT '中6等奖总金额，单位元',
  `win_total_amount` int DEFAULT NULL COMMENT '中奖总金额，单位元',
  `prize_pool_amount` int DEFAULT NULL COMMENT '奖池金额，单位元',
  `sale_amount` int DEFAULT NULL COMMENT '销售额，单位元',
  `red_cold2_ratio` int DEFAULT NULL COMMENT '红冷号个数，最近2期以内未出现的号码',
  `red_hot2_ratio` int DEFAULT NULL COMMENT '红热号个数，最近2期以内出现过的号码',
  `red_cold3_ratio` int DEFAULT NULL COMMENT '红冷号个数，最近3期以内未出现的号码',
  `red_hot3_ratio` int DEFAULT NULL COMMENT '红热号个数，最近3期以内出现过的号码',
  `red_cold4_ratio` int DEFAULT NULL COMMENT '红冷号个数，最近4期以内未出现的号码',
  `red_hot4_ratio` int DEFAULT NULL COMMENT '红热号个数，最近4期以内出现过的号码',
  `red_cold5_ratio` int DEFAULT NULL COMMENT '红冷号个数，最近5期以内未出现的号码',
  `red_hot5_ratio` int DEFAULT NULL COMMENT '红热号个数，最近5期以内出现过的号码',
  `red_cold6_ratio` int DEFAULT NULL COMMENT '红冷号个数，最近6期以内未出现的号码',
  `red_hot6_ratio` int DEFAULT NULL COMMENT '红热号个数，最近6期以内出现过的号码',
  `red_cold7_ratio` int DEFAULT NULL COMMENT '红冷号个数，最近7期以内未出现的号码',
  `red_hot7_ratio` int DEFAULT NULL COMMENT '红热号个数，最近7期以内出现过的号码',
  `red_cold8_ratio` int DEFAULT NULL COMMENT '红冷号个数，最近8期以内未出现的号码',
  `red_hot8_ratio` int DEFAULT NULL COMMENT '红热号个数，最近8期以内出现过的号码',
  `red_cold9_ratio` int DEFAULT NULL COMMENT '红冷号个数，最近9期以内未出现的号码',
  `red_hot9_ratio` int DEFAULT NULL COMMENT '红热号个数，最近9期以内出现过的号码',
  `red_cold10_ratio` int DEFAULT NULL COMMENT '红冷号个数，最近10期以内未出现的号码',
  `red_hot10_ratio` int DEFAULT NULL COMMENT '红热号个数，最近10期以内出现过的号码',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `opened_date` (`opened_date`) USING BTREE,
  KEY `opened_phase_year` (`opened_phase_year`) USING BTREE,
  KEY `opened_phase_num` (`opened_phase_num`) USING BTREE,
  KEY `opened_phase` (`opened_phase`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='双色球开奖表';