public class HW03_4108056029_3 extends HillFinding{
    public int H_Finding(int[] A){
        int n=A.length;
        int l=1,r=n-1,m=(n-1)>>1;
        int left_num=A[l];
        int ret=-1;
        while(A[m-1]<=A[m] && A[m]<=A[m+1]){
            if(A[m]<left_num) {
                if(A[m-2]>A[m-1]) {
                    ret=n-m;
                    break;
                }
                r=m-1;
            }
            else {
                l=m+1;
                if(A[m+1]>A[m+2]) {
                    ret=n-m-3;
                    break;
                }
            }
            m=(r+l)>>1;
        }
        if(A[m-1]>A[m]) return n-m-1;
        else if(A[m]>A[m+1]) return n-m-2;
        else return ret;
    }
}
