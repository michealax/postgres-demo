package postgres.controller;


import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import postgres.entity.City;
import postgres.entity.State;
import postgres.entity.Student;
import postgres.entity.custom.CustomStudent;
import postgres.service.StudentService;

import javax.annotation.Resource;
import java.util.List;

@Controller
@CrossOrigin
public class StudentController {
    @Resource
    private StudentService studentService;

    /**
     * get the all students*/
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public String getStudents(Model model, @RequestParam(defaultValue = "studentid") String order) {
        List<CustomStudent> students = studentService.orderStudent(order);
        model.addAttribute("students", students);
        return "index";
    }

    /**
     * get the all students*/
    @RequestMapping(value = "all", method = RequestMethod.POST)
    @ResponseBody
    public String postStudents( String order) {
        List<CustomStudent> students = studentService.orderStudent(order);
        return JSON.toJSONString(students);
    }

    /**
     * add a new student*/
    @RequestMapping(value = "addStudent", method = RequestMethod.POST)
    @ResponseBody
    public String addStudent(@RequestBody Student student) {
        Student temp = studentService.getStudentByStudentID(student.getStudentid());
        if (temp != null){
            return JSON.toJSONString(-1);
        }
        int result = studentService.addStudent(student);
        return JSON.toJSONString(result);
    }

    /**
     * search a student*/
    @RequestMapping(value = "searchStudent")
    @ResponseBody
    public String searchStudent(@RequestParam String studentid) {
        CustomStudent temp = studentService.getStudentByID(studentid);
        return JSON.toJSONString(temp);
    }

    /**
     * search the cities accords to a state*/
    @RequestMapping(value = "changeState", method = RequestMethod.POST)
    @ResponseBody
    public Object changeState(Integer id) {
        List<City> states = studentService.getCityByID(id);
        return JSON.toJSONString(states);
    }

    /**
     * search the states accords to a country*/
    @RequestMapping(value = "changeCountry", method = RequestMethod.POST)
    @ResponseBody
    public Object changeCountry(Integer id) {
        List<State> states = studentService.getStateByID(id);
        return JSON.toJSONString(states);
    }
}
