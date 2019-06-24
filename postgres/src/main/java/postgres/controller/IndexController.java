package postgres.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import postgres.entity.City;
import postgres.entity.Country;
import postgres.entity.State;
import postgres.service.StudentService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
public class IndexController {
    @Resource
    private StudentService studentService;
    @RequestMapping("/")
    public String index(){
        return "redirect:/all";
    }

    @RequestMapping("/add")
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView("add");
        List<Country> countries = studentService.getCountry();
        List<State> states = new ArrayList<>();
        List<City> cities = new ArrayList<>();
        if (countries != null){
            states = studentService.getStateByID(countries.get(0).getCountryid());
            if (states != null){
                cities = studentService.getCityByID(states.get(0).getStateid());
            }
        }

        modelAndView.addObject("countries", countries);
        modelAndView.addObject("states",states);
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @RequestMapping("/search")
    public String search(){
        return "search";
    }

    @RequestMapping("/404")
    public String error(){
        return "/404";
    }
}
