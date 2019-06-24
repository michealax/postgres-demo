package postgres.service;

import postgres.entity.City;
import postgres.entity.Country;
import postgres.entity.State;
import postgres.entity.Student;
import postgres.entity.custom.CustomStudent;

import java.util.List;

public interface StudentService {
    List<CustomStudent> getAllStudents();

    int addStudent(Student student);

    Student getStudentByStudentID(String studentid);

    CustomStudent getStudentByID(String studentid);

    List<Country> getCountry();

    List<State> getStateByID(Integer id);

    List<City> getCityByID(Integer id);

    List<CustomStudent> orderStudent(String order);
}
