package yufa;

public class For_break_to_mark {
    public static void main(final String[] args) {
        k1 :  for(int z=0; z<9; z++){
            System.out.print (" z"+z);

            for(int i=0; i<9; i++){
                System.out.print(" i" + i);
                for(int j=0; j<9; j++){
                     break k1;//break to k1
                     //;
                }
            }
        }
    }
}
