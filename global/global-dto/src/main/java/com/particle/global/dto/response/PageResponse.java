package com.particle.global.dto.response;

import com.particle.global.exception.code.IErrorCode;

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
	private static int defaultPageSize = 10;
	/**
	 * 默认当前页数
	 */
	private static int defaultPageNo = 1;

	/**
	 * 总条数
	 */
	private int totalCount = 0;

	/**
	 * 每页分页条数
	 */
	private int pageSize = defaultPageSize;

	/**
	 * 当前页数
	 */
	private int pageNo = defaultPageNo;

	/**
	 * 数据
	 */
	private Collection<T> data;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		if (pageSize < 1) {
			return 10;
		}
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			this.pageSize = defaultPageSize;
		} else {
			this.pageSize = pageSize;
		}
	}

	public int getPageNo() {
		if (pageNo < defaultPageNo) {
			return defaultPageNo;
		}
		return pageNo;
	}

	public void setPageNo(int pageNo) {
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

	public int getTotalPages() {
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
	public static <T> PageResponse<T> of(int pageSize, int pageNo) {
		PageResponse<T> response = new PageResponse<>();
		response.setSuccess(true);
		response.setData(Collections.emptyList());
		response.setTotalCount(0);
		response.setPageSize(pageSize);
		response.setPageNo(pageNo);
		return response;
	}

	public static <T> PageResponse<T> of(Collection<T> data, int totalCount, int pageSize, int pageNo) {
		PageResponse<T> response = new PageResponse<>();
		response.setSuccess(true);
		response.setData(data);
		response.setTotalCount(totalCount);
		response.setPageSize(pageSize);
		response.setPageNo(pageNo);
		return response;
	}

}
