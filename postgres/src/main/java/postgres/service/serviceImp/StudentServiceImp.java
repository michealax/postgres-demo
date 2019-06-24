package postgres.service.serviceImp;

import org.springframework.stereotype.Service;
import postgres.dao.CityMapper;
import postgres.dao.CountryMapper;
import postgres.dao.StateMapper;
import postgres.dao.StudentMapper;
import postgres.entity.City;
import postgres.entity.Country;
import postgres.entity.State;
import postgres.entity.Student;
import postgres.entity.custom.CustomStudent;
import postgres.service.StudentService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private CountryMapper countryMapper;
    @Resource
    private StateMapper stateMapper;
    @Resource
    private CityMapper cityMapper;

    @Override
    public List<CustomStudent> getAllStudents() {
        return studentMapper.selectAll();
    }

    @Override
    public int addStudent(Student student) {
        int studentid = studentMapper.addStudent(student);
        return studentid;
    }

    @Override
    public Student getStudentByStudentID(String studentid) {
        return studentMapper.selectByStudentID(studentid);
    }

    @Override
    public CustomStudent getStudentByID(String studentid) {
        return studentMapper.getStudentByID(studentid);
    }

    @Override
    public List<Country> getCountry() {
        return countryMapper.selectAll();
    }

    @Override
    public List<State> getStateByID(Integer id) {
        return stateMapper.selectByCountryByID(id);
    }

    @Override
    public List<City> getCityByID(Integer id) {
        return cityMapper.selectByStateID(id);
    }

    @Override
    public List<CustomStudent> orderStudent(String order) {
        return studentMapper.orderStudent(order);
    }
}
