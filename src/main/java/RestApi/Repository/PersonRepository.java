package RestApi.Repository;

import RestApi.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
//Создаем интерфейс для использования CRUD методов
public interface PersonRepository extends JpaRepository<Person,Integer> {}
