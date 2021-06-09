package com.exam2.Service;


import com.exam2.Model.Employee;
import com.exam2.Model.User;
import com.framework.Exception.EntityManagerException;
import com.framework.Services.Dao.EntityManager;
import com.framework.Services.Dao.EntityManagerFactory;
import com.google.inject.Inject;

public class UserService {
    public EntityManager entityManager;

    @Inject
    public UserService(EntityManagerFactory entityManagerFactory) {
        entityManager = entityManagerFactory.get(User.class);
    }

    public Employee getUserWithBestSalary() throws EntityManagerException {
        return (Employee) entityManager
                .runGetQuery(
                        String.format(
                                "select e from Employee e where e.salary = (select max(employee.salary) from Employee employee)"
                        )
                    )
                .get(0);
    }

    public void createUser(User user) throws EntityManagerException {
        this.entityManager.persist(user);
    }
}
