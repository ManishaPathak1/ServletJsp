package com.example.finalproject;

import Model.User;
import PasswordHashing.HashPassword;
import Service.userService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "helloServlet", urlPatterns = "/user")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");


        String page = req.getParameter("page");
        if (page.equalsIgnoreCase("register")) {
            User user = new User();
            user.setUserName(req.getParameter("username"));
            user.setEmail(req.getParameter("email"));
            user.setAddress(req.getParameter("address"));
           // user.setPassword(req.getParameter("password"));
            user.setPassword(HashPassword.passwordHashing(req.getParameter("password")));

            new userService().insertUser(user);
            RequestDispatcher rq = req.getRequestDispatcher("index.jsp");
            rq.forward(req, resp);
        }
        if (page.equalsIgnoreCase("newuser")) {
            RequestDispatcher rq = req.getRequestDispatcher("pages/register.jsp");
            rq.forward(req, resp);
        }
        if (page.equalsIgnoreCase("loginuser")) {
            RequestDispatcher rq = req.getRequestDispatcher("index.jsp");
            rq.forward(req, resp);
        }
        if (page.equalsIgnoreCase("login")) {
            //form data
            String name = req.getParameter("username");
            String password = HashPassword.passwordHashing(req.getParameter("password"));
            // call service
            User user = new userService().getUserLogin(name, password);
            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("sn", name);
                Cookie cookie = new Cookie("cn", name);
                resp.addCookie(cookie);
                RequestDispatcher rq = req.getRequestDispatcher("pages/Dash.jsp");
                rq.forward(req, resp);

            } else {
                req.setAttribute("message", "invalid username or password");

                RequestDispatcher rq = req.getRequestDispatcher("index.jsp");
                rq.forward(req, resp);

            }


        }
        if (page.equalsIgnoreCase("Dash")) {
            User user = new User();
            List<User> userList = new userService().getUserList();
            req.setAttribute("user", user);
            req.setAttribute("userList", userList);
            RequestDispatcher rq = req.getRequestDispatcher("pages/Dash.jsp");
            rq.forward(req, resp);
        }
        if (page.equalsIgnoreCase("delete")) {
            String userid = req.getParameter("userId");
            new userService().deleteUser(Integer.parseInt(userid));
            RequestDispatcher rq = req.getRequestDispatcher("pages/Dash.jsp");
            rq.forward(req, resp);
        }
        if (page.equalsIgnoreCase("userlist")) {
            User user = new User();
            List<User> userList = new userService().getUserList();
            req.setAttribute("user", user);
            req.setAttribute("userList", userList);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/UserList.jsp");
            requestDispatcher.forward(req, resp);
        }
        if (page.equalsIgnoreCase("userdetails")) {
            String userid = req.getParameter("id");
            User user = new userService().getUserRow(Integer.parseInt(userid));
            req.setAttribute("user", user);
            req.setAttribute("id", userid);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/userDetails.jsp");
            requestDispatcher.forward(req, resp);

        }
        if (page.equalsIgnoreCase("deleteuser")){
            int id=Integer.parseInt(req.getParameter("id"));
            new userService().deleteUser(id);
            List<User> userList = new userService().getUserList();

            req.setAttribute("userList", userList);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/UserList.jsp");
            requestDispatcher.forward(req, resp);
        }

        //sir teached
        if (page.equalsIgnoreCase("editUser")) {

            // ID:
            int id = Integer.parseInt(req.getParameter("id"));
            User user = new userService().getUserRow(id);

            req.setAttribute("user", user);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/updateUser.jsp");
            requestDispatcher.forward(req, resp);
        }

        if (page.equalsIgnoreCase("updateUser")) {

            //step 1:
            User user = new User();

            int id = Integer.parseInt(req.getParameter("id"));

            user.setUserName(req.getParameter("userName"));
            user.setAddress(req.getParameter("address"));
            user.setEmail(req.getParameter("email"));
            user.setPassword(req.getParameter("password"));

            try {
                new userService().editUser(id, user);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            List<User> userList = new userService().getUserList();
            req.setAttribute("userList", userList);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/UserList.jsp");
            requestDispatcher.forward(req, resp);

        }
        if (page.equalsIgnoreCase("adduser")){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/register.jsp");
            requestDispatcher.forward(req, resp);

        }

    }
        //i did
//        if (page.equalsIgnoreCase("edituser")){
//            String userid = req.getParameter("id");
//            String name=req.getParameter("username");
//            String password=req.getParameter("password");
//            String email=req.getParameter("email");
//            String address=req.getParameter("address");
//            User user=new User() ;
//            user.setUserName("name");
//            user.setPassword("password");
//            user.setEmail("email");
//            user.setAddress("address");
//            new userService().editUser(user);
//            User editedUser=new userService().getUserRow(Integer.parseInt(userid));
//            req.setAttribute("editedUser",user);
//            RequestDispatcher rq = req.getRequestDispatcher("pages/editedFile.jsp");
//            rq.forward(req, resp);
//        }
//        if (page.equalsIgnoreCase("update")){
//            User user=new User();
//
//
//            user.setUserName(req.getParameter("username"));
//            user.setPassword(req.getParameter("password"));
//            user.setEmail(req.getParameter("email"));
//            user.setAddress(req.getParameter("address"));
//
//
//            new userService().editUser(user);
//            List<User> userList = new userService().getUserList();
//            req.setAttribute("user",user);
//            req.setAttribute("userList",userList);
//            RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/update.jsp");
//            requestDispatcher.forward(req, resp);
//        }
//    }
}