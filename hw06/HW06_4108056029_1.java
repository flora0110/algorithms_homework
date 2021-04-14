public class HW06_4108056029_1 extends Dessert_Desert{
    int[][] inputArr;
    int len;//each array's length
    public int[] maxBlocks(int[][] inputArr){
        long start =System.currentTimeMillis();

        int n=inputArr.length;
        this.inputArr = inputArr;
        int[] ans = new int[n];
        int i,j;
        int[] min = new int[1000000];
        int max;
        for(i=0;i<n;i++){
            len = inputArr[i].length;
            max = inputArr[i][0];
            min[len-1] = inputArr[i][len-1];
            for(j=len-2;j>=0;j--){
                min[j] = Math.min(min[j+1],inputArr[i][j]);
            }
            for(j=0;j<len-1;j++){
                if(inputArr[i][j]>max) max = inputArr[i][j];
                if(max<=min[j+1]){
                    ans[i]++;
                }
            }
            ans[i]++;
        }
        long now =System.currentTimeMillis();
        System.out.println("test1 time:"+(now-start)/1000.0);
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
