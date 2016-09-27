/**
 *  Created by Nikita Bondar 22.09.2016.
 *
 *  Здравствуйте, не до конца понял задание, такие неточности, как, какими должны быть участники? 
 *  Объектами или строкой? Массив объектов или что нужно было? В общем реализовал так, как считал нужным.
 */
package lesson;

public class AppOne {

    public static void main(String[] args) {
        Course course = new Course();
        Team t1 = new Team("Cat", "Rabbit", "Horse", "Dog");
        Team t2 = new Team("Cat", "Cat", "Rabbit", "Rabbit");
        t1.setTeamName("NA'VI");
        t2.setTeamName("Virtus Pro");
        course.doIt(t1);
        course.doIt(t2);
        t1.showResults();
        t2.showResults();
        t1.showSuccess();
        t2.showSuccess();
    }

}