package com.shixiaobai.core.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum FileType {
    IMG("png","jpeg","jpe","gif"),

    DOC("ppt","pptx","doc","docx","pdf"),
    BIN("exe","sh","jar","msi"),
    ARCHIVE("zip","rar"),
    OTHER("*");
    //对应的文件类型的扩展名集合
    private Set<String> extend=new HashSet<>();
    FileType(String ...extend){
        this.extend.addAll(Arrays.asList(extend));
    }

    /*根据文件扩展名获取文件类型*/
    //根据文件类型名获取文件类型对象
    public static FileType lookupByName(String name){
        for(FileType fileType:FileType.values()){
            if(fileType.name().equals(name)){
                return fileType;
            }
        }
        return FileType.OTHER;
    }



}
