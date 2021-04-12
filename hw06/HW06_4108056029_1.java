public class HW06_4108056029_1 extends Dessert_Desert{
    int[][] inputArr;
    int len;//each array's length
    public int max(int a,int b){
        if(a>b) return a;
        else {return b;}
    }
    public int[] maxBlocks(int[][] inputArr){
        int n=inputArr.length;
        this.inputArr = inputArr;
        int[] ans = new int[n];
        int i,j,k,temp;
        //int[] max;
        int[] mark;
        for(i=0;i<n;i++){
            len = inputArr[i].length;
            //max = new int[len];
            mark = new int[len];
            //max[0] = inputArr[i][0];
            mark[0] = 0;
            for(j=1;j<len;j++){
                //max[j]=max(max[j-1],inputArr[i][j]);
                if(inputArr[i][mark[j-1]]>inputArr[i][j]){
                    mark[j] = mark[j-1];
                    //max[j] = max[j-1];
                }
                else{
                    mark[j] = j;
                    //max[j] = inputArr[i][j];
                }
                for(k=j;k>=0;k--){
                    if(inputArr[i][j]>=inputArr[i][mark[k]]) break;
                    else{
                        //max[k] = max[j];
                        mark[k] = mark[j];
                    }
                }
            }
            for(j=1;j<len;j++){
                if(mark[j-1]!=mark[j]) ans[i]++;
            }
            ans[i]++;
            //System.out.println(ans[i]);
        }
        return ans;
    }
    public void show(int[] dif){
        //System.out.println();
        for(int i=0;i<dif.length;i++){
            System.out.print(dif[i]+" ");
        }
        System.out.println();
    }
    public static void main(String[] args){
        HW06_4108056029_1 test = new HW06_4108056029_1();
        int[][] arr = {
                        {1, 3, 2, 5, 4, 7, 6},
                        {1, 1, 1, 1, 1, 1, 1},
                        {1, 3, 5, 7, 9},
                        {1, 2, 3},
                        {5, 4, 3, 2, 1},
                        {2, 1, 3, 2},
                        {2,5,4,1,2}};
        test.maxBlocks(arr);
    }
}
