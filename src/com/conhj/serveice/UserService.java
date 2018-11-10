package com.conhj.serveice;

import com.conhj.dao.UserImpl;
import com.conhj.vo.User;

import java.util.List;

public class UserService {
      private UserImpl dao;
      public UserService(){
          dao=new UserImpl();
      }
    public List<User> queryAll(){
        return dao.queryAll();
    }
    public boolean addUser(User user){
        return dao.addUser(user);
    }
    public boolean delUser(User user){
        return dao.delUser(user);
    }
    public User queryOne(User user){
        return dao.queryOne(user);
    }
    public boolean updateUser(User user){
        return dao.updateUser(user);
    }


}
