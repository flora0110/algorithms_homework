
public class HW05_4108056029_2 extends LLK{
    int n;
    int count;//if collision ,which address we can fill in
    double[][] key;//vetor to key
    double[][] buckets;//ket to hash
    int b;//number of buckets
    public boolean checkLLK(int[][] array){
        double len,x_dif,y_dif,unit_x,unit_y;
        n=array.length;
        key = new double[n][n];
        int i,j,k;
        b = (n/284+1)*284+1;//key will in range (1,285) 0 for empty
        buckets = new double[n][b];
        for(i=0;i<n;i++){
            for(j=i+1;j<n;j++){
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
                System.out.println("i " +i+" key: "+key[i][j]);
            }
        }
        for(i=0;i<n ;i++){
            for(j=0;j<i;j++){
                if(hashcontain(i,key[j][i])) return true;
                else buckets[i][count]=key[j][i];
                System.out.println("buckets["+i+"]["+count+"]"+buckets[i][count]);
            }
            for(j=i+1;j<n ;j++){
                if(hashcontain(i,key[i][j])) return true;
                else buckets[i][count]=key[i][j];
                System.out.println("buckets["+i+"]["+count+"]"+buckets[i][count]);
            }
        }
        return false;
    }
    public boolean hashcontain(int now,double key){
        int hash = (int) key*(b/284);
        for(int i=0;i<n;i++){
            System.out.println("in hashcontain      buckets["+now+"]["+((hash+i)%b)+"]"+buckets[now][(hash+i)%b]);
            if(buckets[now][(hash + i)%b]==key) return true;
            else if(buckets[now][(hash + i)%b]==0) {count=(hash+i)%b;return false;}
        }
        return false;
    }
    public static void main(String[] args){
        HW05_4108056029_2 test = new HW05_4108056029_2();
        int[][] arr = {{1,1},{2,2},{3,2},{4,1},{8,-1}};
        System.out.println(test.checkLLK(arr));
    }
}
