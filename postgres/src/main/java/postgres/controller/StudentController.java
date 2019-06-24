package postgres.controller;


import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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

    @RequestMapping("detail")
    @ResponseBody
    public String detail() {
        return "Hello world";
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public String getStudents(Model model, @RequestParam(defaultValue = "studentid") String order) {
        List<CustomStudent> students = studentService.orderStudent(order);
        model.addAttribute("students", students);
        return "index";
    }

    @RequestMapping(value = "all", method = RequestMethod.POST)
    @ResponseBody
    public String postStudents( String order) {
        List<CustomStudent> students = studentService.orderStudent(order);
        return JSON.toJSONString(students);
    }

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

    @RequestMapping(value = "searchStudent")
    @ResponseBody
    public String searchStudent(@RequestParam String studentid) {
        CustomStudent temp = studentService.getStudentByID(studentid);
        return JSON.toJSONString(temp);
    }

    @RequestMapping(value = "changeState", method = RequestMethod.POST)
    @ResponseBody
    public Object changeState(Integer id) {
        List<City> states = studentService.getCityByID(id);
        return JSON.toJSONString(states);
    }

    @RequestMapping(value = "changeCountry", method = RequestMethod.POST)
    @ResponseBody
    public Object changeCountry(Integer id) {
        List<State> states = studentService.getStateByID(id);
        return JSON.toJSONString(states);
    }
}
