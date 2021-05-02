public class HW07_4108056029_1 extends Buy_Phone{
    int[][] inputArr;
    public int[][] bestPhone(int[][] inputArr){
        int n = inputArr.length;
        this.inputArr = inputArr;
        quick_sort(0,n-1);
        int num=n-1,maxy = inputArr[n-1][1];
        int[][] remind = new int[n][2];
        remind[num--] = inputArr[n-1];
        for(int i=n-2;i>=0;i--){
            if(inputArr[i][1]>maxy){
                remind[num--] = inputArr[i];
                maxy = inputArr[i][1];
            }
        }
        int[][] ans = new int[n-num-1][2];
        System.arraycopy(remind,num+1,ans,0,n-num-1);
        return ans;
    }
    public void quick_sort(int left,int right){
        int i,j;
        int[] temp = new int[2];
        if(left<right){
            int[] s=inputArr[(left+right)>>1];
            i=left;
            j=right;
            while(i<j) {
                while((inputArr[i][0] < s[0]) || ((inputArr[i][0] == s[0])&&(inputArr[i][1] < s[1]))) i++;//to right
                while(inputArr[j][0] > s[0] || ((inputArr[j][0] == s[0])&&(inputArr[j][1] > s[1]))) j--;// to left
                if(i >= j) break;
                temp = inputArr[i];
                inputArr[i] = inputArr[j];
                inputArr[j] = temp;
            }
            quick_sort(left,i-1);
            quick_sort(j+1,right);
        }
    }
    public static void main(String[] args){
        HW07_4108056029_1 test = new HW07_4108056029_1();
        int[][] inputArr = {{2,4},{2,10},{5,4},{4,8},{1,1},{5,5},{8,4},{10,2},{10,1}};
        int[][] ans= test.bestPhone(inputArr);
        /*for(int i=0;i<ans.length;i++){
            System.out.println(ans[i][0]+" "+ans[i][1]);
        }*/
    }
}
