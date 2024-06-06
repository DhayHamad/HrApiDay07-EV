package org.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import org.example.dto.EmployeeDTO;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(
            @HeaderParam("apiKey") String apiKey,
            @CookieParam("UserName") String UserName,
            @Context HttpHeaders httpHeaders,
            @Context UriInfo uriInfo
    )
    {
        System.out.println(httpHeaders.getDate());
        System.out.println(httpHeaders.getLanguage());
        System.out.println(httpHeaders.getMediaType());
        System.out.println(httpHeaders.getCookies());
        System.out.println("----------------------");
        System.out.println(uriInfo.getAbsolutePath());
        System.out.println(uriInfo.getPathSegments());
        System.out.println(uriInfo.getQueryParameters());
        System.out.println(uriInfo.getQueryParameters().get("min_salary"));

        return "Got it! apiKey: "+apiKey+", UserName: "+UserName;
    }

    @GET
    @Path("{info}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getDate(@PathParam("info") EmployeeDTO info) {
        return "Employee info: " + info;
    }

}
