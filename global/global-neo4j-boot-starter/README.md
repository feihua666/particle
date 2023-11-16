# 全局neo4j图数据库模块
将访问neo4j图数据库的模块单独提出来，主要是封装一些常用的基础类  

neo4j启动默认两个端口，分别为7474（用于web访问如：http://localhost:7474）和7687(用于数据库驱动连接如：bolt://localhost:7687)  

类关系梳理：
Neo4jRepository 依赖了Neo4jTemplate,Neo4jTemplate依赖了Neo4jClient,Neo4jClient依赖了Driver，Driver是neo4j的驱动包类