package RestApi.Controller;

import RestApi.Model.Person;
import RestApi.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.List;

//Создаем контроллер для обработки HTTP-запросов к приложению
@Controller
public class PersonController {
    //Получаем bean personRepository
    @Autowired
    private PersonService personService;

    //Просматриваем список людей
    @RequestMapping("/")
    public String showAllPeople(Model model){
        List<Person> listPerson = personService.listAll();
        model.addAttribute("listPerson", listPerson);
        return "Person";
    }
    //Получаем страницу создания нового пользователя
    @RequestMapping("/new")
    public String showAddPerson(Model model){
        Person person = new Person();
        model.addAttribute("person", person);
        return "AddPerson";
    }
    //Добавляем нового пользователя
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String savePerson(@Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
           return "AddPerson";
        }
        personService.save(person);
        return "redirect:/";
    }
    //Редактируем пользователя
    @RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPerson(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView("EditPerson");
        Person person = personService.get(id);
        mav.addObject("person", person);
        return mav;
    }
    //Сохраняем изменения
    @RequestMapping(value="/change", method = RequestMethod.POST)
    public String changePerson(@Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "EditPerson";
        }
        personService.save(person);
        return "redirect:/";
    }
    //Удаляем пользователя
    @RequestMapping("/delete/{id}")
    public String deletePerson(@PathVariable(name="id")int id){
        personService.delete(id);
        return "redirect:/";
    }

}
