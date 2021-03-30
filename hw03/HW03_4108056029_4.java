public class HW03_4108056029_4 extends HillFinding{
    static int n;
    static int[] A;
    public static class inteval extends Thread{
        boolean isTerminated = false;
        int l,r,m;
        int left_num;
        int cut;
        static int[] A;
        public void terminate(){
            isTerminated=true;
            interrupt();
        }
        public inteval(int l,int r){
            this.l=l;
            this.r=r;
            this.m=(l+r)>>1;
            //System.out.println(l+" "+r);
            left_num=A[l];
        }
        public void run(){
            //try{
                int R=r;
                while(A[m-1]<=A[m] && l<=R && !isTerminated){
                    //System.out.println("m "+m+" r: "+r+" l "+l+" cut:"+cut);
                    if(A[m]<left_num) r=m-1;
                    else l=m+1;
                    m=(r+l)>>1;
                }
                //System.out.println("ll:"+l+"rr: "+r+"m: "+m);
                if(l<=R) cut=n-m-1;
                else cut=-1;
            /*}
            catch(InterruptedException e){
            }
            finally{
                cut=-1;
            }*/
        }
        public int get_cut(){
            return cut;
        }
    }
    public int H_Finding(int[] A){
        A=A;
        n=A.length;
        inteval.A=A;
        inteval[] counter = new inteval[2];
        int turn;
        int split=n>>2;
        counter[0] = new inteval(0,split+1);
        counter[0].start();
        counter[1] = new inteval(split+1,n-1);
        counter[1].start();
        int ans=0;
        try{
            int term=0;
            for(turn=0;turn<2;turn++){
                if(term==0){
                    counter[turn].join();
                    ans=counter[turn].get_cut();
                    if(ans!=-1) term=1;
                }
                else{
                    counter[turn].terminate();
                }
            }
        }catch(InterruptedException e){}
        return ans;
    }
}
