package RestApi.Service;

import RestApi.Model.Person;
import RestApi.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

//Создаем класс PersonService для использования методов работы с данными (просмотр, сохранение, изменение, удаление)
@Controller
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> listAll(){
        return personRepository.findAll();
    }

    public void save(Person person){
        personRepository.save(person);
    }

    public Person get(Integer id){
        return personRepository.findById(id).get();
    }

    public void delete(Integer id){
        personRepository.deleteById(id);
    }

}
