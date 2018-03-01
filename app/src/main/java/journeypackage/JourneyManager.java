package journeypackage;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import personpackage.Person;

/**
 * Created by clausyd on 14/02/18.
 */

public class JourneyManager {

    public List<Journey> journeyList;




    public JourneyManager() {
        journeyList = new ArrayList<>();

    }



    public void addJourney(Journey j) {
        Journey  journey = j;

        journeyList.add(j);
    }

    public List<Journey> getJourneyList() {
        return this.journeyList;
    }
//        Journey journey = j;
//        int index = search(journey);
//        if (index == 0) {
//            return false;
//        } else {
//            journeyList.add(journey);
//            return true;
//        }




//    public int search(Journey journey) {
//        int index;
//        Collections.sort(journeyList,emailComparator);
//        index =Collections.binarySearch(journeyList,journey,emailComparator);
//        return index;
//    }


//    public static Comparator<Journey> emailComparator = new Comparator<Journey>() {
//        public int compare(Journey a, Journey b) {
//            return a.email.compareTo(b.email);
//        }
//    };
}