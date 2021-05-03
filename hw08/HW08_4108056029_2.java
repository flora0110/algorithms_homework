public class HW08_4108056029_2 extends Buy_Phone_v2{
    public int[][] bestPhone(int[][] inputArr){
        int n = inputArr.length;
        int len = inputArr[0].length;
        int[][] aux = new int[n][len];
        int[] auxmark = new int[len];
        int[] mark = new int[n];
        sort(inputArr,aux,mark,auxmark,0,n-1,0);
        int[][] loser = new int[n][n];
        for(int i=0;i<n;i++){
            mark[i] = i;
        }
        for(int i=1;i<len;i++){
            sort(inputArr,aux,mark,auxmark,0,n-1,i);
            for(int j=0;j<n;j++){
                for(int k=j;k>=0;k--){
                    loser[mark[j]][mark[k]] = 1;
                }
            }
        }
        sort(inputArr,aux,mark,auxmark,0,n-1,0);
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
    public static void sort(int[][] inputArr,int[][] aux,int[] mark,int[] auxmark,int lo,int hi,int d){
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
            aux[count[inputArr[i][d]+1]++] = inputArr[i];
            auxmark[count[inputArr[i][d]+1]++] = mark[i];
        }
        for(i=lo;i<=hi;i++){
            inputArr[i] = aux[i-lo];
            mark[i] = auxmark[i-lo];
        }
        for(i=0;i<10;i++){
            if(d==3) d=-1;
            sort(inputArr,aux,mark,auxmark,lo+count[i],lo+count[i+1]-1,d+1);
        }
    }
}
