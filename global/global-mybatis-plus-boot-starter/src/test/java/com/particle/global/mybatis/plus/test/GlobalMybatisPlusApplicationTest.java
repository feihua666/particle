package com.particle.global.mybatis.plus.test;

import com.particle.global.mybatis.plus.test.demo.service.DemoService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2022-06-29 16:47
 */
@SpringBootApplication
@SpringBootTest
@MapperScan("com.particle.global.mybatis.plus.test.demo.mapper")
public class GlobalMybatisPlusApplicationTest {

	@Autowired
	private DemoService demoService;

	@Test
	void contextLoads() {
	}
	@Test
	public void test(){
		demoService.queryById(11L);
	}
}
