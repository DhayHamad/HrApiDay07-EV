package org.example.helper;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.util.Arrays;

@Provider
public class SignUpFilter implements ContainerRequestFilter, ContainerResponseFilter
{

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException
    {
        System.out.println(containerRequestContext.getHeaders()+"\n");
        System.out.println(containerRequestContext.getUriInfo().getAbsolutePath());
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException
    {
        System.out.println("\n"+containerRequestContext.getHeaders()+"\n");
        System.out.println(Arrays.toString(containerResponseContext.getEntityAnnotations())+"\n");
        System.out.println(containerResponseContext.getEntity()+"\n");
        containerResponseContext.getHeaders().add("SignUp","info");

    }
}
