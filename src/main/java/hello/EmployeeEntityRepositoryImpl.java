package hello;

public class EmployeeEntityRepositoryImpl extends EntityRepositoryBase {

    @Override
    public Class getEntityClass() {
        return Employee.class;
    }

}
