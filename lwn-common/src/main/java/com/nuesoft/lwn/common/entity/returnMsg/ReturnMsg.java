package com.nuesoft.lwn.common.entity.returnMsg;

import lombok.Data;

/**
 * 返回信息实体
 *
 * @author yayong
 */

@Data
public class ReturnMsg {
	private String returnMsg;

	/**
	 * 有参构造
	 *
	 * @param returnMsg
	 */
	public ReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}


}
