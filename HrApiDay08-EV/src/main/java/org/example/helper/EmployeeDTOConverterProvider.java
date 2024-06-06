package org.example.helper;

import jakarta.ws.rs.ext.*;
import org.example.dto.EmployeeDTO;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
public class EmployeeDTOConverterProvider implements ParamConverterProvider
{
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
        if(rawType.getName().equals(EmployeeDTO.class.getName())) {
            return new EmployeeDTOParamConverter();
        }
        return null;
    }
}
