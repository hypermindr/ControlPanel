<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import ="javax.faces.application.FacesMessage"%>
<%@ page import ="javax.faces.context.FacesContext"%>
<%@ page import ="javax.servlet.http.HttpSession"%>

        <% if (request.getSession(false) == null){
        
         FacesContext.getCurrentInstance().getExternalContext().redirect("../faces/paginas/index.jsp");   
         }else{
           if(request.getSession().getAttribute("atoken") ==null ){
               FacesContext.getCurrentInstance().getExternalContext().redirect("../faces/paginas/index.jsp");
           }
         }
         %>
