package com.particle.global.neo4j.test.demo.test;

import com.particle.global.neo4j.test.demo.ActorGRDO;
import com.particle.global.neo4j.test.demo.MovieGDO;
import com.particle.global.neo4j.test.demo.PersonGDO;
import com.particle.global.neo4j.test.demo.repository.ActorNeo4jRepository;
import com.particle.global.neo4j.test.demo.repository.MovieNeo4jRepository;
import com.particle.global.neo4j.test.demo.repository.PersonNeo4jRepository;
import com.particle.global.test.SuperTest;
import com.particle.global.tool.json.JsonTool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023/11/15 16:01
 */
@SpringBootTest
public class DemoTest extends SuperTest {

    @Autowired
    private MovieNeo4jRepository movieNeo4jRepository;

    @Autowired
    private PersonNeo4jRepository personNeo4jRepository;

    @Autowired
    private ActorNeo4jRepository actorNeo4jRepository;

    @Test
    public void demoEntryTest(){
        deleteAllData();
        insertTestData();
        queryData();
        queryData1();
        queryData2();
        queryData3();
        queryData4();
    }


    /**
     * 删除所有数据
     */
    public void deleteAllData() {
        movieNeo4jRepository.deleteAll();
        personNeo4jRepository.deleteAll();
    }
    /**
     * 插入测试数据
     */
    public void insertTestData() {
        List<MovieGDO> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String name = "电影电影" + i;
            MovieGDO movieGDO = new MovieGDO();
            movieGDO.initAddData();
            movieGDO.setName(name);

            PersonGDO personGDO = new PersonGDO();
            personGDO.initAddData();
            personGDO.setName("导演导演" + i);
            movieGDO.getDirectors().add(personGDO);

            PersonGDO personGDO1 = new PersonGDO();
            personGDO1.initAddData();
            personGDO1.setName("演员演员" + i);

            ActorGRDO actorGRDO = new ActorGRDO(personGDO1, Collections.singletonList("角色角色" + i));
            actorGRDO.initAddData();
            movieGDO.getActors().add(actorGRDO);

            list.add(movieGDO);
        }
        movieNeo4jRepository.saveAll(list);

        // 添加后id会自动填充
        System.out.println(list);
    }
    /**
     * 查询数据
     */
    public void queryData(){

        System.out.println("queryData ********");
        List<MovieGDO> all = movieNeo4jRepository.findAll();
        System.out.println(JsonTool.toJsonStr(all));

    }
    /**
     * 查询数据
     */
    public void queryData1(){

        List<MovieGDO> all = movieNeo4jRepository.getMovieGDOSByName("电影电影3");
        System.out.println("queryData1 ********");
        System.out.println(JsonTool.toJsonStr(all));

    }

    /**
     * 查询数据
     */
    public void queryData2(){

        List<MovieGDO> all = movieNeo4jRepository.getMovieGDOSByNameWithQuery("电影电影4");
        System.out.println("queryData2 ********");
        System.out.println(JsonTool.toJsonStr(all));

    }
    /**
     * 查询数据
     */
    public void queryData3(){

        List<MovieGDO> all = movieNeo4jRepository.getMovieGDOSByNameWithWhereQuery("电影电影5");
        System.out.println("queryData3 ********");
        System.out.println(JsonTool.toJsonStr(all));

    }

    /**
     * 查询数据
     */
    public void queryData4(){

        List<ActorGRDO> all = actorNeo4jRepository.findAll();
        System.out.println("queryData4 ********");
        System.out.println(JsonTool.toJsonStr(all));

    }
}
