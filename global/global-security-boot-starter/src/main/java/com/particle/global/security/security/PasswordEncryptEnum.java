package com.particle.global.security.security;

import lombok.Data;
import lombok.Getter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <p>
 * 加密方式
 * </p>
 *
 * @author yangwei
 * @since 2022-11-26 11:11
 */
public enum PasswordEncryptEnum {


	/**
	 * 加密方式 {@link org.springframework.security.crypto.factory.PasswordEncoderFactories#createDelegatingPasswordEncoder()}
	 */
	bcrypt("bcrypt"),
	ldap("ldap"),
	MD4("MD4"),
	MD5("MD5"),
	noop("noop"),
	pbkdf2("pbkdf2"),
	scrypt("scrypt"),
	SHA_1("SHA-1"),
	SHA_256("SHA-256"),
	sha256("sha256"),
	argon2("argon2");

	@Getter
	private String encrypt;

	PasswordEncryptEnum(String encrypt) {
		this.encrypt = encrypt;
	}

	/**
	 * 添加前缀，
	 * @param encrypt
	 * @return
	 */
	public static String prefixEncrypt(String encrypt){
		return idPrefix + encrypt + idSuffix;
	}

	/**
	 * 前缀和加密密码拼接
	 * 使用 {@link DelegatingPasswordEncoder}  验证密码时，需要拼接前缀
	 * @param encrypt
	 * @param rawEncodePassword
	 * @return
	 */
	public static String prefixEncodePassword(String encrypt, String rawEncodePassword) {
		return prefixEncrypt(encrypt) + rawEncodePassword;
	}

	/**
	 * 默认为 {@link PasswordEncryptEnum#bcrypt}
	 * @return
	 */
	public static PasswordEncoder passwordEncoder(){
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}


	private static String idPrefix = "{";
	private static String idSuffix = "}";

	/**
	 * 提取id，拷贝自 {@link DelegatingPasswordEncoder#extractId(java.lang.String)}
	 * @param prefixEncodedPassword
	 * @return
	 */
	public static String extractEncrypt(String prefixEncodedPassword) {
		if (prefixEncodedPassword == null) {
			return null;
		}
		int start = prefixEncodedPassword.indexOf(idPrefix);
		if (start != 0) {
			return null;
		}
		int end = prefixEncodedPassword.indexOf(idSuffix, start);
		if (end < 0) {
			return null;
		}
		return prefixEncodedPassword.substring(start + idPrefix.length(), end);
	}

	/**
	 * 带有前缀的密码结构
	 */
	@Data
	public static class PrefixEncodedPassword{
		private String encrypt;
		private String rawEncodePassword;


		public PrefixEncodedPassword(String prefixEncodedPassword) {
			this.encrypt = extractEncrypt(prefixEncodedPassword);
			this.rawEncodePassword = prefixEncodedPassword.replace(prefixEncrypt(this.encrypt), "");
		}

		public PrefixEncodedPassword(String encrypt, String rawEncodePassword) {
			this.encrypt = encrypt;
			this.rawEncodePassword = rawEncodePassword;
		}

		@Override
		public String toString() {
			return prefixEncodePassword(this.encrypt, this.rawEncodePassword);
		}
	}
}
