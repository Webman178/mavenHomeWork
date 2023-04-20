import java.util.List;

public interface EmployeeDAO {
    public void addEmployee(Employee employee);
    public Employee getEmployeeByID(int id);
    public List<Employee> getAllEmployees();
    public void updateEmployee(int id, Employee employee);
    public void deleteEmployee(int id);
    ////////////////////
    public int getId(Employee employee);
}
