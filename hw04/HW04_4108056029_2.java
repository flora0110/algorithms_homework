public class HW04_4108056029_2 extends One_0k_rock{
    public static class inteval extends Thread{
        static boolean a=true;
        String str;
        public inteval(String str){
            this.str=str;
        }
        public void run(){
            int n_m=str.length()-1,j;
            if(n_m%2==0) a=false;
            else{
                for(j=0;j<(n_m+1)/2;j++){
                    //System.out.println(str[i].charAt(j)+" "+str[i].charAt(n_m-j));
                    if(str.charAt(j)=='1' || str.charAt(n_m-j)=='0'){
                        //System.out.println("false");
                        a=false;
                        break;
                    }
                }
            }
        }
        public boolean get(){
            return a;
        }
    }
    public boolean[] one0k(String[] str){
        int n=str.length;
        int n_m;
        boolean[] ans = new boolean[n];
        int i,num,j;
        inteval[] counter = new inteval[8];
        int seperate=n>>3;
        for(i=0;i<seperate;i++){
            for(j=0;j<8;j++){
                counter[j] = new inteval(str[i+j]);
                counter[j].start();
            }
            try{
                for(j=0;j<8;j++){
                    counter[j].join();
                    ans[i+j]=counter[j].get();
                }
            }catch(InterruptedException e){}

        }
        int last=seperate<<3;
        int remaind=n-last;
        for(i=last;i<n;i++){
            counter[i-last] = new inteval(str[i]);
            counter[i-last].start();
        }
        try{
            for(j=0;j<remaind;j++){
                counter[j].join();
                ans[last+j]=counter[j].get();
            }
        }catch(InterruptedException e){}
        return ans;
    }
}
