package com.shixiaobai.core.interceptor.impl;

import com.shixiaobai.core.interceptor.FileInterceptor;

import java.io.File;

public class FilePrintInterceptor  implements FileInterceptor {
    @Override
    public void apply(File file) {
        System.out.println(file.getAbsoluteFile());
    }
}
