# 用户组件
提供基础的用户相关支持，包括用户登录、密码等相关功能

注意：
建表语句脚本中的两个字段已废弃，将自动使用部门业务组件中的部门用户关系表来替代，但实体中的`comp_id`和`dept_id`字段依然有效程序中有自动处理
`comp_id` bigint DEFAULT NULL COMMENT '公司id，冗余字段，由dept_id对应公司派生',
`dept_id` bigint DEFAULT NULL COMMENT '部门id',
