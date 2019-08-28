package com.shixiaobai.config;

import lombok.Getter;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

//文件进行遍历时有些需要排除
@Getter
public class EverythingLikeConfig {
     private  static  volatile EverythingLikeConfig config;
     //建立索引的路径
     private Set<String> includePath=new HashSet<>();
     //排除索引文件的路径
     private Set<String> excludePath=new HashSet<>();

     private  EverythingLikeConfig(){

     }
     public  static  EverythingLikeConfig getInstance(){
         if (config==null){
             synchronized (EverythingLikeConfig.class){
                 if(config==null){
                     config=new EverythingLikeConfig();
                     //遍历的目录
                     //排除的目录
                     //1.获取文件系统
                  FileSystem fileSystem=FileSystems.getDefault();
                 Iterable<Path> iterable= fileSystem.getRootDirectories();
                 iterable.forEach(path -> config.includePath.add(path.toString()));
                 // 排除的目录，c://windows,progranfile,programfiles,progrndata
                     String osname=System.getProperty("os.name");
                     if(osname.startsWith("Windows")){
                         config.getExcludePath().add("C:\\Windows");
                         config.getExcludePath().add("C:\\ProgramFiles(x86)");
                         config.getExcludePath().add("C:\\ProgramFiles");
                         config.getExcludePath().add("C:\\ProgramData");
                     }else{
                         config.getExcludePath().add("/tmp");
                         config.getExcludePath().add("/etc");
                         config.getExcludePath().add("/root");
                     }



                 }
             }
         }
         return config;
     }


}
