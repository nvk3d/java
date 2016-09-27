package lesson;

public class Animal {
    protected String name;
    protected String color;
    protected int age;
    protected String type = "undefined";
    protected boolean challengeOne = false;
    protected boolean challengeTwo = false;
    protected boolean challengeThree = false;
    
    public void setType(String type) {
        this.type = type;
    }
    
    //проверка на испытание
    public void isChallenge(String str) {
        if(str == "Run") {
            challengeOne = true;
        } else if(str == "Jump") {
            challengeTwo = true;
        } else if(str == "Swim" && this.type != "Cat" && this.type != "Rabbit") {
            challengeThree = true;
        } else {
            challengeThree = false;
        }
    }
    
    //возвращает тип
    public String whoAreYou() {
        return this.type;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getColor() {
        return this.color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public int getAge() {
        return this.age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
}