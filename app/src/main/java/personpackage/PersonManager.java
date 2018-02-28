package personpackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PersonManager implements Serializable {
    public List <Person> personList;


    public PersonManager() {
        personList =  new ArrayList<>();
    }



    public boolean addPerson(Person p) {
        Person person = p;
        String email = person.getEmail();
        Person cust = search(email);
        if (cust == null) {
            return false;
        } else {
            personList.add(person);
            return true;
        }

    }

//        public int search(Person person) {
//        int index;
//        Collections.sort(personList,nameComparator);
//        index =Collections.binarySearch(personList,person,nameComparator);
//        return index;
//    }

    public Person search(String email) {
        for(Person p: personList){
            if(p.getEmail().equalsIgnoreCase(email)){
                return p;
            }
        }
        return null;
    }




    public static Comparator<Person> nameComparator = new Comparator<Person>() {
        public int compare(Person a, Person b) {
            return a.email.compareTo(b.email);
        }
    };



}
