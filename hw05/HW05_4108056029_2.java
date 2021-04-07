public class HW05_4108056029_2 extends LLK{
    int n;
    hashmap[][] buckets;//ket to hash
    int b;//number of buckets
    static volatile boolean ans = false;
    public boolean checkLLK(int[][] array){
        n=array.length;
        //key = new double[n][n];
        b = 1 << ((int)Math.ceil(Math.log10((double)n) / 0.3010));//key will in range (1,285) 0 for empty
        buckets = new hashmap[n][b];
        int num_thread = 32;
        int lgTNum = 5;
        Thread[] t = new Thread[num_thread];
        if(n<num_thread){
            double key;
            int index;
            for(int i=0;i<n;i++){
                for(int j=0;j<b;j++){
                    buckets[i][j] = new hashmap();
                }
            }
            for(int i=0;i<n&&!ans;i++){
                //System.out.println("i : "+i);
                for(int j=i+1;j<n&&!ans;j++){
                    //System.out.println("i j : "+i+" "+j);
                    if((array[i][0]-array[j][0])==0) key = 0;
                    else key = (double)(array[i][1]-array[j][1])/(double)(array[i][0]-array[j][0]);
                    //System.out.println("key: "+key+" hashCode: "+Double.valueOf(key).hashCode()+" &0xfff: "+(Double.valueOf(key).hashCode() & 0x7fffffff));
                    //System.out.println("b-1: "+(b-1)+" &b-1 "+((Double.valueOf(key).hashCode() & 0x7fffffff) & (b-1)));
                    System.out.println("key: "+key);
                    index = (Double.valueOf(key).hashCode() & 0x7fffffff) & (b-1);
                    System.out.println("index: "+index);
                    if(buckets[i][index].contain(key)){ ans=true;}
                    //else{System.out.println("false");}

                }
            }
            //System.out.println("end");
            return ans;
        }
        else{
        for(int control = 0;control<num_thread;control++){
            final int k=control;
            t[control] = new Thread(new Runnable(){
                public void run(){
                    double key;
                    final int start = (n* k)     >> lgTNum;
                    final int end   = (n * (k+1)) >> lgTNum;
                    //System.out.println("start: "+start+" end: "+end);
                    int index;
                    for(int i=start;i<end &&!ans;i++){
                        for(int j=0;j<b;j++){
                            buckets[i][j] = new hashmap();
                        }
                    }
                    for(int i=start;i<end&&!ans;i++){
                        for(int j=i+1;j<end&&!ans;j++){
                            /*x_dif = array[i][0]-array[j][0];
                            y_dif = array[i][1]-array[j][1];*/
                            if((array[i][1]-array[j][1])==0) key = 0;
                            else key = (double)(array[i][0]-array[j][0])/(double)(array[i][1]-array[j][1]);

                            index = (Double.valueOf(key).hashCode() & 0x7fffffff) & (b-1);
                            //System.out.println("index: "+index);
                            if(buckets[i][index].contain(key)) ans=true;
                            /*if((y_dif & 0x7fffffff)>(x_dif & 0x7fffffff)){
                                index = (Double.valueOf(x_dif/y_dif).hashCode() & 0x7fffffff) & (b-1);
                                if(buckets[i][index].add(key[i][j])) ans=true;
                            }
                            else{
                                dif = (y_dif/x_dif);
                                key[i][j]=(dif+4)*n;
                            }*/
                        }
                    }

                }
            });
            t[control].start();
        }
        try{
            for(int control=0; control<num_thread; control++){
                t[control].join();
            }
        }catch(InterruptedException e){}
        return ans;
        }
    }
    class hashmap{
        private class Node{
            public double key;
            public Node next;
            public Node(double key){
                this.key = key;
                this.next = null;
            }
        }
        private Node first = null;

        public boolean contain(double key){
            Node temp = first;
            if(temp==null) {
                Node temp1 = new Node(key);
                temp1.next = first;
                first = temp1;
                return false;
            }
            while(temp.next!=null && !(temp.key==key)){

                temp = temp.next;
            }
            if(!(temp.key==key)) {
                Node temp1 = new Node(key);
                temp1.next = temp;
                first = temp1;
                return false;
            }
            else return true;
        }
    }
    public static void main(String[] args){
        HW05_4108056029_2 test = new HW05_4108056029_2();
        int[][] arr = {{1,1},{2,2},{3,2},{4,1},{8,-1}};
        System.out.println(test.checkLLK(arr));
    }
}
