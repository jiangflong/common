package cn.feilong.common.service;
import cn.feilong.common.mapper.IMapper;
import cn.feilong.common.otherBean.Clause;
import org.springframework.data.domain.Pageable;

import java.util.List;


public abstract class AService<T> implements Service<T> {
    protected abstract IMapper<T> mapper();
    @Override
    public List<T> readAll() {
        return this.mapper().findAll();
    }

    @Override
    public T read(Integer id) {
        return this.mapper().findById(id);
    }

    @Override
    public List<T> read(List<Integer> ids) {
        return this.mapper().findAllByIds(ids);
    }

    @Override
    public List<T> read(Pageable pageable, List<Clause> params) {
        return this.mapper().findByPageable(pageable, params);
    }


    @Override
    public T create(T ele) {
        this.mapper().insert(ele);
        return ele;
    }

    @Override
    public int create(List<T> els){
        return this.mapper().insertBatch(els);
    }

    @Override
    public void delete(Integer id) {
        this.mapper().deleteById(id);
    }

    @Override
    public void delete(T ele) {
        this.mapper().delete(ele);
    }


    @Override
    public T update(T ele) {
        this.mapper().update(ele);
        return ele;
    }

    @Override
    public T findByColumn(String column, String value) {
        return this.mapper().findByColumn(column, value);
    }


}
