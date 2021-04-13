public class HW06_4108056029_1 extends Dessert_Desert{
    int[][] inputArr;
    int len;//each array's length
    public int[] maxBlocks(int[][] inputArr){
        int n=inputArr.length;
        this.inputArr = inputArr;
        int[] ans = new int[n];
        int i,j;
        int[] max = new int[100000];
        int[] min = new int[100000];
        for(i=0;i<n;i++){
            len = inputArr[i].length;
            max[0] = inputArr[i][0];
            min[len-1] = inputArr[i][len-1];
            for(j=len-2;j>=0;j--){
                if(min[j+1]<inputArr[i][j]) min[j] = min[j+1];
                else min[j] = inputArr[i][j];
            }
            for(j=1;j<len;j++){
                if(max[j-1]>inputArr[i][j]) max[j] = max[j-1];
                else max[j] = inputArr[i][j];
                if(max[j-1]<=min[j]){
                    ans[i]++;
                }
            }
            ans[i]++;
        }
        return ans;
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
