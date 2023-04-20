import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class Application {
    public static void printList(List<Employee> list) {
        System.out.println("-----------------------------");
        for (Employee employee : list) {
            System.out.println(employee);
        }
        System.out.println("-----------------------------");
    }
    public static void main(String[] args) {
        final  String user = "postgres";
        final String password = "19891989As";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (final Connection connection = DriverManager.getConnection(url,user,password); PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT first_name,last_name, gender, age, city_name FROM employee" +
                " JOIN city ON employee.city_id=city.city_id WHERE id=1");) {

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                String city = resultSet.getString("city_name");
                System.out.printf("%s %s:  %d лет - %s  -  %s",firstName,lastName,age,city,gender);
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("Ошибка подключинея к БД");
            e.printStackTrace();
        }

        System.out.println("-----------------------------");

        EmployeeDAO emp = new EmployeeDAOImpl();
        printList(emp.getAllEmployees());
        Employee employee1 = new Employee("Федоров", "Василий", "male", 31, 1);
        Employee employee2 = new Employee("Темчук", "Захар", "male", 19, 1);
        emp.addEmployee(employee1);
        printList(emp.getAllEmployees());
        System.out.println("Пользователь c ID"+emp.getId(employee1)+" - "+employee1);
    }
}
