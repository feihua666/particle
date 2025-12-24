#!/bin/bash

# 设置mysql的登录用户名和密码
mysql_user=root
mysql_password=root
mysql_host="localhost"
mysql_port="3306"
mysql_charset="utf8"
# 需要备份的数据库
mysql_bak_databases="particle"
# 获取脚本所在目录的绝对路径，无论你从哪个目录运行这个脚本，它都会输出脚本所在的目录的绝对路径
# 获取的路径没有 / 后缀
SCRIPT_DIR=$(cd `dirname $0`; pwd)
# 备份文件存放地址
backup_dir=$SCRIPT_DIR/sqlBackup


# 是否删除过期数据
expire_backup_delete="ON"
# 过期天数，如果 expire_backup_delete 为开启状态，该天数以前的备份将会被删除
expire_days=7
# 备份时间，如：202405080947
backup_time=`date +%Y%m%d%H%M`
# 执行脚本时的欢迎提示
welcome_msg="Welcome to use MySQL backup tools!"

# 判断mysql实例是否正常运行
mysql_ps=`ps -ef |grep mysql |wc -l`
mysql_listen=`netstat -an |grep LISTEN |grep $mysql_port|wc -l`
if [ $mysql_ps -eq 0 ] || [ $mysql_listen -eq 0 ]; then
        echo "ERROR:MySQL is not running! backup stop!"
        exit
else
        echo $welcome_msg
fi
# 创建备份目录
mkdir -p $backup_dir

for db in $mysql_bak_databases; do
# 备份指定数据库中数据
mysqldump -h$mysql_host -P$mysql_port -u$mysql_user -p$mysql_password -B $db> $backup_dir/$db-$backup_time.sql
flag=`echo $?`
if [ $flag -eq 0 ]; then
        echo "database $db success backup to $backup_dir/$db-$backup_time.sql"
else
        echo "database $db backup fail!"
fi
done




# 删除过期数据
if [ "$expire_backup_delete" = "ON" ] && [ -n "$backup_dir" ]; then
        `find $backup_dir/ -type f -mtime +$expire_days | xargs rm -rf`
        echo "Expired backup data delete complete!"
fi
