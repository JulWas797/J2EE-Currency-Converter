package io.github.julwas797.currencyconverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mainPage", value = "")
public class MainPageServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace(response.getWriter());
        }
    }
}