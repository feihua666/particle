package com.particle.global.neo4j.test.demo.test;

import org.neo4j.driver.*;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023/11/15 15:26
 */
public class DirectUseTest {

    private static Driver driver = null;
    public static void main(String[] args) {
        driver();


        deleteAllData();
        insertTestData();
        queryData();

        closeDriver();
    }

    /**
     * 删除所有数据
     */
    public static void deleteAllData() {
        try (Session session = driver.session()) {
            String cql = "match (n) delete n";
            session.run(cql);
        }
    }

    /**
     * 插入测试数据
     */
    public static void insertTestData() {
        try (Session session = driver.session()) {
            for (int i = 0; i < 10; i++) {
                String name = "电影" + i;
                String cql = "create (n:movie {name: '" + name +"'})";
                session.run(cql);
            }

        }
    }

    /**
     * 查询数据
     */
    public static void  queryData(){
        try (Session session = driver.session()) {
            String cql = "MATCH (n:movie {name: $name}) return n";

            Result result = session.run(cql, Values.parameters("name", "电影1"));
            while (result.hasNext()) {
                Record record = result.next();
                for (Value value : record.values()) {

                    System.out.println("name" + ":" + value.get("name"));

                }
            }
        }
    }

    public static Driver driver(){
        if (driver == null) {
            driver = GraphDatabase.driver(
                    "bolt://localhost:7687",
                    AuthTokens.basic("neo4j", "neo4jneo4j"));
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.close();
        }
    }
}
