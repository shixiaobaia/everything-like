package com.shixiaobai.core.common;

import com.shixiaobai.core.model.FileType;
import com.shixiaobai.core.model.Thing;

import java.io.File;
//辅助工具类
//文件转things
public  final class FileConvertThing {
    //普通的工具类，不能覆写不能继承
    private FileConvertThing(){

    }
    public static Thing convert(File file){
        Thing thing=new Thing();
        thing.setName(file.getName());
        thing.setPath(file.getAbsolutePath());
        thing.setDepth(computeFileDepth(file));//怎么计算深度
        thing.setFiletype(computeFileType(file));
        return thing;
    }
    private static int  computeFileDepth(File file){
        int dept=0;

       String[] segments= file.getAbsolutePath().split(File.separator);//文件路径分隔符
        dept=segments.length;
        return dept;
    }
    private static FileType computeFileType(File file){
        if(file.isDirectory()){
            return FileType.OTHER;
        }
        String fileName=file.getName();
        int index=file.getName().lastIndexOf(".");
        if(index!=-1&&index<fileName.length()-1){
           String extend= file.getName().substring(index+1);//取到扩展名
            //小bug abc.
           // return FileType.lookup(extend);
        }
        else{
            return FileType.OTHER;
        }
    }
}
