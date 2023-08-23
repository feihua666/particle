package com.particle.global.openapi.enums;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

import java.util.function.Function;

/**
 * <p>
 * 开放接口摘要算法
 * </p>
 *
 * @author yangwei
 * @since 2023-08-02 17:32
 */
public enum OpenapiDigestAlgorithm {

	/**
	 *
	 */
	MD2("MD2",(str)-> {
		Digester digester = new Digester(DigestAlgorithm.MD2);
		return digester.digestHex(str);
	}),
	MD5("MD5",(str)-> {
		Digester digester = new Digester(DigestAlgorithm.MD5);
		return digester.digestHex(str);
	}),
	SHA_1("SHA-1",(str)-> {
		Digester digester = new Digester(DigestAlgorithm.SHA1);
		return digester.digestHex(str);
	}),
	SHA_256("SHA-256",(str)-> {
		Digester digester = new Digester(DigestAlgorithm.SHA256);
		return digester.digestHex(str);
	}),
	SHA_384("SHA-384",(str)-> {
		Digester digester = new Digester(DigestAlgorithm.SHA384);
		return digester.digestHex(str);
	}),
	SHA_512("SHA-512",(str)-> {
		Digester digester = new Digester(DigestAlgorithm.SHA512);
		return digester.digestHex(str);
	}),

	/**
	 * SM3 是国密摘要算法，国密算法需要引入Bouncy Castle库的依赖
	 */
	SM3("SM3",(str)-> {
		return SmUtil.sm3(str);
	});
	/**
	 * 名称，仅展示
	 */
	private String name;
	/**
	 * 摘要算法
	 */
	private Function<String, String> algorithm;
	OpenapiDigestAlgorithm(String name, Function<String,String> algorithm) {
		this.name = name;
		this.algorithm = algorithm;
	}

	public String getName() {
		return name;
	}

	public Function<String, String> getAlgorithm() {
		return algorithm;
	}

	/**
	 * 摘要计算
	 * @param str
	 * @return
	 */
	public String digestHex(String str) {
		return algorithm.apply(str);
	}
}
