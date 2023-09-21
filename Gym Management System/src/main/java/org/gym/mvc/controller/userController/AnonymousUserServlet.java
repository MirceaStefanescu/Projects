package org.gym.mvc.controller.userController;

import org.gym.mvc.model.user.AnonymousUser;
import org.gym.mvc.model.user.Gender;
import org.gym.persistance.dao.AnonymousUserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/submitForm") public class AnonymousUserServlet extends HttpServlet {

    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        try {
            // Retrieve form data
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String province = request.getParameter("province");
            int postalCode = Integer.parseInt(request.getParameter("postalCode"));
            int age = Integer.parseInt(request.getParameter("age"));
            Gender sex = Gender.valueOf(request.getParameter("sex").toUpperCase());

            // Create an instance of AnonymousUser
            AnonymousUser anonymousUser = new AnonymousUser(username, password, firstName, lastName,
                                                            address, city, province, postalCode,
                                                            age, sex);

            // Perform the action for AnonymousUser
            anonymousUser.performAction();

            // Save the anonymous user using the DAO
            AnonymousUserDAO.saveAnonymousUser(anonymousUser);

            // Redirect
            response.sendRedirect(request.getRequestURI());
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}
