package frq;

public class Main{
    public static void main(String[] args){ // Pattern Generator
        String[][] base = {{"-","-","o","o"},
                           {"-","o","-","|"},
                           {"o","-","-","|"}};
        PatternGenerator pg = new PatternGenerator();
        
        pg.printPattern(base);
        pg.printPattern(pg.horizontalSymmetric(base));
        pg.printPattern(pg.verticalSymmetric(base));
        pg.printPattern(pg.twoWaySymmetric(base));
        pg.printPattern(pg.fullSymmetric(base,2,3));
    }
}