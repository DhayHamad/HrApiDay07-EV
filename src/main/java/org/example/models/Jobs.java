package org.example.models;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.sql.ResultSet;
import java.sql.SQLException;

//@XmlRootElement
public class Jobs {
    private int jobID;
    private String jobTitle;
    private int minSalary;
    private double mxnSalary;

    public Jobs(ResultSet rs) throws SQLException {
        jobID = rs.getInt("job_Id");
        jobTitle = rs.getString("job_title");
        minSalary = rs.getInt("min_Salary");
    }

    public Jobs(int jobID, String jobTitle, int minSalary) {
        this.jobID = jobID;
        this.jobTitle = jobTitle;
        this.minSalary = minSalary;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getMxnSalary() {
        return mxnSalary;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }

    @Override
    public String toString() {
        return "Jobs{" +
                "jobID=" + jobID +
                ", jobnTitle='" + jobTitle + '\'' +
                ", minSalary=" + minSalary +
                '}';
    }

}

