package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    void insertEmp(EmployeeDTO employeeDTO);

    PageResult searchEmpPage(EmployeePageQueryDTO employeePageQueryDTO);

    void startOrStop(int status, int id);

    Employee selectEmpByID(Long id);

    void updateEmpByID(EmployeeDTO employeeDTO);
}
