package org.example.dao;

import org.example.dto.JobFilterDto;
import org.example.models.Jobs;

import java.sql.*;
import java.util.ArrayList;

public class JobDAO {
    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\HrApiDay06-EV\\src\\main\\java\\org\\example\\hr.db";
    private static final String SELECT_ALL_JOBS = "select * from Jobs";
    private static final String SELECT_ONE_JOBS = "select * from Jobs where job_id = ?";
    private static final String INSERT_JOBS = "insert into Jobs values (?, ?, ? ,?)";
    private static final String UPDATE_JOBS = "update Jobs set job_title = ? ,min_salary = ?, max_salary = ? where job_id = ?";
    private static final String DELETE_JOBS = "delete from Jobs where job_id = ?";
    private static final String SELECT_DEPT_WITH_MIN = "select * from Jobs where min_salary = ?";
    // CHEEK IF I BUTET AS MIN_SALARY
    private static final String SELECT_DEPT_WITH_LOC_PAGINATION = "select * from Jobs where min_salary = ? order by job_id limit ? offset ?";
    private static final String SELECT_DEPT_WITH_PAGINATION = "select * from Jobs order by job_id limit ? offset ?";
    //==========================================================================================


    public void setInsertJobs(Jobs d) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(INSERT_JOBS);
        st.setInt(1, d.getJobID());
        st.setString(2, d.getJobnTitle());
        st.setDouble(3, d.getMinSalary());
        st.setDouble(4, d.getMxnSalary());
        st.executeUpdate();
    }

    public void setUpdateJobs(Jobs d) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(UPDATE_JOBS);
        st.setInt(1, d.getJobID());
        st.setString(2, d.getJobnTitle());
        st.setDouble(3, d.getMinSalary());
        st.setDouble(4, d.getMinSalary());
        st.executeUpdate();
    }

    public void setDeleteJobs(int jobstId) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(DELETE_JOBS);
        st.setInt(1, jobstId);
        st.executeUpdate();
    }

    public Jobs selectJobs(int job_id) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_JOBS);
        st.setInt(1, job_id);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Jobs(rs);
        }
        else {
            return null;
        }
    }

    public ArrayList<Jobs> selectAllJobs() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ALL_JOBS);
        ResultSet rs = st.executeQuery();
        ArrayList<Jobs> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new Jobs(rs));
        }

        return jobs;
    }


  public ArrayList<Jobs> selectAllJobs(Double min_salary, Integer limit, int offset) throws SQLException, ClassNotFoundException {
       Class.forName("org.sqlite.JDBC");
       Connection conn = DriverManager.getConnection(URL);
     PreparedStatement st;
     if(min_salary != null && limit != null) {
        st = conn.prepareStatement(SELECT_DEPT_WITH_LOC_PAGINATION);
         st.setDouble(1, min_salary);
         st.setInt(2, limit);
         st.setInt(3, offset);
     }
     else if(min_salary != null) {
         st = conn.prepareStatement(SELECT_DEPT_WITH_MIN);
         st.setDouble(1, min_salary);
     }
     else if(limit != null) {
         st = conn.prepareStatement(SELECT_DEPT_WITH_PAGINATION);
         st.setInt(1, limit);
         st.setInt(2, offset);
     }
     else {
            st = conn.prepareStatement(SELECT_ALL_JOBS);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<Jobs> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new Jobs(rs));
        }

        return jobs;
    }

    public ArrayList<Jobs> selectAllJobs(JobFilterDto filter) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st;
        if (filter.getMin_salary() != null && filter.getLimit() != null) {
            st = conn.prepareStatement(SELECT_DEPT_WITH_LOC_PAGINATION);
            st.setDouble(1, filter.getMin_salary());
            st.setInt(2, filter.getLimit());
            st.setInt(3, filter.getOffset());

        } else if (filter.getMin_salary() != null) {
            st = conn.prepareStatement(SELECT_DEPT_WITH_MIN);
            st.setDouble(1, filter.getMin_salary());

        } else if (filter.getLimit() != null) {
            st = conn.prepareStatement(SELECT_DEPT_WITH_PAGINATION);
            st.setInt(1, filter.getLimit());
            st.setInt(2, filter.getOffset());

        } else {
            st = conn.prepareStatement(SELECT_ALL_JOBS);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<Jobs> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new Jobs(rs));
        }

        return jobs;
    }
}