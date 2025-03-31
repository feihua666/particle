-- 这是基于 postgresql 的数据库脚本
-- 本来想使用 postgresql 做向量存储和嵌入模型向量化，但一直安装 postgresml 扩展有问题且目前不支持 pg17，所以暂没有使用
CREATE EXTENSION IF NOT EXISTS vector;
CREATE EXTENSION IF NOT EXISTS hstore;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

drop table if exists global_ai_vector_store;
CREATE TABLE global_ai_vector_store (
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	content text,
	metadata json,
	embedding vector(1536)
);
CREATE INDEX ON global_ai_vector_store USING HNSW (embedding vector_cosine_ops);
