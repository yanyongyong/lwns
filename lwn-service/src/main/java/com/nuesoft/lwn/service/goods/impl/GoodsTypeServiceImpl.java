package com.nuesoft.lwn.service.goods.impl;

import com.nuesoft.lwn.common.entity.goods.GoodsType;
import com.nuesoft.lwn.common.entity.goods.GoodsTypeResult;
import com.nuesoft.lwn.dao.mapper.goods.GoodsTypeMapper;
import com.nuesoft.lwn.service.goods.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanyong on 2016/10/20.
 * 商品类型逻辑实现
 */
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService{

    @Autowired
    GoodsTypeMapper goodsTypeMapper;

    /**
     * 根据id查询
     * @param parentId
     * @return
     */
    public List<GoodsTypeResult> selectByType(Integer parentId) {

        //根据条件查询
        List<GoodsType> list = goodsTypeMapper.selectByType(parentId);
        List<GoodsTypeResult> resultList = new ArrayList<GoodsTypeResult>();
        //把列表转换成treeNodelist
        for (GoodsType goodsType : list) {
            GoodsTypeResult node = new GoodsTypeResult();
            node.setId(goodsType.getTypeId());
            node.setText(goodsType.getTypeName());
            node.setState(goodsType.getParent()?"closed":"open");
            resultList.add(node);
        }
        //返回结果
        return resultList;
    }
}
