import java.util.*;
public class random_num{
    String[] A;
    int n;
    public random_num(int n,int lenmax){
        this.n=n;
        A=new String[n];
        //int[] a ;
        int num;

        for(int j=0;j<n;j++){
            int strlen=(int)(Math.random()*(lenmax+2))+1;
            //a = new int[strlen];
            String strRet="";
            for(int i=0;i<strlen;i++){
                //a[i]=(int)(Math.random()*(2));
                num=(int)(Math.random()*(2));
                strRet+=Integer.toString(num);
                //System.out.print(num+" ");
            }
            //System.out.print("\n");
            A[j]=strRet;
        }


        /*for(int i=0;i<n;i++){

            System.out.print(A[i]+" ");
        }
        System.out.print("\n");*/
    }
    public String[] create_arr(){
        for(int i=0;i<n;i++){
            System.out.print(A[i].length()+" : ");
            for(int j=0;j<A[i].length();j++){
                System.out.print(A[i].charAt(j));
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        String[] B = {"00000001111111","01"};
        return B;
    }
    public static void main(String[] args){
        random_num ran = new random_num(10,10);
        ran.create_arr();
    }
}
