<%-- 
    Document   : deleteSubject
    Created on : 29-Jan-2023, 9:47:24 AM
    Author     : 236356
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Delete subject</title>
        <link rel="stylesheet" href="css/system.css"/>
        <script type="text/javascript" src="js/index.js"></script>
        <script src='https://kit.fontawesome.com/a076d05399.js'></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

    <a class="site-identity" href="#" <img src = "css/logo.jpg"></a>
</head>
    <body onload="startTime()">
        <nav class="navbar navbar-expand-lg navbar-dark bg-secondary">
        <div class="container-fluid">
            <a class="navbar-brand" href="#" <img src = "css/background.jpg"></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                            <a class="nav-link active" href="subjectsList.jsp"><i class='fas fa-arrow-circle-left'></i></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="index.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="/group4/LogoutServlet">Logout</a>
                    </li>
                </ul>

            </div>
        </div>
    </nav>
        <%
            String subjectError = (String) session.getAttribute("subjectError");
        %>

        <div>
            <form class="" method="POST" action="/group4/DeleteSubjectServlet">
            <table class ="styled-table" style="margin-left:auto;margin-right:auto;">
                    <thead class="thead">
                        <tr>
                            <th></th>
                            <th>Delete subject</th>
                            <th></th>
                        </tr>
                        
                    </thead>
                    <tr><td class="td">Subject ID</td><td><input class="input" type="number" name="deleteSubjectID" placeholder="Enter subject ID to delete" required="deleteSubjectID" onkeypress="clearStatus()" /></td></tr>
                    <tr>
                        <td>
                            <input class="btn btn-secondary fs-4" type="submit" value="Delete"/>
                        </td>
                        <td>
                            <a class="btn btn-secondary fs-4" href="subjectsList.jsp">Close</a>
                        </td>
                    </tr>
                    <tr>
                            <th></th>
                            <th><span class="message"><%= (subjectError != null) ? subjectError : ""%></span></span></th>
                            <th></th>
                    </tr>
                </table>
                          
            </form>
            <%
                subjectError = "";
                session.setAttribute("subjectError", subjectError);
            %>
        </div>
    </body>
</html>
<div class="footer">
    <p>All Rights Reserved © GROUP 4</p>
</div>
