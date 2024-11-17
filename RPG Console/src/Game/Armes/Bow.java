package Game.Armes;
import Game.Weapon;

public class Bow extends Weapon {
    public Bow() {
        super("Bow",100,30);
    }

    public String ascii_art(){
        return
                "   (        \n" +
                        "    \\      \n" +
                        "     )      \n" +
                        "##--------> \n" +
                        "     )      \n" +
                        "    /       \n" +
                        "   (        \n";
    }
}
