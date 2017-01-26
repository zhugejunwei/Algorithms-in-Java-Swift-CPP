
import java.lang.Double;

public class Main {
    
    public static double singleDouble(double[] nums) {
        long mask = 0;
        for (double num : nums) {
            mask ^= Double.doubleToLongBits(num);
        }
        return Double.longBitsToDouble(mask);
    }
    
    public static void main(String[] args) {
        double[] nums = {12123.12312, 12123.12312, 12312.01231, 12312.01231, 1231231.12391182, 1231231.12379817, 1231231.12391182};
        System.out.println(singleDouble(nums));
    }
}

