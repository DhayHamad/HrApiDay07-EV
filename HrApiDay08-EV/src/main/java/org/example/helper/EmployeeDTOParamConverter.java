package org.example.helper;

import jakarta.ws.rs.ext.*;
import org.example.dto.EmployeeDTO;


public class EmployeeDTOParamConverter implements ParamConverter
{
    @Override
    public Object fromString(String value) {

        int department_Code;
        int idSeq;
        String hire_date;

        department_Code = Integer.parseInt(value.substring(0,2));
        idSeq = Integer.parseInt(value.substring(2,6));
        hire_date = value.substring(6);

        EmployeeDTO employeeDTO = new EmployeeDTO(department_Code, idSeq, hire_date);

        return employeeDTO;
    }

    @Override
    public String toString(Object value) {
        return value.toString();
    }
}
