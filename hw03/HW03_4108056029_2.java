public class HW03_4108056029_2 extends HillFinding{
    public int H_Finding(int[] A){
        int n=A.length-1;
        int l=0,r=n,m=n>>1;
        int left_num=A[l];
        while(A[l]==A[m]){
            if(check(A,l,m)==1){ r=m-1; m=(r+l)>>1;}
            else if(check(A,m,n)==1){ l=m+1; m=(r+l)>>1;}
            else return 0;
        }
        while(A[m-1]<=A[m]){
            if(A[m]<left_num) r=m-1;
            else l=m+1;
            m=(r+l)>>1;
        }
        return n-m;
    }
    public int check(int[] A ,int s,int e){
        int c=A[s];
        int check=0;
        for(int i=s;i<=e;i++){
            if(A[i]!=c){
                check=1;
                break;
            }
        }
        return check;
    }
}
