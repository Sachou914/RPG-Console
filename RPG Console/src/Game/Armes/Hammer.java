package Game.Armes;
import Game.Weapon;

public class Hammer extends Weapon {
    public Hammer() {
        super("Hamer",150,50);
    }

    public String ascii_art(){
        return
                "             +-+\n" +
                        "=============| |\n" +
                        "            `:_;'";
    }
}
