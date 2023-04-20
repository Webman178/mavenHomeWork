import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public void addEmployee(Employee employee) {
        String query = "INSERT INTO employee (first_name, last_name, gender, age, city_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection=MyConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,employee.getFirst_name());
            preparedStatement.setString(2,employee.getLast_name());
            preparedStatement.setString(3,employee.getGender());
            preparedStatement.setInt(4,employee.getAge());
            preparedStatement.setInt(5,employee.getCity_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee getEmployeeByID(int id) {
        final String query = "SELECT * FROM employee WHERE id = ?";
        try (Connection connection = MyConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String gender = resultSet.getString("gender");
            int age = resultSet.getInt("age");
            int city_id = resultSet.getInt("city_id");

            Employee employee = new Employee(id, firstName, lastName, gender, age, city_id);
            return employee;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        try (Connection connection = MyConnection.getConnection()) {
            PreparedStatement type = connection.prepareStatement("SELECT * FROM employee");
            ResultSet result = type.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String name1 = result.getString("first_name");
                String name2 = result.getString("last_name");
                String name3 =result.getString("gender");
                int name4 =result.getInt("age");
                int name5 =result.getInt("city_id");

                list.add(new Employee(id,name1,name2,name3,name4,name5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateEmployee(int id, Employee employee) {
        String qwery = "UPDATE employee SET first_name =  ?, last_name = ?, gender = ?, age = ?, city_id = ?";
        try (Connection connection = MyConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(qwery);
            preparedStatement.setString(1,employee.getFirst_name());
            preparedStatement.setString(2,employee.getLast_name());
            preparedStatement.setString(3,employee.getGender());
            preparedStatement.setInt(4,employee.getAge());
            preparedStatement.setInt(5,employee.getCity_id());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEmployee(int id) {
        try (Connection connection = MyConnection.getConnection()) {
            PreparedStatement qwery = connection.prepareStatement("DELETE FROM employee WHERE id = ?");
            qwery.setInt(1,id);

            qwery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getId(Employee employee) {
        int i = 0;
        String query = "SELECT id FROM employee WHERE first_name = ? AND last_name = ? AND age = ?";
        try (Connection connection = MyConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,employee.getFirst_name());
            preparedStatement.setString(2,employee.getLast_name());
            preparedStatement.setInt(3,employee.getAge());

            ResultSet resultSet=preparedStatement.executeQuery();
            i=resultSet.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
