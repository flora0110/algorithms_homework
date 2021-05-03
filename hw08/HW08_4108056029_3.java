public class HW08_4108056029_3 extends Buy_Phone_v2{
    static int[][] aux = new int[20000][16];
    static int[] auxmark = new int[16];
    public int[][] bestPhone(int[][] inputArr){
        int n = inputArr.length;
        int len = inputArr[0].length;
        int[] mark = new int[n];
        sort(inputArr,mark,0,n-1,0);
        int[][] loser = new int[n][n];
        for(int i=0;i<n;i++){
            mark[i] = i;
        }
        for(int i=1;i<len;i++){
            sort(inputArr,mark,0,n-1,i);
            for(int j=0;j<n;j++){
                for(int k=j;k>=0;k--){
                    loser[mark[j]][mark[k]] = 1;
                }
            }
        }
        sort(inputArr,mark,0,n-1,0);
        for(int j=0;j<n;j++){
            for(int k=j;k>=0;k--){
                loser[mark[j]][mark[k]] = 1;
            }
        }
        int imp,count=0;
        for(int k=0;k<n;k++){
            imp = 1;
            for(int j=0;j<n;j++){
                if(loser[k][j]==0) imp = 0;
            }
            if(imp==1){
                count++;
            }
            else mark[k]=-1;
        }
        int[][] ans = new int[count][len];
        count=0;
        for(int i=0;i<n;i++){
            if(mark[i] > -1){
                for(int j=0;j<len;j++){
                    ans[count][j]  = inputArr[i][j];
                }
                count++;
            }
        }
        return ans;
    }
    public static void sort(int[][] inputArr,int[] mark,int lo,int hi,int d){//inputArr->x
        if(hi<=lo) return;
        int [] count = new int[12];
        int i;
        for(i=lo;i<=hi;i++){
            count[inputArr[i][d]+2]++;
        }
        for(i=1;i<11;i++){
            count[i+1] += count[i];
        }
        for(i=lo;i<=hi;i++){
            aux[count[inputArr[i][d]+1]] = inputArr[i];
            auxmark[count[inputArr[i][d]+1]++] = mark[i];
        }
        for(i=lo;i<=hi;i++){
            inputArr[i] = aux[i-lo];
            mark[i] = auxmark[i-lo];
        }
        for(i=0;i<10;i++){
            if(d==3) d=-1;
            sort(inputArr,mark,lo+count[i],lo+count[i+1]-1,d+1);
        }
    }
    public static void main(String[] args){
        HW08_4108056029_3 test = new HW08_4108056029_3();
        int[][] inputArr = {{2,4},{2,10},{5,4},{4,8},{1,1},{1,1},{5,5},{8,4},{10,2},{10,1}};
        int[][] ans= test.bestPhone(inputArr);
        /*for(int i=0;i<ans.length;i++){
            System.out.println(ans[i][0]+" "+ans[i][1]);
        }*/
    }
}
