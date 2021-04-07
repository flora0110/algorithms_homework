public class HW05_4108056029_3 extends LLK{
    int n;
    //int count;//if collision ,which address we can fill in
    double[][] key;//vetor to key
    LinkList[][] buckets;//ket to hash
    int b;//number of buckets
    static boolean ans = false;
    public double abs(double n){
        if(n<0) {return (-n);}
        else return n;
    }
    public boolean checkLLK(int[][] array){
        n=array.length;
        key = new double[n][n];
        b = 5*n+1;//key will in range (1,285) 0 for empty
        buckets = new LinkList[n][b];
        int num_thread = 32;
        Thread[] t = new Thread[num_thread];
        for(int control = 0;control<num_thread;control++){
            final int k=control;
            t[control] = new Thread(new Runnable(){
                public void run(){
                    double dif,x_dif,y_dif;
                    for(int i=k;i<n&&!ans;i+=num_thread){
                        for(int j=i+1;j<n&&!ans;j++){
                            x_dif = array[i][0]-array[j][0];
                            y_dif = array[i][1]-array[j][1];
                            if(abs(y_dif)>abs(x_dif)){
                                dif = (x_dif/y_dif);
                                key[i][j]=(dif+2)*n;
                            }
                            else{
                                dif = (y_dif/x_dif);
                                key[i][j]=(dif+4)*n;
                            }
                        }
                    }
                    int hash;
                    for(int i=k;i<n &&!ans;i+=num_thread){
                        for(int j=0;j<b&&!ans;j++){
                            buckets[i][j] = new LinkList();
                        }
                    }
                    for(int i=k;i<n &&!ans;i+=num_thread){
                        for(int j=i+1;j<n&&!ans;j++){
                            hash = (int) key[i][j];
                            if(buckets[i][hash].get(key[i][j])) ans=true;
                            else buckets[i][hash].add(key[i][j]);
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
    class LinkList{
        private class Node{
            public double key;
            public Node next;
            public Node(double key){
                this.key = key;
                this.next = null;
            }
        }
        private Node first = null;
        public void add(double key){

            Node temp = new Node(key);
            temp.next = first;
            first = temp;
        }
        public boolean get(double key){
            Node temp = first;
            if(temp==null) return false;
            while(temp.next!=null && !(temp.key==key)){

                temp = temp.next;
            }
            if(!(temp.key==key)) return false;
            else return true;
        }
    }
    public static void main(String[] args){
        HW05_4108056029_3 test = new HW05_4108056029_3();
        int[][] arr = {{1,1},{2,2},{3,2},{4,1}};
        System.out.println(test.checkLLK(arr));
    }
}
