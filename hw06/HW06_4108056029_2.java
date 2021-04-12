public class HW06_4108056029_2 extends Dessert_Desert{
    int[][] inputArr;
    int len;//each array's length
    public int[] maxBlocks(int[][] inputArr){
        int n=inputArr.length;
        this.inputArr = inputArr;
        int[] ans = new int[n];
        int i,j;
        int[] max;
        int[] min;
        for(i=0;i<n;i++){
            len = inputArr[i].length;
            max = new int[len];
            min = new int[len];
            max[0] = inputArr[i][0];
            min[len-1] = inputArr[i][len-1];
            for(j=len-2;j>=0;j--){
                min[j] = Math.min(min[j+1],inputArr[i][j]);
            }
            for(j=1;j<len;j++){
                max[j] = Math.max(max[j-1],inputArr[i][j]);
                if(max[j-1]<=min[j]){
                    ans[i]++;
                }
            }
            ans[i]++;
        }
        return ans;
    }
    public static void main(String[] args){
        HW06_4108056029_2 test = new HW06_4108056029_2();
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
