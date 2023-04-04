package frq;

public class PatternGenerator{
    public String[][] horizontalSymmetric(String[][] base){
        String[][] pattern = new String[base.length][base[0].length*2];
        
        for(int i = 0; i < base.length; i++){
            for(int j = 0; j < base[0].length; j++){
                pattern[i][j] = base[i][j];
                pattern[i][pattern[0].length-j-1] = base[i][j];
            }
        }
        return pattern;
    }
    
    public String[][] verticalSymmetric(String[][] base){
        String[][] pattern = new String[base.length*2][base[0].length];
        
        for(int i = 0; i < base.length; i++){
            for(int j = 0; j < base[0].length; j++){
                pattern[i][j] = base[i][j];
                pattern[pattern.length-i-1][j] = base[i][j];
            }
        }
        return pattern;
    }
    
    public String[][] twoWaySymmetric(String[][] base){
        return verticalSymmetric(horizontalSymmetric(base));
    }
    
    public String[][] fullSymmetric(String[][] base, int vReps, int hReps){
        String[][] pattern = twoWaySymmetric(base);
        String[][] full = new String[pattern.length*vReps][pattern[0].length*hReps];
        
        for(int i = 0; i < pattern.length; i++){
            for(int j = 0; j < pattern[0].length; j++){
                for(int k = 0; k < vReps; k++){
                    for(int l = 0; l < hReps; l++){
                        full[i+(k*pattern.length)][j+(l*pattern[0].length)] = pattern[i][j];
                    }
                }
            }
        }
        return full;
    }
    
    public void printPattern(String[][] pattern){
        for(int i = 0; i < pattern.length; i++){
            for(int j = 0; j < pattern[0].length; j++){
                System.out.print(pattern[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}