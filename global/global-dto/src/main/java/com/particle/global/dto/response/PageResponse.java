package com.particle.global.dto.response;

import com.particle.global.light.share.code.IErrorCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 分页响应
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 14:51
 */
public class PageResponse<T> extends Response {

	private static final long serialVersionUID = 1L;

	/**
	 * 默认分页条数
	 */
	private static long defaultPageSize = 10;
	/**
	 * 默认当前页数
	 */
	private static long defaultPageNo = 1;

	/**
	 * 总条数
	 */
	private long totalCount = 0;

	/**
	 * 每页分页条数
	 */
	private long pageSize = defaultPageSize;

	/**
	 * 当前页数
	 */
	private long pageNo = defaultPageNo;

	/**
	 * 数据
	 */
	private Collection<T> data;

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getPageSize() {
		if (pageSize < 1) {
			return 10;
		}
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		if (pageSize < 1) {
			this.pageSize = defaultPageSize;
		} else {
			this.pageSize = pageSize;
		}
	}

	public long getPageNo() {
		if (pageNo < defaultPageNo) {
			return defaultPageNo;
		}
		return pageNo;
	}

	public void setPageNo(long pageNo) {
		if (pageNo < defaultPageNo) {
			this.pageNo = defaultPageNo;
		} else {
			this.pageNo = pageNo;
		}
	}

	public List<T> getData() {
		return null == data ? Collections.emptyList() : new ArrayList<>(data);
	}

	public void setData(Collection<T> data) {
		this.data = data;
	}

	public long getTotalPages() {
		return this.totalCount % this.pageSize == 0 ? this.totalCount
				/ this.pageSize : (this.totalCount / this.pageSize) + 1;
	}

	public boolean isEmpty() {
		return data == null || data.isEmpty();
	}

	public boolean isNotEmpty() {
		return !isEmpty();
	}

	public static PageResponse buildSuccess() {
		PageResponse response = new PageResponse();
		response.setSuccess(true);
		return response;
	}

	public static PageResponse buildFailure(IErrorCode errCodeGlobal) {
		return buildFailure(errCodeGlobal,null);
	}
	public static PageResponse buildFailure(IErrorCode errCodeGlobal,String userTip) {
		PageResponse response = new PageResponse();
		response.setSuccess(false);
		response.setStatus(errCodeGlobal.getStatus());
		response.setErrCode(errCodeGlobal.getErrCode());
		response.setErrMessage(handleUserTip(errCodeGlobal,userTip));
		return response;
	}
	public static <T> PageResponse<T> of(long pageSize, long pageNo) {
		PageResponse<T> response = new PageResponse<>();
		response.setSuccess(true);
		response.setData(Collections.emptyList());
		response.setTotalCount(0);
		response.setPageSize(pageSize);
		response.setPageNo(pageNo);
		return response;
	}

	public static <T> PageResponse<T> of(Collection<T> data, long totalCount, long pageSize, long pageNo) {
		PageResponse<T> response = new PageResponse<>();
		response.setSuccess(true);
		response.setData(data);
		response.setTotalCount(totalCount);
		response.setPageSize(pageSize);
		response.setPageNo(pageNo);
		return response;
	}

}
