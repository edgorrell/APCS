import java.text.DecimalFormat;

public class Main{
    public static void main(String[] args){
        DecimalFormat fmt1 = new DecimalFormat("000");
        DecimalFormat fmt2 = new DecimalFormat("0000");
        int num1 = 0,num2 = 0, num3 = 0;
        
        num1 += (100 * (int)(9 * Math.random() + 1));
        num1 += (10 * (int)(7 * Math.random()));
        num1 += ((int)(7 * Math.random()));
        
        num2 = (int)(742 * Math.random());
        
        num3 = (int)(10000 * Math.random());
        System.out.println(num1 + "-" + fmt1.format(num2) + "-" + fmt2.format(num3));
    }
}