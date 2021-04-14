public class HW06_4108056029_2 extends Dessert_Desert{
    int[][] inputArr;
    int len;//each array's length
    public int[] maxBlocks(int[][] inputArr){
        long start =System.currentTimeMillis();
        int n=inputArr.length;
        this.inputArr = inputArr;
        int[] ans = new int[n];
        int i,j,end,max;
        int[] min = new int[1000000];
        int[] mark = new int[1000000];
        for(i=0;i<n;i++){
            len = inputArr[i].length;
            max = inputArr[i][0];
            min[len-1] = inputArr[i][len-1];
            mark[len-1] = 0;
            for(j=len-2;j>=0;j--){
                if(inputArr[i][j] < min[j+1]) {
                    min[j] = inputArr[i][j];
                    mark[j] = 0;
                }
                else {
                    min[j] = min[j+1];
                    mark[j] = mark[j+1]+1;
                }
            }
            for(j=0;j<len-1;){
                //System.out.println("j="+j);
                if(inputArr[i][j]>max) max = inputArr[i][j];
                if(max<=min[j+1]){
                    ans[i]++;
                    j++;
                }
                else{
                    end=j+mark[j];
                    //System.out.println("else j="+j);
                    //System.out.println("k+1="+(k+1)+" k+mark[k]"+(k+mark[k]));
                    for(j=j+1;j<end;j++){
                        //System.out.println("in");
                        if(inputArr[i][j]>max) max = inputArr[i][j];
                    }
                    //System.out.println("after j="+j);
                }
            }
            ans[i]++;

            //show(min,len);
            //show(mark,len);

            //System.out.println(ans[i]);
        }
        long now =System.currentTimeMillis();
        System.out.println("test2 time:"+(now-start)/1000.0);
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
