public class HW03_4108056029_5 extends HillFinding{
    public int H_Finding(int[] A){
        int n=A.length;
        int l=1,r=n-1,m=(n-1)>>1;
        int left_num=A[l];
        while(A[m-1]<=A[m] && A[m]<=A[m+1]){
            if(A[m]<left_num) r=m-1;
            else l=m+1;
            m=(r+l)>>1;
        }
        if(A[m-1]>A[m]) return n-m-1;
        else return n-m-2;
    }
}
