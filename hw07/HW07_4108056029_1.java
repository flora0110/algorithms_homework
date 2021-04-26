public class HW07_4108056029_1 extends Buy_Phone{
    int n;
    int[][] ans;
    int[][] inputArr;
    public int[][] bestPhone(int[][] inputArr){
        n = inputArr.length;
        int i,j;
        this.inputArr = inputArr;
        quick_sort(0,n-1);
        /*for(i=0;i<n;i++){
            System.out.println(inputArr[i][0]+" "+inputArr[i][1]);
        }*/
        //int x[] = new int[n];
        int y[] = new int[n];
        for(i=0;i<n;i++){
            //x[i] = inputArr[i][0];
            y[i] = inputArr[i][1];
        }
        int num=0,maxy;
        int[] remind = new int[n];
        for(i=0;i<n;i++){
            //System.out.println("i is "+i);
            //last = i;
            //maxy = inputArr[i][1];
            maxy = y[i];
            for(j=i+1;j<n;j++){
                if(y[j]>=maxy){
                    //inputArr[last][1] = -1;
                    //last = j;
                    maxy = y[j];
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
        if(right<=left+9){
            insertion_sort();
            return;
        }
        int[] temp = new int[2];
        int[] s=inputArr[(left+right)>>1];
        int i=left,j=right;
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
    public void insertion_sort(){
        int[] temp = new int[2];
        for(int i = 0; i < n; i++){
            for (int j = i; j > 0; j--){
                if (inputArr[j][0]<inputArr[j-1][0]){
                    temp = inputArr[j];
                    inputArr[j] = inputArr[j-1];
                    inputArr[j-1] = temp;
                }
                else if(inputArr[j][0]==inputArr[j-1][0] && inputArr[j][1]<inputArr[j-1][1]){
                    temp = inputArr[j];
                    inputArr[j] = inputArr[j-1];
                    inputArr[j-1] = temp;
                }
                else break;
            }
        }
    }
    public static void main(String[] args){
        HW07_4108056029_1 test = new HW07_4108056029_1();
        //int[][] inputArr = {{2,4},{2,10},{5,4},{4,8},{1,1},{5,5},{8,4},{10,2},{10,1}};
        //int[][] inputArr = {{2,1},{5,3},{5,4},{7,2}};
        int[][] inputArr = {{0,10},{1,1},{2,3},{2,4},{3,1},{4,6},{4,8},{5,1},{6,4}};
        int[][] ans= test.bestPhone(inputArr);
        /*for(int i=0;i<ans.length;i++){
            System.out.println(ans[i][0]+" "+ans[i][1]);
        }*/
    }
}
