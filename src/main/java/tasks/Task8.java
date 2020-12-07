package tasks;

import common.Person;
import common.Task;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

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
    //оставляем проверку, т.к. skip для пусого листа - NullPointerException
    public List<String> getNames(List<Person> persons) {
        if (persons.size() == 0) {
            return Collections.emptyList();
        }
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

        Map<Integer, String> map = new HashMap<>();
        for (Person person : persons) {
            Integer personId = person.getId();
            if (!map.containsKey(personId)) {
                map.put(personId, convertPersonToString(person));
            }
        }
        return map;
    }

    // есть ли совпадающие в двух коллекциях персоны?
    //получаем линейную сложность
    public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
        Set<Person> person1Set = new HashSet<>(persons1);
        Set<Person> person2Set = new HashSet<>(persons2);
        Set<Person> allPersonSet = new HashSet<>();
        allPersonSet.addAll(person1Set);
        allPersonSet.addAll(person2Set);
        return allPersonSet.size() < person1Set.size() + person2Set.size();
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
