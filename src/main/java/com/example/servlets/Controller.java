package com.example.servlets;

import java.io.*;
import java.util.Objects;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            RequestDispatcher dispatcher;
            String r = req.getHeader("r");
            String x = req.getHeader("x");
            String y = req.getHeader("y");
            if (Objects.isNull(r) ||
                    Objects.isNull(x) ||
                    Objects.isNull(y) ||
                    r.isBlank() ||
                    x.isBlank() ||
                    y.isBlank()) {

                dispatcher = req.getRequestDispatcher("/index.jsp");
                dispatcher.forward(req, resp);

            } else {

                dispatcher = req.getRequestDispatcher("/area-check");
                dispatcher.forward(req, resp);

            }

        } catch (IOException | ServletException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String delete = req.getHeader("invalidate");
        if (!Objects.isNull(delete) && delete.trim().equals("session")) {
            var out = resp.getWriter();
            out.println("{\"status\":\"successfully\"}");
            req.getSession().invalidate();
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "invalid delete request");
        }
    }
}