package com.shixiaobai.core.search;

import com.shixiaobai.core.model.Condition;
import com.shixiaobai.core.model.Thing;

import java.util.List;

public interface FileSearch {
    //根据condition条件进行数据库的建索
    List<Thing>   search(Condition condition);





}
