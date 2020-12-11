package tasks;

import common.Person;
import common.Task;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Collections.disjoint;
import static java.util.stream.Collectors.*;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 implements Task {


    //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
    public List<String> getNames(List<Person> persons) {
        return persons.stream().skip(1).map(Person::getFirstName).collect(toList());
    }

    //ну и различные имена тоже хочется
    public Set<String> getDifferentNames(List<Person> persons) {
        return new HashSet<>(getNames(persons));
    }

    //Для фронтов выдадим полное имя, а то сами не могут
    public String convertPersonToString(Person person) {

        return Stream.of(person.getFirstName(), person.getMiddleName(), person.getSecondName())
                .filter(Objects::nonNull)
                .collect(joining(" "));

    }

    // словарь id персоны -> ее имя
    public Map<Integer, String> getPersonNames(Collection<Person> persons) {
        return persons.stream()
                .collect(toMap(Person::getId, this::convertPersonToString, (person1,person2)->person1));
    }

    // есть ли совпадающие в двух коллекциях персоны?
    //получаем линейную сложность
    public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
        return !disjoint(persons1,persons2);
    }

    //...
    public long countEven(Stream<Integer> numbers) {
        return numbers.filter(num -> num % 2 == 0).count();
    }

    @Override
    public boolean check() {
        boolean codeSmellsGood = true;
        boolean reviewerDrunk = false;
        return codeSmellsGood || reviewerDrunk;
    }
}
