
import java.util.*;

public class Algorithms {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] A = {10, 2, 47, 3, 7, 9, 1, 98, 21};
        SegmentTreeSum st = new SegmentTreeSum(A);
        System.out.println(st.rsq(0, 8));
        st.updateValue(1, 3);
        System.out.println(st.rsq(0, 8));

    }

}
