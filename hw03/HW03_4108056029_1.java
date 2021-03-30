public class HW03_4108056029_1 extends HillFinding{
    public int H_Finding(int[] A){
        int n=A.length-1;
        int l=1,r=n,m=n>>1;
        int left_num=A[l];
        while(A[m-1]<=A[m]){
            if(A[m]<left_num) r=m-1;
            else l=m+1;
            m=(r+l)>>1;
        }
        return n-m;
    }
}
