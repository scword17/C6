package com.conhj.dao;

import com.conhj.db.DruidDB;
import com.conhj.vo.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserImpl {
    private static Connection connection=null;
    public UserImpl(){
        connection= DruidDB.getConnection();
    }
    public boolean addUser(User user)  {
        PreparedStatement ps=null;
        boolean flag=false;
        try{
            ps=connection.prepareStatement("insert into user (name,pwd) values(?,?)");
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            flag=ps.executeUpdate()>0?true:false;

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }
        return flag;



    }

    public boolean updateUser(User user)  {
        PreparedStatement ps=null;
        boolean flag=false;
        try{
            ps=connection.prepareStatement("update user set name=?,pwd=? where id=?");
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setInt(3,user.getId());
            flag=ps.executeUpdate()>0?true:false;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }
        return flag;



    }
    public boolean delUser(User user)  {
        PreparedStatement ps=null;
        boolean flag=false;
        try{
            ps=connection.prepareStatement("delete from user where id=?");
            ps.setInt(1,user.getId());
            flag=ps.executeUpdate()>0?true:false;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }
        return flag;
    }
    public List<User> queryAll() {
        List<User>list=new ArrayList<>();
        PreparedStatement ps=null;
        ResultSet result=null;
        try {
            ps=connection.prepareStatement("select * from user order by id desc");
             result=ps.executeQuery();
             while(result.next()){
                 System.out.println(result.getString("id")+" "+result.getString("name")+result.getString("pwd"));
               User user=new User();
               user.setId(result.getInt("id"));
               user.setUsername(result.getString("name"));
               user.setPassword(result.getString("pwd"));
               list.add(user);
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(result!=null){
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }
        return list;
    }

    public User queryOne(User userFromWeb) {
       User user =new User();
        PreparedStatement ps=null;
        ResultSet result=null;
        try {
            ps=connection.prepareStatement("select id,name,pwd from user where id=? ");
            ps.setInt(1,userFromWeb.getId());
            result=ps.executeQuery();

//          System.out.println(result.getString("id")+" "+result.getString("name")+result.getString("pwd"));
           if(result.next()) {
               user.setId(result.getInt("id"));
               user.setUsername(result.getString("name"));
               user.setPassword(result.getString("pwd"));
               return user;
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(result!=null){
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }
        return null;
    }





}
