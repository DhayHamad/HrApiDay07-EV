package org.example.controller;

import jakarta.ws.rs.*;
import org.example.dao.EmployeesDAO;
import org.example.models.Employees;

import java.util.ArrayList;

@Path("/employees")
public class EmployeesController {
    EmployeesDAO dao = new EmployeesDAO();

    @GET
    public ArrayList<Employees> SELECT_ALL_EMPLOYEES() {

        try {
            return dao.selectAllEmployees();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("{employee_id}")
    public Employees SELECT_ONE_EMPLOYEE(@PathParam("employee_id") int employee_id) {

        try {
            return dao.selectEmployees(employee_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DELETE
    @Path("{employee_id}")
    public void DELETE_JOB(@PathParam("employee_id") int employee_id) {

        try {
            dao.deleteEmployees(employee_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @POST
    public void INSERT_JOB(Employees Employees) {

        try {
            dao.insertEmployees(Employees);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("{employee_id}")
    public void UPDATE_JOB(@PathParam("employee_id") int employee_id, Employees employees) {

        try {
            employees.setJob_id(employee_id);
            dao.updateEmployees(employees);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
