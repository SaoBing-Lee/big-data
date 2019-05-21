package com.yangzhongli.sp.service.base;


import tk.mybatis.mapper.entity.Example;

import java.util.List;
public interface BaseService<T, Id> {

    int save(T t);

    int delete(T t);

    int deleteById(Id id);

    int update(T t);

    T getById(Id id);

    List<T> getAll();

    int saveAll(List<T> list);

    List<T> searchByExample(Example example);

}
