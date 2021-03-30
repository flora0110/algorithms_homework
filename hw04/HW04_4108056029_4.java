public class HW04_4108056029_4 extends One_0k_rock{
    int n;
    public boolean[] one0k(String[] str){
        int n=str.length;
        int str_len,mid;
        boolean[] ans = new boolean[n];
        int i,start,sep,next,j=0;
        for(i=0;i<n;i++){
            str_len=str[i].length();
            if(str_len%2==1) ans[i]=false;
            else{
                mid=str_len>>1;
                if(str[i].charAt(mid-1)=='0' && str[i].charAt(mid)=='1'){
                    ans[i]=true;
                    start=0;
                    j=0;
                    next=1;
                    for(sep=2;j<str_len>>1 && ans[i];sep=sep<<1){
                        for(;j<str_len>>1;j+=sep){
                            if(str[i].charAt(j)=='1' || str[i].charAt(str_len-1-j)=='0'){
                                ans[i]=false;
                                break;
                            }
                        }
                        start+=next;
                        j=start;
                        next=next<<1;
                    }
                }
                else ans[i]=false;
            }

        }
        return ans;
    }
}
