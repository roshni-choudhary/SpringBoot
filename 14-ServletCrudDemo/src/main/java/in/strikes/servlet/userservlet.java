package in.strikes.servlet;

import in.strikes.model.user;
import in.strikes.service.userservice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class userservlet extends HttpServlet {

    private userservice userservice = new userservice();

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");
        String name = req.getParameter("name");
        String mobno = req.getParameter("mobno");
        String email = req.getParameter("email");

        if (idParam == null || name == null || mobno == null || email == null) {

            resp.setStatus(400);
            resp.setContentType("application/json");

            resp.getWriter().write(
                    "{\n" +
                            "    \"message\": \"some fields are missing\"\n" +
                            "}"
            );

            return;
        }

        Integer id = Integer.parseInt(idParam);

        user u = new user(email, id, mobno, name);

        user cuser = userservice.createuser(u);

        resp.setStatus(201);
        resp.setContentType("application/json");

        resp.getWriter().write(
                "{\n" +
                        "    \"message\": \"User added successfully\"\n" +
                        "}"
        );
    }


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");

        // If id is not provided -> return all users
        if (idParam == null) {

            List<user> list = userservice.getallusers();

            resp.setStatus(200);
            resp.setContentType("application/json");

            resp.getWriter().write(userToJSONList(list));

            return;
        }

        // If id is provided -> return particular user
        Integer id = Integer.parseInt(idParam);

        user userResponse = userservice.getuser(id);

        // User not found
        if (userResponse == null) {

            resp.setStatus(404);
            resp.setContentType("application/json");

            resp.getWriter().write(
                    "{\n" +
                            "    \"message\": \"User not found\"\n" +
                            "}"
            );

            return;
        }

        // User found
        resp.setStatus(200);
        resp.setContentType("application/json");

        resp.getWriter().write(userToJson(userResponse));
    }


    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        super.doPut(req, resp);
    }


    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        super.doDelete(req, resp);
    }


    private String userToJson(user userResponse) {

        return "{\n" +
                "    \"id\": " + userResponse.getId() + ",\n" +
                "    \"name\": \"" + userResponse.getName() + "\",\n" +
                "    \"email\": \"" + userResponse.getEmail() + "\",\n" +
                "    \"mobile\": \"" + userResponse.getMobno() + "\"\n" +
                "}";
    }


    private String userToJSONList(List<user> userlist) {

        StringBuilder sb = new StringBuilder();

        sb.append("[");

        for (int i = 0; i < userlist.size(); i++) {

            sb.append(userToJson(userlist.get(i)));

            if (i < userlist.size() - 1) {
                sb.append(",");
            }
        }

        sb.append("]");

        return sb.toString();
    }
}