import java.util.ArrayList;





public class rot {

    public static void main(final String[] args) {
    /*
        final String rows = new Integer(135).toString();
        final  String cols = new Integer(338).toString();
        final String threshold = new Integer(18).toString();
        System.out.println(movingCount(threshold, rows, cols)? "run":"stop");*/
        /*final int [][] array = [{1,2}, {2,4}]	;*/
//
         final int[] a = {4, 2, 1, 6, 3, 6, 0, -5, 1, 1};
         final ArrayList<Integer> reList = GetLeastNumbers_Solution(a, 4);
         for(final Integer n : reList){
             System.out.println(n);
         }
    }
public static ArrayList<Integer> GetLeastNumbers_Solution(final int [] input, final int k) {
        if(k<=0){
            return null;
        }
        final ArrayList<Integer> reList = new ArrayList<Integer>();
        if(input.length<=k){
            for(int i=0; i<input.length; i++){
                reList.add(input[i]);
            }
            return reList;
        }else{
            //排序
            final int[] a = input;
            int i, j;
            int low, high, mid;
            int temp;
            for (i = 1; i < input.length; i++) {
                temp = a[i];
                low = 0;
                high = i - 1;
                while (low <= high) {
                    mid = (low + high) / 2;
                    if (a[mid] > temp) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }
                for (j = i - 1; j > high; j--) {
                    a[j + 1] = a[j];
                }
                a[high + 1] = temp;
            }

            for (i = 0; i < input.length; i++) {
                System.out.printf("%d", a[i]);
                reList.add(a[i]);
            }
            return reList;
        }
    }
public int[] sort(final int[] input){
    final int[] a = input;
    int i, j;
    int low, high, mid;
    int temp;
    for (i = 1; i < 10; i++) {
        temp = a[i];
        low = 0;
        high = i - 1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (a[mid] > temp) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }

        }
        for (j = i - 1; j > high; j--) {
            a[j + 1] = a[j];
        }
        a[high + 1] = temp;
    }
    return a;
}
        public boolean Find(final int target, final int [][] array) {
           /* array.*/
            return false;
        }

    public static int Fibonacci( final int n) {

        if(n == 0){
            return 0;
        }else if(n==1){
            return 1;
        }else{
            return Fibonacci(n-1) +Fibonacci(n-2);
        }
    }



    static boolean  movingCount(final String threshold,final String rows,final String cols)
    {
        final char[] rowss = rows.toCharArray();
        final char[] colss = cols.toCharArray();
        int sum = 0;
        for(final char c : rowss){
            sum += Integer.parseInt(c+"");
        }
        for(final char c : colss){
            sum += Integer.parseInt(c+"");
        }
        final int nThreshold= Integer.parseInt(threshold);
        return sum<=nThreshold?true:false;
    }
}
