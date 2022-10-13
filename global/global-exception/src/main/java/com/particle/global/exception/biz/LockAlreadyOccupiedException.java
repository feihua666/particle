package com.particle.global.exception.biz;

import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.exception.code.IErrorCode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 锁已经被占用异常
 * 一般在分布式锁，本地锁使用，在尝试加锁时，锁还没有被释放
 * </p>
 *
 * @author yangwei
 * @since 2022-05-14 12:18
 */
public class LockAlreadyOccupiedException extends BizException {
	public static IErrorCode DEFAULT_ERR_CODE = ErrorCodeGlobalEnum.LOCK_ALREADY_OCCUPIED_ERROR;

	public LockAlreadyOccupiedException() {
		super(DEFAULT_ERR_CODE);
	}

	public LockAlreadyOccupiedException(String lockKey) {
		super(DEFAULT_ERR_CODE, wrapData(lockKey));

	}

	private static Map<String, Object> wrapData(String lockKey) {
		Map<String, Object> data = new HashMap<>(1);
		data.put("lockKey", lockKey);
		return data;
	}

}
