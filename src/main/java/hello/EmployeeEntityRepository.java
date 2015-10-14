package hello;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeEntityRepository extends CrudRepository<Employee, Long>, EntityRepository<Employee> {

}
