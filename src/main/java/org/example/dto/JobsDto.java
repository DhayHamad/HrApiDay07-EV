package org.example.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JobsDto {
    private int jobID;
    private String jobnTitle;
    private double minSalary;
    private double mxnSalary;

    private ArrayList<LinkDto> links = new ArrayList<>();

    public JobsDto() {
    }

    public JobsDto(ResultSet rs) throws SQLException {
        jobID = rs.getInt("job_Id");
        jobnTitle = rs.getString("job_title");
        minSalary = rs.getInt("min_Salary");
    }

    public JobsDto(int jobID, String jobnTitle, int minSalary) {
        this.jobID = jobID;
        this.jobnTitle = jobnTitle;
        this.minSalary = minSalary;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public String getJobnTitle() {
        return jobnTitle;
    }

    public void setJobnTitle(String jobnTitle) {
        this.jobnTitle = jobnTitle;
    }

    public double getMxnSalary(double mxnSalary) {
        return this.mxnSalary;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(double minSalary) {
        this.minSalary = minSalary;
    }

    public ArrayList<LinkDto> getLinks() {
        return links;
    }

    public void addLink(String url, String rel) {
        LinkDto link = new LinkDto();
        link.setLink(url);
        link.setRel(rel);
        links.add(link);
    }
    @Override
    public String toString() {
        return "Jobs{" +
                "jobID=" + jobID +
                ", jobnTitle='" + jobnTitle + '\'' +
                ", minSalary=" + minSalary +
                '}';
    }

}

