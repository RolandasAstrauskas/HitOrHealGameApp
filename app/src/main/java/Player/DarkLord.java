package Player;

public class DarkLord {

    private String name = "Dark Lord";
    private int healthDark = 150;

    public DarkLord() {
    }


    public void damageDarkLord(int hit) {
        this.healthDark = this.healthDark - hit;

    }

    public String getName() {
        return name;
    }

    public int getHealthDark() {
        return healthDark;


    }
}
