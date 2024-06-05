package org.example.controller;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.dao.JobDAO;
import org.example.dto.JobFilterDto;
import org.example.dto.JobsDto;
import org.example.exceptions.DataNotFoundException;
import org.example.models.Jobs;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
@Path("/jobs")
public class JobsController {

    JobDAO dao = new JobDAO();
    @Context UriInfo uriInfo;
    @Context HttpHeaders headers;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAllJob(
//            @QueryParam("min_salary") Double min_salary,
//            @QueryParam("limit") Integer limit,
//            @QueryParam("offset") int offset
            @BeanParam JobFilterDto filter
    ) {
       /* try {
            return dao.selectAllJobs(filter);
            // return dao.selectAllJobs(min_salary, limit, offset);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/
        try {
            GenericEntity<ArrayList<Jobs>> job = new GenericEntity<ArrayList<Jobs>>(dao.selectAllJobs(filter)) {};
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(job)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }

            return Response
//                    .ok()
//                    .entity(job)
//                    .type(MediaType.APPLICATION_JSON)
                    .ok(job, MediaType.APPLICATION_JSON)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("{jobId}")
    public Response getJOB(@PathParam("jobId") int jobId) throws SQLException {

        /*try {
            return dao.selectJobs(jobId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
        try {
            Jobs dept = dao.selectJobs(jobId);

            if (dept == null) {
                throw new DataNotFoundException("Job " + jobId + "Not found");
            }

            JobsDto dto = new JobsDto();
            dto.setJobID(dept.getJobID());
            dto.setJobnTitle(dept.getJobnTitle());
            dto.setMinSalary(dept.getMinSalary());
            dto.getMxnSalary(dept.getMxnSalary());

            return Response.ok(dto).build();
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @DELETE
    @Path("{jobId}")
    public void deleteJOB(@PathParam("jobId") int jobId) {

        try {
            dao.setDeleteJobs(jobId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response insertJOB(Jobs job) {

        /*try {
            dao.setInsertJobs(job);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
        try {
            dao.setInsertJobs(job);
           // NewCookie cookie = (new NewCookie.Builder("username")).value("OOOOO").build();
            URI uri = uriInfo.getAbsolutePathBuilder().path(job.getJobID() + "").build();
            return Response
                    .status(Response.Status.CREATED)
                  //  .created(uri)

                   .cookie(new NewCookie("username", "OOOOO"))
             //       .cookie(cookie)
                    .header("Created by", "Wael")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("{jobId}")
    public void updateJOB(@PathParam("jobId") int deptId, Jobs job) {

        try {
            job.setJobID(deptId);
            dao.setUpdateJobs(job);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
