package com.wsmhz.common.business.service;

import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * create by tangbj on 2018/4/27
 */
public interface BaseService<T> {
    /**
     * 根据id查询数据
     */
     T selectByPrimaryKey(Long id);
    /**
     * 查询所有数据
     */
     List<T> selectAll();
    /**
     * 根据条件查询一条数据，如果有多条数据会抛出异常
     */
     T selectOne(T record);
    /**
     * 根据条件查询数据列表,根据record不为null的字段
     */
     List<T> select(T record);
    /**
     * 根据条件查询
     * @param property 数据的原有字段
     * @param value 传入的条件
     * @return
     */
     List<T> selectByExample(T record, String property, Object value);
    /**
     * 根据条件查询
     * @return
     */
    List<T> selectByExample(Example example);
    /**
     * 分页查询(根据更新时间降序)
     * @param pageNum 当前页
     * @param pageSize  每页数量
     */
     PageInfo<T> selectPageListOrderByDate(Integer pageNum, Integer pageSize, T record);
    /**
     * 分页查询
     * @param pageNum 当前页
     * @param pageSize  每页数量
     */
     PageInfo<T> selectPageList(Integer pageNum, Integer pageSize, T record);
    /**
     * 新增数据，返回成功的条数
     */
     Integer insert(T record);
    /**
     * 新增数据，使用不为null的字段，返回成功的条数
     */
     Integer insertSelective(T record);
    /**
     * 修改数据，返回成功的条数
     */
     Integer updateByPrimaryKey(T record);
    /**
     * 修改数据，使用不为null的字段，返回成功的条数
     */
     Integer updateByPrimaryKeySelective(T record);
    /**
     * 根据id删除数据
     */
     Integer deleteByPrimaryKey(Long id);
    /**
     * 根据符合Exmaple条件删除
     * @param property 数据的原有字段
     * @param value 传入的条件
     */
     Integer deleteByExample(T record, String property, Object value);
}
