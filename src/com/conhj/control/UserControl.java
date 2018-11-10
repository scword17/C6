package com.conhj.control;

import com.alibaba.fastjson.JSON;
import com.conhj.serveice.UserService;
import com.conhj.vo.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(
        name = "userControl",urlPatterns = {"/user"}
)
public class UserControl extends HttpServlet{
   private static UserService service=new UserService();
    @Override
    public void destroy() {
        super.destroy();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
       String action=req.getParameter("action");
        System.out.println(action);
        User user;
       switch(action){
           case "show":
               List<User> list=service.queryAll();
               resp.setCharacterEncoding("utf-8");
               resp.setContentType("text");
               PrintWriter out=resp.getWriter();
               String ss=JSON.toJSONString(list);
               System.out.println(ss);
               out.print(ss);
               out.flush();
               out.close();
               break;

           case "add":
               user=new User();
               user.setUsername(req.getParameter("username"));
               user.setPassword(req.getParameter("password"));
               if(service.addUser(user)){
                   RequestDispatcher dispatcher=req.getRequestDispatcher("/index.html");
                   try {
                       dispatcher.forward(req,resp);
                   } catch (ServletException e) {
                       e.printStackTrace();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
               break;

           case "del":
                user=new User();
               user.setId(Integer.parseInt(req.getParameter("id")));
               if(service.delUser(user)){
                   RequestDispatcher dispatcher=req.getRequestDispatcher("/index.html");
                   try {
                       dispatcher.forward(req,resp);
                   } catch (ServletException e) {
                       e.printStackTrace();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
               break;
           case "queryOne":
               user=new User();
               user.setId(Integer.parseInt(req.getParameter("id")));
               User user2=service.queryOne(user);

               resp.setCharacterEncoding("utf-8");
               resp.setContentType("text");
               PrintWriter out2=resp.getWriter();
               String sss=JSON.toJSONString(user2);
               out2.print(sss);
               out2.flush();
               out2.close();
               break;

           case "update":
               user=new User();
               user.setId(Integer.parseInt(req.getParameter("id")));
               user.setUsername(req.getParameter("username"));
               user.setPassword(req.getParameter("password"));
               if(service.updateUser(user)){
                   RequestDispatcher dispatcher=req.getRequestDispatcher("/index.html");
                   try {
                       dispatcher.forward(req,resp);
                   } catch (ServletException e) {
                       e.printStackTrace();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }

               break;

       }

    }


}
