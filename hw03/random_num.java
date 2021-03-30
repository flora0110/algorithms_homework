import java.util.*;
public class random_num{
    int [] A;
    int n;
    public random_num(int n,int range){
        this.n=n;
        A=new int[n];
        for(int i=0;i<n;i++){
            A[i]=(int)(Math.random()*(range+range+1))-range;
            //System.out.print(A[i]+" ");
        }
        //System.out.print("\n");
        quick();
        /*for(int i=0;i<n;i++){

            System.out.print(A[i]+" ");
        }
        System.out.print("\n");*/
    }
    public void quick() {
        sort(0,(A.length)-1);
    }

    private void sort(int left, int right) {
        if(left < right) {
            int q = partition(left, right);
            sort( left, q-1);
            sort( q+1, right);
        }

    }

    private int partition(int left, int right) {
        int i = left - 1;
        for(int j = left; j < right; j++) {
            if(A[j] <= A[right]) {
                i++;
                swap(i, j);
            }
        }
        swap(i+1, right);
        return i+1;
    }

    private void swap( int i, int j) {
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
    public int[] create_arr(){
        int cut=0;
        cut= (int)(Math.random()*(n-1))+1;
        System.out.println("cut :"+cut);
        //cut=0;
        int f=0;
        int[] B = new int[n];
        for(int i=cut+1;i<n;i++){
            B[f++]=A[i];
            //System.out.print(B[f-1]+" ");
        }
        for(int i=0;i<cut+1;i++){
            B[f++]=A[i];
            //System.out.print(B[f-1]+" ");
        }
        //System.out.println("\n");
        return B;
    }
}
