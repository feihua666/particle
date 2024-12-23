package com.particle.global.web.filter;

import cn.hutool.core.io.IoUtil;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <p>
 * request包装可连续读取，但耗费内存，暂不使用
 * </p>
 *
 * @author yangwei
 * @since 2021-08-03 22:19
 */
public class BodyReaderHttpServletRequestWrapper extends ContentCachingRequestWrapper {

	public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		// 这里先获取一下，以缓存否则会有问题
		super.getParameterMap();
		// 再从 inputstream 中读取出来,在有的content-type下如：form-data，可能会有流已经被关闭的情况，
		// 这里try一下，有异常也不影响正常计数据，因为super.getParameterMap(); 已经把数据读出来了，流被关闭了
		try {
			IoUtil.readBytes(super.getInputStream(),false);
		}catch (Exception e){}
	}


	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(getContentAsByteArray());
		return new ServletInputStream() {
			@Override
			public boolean isFinished() {
				return byteArrayInputStream.available() == 0;
			}

			/**
			 * 已经转为内存数据，已经准备好
			 * @return
			 */
			@Override
			public boolean isReady() {
				return true;
			}

			@Override
			public void setReadListener(ReadListener listener) {

			}

			@Override
			public int read() {
				return byteArrayInputStream.read();
			}
		};
	}
}
