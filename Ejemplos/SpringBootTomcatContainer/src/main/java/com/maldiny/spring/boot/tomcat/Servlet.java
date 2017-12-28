package com.maldiny.spring.boot.tomcat;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/servlet", name = "servlet")
public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response
              .getOutputStream()
              .write("Hola Mundo!".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}