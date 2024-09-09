# 本地kibana服务器
参考：https://www.elastic.co/guide/cn/kibana/current/docker.html
kibana 为es的一个可视化界面
|功能| 命令                 | 备注                                                                                    |
| --- |--------------------|---------------------------------------------------------------------------------------|
|启动kibana| `./start-kibana.sh` | kibana browser访问：[http://localhost:5601/](http://localhost:5601/) 默认用户/密码为：kibana/kibana |
|停止kibana| `./stop-kibana.sh`  | 将清空所有network和volume                                                                   |



# 问题备注
我单独使用[elasticsearch](..%2Felasticsearch)，连接不上es，我将kibana改为了host模式，可以连接上。但kibana访问不了，没有解决，暂时将kibana移到了elasticsearch中。