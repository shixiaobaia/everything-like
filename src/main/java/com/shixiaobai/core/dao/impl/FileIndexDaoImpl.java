package com.shixiaobai.core.dao.impl;

import com.shixiaobai.core.dao.FileIndexDao;
import com.shixiaobai.core.model.Condition;
import com.shixiaobai.core.model.FileType;
import com.shixiaobai.core.model.Thing;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class FileIndexDaoImpl implements FileIndexDao {
    private final DataSource dataSource;

    public FileIndexDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Thing thing) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //1.获取数据库链接
            connection = dataSource.getConnection();
            //2.准备SQL语句
            //insert into file_infex
            String sql = "insert into file_index(name,path,depth,file_type)values (?,?,?,?,)";
            //3.准备命令
            statement = connection.prepareStatement(sql);
            //4.设置参数1，2，3，4
            statement.setString(1, thing.getName());
            statement.setString(2, thing.getPath());
            statement.setString(3, String.valueOf(thing.getDepth()));
            //FileType.Doc->doc
            statement.setString(4, thing.getFiletype().name());
            //5.执行命令
            statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            releaseResource(null,statement,connection);
        }
    }



    public  void delete(Thing thing){
        Connection connection=null;
        PreparedStatement statement=null;
        try{
            //1.获取数据库链接
            connection =dataSource.getConnection();
            //2.准备sql语句
            String sql="delete from file_index where path like'"+thing.getPath()+"%'";
            //3.准备命令
            statement=connection.prepareStatement(sql);
            //4.执行命令
            statement.executeUpdate();

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            releaseResource(null,statement,connection);
        }
    }
    @Override
    public List<Thing> search(Condition condition) {
        List<Thing> things = new ArrayList<>();

        //TODO
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            //1.获取数据库链接
            connection = dataSource.getConnection();
           //2.准备sql语句

            //name:like模糊匹配
            //filetype:=
            //limit:limit offset
            //深度从小到大，从大到小，深度排序,orderby
            StringBuilder  sqlBuilder=new StringBuilder();//为何不用stringbuffer,离开方法会销毁，不会被多线程共享
            sqlBuilder.append("select name,path,depth,file_type from file_index");
            //name前模糊后模糊还是前后
            sqlBuilder.append("where")
                    .append("name like %").append(condition.getName()).append("%'");
            if(condition.getFileType()!=null){
                sqlBuilder.append("and file_type='")
                        .append(condition.getFileType().toUpperCase()).append("'");//数据库寸的大写，万一用户输入小写
            }
            //limit,order必选的
            if(condition.getOrderByAsc()!=null){
                sqlBuilder.append("order by depth") .append(condition.getOrderByAsc()?"asc":"dsc");
            }
            if(condition.getLimit()!=null){
                sqlBuilder.append("limit").append(condition.getLimit()).append("offset 0");
            }


            //3.准备命令
            statement = connection.prepareStatement(sqlBuilder.toString());
            //4.设置参数1，2，3，4


            //5.执行命令
            resultSet=statement.executeQuery();

            //6.处理结果
            while (resultSet.next()) {
                //数据库中的行记录变为java中的thing对象

                Thing thing = new Thing();
                thing.setName(resultSet.getString("name"));
                thing.setPath(resultSet.getString("path"));
                thing.setDepth(resultSet.getInt("depth"));
                String fileType = resultSet.getString("file_type");
                thing.setFiletype(FileType.lookupByName(fileType));
                things.add(thing);


            }


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            releaseResource(resultSet,statement,connection);
        }


        return things;

    }
    //解决内部代码大量重复问题：重构
    private  void releaseResource(ResultSet resultSet,PreparedStatement statement,Connection connection){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }




}

