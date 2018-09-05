package com.wsmhz.common.business.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * create by tangbj on 2018/4/27
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private Mapper<T> mapper;

    @Override
    public T selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public T selectOne(T record) {
        return mapper.selectOne(record);
    }

    @Override
    public List<T> select(T record) {
        return mapper.select(record);
    }

    @Override
    public List<T> selectByExample(T record, String property, Object value) {
        Example example = new Example(record.getClass());
        example.createCriteria().andEqualTo(property, value);
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> selectByExample(Example example) {
        return mapper.selectByExample(example);
    }

    @Override
    public PageInfo<T> selectPageListOrderByDate(Integer pageNum, Integer pageSize, T record) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(record.getClass());
        example.setOrderByClause("update_date desc");
        List<T> list = mapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<T> selectPageList(Integer pageNum, Integer pageSize, T record) {
        PageHelper.startPage(pageNum, pageSize);
        List<T> list = mapper.select(record);
        return new PageInfo<>(list);
    }

    @Override
    public Integer insert(T record) {
        return mapper.insert(record);
    }

    @Override
    public Integer insertSelective(T record) {
        return mapper.insertSelective(record);
    }

    @Override
    public Integer updateByPrimaryKey(T record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public Integer updateByPrimaryKeySelective(T record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteByExample(T record, String property, Object value) {
        Example example = new Example(record.getClass());
        example.createCriteria().andEqualTo(property, value);
        return mapper.deleteByExample(example);
    }


}
