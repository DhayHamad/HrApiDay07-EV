package org.example.dto;

public class EmployeeDTO
{
    private int department_Code;
    private int idSeq;
    private String hire_date;

    public EmployeeDTO() {
    }

    public EmployeeDTO(int department_Code, int idSeq, String hire_date) {
        this.department_Code = department_Code;
        this.idSeq = idSeq;
        this.hire_date = hire_date;
    }

    public void setDepartment_Code(int department_Code) {
        this.department_Code = department_Code;
    }

    public void setIdSeq(int idSeq) {
        this.idSeq = idSeq;
    }

    public void setHire_date(String hire_date) {
        this.hire_date = hire_date;
    }

    public int getDepartment_Code() {
        return department_Code;
    }

    public int getIdSeq() {
        return idSeq;
    }

    public String getHire_date() {
        return hire_date;
    }
}
