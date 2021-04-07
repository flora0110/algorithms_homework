
public class HW05_4108056029_4 extends LLK{
    int n;
    int count;//if collision ,which address we can fill in
    double[][] key;//vetor to key
    double[][] buckets;//ket to hash
    int b;//number of buckets
    static boolean ans = false;
    public boolean checkLLK(int[][] array){

        n=array.length;
        key = new double[n][n];

        b = (n/284+1)*284+1;//key will in range (1,285) 0 for empty
        buckets = new double[n][b];
        int num_thread = 32;
        Thread[] t = new Thread[num_thread];
        for(int control = 0;control<num_thread;control++){
            final int k=control;
            t[control] = new Thread(new Runnable(){
                public void run(){
                    double len,x_dif,y_dif,unit_x,unit_y;
                    for(int i=k;i<n&&!ans;i+=num_thread){
                        for(int j=i+1;j<n&&!ans;j++){
                            //System.out.println("i " +i+" j "+j);
                            x_dif = array[i][0]-array[j][0];
                            y_dif = array[i][1]-array[j][1];
                            len = Math.sqrt((x_dif*x_dif)+(y_dif*y_dif));
                            unit_x=x_dif/len;
                            unit_y=y_dif/len;
                            if(unit_y<0){//opposite vector can be a line
                                unit_x = -unit_x;
                                unit_y = -unit_y;
                            }
                            if((unit_x+ unit_y) < 1/Math.sqrt(2)){
                                key[i][j]=(unit_x+unit_y)*100+101;//+100+1 to matain 0 for represent empty
                            }
                            else{
                                key[i][j]=(unit_x+unit_y)*100+143;
                            }
                        }
                    }
                    for(int i=k;i<n &&!ans;i+=num_thread){
                        for(int j=i+1;j<n &&!ans;j++){

                            if(hashcontain(i,key[i][j])) ans=true;
                            else buckets[i][count]=key[i][j];
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
    public boolean hashcontain(int now,double key){
        int hash = (int) key*(b/284);
        for(int i=0;i<n;i++){
            if(buckets[now][(hash + i)%b]==key) return true;
            else if(buckets[now][(hash + i)%b]==0) {count=(hash+i)%b;return false;}
        }
        return false;
    }
    public static void main(String[] args){
        HW05_4108056029_4 test = new HW05_4108056029_4();
        int[][] arr = {{1,1},{2,2},{3,2},{4,1},{8,-1}};
        System.out.println(test.checkLLK(arr));
    }
}
