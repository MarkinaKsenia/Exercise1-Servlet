package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/put")
public class ServletUpdate extends HttpServlet {
    Model model = Model.getInstanse();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");

        StringBuffer jb = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

        request.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);
        int id = jobj.get("id").getAsInt();
        String name = jobj.get("name").getAsString();
        String surname = jobj.get("surname").getAsString();
        double salary = jobj.get("salary").getAsDouble();

        if (id > 0) {
            if (id > model.getFromList().size()) {
                pw.print(gson.toJson("Такого пользователя нет :( "));
            } else {
                User user = new User(name,surname,salary);
                model.add(user,id);
                pw.print(gson.toJson(model.getFromList()));
            }
        } else {
            pw.print(gson.toJson("ID должен быть больше 0"));
        }

    }
}
