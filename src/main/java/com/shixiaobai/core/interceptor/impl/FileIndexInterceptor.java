package com.shixiaobai.core.interceptor.impl;

import com.shixiaobai.core.common.FileConvertThing;
import com.shixiaobai.core.dao.FileIndexDao;
import com.shixiaobai.core.interceptor.FileInterceptor;
import com.shixiaobai.core.model.Thing;

import java.io.File;

public class FileIndexInterceptor implements FileInterceptor {
    private  final FileIndexDao fileIndexDao;

    public FileIndexInterceptor(FileIndexDao fileIndexDao) {
        this.fileIndexDao = fileIndexDao;
    }

    @Override
    public void apply(File file) {
       Thing thing= FileConvertThing.convert(file);
        System.out.println("Thing=>"+thing);
        fileIndexDao.insert(thing);
    }
}
