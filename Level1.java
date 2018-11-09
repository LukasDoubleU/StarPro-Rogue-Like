import greenfoot.Greenfoot;

public class Level1 extends Level {

    /**
     * Default Welt 0 = Leer 1 = Wand 2 = Spieler 3 = Gegnertyp 1 4 = Barrel 5 =
     * Wand 90° links 6 = Wand 90° rechts 7 = Wand 180°
     */

    private double[][] world = {
            /**
             * 18 Zeilen von Oben nach unten für 720 Pixel Die letzten 2 Zeilen sind für
             * Lifebar und Timer da
             *
             * 32 Elemente von Links nach Rechts für 1280 Pixel
             */
            { 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 },
            { 9, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 9 },
            { 9, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 9 },
            { 9, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 9 },
            { 9, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 9 },
            { 9, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 20, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 1.3, 0, 0, 0, 0, 0, 0, 9 },
            { 9, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 1.3, 1.3, 1.3, 1.3, 1.3, 0, 0, 0, 0, 0, 0, 6, 1.3, 1.3, 1.3, 0, 0, 0, 0,
                    0, 0, 9 },
            { 13, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 11 },
            { 14, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 8, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12 },
            { 9, 3, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 1.3, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
            { 9, 1.1, 1.1, 1.1, 1.1, 0, 0, 0, 0, 1.3, 6, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                    9 },
            { 9, 0, 8, 7, 1.1, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
            { 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 9 },
            { 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 1.3, 1.3, 1.3, 1.3, 1.3, 1.3, 1.3, 2, 0, 0, 0, 6, 5, 0, 0, 0,
                    2, 9 },
            { 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 7, 0, 0, 0, 3, 20, 1.1, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
            { 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1.1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 },
            { 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 95, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99,
                    99, 99, 99, 99, 99, 99 },
            { 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99,
                    99, 99, 99, 99, 99, 99 }, };

    public Level1() {
        super("background_final.png");
        generateWorld(world);
    }

    @Override
    public void finish() {
       if(monstercount==0) {
           Greenfoot.setWorld(new Level2());
       }
    }
}
