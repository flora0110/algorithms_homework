public class HW07_4108056029_4 extends Buy_Phone{
    int n;
    int[][] ans;
    //int[][] inputArr;
    int[] x;
    int[] y;
    public int[][] bestPhone(int[][] inputArr){
        n = inputArr.length;
        int i,j;
        //this.inputArr = inputArr;
        x = new int[n];
        y = new int[n];
        for(i=0;i<n;i++){
            x[i] = inputArr[i][0];
            y[i] = inputArr[i][1];
        }
        quick_sort(0,n-1);
        int num=0,maxy;
        int[] remind = new int[n];
        for(i=0;i<n;i++){
            //System.out.println("i is "+i);
            //last = i;
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
            ans[i][0] = x[remind[i]];
            ans[i][1] = y[remind[i]];
        }
        return ans;
    }
    public void quick_sort(int left,int right){
        int i,j;
        //int[] temp = new int[2];
        if(left<right){
            //int[] s=inputArr[(left+right)>>1];
            int mid = (left+right)>>1;
            int sx = x[mid],sy = y[mid],temp;
            i=left;
            j=right;
            while(i<j) {
                while((x[i] < sx) || ((x[i] == sx)&&(y[i] < sy))) i++;//to right
                while(x[j] > sx || ((x[j] == sx)&&(y[j] > sy))) j--;// to left
                if(i >= j) break;
                temp = x[i];
                x[i] = x[j];
                x[j] = temp;
                temp = y[i];
                y[i] = y[j];
                y[j] = temp;
            }
            quick_sort(left,i-1);
            quick_sort(j+1,right);
        }
    }
    public static void main(String[] args){
        HW07_4108056029_4 test = new HW07_4108056029_4();
        int[][] inputArr = {{2,4},{2,10},{5,4},{4,8},{1,1},{5,5},{8,4},{10,2},{10,1}};
        int[][] ans= test.bestPhone(inputArr);
        for(int i=0;i<ans.length;i++){
            System.out.println(ans[i][0]+" "+ans[i][1]);
        }
    }
}
