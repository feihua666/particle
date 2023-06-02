package com.particle.global.security;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.security.security.login.UserGrantedAuthority;
import lombok.Data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023-06-02 16:16
 */
public class ObjectMapperTest {

	public static void main(String[] args) throws IOException {
		LoginUserTest();
		UserTest();
	}


	public static void LoginUserTest() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);

		LoginUser loginUser = new LoginUser();
		loginUser.setLoginIp("xxx");
		loginUser.setName("name");
		loginUser.addAuthority(UserGrantedAuthority.userGrantedAuthority);
		String s = objectMapper.writerWithView(LoginUser.UserWebIgnoreView.class).writeValueAsString(loginUser);
		System.out.println(s);
	}

	public static void UserTest() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
		//创建对象
		UserTest user = new UserTest("isea533","123456");
		//序列化
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		objectMapper.writerWithView(UserTest.WithoutPasswordView.class).writeValue(bos,user);
		System.out.println(bos.toString());


		bos.reset();
		objectMapper.writerWithView(UserTest.WithPasswordView.class).writeValue(bos,user);
		System.out.println(bos.toString());
	}
	@Data
	private static class UserTest{
		public interface WithoutPasswordView {};
		public interface WithPasswordView extends WithoutPasswordView {};

		@JsonView(WithoutPasswordView.class)

		private String username;
		@JsonView(WithPasswordView.class)

		private String password;
		private String name  = "name";

		public UserTest() {
		}

		public UserTest(String username,String password) {
			this.username = username;
			this.password = password;
		}

		public String getUsername() {
			return this.username;
		}

		public String getpassword() {
			return this.password;
		}
	}
}
