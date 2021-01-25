package cn.feilong.common.service;


import cn.feilong.common.otherBean.Clause;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Service<T> {


    // 查询所有记录
    public List<T> readAll();

    // 按id 查询记录
    public T read(Integer id);

    // 按id 批量查询记录
    public List<T> read(List<Integer> ids);

    // 分页查询
    public List<T> read(Pageable pageable, List<Clause> params);

    // 新建记录
    T create(T ele);

    int create(List<T> els);

    // 按id删除记录
    void delete(Integer id);

    // 删除记录
    void delete(T ele);

    // 更新单个记录
    T update(T ele);

    // 通过column 查找
    T findByColumn(String column, String value);

}