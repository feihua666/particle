package com.particle.global.neo4j.test.demo.repository;

import com.particle.global.neo4j.repository.BaseNeo4jRepository;
import com.particle.global.neo4j.test.demo.MovieGDO;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023/11/15 16:00
 */
public interface MovieNeo4jRepository extends BaseNeo4jRepository<MovieGDO,Long> {

    /**
     * 根据名称获取
     * @param name
     * @return
     */
    List<MovieGDO> getMovieGDOSByName(String name);


    @Query("MATCH (n:movie {name: $name}) return n")
    List<MovieGDO> getMovieGDOSByNameWithQuery(String name);

    @Query("MATCH (n:movie) WHERE n.name=$name return n")
    List<MovieGDO> getMovieGDOSByNameWithWhereQuery(String name);

}
