<%-- 
    Document   : update
    Created on : 26-Jan-2023, 1:48:13 PM
    Author     : 236358
--%>
<%@page import="java.sql.SQLException"%>
<%@include file="footer.jsp"%>
<%@page import="com.model.dao.UserSqlDAO"%>
<%@page import="com.model.Users"%>
<%@page import="com.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account</title>
        <link rel="stylesheet" href="css/layout.css"/>
        <script type="text/javascript" src="js/index.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        
    </head>
    <body >
        
        <nav class="navbar navbar-dark bg-dark">
            <div class="contianer-fluid">
                <div class="navbar-header">
                    <table>
                        <tr>
                            <td><h1 class="header">University</h1></td>
                            <td><a class="button" href="index.jsp">Home</a></td>
                            <td><a class="button" href="/group4/LogoutServlet">Logout</a></td>
                        </tr>                                                              
                    </table>
                </div>
            </div>
        </nav>
      
        <%
            User user = (User) session.getAttribute("user");
            String emailView = request.getParameter("emailView");
            String submitted = request.getParameter("submitted");
        %>
        <div style="margin: auto;">
            <form method="POST" action="/group4/UpdateStudentServlet">
                <table class="tab">

                    <thead class="thead">
                        <tr>
                            <th></th>
                            <th>Edit User </th>
                            <th></th>
                        </tr>
                        <tr>
                            <th></th>
                            <th><span class="message"><%= (submitted != null) ? "Update is Successfull" : ""%></span></th>
                            <th></th>
                        </tr>
                    </thead>
      
                    <tr><td class="td">ID </td><td><input class="input" type="text" name="ID" value="<%= user.getID()%>" readonly="true" /></td></tr>
                    <tr><td class="td">Name</td><td><input class="input" type="text" name="name" value="<%= user.getName()%>" /></td></tr>
                    <tr><td class="td">Email</td><td><input class="input" type="text" name="email" value="<%= user.getEmail()%>" readonly="true"/></td></tr>
                    <tr><td class="td">Password</td><td><input class="input" type="password" name="password" value="<%= user.getPassword()%>" /></td></tr>
                    <tr><td class="td">DOB</td><td><input class="input" type="date" name="dob" value="<%= user.getDOB()%>"/></td></tr>
                    <tr><td><input class="input" type="hidden" name="submitted" value="submitted" %></td></tr>
                    <tr>
                        <%if(emailView != null) {%>
                        <td><a class="button tabButton" href="index.jsp">Home</a></td>     
                        <%}else {%>
                        <td><a class="button" href="studentsList.jsp">StudentList</a></td>
                        <%}%>
                        <td>
                            <input class="button tabButton" type="submit" value="Update" />
                            <a class="button tabButton" href="delete.jsp" >Delete</a>                        
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
