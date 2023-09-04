package by.mikhalenya.servlets;

import by.mikhalenya.connectors.ConnectorPostgreSQL;
import by.mikhalenya.dao.Users.UsersDaoDML;
import by.mikhalenya.entities.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet("/user")
public class UsersServlet extends HttpServlet {
    Connection connection = new ConnectorPostgreSQL().openDBConnection();
    UsersDaoDML usersDaoDML = new UsersDaoDML(connection);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("Hello");
        String method = req.getParameter("method");
        Users users = new Users();
        users.setIdUser( Integer.parseInt(req.getParameter("id")));
        users.setName(req.getParameter("name"));
        users.setPatronymic(req.getParameter("patronymic"));
        users.setLastname(req.getParameter("lastname"));
        users.setAddress(req.getParameter("address"));
        users.setPhone(req.getParameter("phone"));
        users.setEmail(req.getParameter("email"));

        insertToDbUser(users);
    }

    public void insertToDbUser(Users users){
        usersDaoDML.create(users);
    }
}
