package Game.Armes;

import Game.Weapon;

public class Axe extends Weapon {
    public Axe() {
        super("Axe",120,40);
    }
    public String ascii_art(){
        return
                "    ,  /\\  .    \n" +
                        "   //`-||-'\\\\   \n" +
                        "  (| -=||=- |)    \n" +
                        "   \\\\,-||-.//   \n" +
                        "    `  ||  '    \n" +
                        "       ||       \n" +
                        "       ||       \n" +
                        "       ||       \n" +
                        "       ||       \n" +
                        "       ||       \n" +
                        "       ()       \n";
    }

}
