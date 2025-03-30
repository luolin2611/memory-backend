# 密码助手

# 1.概述

# 2.数据库



## 2.1 用户表(t_user)

```sql
-- ----------------------------
-- Table structure for t_user
-- ----------------------------

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID，自增',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `email` varchar(64) NOT NULL COMMENT '电子邮件地址',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `salt` varchar(64) NOT NULL COMMENT '密码盐值',
  `signature` varchar(256) DEFAULT NULL COMMENT '个性签名',
  `status` varchar(1) DEFAULT '0' COMMENT '用户状态： 0-在用,1-无效',
  `avatar_url` varchar(1024) DEFAULT NULL COMMENT '头像',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`),
  UNIQUE KEY `idx_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
```

## 2.2 记忆库表（t_memory_library）

```sql
-- ----------------------------
-- Table structure for t_memory_library
-- ----------------------------

CREATE TABLE `t_memory_library` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `user_id` bigint(20) NOT NULL COMMENT '所属用户ID',
  `name` varchar(255) NOT NULL COMMENT '记忆库名称',
  `description` varchar(512) DEFAULT NULL COMMENT '记忆库描述',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='记忆库表';
```

## 2.2 文章表（t_articles）

```sql
-- ----------------------------
-- Table structure for t_articles
-- ----------------------------

CREATE TABLE `t_articles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `library_id` bigint(20) DEFAULT NULL COMMENT '所属记忆库ID',
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题，必填',
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '内容，必填',
  `mnemonic` text COLLATE utf8mb4_unicode_ci COMMENT '助记，非必填',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，自动更新',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间，自动更新',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章表';
```



## 2.4 文章记忆表(t_article_memory)

```sql
-- ----------------------------
-- Table structure for t_article_memory
-- ----------------------------

CREATE TABLE `t_article_memory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `article_id` bigint(20) NOT NULL COMMENT '文章ID，外键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `next_review_at` timestamp NOT NULL COMMENT '下次复习时间',
  `interval_day` int(11) NOT NULL DEFAULT '1' COMMENT '复习间隔，单位为天',
  `ease_factor` float NOT NULL DEFAULT '2.5' COMMENT '熟练度因子',
  `consecutive_correct` int(11) NOT NULL DEFAULT '0' COMMENT '连续正确次数',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，自动更新',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间，自动更新',
  PRIMARY KEY (`id`),
  KEY `article_id` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章记忆表';
```







# 3.平台依赖环境信息

3.1 依赖Redis

3.2 依赖minio

3.3 依赖mysql







4.AI 使用技巧

4.1 后端生成接口代码

前置内容准备

Deepseek完成

1.使用Deepseek根据需求文档，生成数据库表。

2.









1.先开发一套模板(Controller、Service、Mapper.xml)，AI选择核心文件

2.根据现有的模板导出接口，提供接口给AI作为接口模板

3.使用Deepseek设计的表，提供给AI

4.描述表的关联关系

5.根据内容生成，生成接口AI





数据库表生成流程

![image-20250322095424294](./assets/image-20250322095424294.png)

接口设计流程

![image-20250322095523464](./assets/image-20250322095523464.png)















