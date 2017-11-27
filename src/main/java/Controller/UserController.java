package Controller;

import entity.Library;
import entity.User;
import main.UserDAO;
import persistence.PersistenceUtil;
import Parser.parser;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.servlet.http.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Path("/user")
public class UserController {
    static  HttpSession session;
    static Library library;
    public static int UserId;

    //StudentService studentService = new StudentService();
  /*  @GET
    @Path("/{userId}")
    @Produces("application/json")
    public StudentDTO getStudent(@PathParam("userId") String id) {

        StudentService StudentService = new StudentService();
        StudentDTO result = StudentService.getStudent(id);
        return result != null ? result : new StudentDTO();


    }

    @GET
    @Produces("application/json")
    public List<User> getAllStudents() {
        StudentService studentService = new StudentService();
        return studentService.getAllStudents();
    }

    */

    @POST
    @Path("/add")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createUser(@FormParam("firstName") String firstName,
                           @FormParam("lastName") String lastName,
                           @FormParam("email") String email,
                           @FormParam("password") String password,
                           @Context HttpServletResponse servletResponse) throws IOException {
        User student = new User();
        if (firstName != null)
            student.setFirstName(firstName);
        if (lastName != null)
            student.setLastName(lastName);
        if (email != null)
            student.setEmail(email);
        if (password != null)
            student.setPassword(password);
        UserDAO.createUser(firstName, lastName, email, password);
        servletResponse.sendRedirect("/login.jsp");
    }

    @GET
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void loginUser(@Context HttpServletResponse servletResponse,
                          @Context HttpServletRequest servletRequest) throws IOException {



        String email = servletRequest.getParameter("email");
        String password = servletRequest.getParameter("password");


        User user = PersistenceUtil.findUserByEmail(email);
        UserId = user.getId();

        session = servletRequest.getSession();
        session.setAttribute("email", email);
        servletResponse.sendRedirect("/LoggedIn.jsp");


    }

    @GET
    @Path("/logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logoutUser(){
        session.invalidate();
        return Response.ok("successfully logged out").build();

    }

    public static void getLibrary(Library l){
       library = l;
    }

    public static void returnLoggedInUser(@Context HttpServletResponse servletResponse,
                                   @Context HttpServletRequest servletRequest) throws IOException {
        Cookie ck[] = servletRequest.getCookies();
        if (ck != null) {
            String email = ck[0].getValue();
            User user = PersistenceUtil.findUserByEmail(email);
            UserId = user.getId();

        }
    }

    public static int returnUserId(@Context HttpServletResponse servletResponse,
                                   @Context HttpServletRequest servletRequest) throws IOException{
        returnLoggedInUser(servletResponse, servletRequest);
        return UserId;
    }




}














