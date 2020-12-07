package Player;

public class Player {

    private int playerID;
    private String name;
    private int health;
    private int experience;


    public Player(String name) {
        this.name = name;
        this.experience = 0;
        this.health = 100;
    }

    public Player(int playerID, String name, int experience) {
        this.playerID = playerID;
        this.name = name;
        this.experience = experience;
    }

    public void damagePlayer(int hit){
        this.health = this.health - hit;
        if(this.health <= 0){
            this.health = 0;
        }

    }

    public void healPlayer(int heal){
        this.health = this.health + heal;
        if(this.health >= 150){
            this.health = 100;

        }

    }

    public void getExperienceInGame(int hitOrHeal){
        this.experience = this.experience + hitOrHeal;

    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getExperience() {
        return experience;
    }

    public int getPlayerID() {
        return playerID;
    }
}
