package cn.feilong.common.mapper;


import cn.feilong.common.otherBean.Clause;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 抽离出mapper接口，方便使用
 * @param <T>
 */
public interface IMapper<T> {
    
    T findByColumn(@Param("column") String column, @Param("value") String value);
    //List<T> findAllByColumn(@Param("column") String column, @Param("value") String value,@Param("uid") Integer uid);

    List<T> findByColumnsOr(@Param("params1") List<Clause> params1,@Param("params2") List<Clause> params2);

    List<T> findByColumns(@Param("params") List<Clause> params);



    List<T> findAll();

    List<T> findAllWithCondition(@Param("params") List<Clause> params);

    List<T> findAllByIds(@Param("ids") List<Integer> ids);

    List<T> findAllByIdsWithCondition(@Param("ids") List<Integer> ids,@Param("params") List<Clause> params);

    List<T> findByPageable(@Param("pageable") Pageable pageable,@Param("params") List<Clause> params);

    List<T> findByPageableOr(@Param("pageable") Pageable pageable,@Param("params1") List<Clause> params1,@Param("params2") List<Clause> params2);

    T findById(Integer id);


    Integer deleteById(Integer id);

    Integer delete(T ele);

    Integer deleteBatch(@Param("list") List<Integer> list);

    Integer insert(T element);

    Integer insertBatch(@Param("list") List<T> list);

    Integer update(T element);
}
