package Game.Armes;
import Game.Weapon;

public class Knive extends Weapon {
    public Knive() {
        super("Knive",75,25);
    }

    public String ascii_art(){
        return
                        "       ___________________________________ ______________________\n"+
                        "    .'                                    | (_)     (_)    (_)   \\ \n"+
                        "  .'                                      |  ____          ____   } \n"+
                        ".',,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,_____|_(    `--------'    )_/ \n";
    }
}



