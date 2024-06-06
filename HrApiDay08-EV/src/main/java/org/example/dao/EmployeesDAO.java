package org.example.dao;

import org.example.models.Employees;

import java.sql.*;
import java.util.ArrayList;

public class EmployeesDAO {
    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\HrApiDay05-HW\\src\\main\\java\\org\\example\\hr.db";
    private static final String SELECT_ALL_EMPLOYEES = "select * from employees";
    private static final String SELECT_ONE_EMPLOYEES = "select * from employees where employee_id = ?";
    private static final String SELECT_EMPLIYEE_WITH_SALARY = "select * from employees where salary = ?";
    private static final String SELECT_EMPLIYEE_WITH_SALARY_PAGINATION = "select * from employees where salary = ? order by employee_id limit ? offset ?";
    private static final String SELECT_EMPLIYEE_WITH_PAGINATION = "select * from employees order by employee_id limit ? offset ?";
    private static final String INSERT_EMPLOYEES = "insert into employees values (?, ?, ?, ?, ?, ?,?, ?, ?, ?)";
    private static final String UPDATE_EMPLOYEES= "update employees set first_name = ?, last_name = ? ,email = ? ,phone_number = ? ,hire_date = ? ,job_id = ?,Salary = ? ,manager_id = ? ,department_id = ? where employee_id = ?";
    private static final String DELETE_EMPLOYEES = "delete from employees where employee_id = ?";

    public void insertEmployees(Employees e) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(INSERT_EMPLOYEES);
        st.setInt(1, e.getEmployee_id());
        st.setString(2, e.getFirst_name());
        st.setString(3, e.getLast_name());
        st.setString(4, e.getEmail());
        st.setString(5, e.getPhone_number());
        st.setString(6, e.getHire_date());
        st.setInt(7, e.getJob_id());
        st.setDouble(8, e.getSalary());
        st.setInt(9, e.getManager_id());
        st.setInt(10, e.getDepartment_id());

        st.executeUpdate();
        //conn.close();
    }

    public void updateEmployees(Employees e) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(UPDATE_EMPLOYEES);
        st.setString(1, e.getFirst_name());
        st.setString(2, e.getLast_name());
        st.setString(3, e.getEmail());
        st.setString(4, e.getPhone_number());
        st.setString(5, e.getHire_date());
        st.setDouble(6, e.getSalary());
        st.setInt(7, e.getJob_id());
        st.setInt(8, e.getEmployee_id());
        st.setInt(9, e.getDepartment_id());
        st.setInt(   10, e.getEmployee_id());

        st.executeUpdate();
        //conn.close();

    }

    public void deleteEmployees(int employeeId) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(DELETE_EMPLOYEES);
        st.setInt(1, employeeId);
        st.executeUpdate();
    }

    public Employees selectEmployees(int employeeId) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_EMPLOYEES);
        st.setInt(1, employeeId);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Employees(rs);
        }
        else {
            return null;
        }
    }

    public ArrayList<Employees> selectAllEmployees() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ALL_EMPLOYEES);
        ResultSet rs = st.executeQuery();
        ArrayList<Employees> employees = new ArrayList<>();
        while (rs.next()) {
            employees.add(new Employees(rs));
        }

        return employees;
    }

}