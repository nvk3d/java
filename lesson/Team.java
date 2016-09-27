package lesson;

public class Team {
    protected String name = "undefined";
    protected Animal[] animal = new Animal[4];
    
    Team(String type1, String type2, String type3, String type4) {
        for(int i = 0; i < 4; i++) {
            animal[i] = new Animal();
        }
        animal[0].setType(type1);
        animal[1].setType(type2);
        animal[2].setType(type3);
        animal[3].setType(type4);
    }
    
    public void showResults() {
        System.out.println("In Team " + name + ":");
        for (int i = 0; i < 4; i++) {
            if(animal[i].challengeOne && animal[i].challengeTwo && animal[i].challengeThree) {
                System.out.println("Animal " + animal[i].whoAreYou() + " has passed the distance.");
            } else {
                System.out.println("Animal " + animal[i].whoAreYou() + " is removed from the distance.");
            }
        }
        System.out.println();
    }
    
    public void showSuccess() {
        boolean flag = false;
        System.out.println("In Team " + name + " was successful:");
        for (int i = 0; i < 4; i++) {
            if(animal[i].challengeOne && animal[i].challengeTwo && animal[i].challengeThree) {
                System.out.println("Animal " + animal[i].whoAreYou() + " has passed the distance.");
                flag = true;
            }
        }
        if(!flag) {
            System.out.println("Not successfully in this team.");
        }
        System.out.println();
    }
    
    public void setTeamName(String name) {
        this.name = name;
    }
    
}