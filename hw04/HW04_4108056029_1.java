public class HW04_4108056029_1 extends One_0k_rock{
    int n;
    public boolean[] one0k(String[] str){
        int n=str.length;
        int n_m;
        boolean[] ans = new boolean[n];
        int i,j;
        for(i=0;i<n;i++){
            n_m=str[i].length()-1;
            if(n_m%2==1){
                ans[i]=true;
                for(j=0;j<(n_m+1)>>1;j++){
                    if(str[i].charAt(j)=='1' || str[i].charAt(n_m-j)=='0'){
                        ans[i]=false;
                        break;
                    }
                }
            }
        }
        return ans;
    }
}
