package com.shixiaobai.core.dao;

import com.shixiaobai.core.model.Condition;
import com.shixiaobai.core.model.Thing;

import java.util.List;
//业务层访问数据库的CRUD
public interface FileIndexDao {
    //插入数据Thing
    void insert(Thing thing);

    List<Thing> search(Condition condition);
}
