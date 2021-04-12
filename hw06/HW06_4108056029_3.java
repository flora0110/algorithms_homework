public class HW06_4108056029_3 extends Dessert_Desert{
    int[][] inputArr;
    int len;//each array's length
    public int[] maxBlocks(int[][] inputArr){
        int n=inputArr.length;
        this.inputArr = inputArr;
        int[] ans = new int[n];
        int i,j,k,temp;
        int[] dif;
        for(i=0;i<n;i++){
            len = inputArr[i].length;
            dif = new int[len];
            for(j=0;j<len;j++){
                dif[j]=1;
                for(k=j;k>0;k--){
                    if(inputArr[i][k]<inputArr[i][k-1]){
                        temp = inputArr[i][k];
                        inputArr[i][k] = inputArr[i][k-1];
                        inputArr[i][k-1] = temp;
                        dif[k]=j-k+2;
                        dif[k-1]=j-k+2;
                    }
                    else break;
                }
                show(dif);
            }
            for(j=0;j<len;){
                System.out.println("j= "+j);
                ans[i]++;
                j+=dif[j];
            }
            System.out.println(ans[i]);
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
        HW06_4108056029_3 test = new HW06_4108056029_3();
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
