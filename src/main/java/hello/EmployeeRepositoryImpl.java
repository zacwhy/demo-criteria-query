package hello;

public class EmployeeRepositoryImpl extends EntityRepositoryBase {

    @Override
    public Class getEntityClass() {
        return Employee.class;
    }

}
