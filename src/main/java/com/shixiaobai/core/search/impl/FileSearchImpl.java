package com.shixiaobai.core.search.impl;

import com.shixiaobai.core.dao.FileIndexDao;
import com.shixiaobai.core.model.Condition;
import com.shixiaobai.core.model.Thing;
import com.shixiaobai.core.search.FileSearch;

import java.util.List;

public class FileSearchImpl implements FileSearch {
    //被final修饰有几种方式
    //直接赋值，立即初始化，构造方法的方式，构造块
private  final FileIndexDao fileIndexDao;

    public FileSearchImpl(FileIndexDao fileIndexDao) {
        this.fileIndexDao = fileIndexDao;

    }


    @Override
    public List<Thing> search(Condition condition) {
        //TODO
        //数据库的处理逻辑

        return this.fileIndexDao.search(condition);

    }
}
