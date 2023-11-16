package com.particle.global.neo4j.test.demo.repository;

import com.particle.global.neo4j.repository.BaseNeo4jRepository;
import com.particle.global.neo4j.test.demo.PersonGDO;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023-11-16 13:42:10
 */
public interface PersonNeo4jRepository extends BaseNeo4jRepository<PersonGDO,Long> {
}
