package com.bcp.retobcp.response;

import com.bcp.retobcp.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseWebResponse<T> {
	private T data;

	public static BaseWebResponse successNoData() {
		return BaseWebResponse.builder().build();
	}

	public static <T> BaseWebResponse<T> successWithData(T data) {
		return BaseWebResponse.<T>builder().data(data).build();
	}

	public static BaseWebResponse error(ErrorCode errorCode) {
		return BaseWebResponse.builder().build();
	}
}
