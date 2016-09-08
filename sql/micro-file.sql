CREATE TABLE `base_fileresource` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `Path` varchar(128) NOT NULL DEFAULT '' COMMENT '文件相对路径',
  `OriginalName` varchar(50) NOT NULL DEFAULT '' COMMENT '原始文件名',
  `FileSize` int(11) NOT NULL COMMENT '文件大小',
  `BizType` varchar(50) NOT NULL DEFAULT '' COMMENT '文件业务类型',
  `ResType` int(11) NOT NULL DEFAULT '1' COMMENT '文件资源类型 1图片 2 压缩包',
  `CloudType` int(11) NOT NULL COMMENT '云服务商 1 又拍云 2阿里云',
  `Branch` varchar(50) NOT NULL DEFAULT '  ' COMMENT '分支',
  `IsDeleted` tinyint(11) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  `CreateTime` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '创建时间',
  `UpdateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;