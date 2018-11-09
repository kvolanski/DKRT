package br.edu.fapi.dkrt.servlets;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class AbstractBaseHttpServlet extends HttpServlet {

    protected void setSessionAttribute(HttpServletRequest req, String key, Object value){
        req.setAttribute(key, value);
    }
}
