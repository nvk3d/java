package lesson;;

public class Course {
    protected String[] mas = {"Run", "Jump", "Swim"};
    
    void doIt(Team team) {
        int k = 0;
        for(int i = 0; i < team.animal.length; i++) {
            team.animal[i].isChallenge(mas[k]);
            k++;
            team.animal[i].isChallenge(mas[k]);
            k++;
            team.animal[i].isChallenge(mas[k]);
            k = 0;
        }
    }
    
}