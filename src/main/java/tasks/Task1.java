package tasks;

import common.Person;
import common.PersonService;
import common.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toMap;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимпотику работы
 */
public class Task1 implements Task {

    // !!! Редактируйте этот метод !!!
    //O(n)
    private List<Person> findOrderedPersons(List<Integer> personIds) {
        Set<Person> persons = PersonService.findPersons(personIds);
        Map<Integer, Person> personMap = persons.stream()
                .collect(toMap(Person::getId, identity()));
        List<Person> personList = personIds.stream()
                .map(personMap::get)
                .collect(toList());
        return personList;
    }


    @Override
    public boolean check() {
        List<Integer> ids = List.of(1, 2, 3);

        return findOrderedPersons(ids).stream()
                .map(Person::getId)
                .collect(toList())
                .equals(ids);
    }

}
