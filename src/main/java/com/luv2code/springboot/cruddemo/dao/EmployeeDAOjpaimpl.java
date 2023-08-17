package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOjpaimpl implements EmployeeDAO{

    // define field for EntityManager
    private EntityManager entityManager;

    // setup constructor injection
    @Autowired
    public EmployeeDAOjpaimpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {

        // create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee",Employee.class);

        // execute and return a list of queries
        List<Employee> employees = theQuery.getResultList();

        // return list
        return employees;
    }

    @Override
    public Employee findById(int theId) {

        // get the employee
        Employee theEmployee = entityManager.find(Employee.class,theId);

        // return the employee
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        // save the employee
        Employee dbEmployee = entityManager.merge(theEmployee);
        // if id == 0 then insert/save else update

        // return the employee
        return dbEmployee; // it has updated id from the db
    }

    @Override
    public void deleteById(int theId) {

        // find the employee
        Employee theEmployee = entityManager.find(Employee.class,theId);

        // remove the employee
        entityManager.remove(theEmployee);

    }
}
