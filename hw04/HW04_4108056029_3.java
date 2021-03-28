public class HW04_4108056029_3 extends One_0k_rock{
    static String[] Str;
    static boolean[] ans;
    public static class inteval extends Thread{
        int start,end,seperate;
        public inteval(int start,int end){
            this.start=start;
            this.end=end;
            seperate=end-start;
        }
        public void run(){

            int n_m;
            int i,j;
            for(i=start;i<end;i++){
                n_m=Str[i].length()-1;
                if(n_m%2==0) ans[i]=false;
                else{
                    ans[i]=true;
                    for(j=0;j<(n_m+1)/2;j++){
                        if(Str[i].charAt(j)=='1' || Str[i].charAt(n_m-j)=='0'){
                            ans[i]=false;
                            break;
                        }
                    }
                }
            }
        }
        public boolean[] get(){
            return ans;
        }
    }
    public boolean[] one0k(String[] str){
        Str=str;
        int n=str.length;
        int n_m;
        int i;
        ans = new boolean[n];
        inteval[] counter = new inteval[8];
        int seperate=n>>3;
        for(i=0;i<7;i++){
            counter[i] = new inteval(seperate*i,seperate*(i+1));
            counter[i].start();
        }
        counter[7] = new inteval(seperate*6,n);
        counter[7].start();
        try{
            for(i=0;i<8;i++){
                counter[i].join();
            }
        }catch(InterruptedException e){}
        return ans;
    }
}
