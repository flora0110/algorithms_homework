public class HW07_4108056029_2 extends Buy_Phone{
    int n;
    int[][] ans;
    int[][] inputArr;
    public int[][] bestPhone(int[][] inputArr){
        n = inputArr.length;
        int i,j;
        this.inputArr = inputArr;
        quick_sort(0,n-1);
        int num=n,last,lasty;
        for(i=0;i<n && inputArr[i][0]>-1;i++){
            last = i;
            lasty = inputArr[i][1];
            for(j=i+1;j<n && lasty>-1;j++){
                if(inputArr[j][1]>=lasty){
                    inputArr[last][1] = -1;
                    last = j;
                    lasty = inputArr[j][1];
                    num--;
                }
            }
        }
        ans = new int[num][2];
        num=0;
        for(i=0;i<n;i++){
            if(inputArr[i][1]!=-1){
                ans[num][0] = inputArr[i][0];
                ans[num++][1] = inputArr[i][1];
            }
        }
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
        HW07_4108056029_2 test = new HW07_4108056029_2();
        int[][] inputArr = {{2,4},{2,10},{5,4},{4,8},{1,1},{5,5},{8,4},{10,2},{10,1}};
        int[][] ans= test.bestPhone(inputArr);
        for(int i=0;i<ans.length;i++){
            System.out.println(ans[i][0]+" "+ans[i][1]);
        }
    }
}
