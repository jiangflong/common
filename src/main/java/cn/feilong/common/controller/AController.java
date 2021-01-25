package cn.feilong.common.controller;

import cn.feilong.common.service.Service;
import cn.feilong.common.utils.ResponseBuilder;
import com.vividsolutions.jts.io.ParseException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;


public abstract class AController<T> implements Controller<T> {
    public abstract Service<T> aService();

    @Override
    @RequestMapping(value = "readAll", method = RequestMethod.GET)
    public ResponseEntity readAll() {
        return ResponseBuilder.ok(this.aService().readAll());
    }
    //查询一个对象
    @Override
    @RequestMapping(value = "read/{id}", method = RequestMethod.GET)
    public ResponseEntity read(@PathVariable Integer id) {
        return ResponseBuilder.ok(this.aService().read(id));
    }

    //查询多个对象
    @Override
    @RequestMapping(value = "readByIds", method = RequestMethod.GET)
    public ResponseEntity read(@RequestBody List<Integer> ids)
    {
        return ResponseBuilder.ok(this.aService().read(ids));
    }

    @Override
    @RequestMapping(value = "readpage", method = RequestMethod.GET)
    public ResponseEntity read(Pageable pageable) {
        return ResponseBuilder.ok(this.aService().read(pageable,null));
    }

    @Override
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody(required = true) T ele) throws IOException, ParseException {
         var element  = this.aService().create(ele);
        return ResponseBuilder.ok(element);
    }

    @Override
    @RequestMapping(value = "createAll", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody(required = true) List<T> ele) {
        var element  = this.aService().create(ele);
        return ResponseBuilder.ok(element);
    }

    //删除
    @Override
    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public void delete(@PathVariable Integer id) {
        this.aService().delete(id);
    }

    //更新
    @Override
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseEntity update(@RequestBody(required = true) T ele) throws Exception {
        this.aService().update(ele);
        return ResponseBuilder.ok();
    }

}
