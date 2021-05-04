public class HW08_4108056029_1 extends Buy_Phone_v2{
    //int[][] inputArr;
    //int n;
    static int[][] array;
    final static public int[][] aux = new int[10000][2];
    static int[][] ans;
    public int[][] bestPhone(int[][] inputArr){
        array = inputArr;
        int n = array.length;
        //this.array = array;
        sort(array,0,n-1,0);
        int[][] exist = new int[n][6];
        exist[0] = array[n-1];
        int num=1;//ans array's size
        int win;
        int check;
        for(int i=n-2;i>=0;i--){//control array[i]
            check=1;
            for(int j=0;j<num;j++){//control exist[j]
                win=0;
                for(int h=1;h<6;h++){
                    if(array[i][h]>exist[j][h]){
                        win=1;
                    }
                }
                if(win==0){ check=0; break;}
            }
            if(check==1) exist[num++] = array[i];
        }
        int[][] ans = new int[num][6];
        int count = 0;
        for(int i=num-1;i>=0;i--){
            ans[i] = exist[count++];
        }
        return ans;
    }
    private static void sort(int[][] array,int lo,int hi,int d){//array->x
        if(hi<=lo) return;
        int [] count = new int[12];
        int i;
        for(i=lo;i<=hi;i++){
            count[array[i][d]+2]++;
        }
        for(i=1;i<11;i++){
            count[i+1] += count[i];
        }
        for(i=lo;i<=hi;i++){
            aux[count[array[i][d]+1]++] = array[i];
            //auxmark[count[array[i][d]+1]++] = mark[i];
        }
        for(i=lo;i<=hi;i++){
            array[i] = aux[i-lo];
            //mark[i] = auxmark[i-lo];
        }
        for(i=0;i<10;i++){
            if(d==3) d=-1;
            sort(array,lo+count[i],lo+count[i+1]-1,d+1);
        }
    }
    /*public static void main(String[] args){
        HW08_4108056029_1 test = new HW08_4108056029_1();
        //int[][] inputArr = {{2,3,5,0,1,2,3},{1,7,3,2,5,0,1},{3,0,0,2,3,4,7},{0,2,3,4,5,6,1},{2,2,5,6,7,1,0}};
        int[][] inputArr = {{8,7,7,4,2,1},{2,4,9,2,2,1},{4,0,5,1,3,2},{5,2,4,3,7,3},{7,5,6,9,8,9}};
        int[][] ans = test.bestPhone(inputArr);
        for(int i=0;i<ans.length;i++){
            for(int j=0;j<6;j++){
                System.out.print(ans[i][j]+" ");
            }
            System.out.println();
        }
    }*/
}
