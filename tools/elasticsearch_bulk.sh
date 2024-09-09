#!/bin/bash
# 命令 split -l 1000 -a 10 data.json ./tmp/data_part_ 的作用是将名为 data.json 的文件分割成多个小文件，
# 每个小文件包含最多10000行，并将这些小文件保存到当前目录下，文件名以 data_part_ 开头，后跟一个由最多10个字符组成的字母数字序列
# 执行这个命令后，data.json 文件将被分割成多个文件，每个文件包含最多10000行，文件名类似于 data_part_aaaaaaaaaa、data_part_aaaaaaaaab 等，直到包含所有原始文件的行
split -l 10000 -a 10 data.json data_part_

BULK_FILES=data_part_*

for f in $BULK_FILES;
 do curl -H "Content-Type: application/json" -XPOST http://localhost:9200/_bulk --data-binary @$f
done