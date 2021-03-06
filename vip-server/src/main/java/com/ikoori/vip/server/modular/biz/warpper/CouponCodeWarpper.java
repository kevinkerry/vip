package com.ikoori.vip.server.modular.biz.warpper;

import java.util.List;
import java.util.Map;

import com.ikoori.vip.common.constant.state.CouponCodeStatus;
import com.ikoori.vip.common.warpper.BaseControllerWarpper;

/**
 * 券码包装类
 *
 * @author chengxg
 * @date 2017年2月13日 下午10:47:03
 */
public class CouponCodeWarpper extends BaseControllerWarpper {

    public CouponCodeWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
	public void warpTheMap(Map<String, Object> map) {
		Integer status = Integer.valueOf(map.get("useStatus").toString());
		map.put("useStatus", CouponCodeStatus.valueOf(status));
	}

}
