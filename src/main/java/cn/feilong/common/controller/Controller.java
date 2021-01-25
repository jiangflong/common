package cn.feilong.common.controller;

import com.vividsolutions.jts.io.ParseException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;

public interface Controller<T> {
    @RequestMapping(value = "readAll", method = RequestMethod.GET)
    ResponseEntity readAll();

    //查询一个对象
    @RequestMapping(value = "read/{id}", method = RequestMethod.GET)
    ResponseEntity read(@PathVariable Integer id);

    //查询多个对象
    @RequestMapping(value = "readByIds", method = RequestMethod.GET)
    ResponseEntity read(@RequestBody List<Integer> ids);

    @RequestMapping(value = "readpage", method = RequestMethod.GET)
    ResponseEntity read(Pageable pageable);

    @RequestMapping(value = "create", method = RequestMethod.POST)
    ResponseEntity create(@RequestBody(required = true) T ele) throws IOException, ParseException;

    @RequestMapping(value = "createAll", method = RequestMethod.POST)
    ResponseEntity create(@RequestBody(required = true) List<T> ele);

    //删除
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    void delete(Integer id);

    //更新
    @RequestMapping(value = "update", method = RequestMethod.POST)
    ResponseEntity update(@RequestBody(required = true) T ele) throws Exception;
}
