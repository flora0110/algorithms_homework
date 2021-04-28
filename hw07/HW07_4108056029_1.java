public class HW07_4108056029_1 extends Buy_Phone{
    int n;
    int[][] ans;
    int[][] inputArr;
    public int[][] bestPhone(int[][] inputArr){
        n = inputArr.length;
        int i,j;
        this.inputArr = inputArr;
        quick_sort(0,n-1);
        int num=0,maxy;
        int[] remind = new int[n];
        for(i=0;i<n;i++){
            //System.out.println("i is "+i);
            //last = i;
            maxy = inputArr[i][1];
            for(j=i+1;j<n;j++){
                if(inputArr[j][1]>=maxy){
                    //inputArr[last][1] = -1;
                    //last = j;
                    maxy = inputArr[j][1];
                    //num--;
                    i = j;
                    //System.out.println("change i to "+i);
                }
            }
            remind[num++] = i;
        }
        ans = new int[num][2];
        for(i=0;i<num;i++){
            ans[i][0] = inputArr[remind[i]][0];
            ans[i][1] = inputArr[remind[i]][1];
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
                inputArr[i][0] = inputArr[i][0] ^ inputArr[j][0];
                inputArr[j][0] = inputArr[i][0] ^ inputArr[j][0];
                inputArr[i][0] = inputArr[i][0] ^ inputArr[j][0];
                inputArr[i][1] = inputArr[i][1] ^ inputArr[j][1];
                inputArr[j][1] = inputArr[i][1] ^ inputArr[j][1];
                inputArr[i][1] = inputArr[i][1] ^ inputArr[j][1];
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
