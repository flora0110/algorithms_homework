public class HW03_4108056029_1 extends HillFinding{
    public int H_Finding(int[] A){
        int n=A.length;
        int l=0,r=n-1,m=(n-1)>>1;
        int left_num=A[l];
        while(A[m-1]<=A[m]){
            //System.out.println(l+" "+m+" "+r);
            if(A[m]<left_num) r=m-1;
            else l=m+1;
            m=(r+l)>>1;
        }
        return n-m-1;
    }
    public static void main(String[] args){
        //int A[]={5,7,8,8,1,3,4};
        random_num ran = new random_num(1000,1000);
        int[] A=ran.create_arr();
        //System.out.println("--------------------------------");
        HW03_4108056029_1 test = new HW03_4108056029_1();
        System.out.println(test.H_Finding(A));
    }
}
