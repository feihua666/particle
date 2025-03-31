# 本地milvus服务器
参考：https://milvus.io/docs/install_standalone-docker-compose.md
参考：https://github.com/zilliztech/attu
在 [docker-compose.yml](docker-compose.yml) 文件中做了一些修改，加了particle前缀
milvus 默认的用户和密码为：root milvus

|功能|命令|备注|
| --- | --- | --- |
|启动milvus|`./start-milvus.sh`|milvus访问：[http://localhost:3000/](http://localhost:3000/)|
|停止milvus|`./stop-milvus.sh`|将清空所有network和volume|

