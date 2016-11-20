package com.nuesoft.lwn.controller.goods;

import com.nuesoft.lwn.common.entity.goods.GoodsTypeResult;
import com.nuesoft.lwn.service.goods.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by yanyong on 2016/10/20.
 * 商品类型业务控制
 */

@Controller
@RequestMapping(value = "/goodsType")
public class GoodsTypeController {
    @Autowired
    GoodsTypeService goodsTypeService;

    /**
     * 查询商品类型
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectByType")
    public List<GoodsTypeResult> selectByType(@RequestParam(value="id",defaultValue="0")Integer id){
        List<GoodsTypeResult> list = goodsTypeService.selectByType(id);
        return list;
    }
}
