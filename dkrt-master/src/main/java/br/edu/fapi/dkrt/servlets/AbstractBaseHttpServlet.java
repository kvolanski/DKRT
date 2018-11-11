package br.edu.fapi.dkrt.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class AbstractBaseHttpServlet extends HttpServlet {

    public void setSessionAttribute(HttpServletRequest req, String key, Object value) {
        req.setAttribute(key, value);
    }
}
