public class HW03_4108056029_2_old extends HillFinding{
    static int n;
    static int[] A;
    public static class inteval extends Thread{
        int l,r,m;
        int left_num;
        int cut;
        static int[] A;
        public inteval(int l,int r){
            this.l=l;
            this.r=r;
            this.m=(l+r)>>1;
            //System.out.println(l+" "+r);
            left_num=A[l];
        }
        public void run(){
            //cut=0;
            int R=r;
            while(A[m-1]<=A[m] && l<=R){
                //System.out.println("m "+m+" r: "+r+" l "+l+" cut:"+cut);
                if(A[m]<left_num) r=m-1;
                else l=m+1;
                m=(r+l)>>1;
            }
            //System.out.println("ll:"+l+"rr: "+r+"m: "+m);
            if(l<=R) cut=n-m-1;
            else cut=-1;
        }
        public int get_cut(){
            return cut;
        }
    }
    public int H_Finding(int[] A){
        A=A;
        n=A.length;
        inteval.A=A;
        inteval[] counter = new inteval[8];
        int turn;
        int split=n>>3;
        for(turn=0;turn<7;turn++){
            counter[turn] = new inteval(split*turn+1,split*(turn+1)+1);
            counter[turn].start();
        }
        counter[7] = new inteval(split*7+1,n-1);
        counter[7].start();
        int ans=0;
        try{
            for(turn=0;turn<8;turn++){
                counter[turn].join();
                ans=counter[turn].get_cut();
                if(ans!=-1) break;
            }
        }catch(InterruptedException e){}
        return ans;
    }
}
