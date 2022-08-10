package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DataStore;
import model.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "PersonServlet",  urlPatterns = "/name")
public class PersonServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //String requestUrl = request.getRequestURI();
        String name = request.getParameter("name");
        if (name == null){
            System.out.println("A name was not provided");
        }else{
            System.out.println("HHello:   " + name);
        }
        //String name = requestUrl.substring("/rest_api_war_exploded/people/".length());
        //req'ten parametre çek.
        Person person = DataStore.getInstance().getPerson(name);
        System.out.println(person.getName() + " " + person.getAbout() + " " + person.getBirthYear());




        response.setContentType("application/json");

        if (person != null) {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();

            Gson gson = builder.create();
            String json = gson.toJson(person);
            response.getOutputStream().println(json);


        } else {
            response.getOutputStream().println("{}");
        }
    }
        //

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String name = request.getParameter("name");
        String about = request.getParameter("about");
        int birthYear = Integer.parseInt(request.getParameter("birthYear"));

        DataStore.getInstance().putPerson(new Person(name, about, birthYear));
    }
}