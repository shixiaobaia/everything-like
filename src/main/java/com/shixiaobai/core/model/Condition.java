package com.shixiaobai.core.model;

import lombok.Data;

@Data   //变量名会变紫
public class Condition {
    private String name;
    private String fileType;
    private  Integer limit;
    //检索结果的文件信息升序排列
    //默认是true->asc
    //false->desc
    private  Boolean  orderByAsc;
}
