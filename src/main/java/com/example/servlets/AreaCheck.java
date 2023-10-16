package com.example.servlets;

import com.example.models.Point;
import com.example.utils.JsonWorker;
import com.example.utils.Validator;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/area-check")
public class AreaCheck extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            double start = System.nanoTime();
            int r = Integer.parseInt(req.getHeader("r"));
            double x = Double.parseDouble(req.getHeader("x"));
            double y = Double.parseDouble(req.getHeader("y"));
            Point point = new Point(r, x, y, LocalDateTime.now(), (System.nanoTime() - start) / 1_000_000.0);
            HttpSession session = req.getSession();
            String pointHash = String.valueOf(point.hashCode());
            session.setAttribute(pointHash, JsonWorker.toJSON(point));
        } catch (IOException | Validator.InvalidArgumentException | NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/check-result.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (IOException | ServletException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

}
