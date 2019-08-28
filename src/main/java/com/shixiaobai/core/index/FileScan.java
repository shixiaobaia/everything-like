package com.shixiaobai.core.index;

import com.shixiaobai.core.interceptor.FileInterceptor;

//希望一次处理一个盘符
public interface FileScan {
    //遍历path
    void index(String path);
    //遍历的拦截器
    void interceptor(FileInterceptor interceptor);

}
