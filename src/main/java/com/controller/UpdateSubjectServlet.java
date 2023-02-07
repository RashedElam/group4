/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.Subject;
import com.model.dao.SubjectSqlDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 236356
 */
public class UpdateSubjectServlet extends HttpServlet {

@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            
            SubjectSqlDAO subjectSqlDAO = (SubjectSqlDAO) session.getAttribute("subjectSqlDAO");
            String subjectNameRegEx = "^[a-zA-Z ]+$";
            
            String oldSubjectName = request.getParameter("oldSubjectName");//TODO - somewhere need to do setParameter
            String newSubjectName = request.getParameter("newSubjectName");//TODO - somewhere need to do setParameter
            String newSubjectDesc = request.getParameter("newSubjectDesc");

            
            Subject getOldSubject = subjectSqlDAO.getSubject(oldSubjectName);
            Subject getNewSubject = subjectSqlDAO.getSubject(newSubjectName);
            
            if(!oldSubjectName.matches(subjectNameRegEx)){
                if(!newSubjectName.matches(subjectNameRegEx)){
                    session.setAttribute("subjectError", oldSubjectName + " " + newSubjectName + " " + "are an incorrect subject name format");
                    response.sendRedirect("updateSubject.jsp");
                }
                else {
                    session.setAttribute("subjectError", oldSubjectName + " is an incorrect subject name format");//TODO - Create subjectError somewhere, refer AdminRegisterServlet
                    response.sendRedirect("updateSubject.jsp");
                }
            }
            else if(!newSubjectName.matches(subjectNameRegEx)){
                session.setAttribute("subjectError", newSubjectName + " is an incorrect subject name or format");//TODO - Create subjectError somewhere, refer AdminRegisterServlet
                response.sendRedirect("updateSubject.jsp");
            }
            
            else if(getNewSubject != null){
                session.setAttribute("subjectError", newSubjectName + " already exists");
                response.sendRedirect("updateSubject.jsp");
                
            }else if(getOldSubject.equals(getNewSubject)){
                session.setAttribute("subjectError", "Old Subject name" + oldSubjectName + " and new subject name" + newSubjectName + " are same");
                response.sendRedirect("updateSubject.jsp");
            }
            else {
                subjectSqlDAO.update(oldSubjectName, newSubjectName, newSubjectDesc);
                Subject subject = subjectSqlDAO.getSubject(newSubjectName);
                session.setAttribute("subject", subject);
                //TODO - If required, "Subject created successfully" message can be retained here or added to createSubject.jsp
                //session.setAttribute("subjectError", "Subject creates successfully");
                request.getRequestDispatcher("subjectsList.jsp").include(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateSubjectServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}