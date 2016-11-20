package com.nuesoft.lwn.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.user.Priv;
import com.nuesoft.lwn.dao.mapper.user.PrivMapper;
import com.nuesoft.lwn.service.user.PrivService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yanyong on 2016/10/17.
 * 权限业务流程逻辑实现
 */

@Service
public class PrivServiceImpl implements PrivService {

    @Autowired
    PrivMapper privMapper;

    /**
     * 新增权限
     * @param priv
     * @return
     */
    public int insertPriv(Priv priv) {
        return privMapper.insertPriv(priv);
    }

    /**
     * 根据id删除权限
     * @param rolePrivId
     */
    public int deleteByPrivId(Integer rolePrivId) {
        return privMapper.deleteByPrivId(rolePrivId);
    }

    /**
     * 模糊查询
     * @param page
     * @param rows
     * @param priv
     * @return
     */
    public EUDataGridResult dimSelectPriv(int page, int rows, Priv priv) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Priv> list = privMapper.dimSelectPriv(priv);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Priv> pageInfo = new PageInfo<Priv>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    /**
     * 分页查询
     * @param page,要显示第几页
     * @param rows，每页显示的行数
     * @return
     */
    public EUDataGridResult selectAllPriv(int page, int rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Priv> list = privMapper.selectAllPriv();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Priv> pageInfo = new PageInfo<Priv>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    /**
     * 编辑权限
     * @param priv
     */
    public int editPrivByrolePrivId(Priv priv) {
        return privMapper.editPrivByrolePrivId(priv);
    }
}
