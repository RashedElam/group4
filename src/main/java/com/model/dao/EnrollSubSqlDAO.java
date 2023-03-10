/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.dao;

import com.model.Subject;
import com.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 236325
 */
public class EnrollSubSqlDAO {
    private Statement st;
    private PreparedStatement updateEnrollSt;
    private String updateEnrollQuery = "UPDATE university.subjectenrollment SET subject1=?, subject2=?, subject3=?, subject4=? WHERE ID=?";
    private PreparedStatement deleteSt;
    private String deleteQuery = "DELETE FROM university.subjectenrollment WHERE ID=?";
    private PreparedStatement deleteEnrolledSubjectSt;
    private String deleteEnrollSubjectQuery = "UPDATE university.subjectenrollment "
            + "SET subject1 = CASE WHEN subject1=? THEN subject1 = NULL ELSE subject1 END, "
            + "subject2 = CASE WHEN subject2=? THEN subject2 = NULL ELSE subject2 END, "
            + "subject3 = CASE WHEN subject3=? THEN subject3 = NULL ELSE subject3 END, "
            + "subject4 = CASE WHEN subject4=? THEN subject4 = NULL ELSE subject4 END "
            + "WHERE ID=?;";
    
    public EnrollSubSqlDAO(java.sql.Connection connection) throws SQLException {
        this.st = connection.createStatement();
        this.updateEnrollSt = connection.prepareStatement(updateEnrollQuery);
        this.deleteSt = connection.prepareStatement(deleteQuery);
        this.deleteEnrolledSubjectSt = connection.prepareStatement(deleteEnrollSubjectQuery);
    }

    //Create Query   
    public void enrollSubject(int ID, String sub1, String sub2, String sub3, String sub4) throws SQLException {
        String columns = "INSERT INTO university.subjectenrollment(ID, subject1, subject2, subject3, subject4)";
        String values = "VALUES('"+ID+"','"+sub1+"','"+sub2+"','"+sub3+"','"+sub4+"')";
        st.executeUpdate(columns + values);
    }

     public Subject getEnrollSubject(int ID) throws SQLException {
        String query = "SELECT * FROM university.subjectenrollment WHERE ID=" + ID;
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            
            int userID = Integer.parseInt(rs.getString(2));
            if (ID == userID) {
                int currentID = Integer.parseInt(rs.getString(1));
                String sub1 = rs.getString(3);
                String sub2 = rs.getString(4);
                String sub3 = rs.getString(5);
                String sub4 = rs.getString(6);
                return new Subject(currentID, ID, sub1, sub2, sub3, sub4);
            }
        }
        return null;
    }
    
     // Query to get the list of enrolled subjects
    public List<Subject> getEnrolledSubs(int ID) throws SQLException{
        String query = "SELECT * FROM university.subjectenrollment WHERE ID=" + ID;
        ResultSet rs = st.executeQuery(query);
        
        List<Subject> subList = new ArrayList();
        
        while (rs.next()) {
            int currentID = Integer.parseInt(rs.getString(1));
            int userID = Integer.parseInt(rs.getString(2));
            String sub1 = rs.getString(3);
            String sub2 = rs.getString(4);
            String sub3 = rs.getString(5);
            String sub4 = rs.getString(6);
            subList.add(new Subject(currentID, userID, sub1, sub2, sub3, sub4));

        }
        return subList;
    }
    
     //Read Query - Read One/For Search/Filter
    public Subject getEnrolledSubjects(int ID) throws SQLException {
        String query = "SELECT * FROM university.subjectenrollment WHERE ID = "+ID;
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String newSubject1 = rs.getString(3);
            String newSubject2 = rs.getString(4);
            String newSubject3 = rs.getString(5);
            String newSubject4 = rs.getString(6);
            int enrollID = Integer.parseInt(rs.getString(1));
            int userID = Integer.parseInt(rs.getString(2));
            return new Subject(enrollID,userID, newSubject1, newSubject2, newSubject3, newSubject4);
            
        }
        return null;
    }
    
    //Update Student subjects
     public void updateEnrollSub(String sub1, String sub2, String sub3, String sub4, int ID) throws SQLException{
        updateEnrollSt.setString(1, sub1);
        updateEnrollSt.setString(2, sub2);
        updateEnrollSt.setString(3, sub3);
        updateEnrollSt.setString(4, sub4);
        updateEnrollSt.setString(5, Integer.toString(ID));
        int row = updateEnrollSt.executeUpdate();
        System.out.println("Row "+row+" has been successflly updated");
    }
    
    //Read Query - Read One
    public Subject getSubject(int ID) throws SQLException {
        String query = "SELECT * FROM university.subjects WHERE SUBJECTID=" + ID;
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            int currentID = Integer.parseInt(rs.getString(1));

            if (ID == currentID) {
                String name = rs.getString(2);
                String desc = rs.getString(3);
                return new Subject(ID, name, desc);
            }
        }
        return null;
    }
    
    //Read Query - Read One/For Search/Filter
    public Subject getSubject(String subjectName) throws SQLException {
        String query = "SELECT * FROM university.subjects WHERE SUBJECTNAME='"+subjectName+"'";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String currentSubject = rs.getString(2);
            String subjectDesc = rs.getString(3);
            int ID = Integer.parseInt(rs.getString(1));
            return new Subject(ID, currentSubject,subjectDesc);
        }
        return null;
    }
    
    
     //Read Query - Read All
    public List<Subject> getSubjects() throws SQLException {
        String query = "SELECT * FROM university.subjects";
        ResultSet rs = st.executeQuery(query);
        List<Subject> temp = new ArrayList<>();
        
        while (rs.next()) {
            int ID = Integer.parseInt(rs.getString(1));
            String name = rs.getString(2);
            String subjectDesc = rs.getString(3);
            temp.add(new Subject(ID, name, subjectDesc));
        }    
        return temp;
    }

    //Delete Query - by ID
     public void deleteSub(int userID, String subToDelete) throws SQLException{
        deleteEnrolledSubjectSt.setString(1, subToDelete);
        deleteEnrolledSubjectSt.setString(2, subToDelete);
        deleteEnrolledSubjectSt.setString(3, subToDelete);
        deleteEnrolledSubjectSt.setString(4, subToDelete);
        deleteEnrolledSubjectSt.setString(5,Integer.toString(userID));
        int r = deleteEnrolledSubjectSt.executeUpdate();
        System.out.println(subToDelete+" Subject "+r+" has been successflly deleted");
    }
     
     public User enrollSubjectWS(String ID, String sub1, String sub2, String sub3, String sub4) throws SQLException {
        String columns = "INSERT INTO university.subjectenrollment(ID, subject1, subject2, subject3, subject4)";
        String values = "VALUES('"+ID+"','"+sub1+"','"+sub2+"','"+sub3+"','"+sub4+"')";
        st.executeUpdate(columns + values);
       return new User(ID, sub1, sub2, sub3, sub4);
    }
}
