package Database;

import java.util.Comparator;

import Player.Player;

public class SortList implements Comparator<Player> {


    @Override
    public int compare(Player o1, Player o2) {
        return Integer.compare(o1.getExperience(), o2.getExperience());
    }
}
