package com.shixiaobai.core.model;

import lombok.Data;

//文件属性信息索引之后的记录Thing表示
@Data
public class Thing {
    //文件名称保留，不要路径
    private String name;
    private String path;
    private Integer depth;//文件路径深度
    private FileType   filetype ;//文件类型

}
