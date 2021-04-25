public class HW07_4108056029_1 extends Buy_Phone{
    int n;
    int[][] ans;
    int[][] inputArr;
    public int[][] bestPhone(int[][] inputArr){
        n = inputArr.length;
        int i,j;
        this.inputArr = inputArr;
        shuffle();
        sort(0,n-1);
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
    public void sort(int lo,int hi){
        if(hi<=lo+9){
            insertion_sort();
            return;
        }
        int j=partition(lo,hi);
        sort(lo,j-1);
        sort(j+1,hi);
    }
    public int partition(int lo,int hi){
        int i=lo,j=hi+1;
        int[] temp = new int[2];
        while(true){
            while(inputArr[i][0] < inputArr[lo][0] || ((inputArr[i][0] == inputArr[lo][0])&&(inputArr[i][1] < inputArr[lo][1]))){
                i++;
                if(i==hi) break;
            }
            while(inputArr[lo][0] < inputArr[j][0] || ((inputArr[lo][0] == inputArr[j][0])&&(inputArr[lo][1] < inputArr[j][1]))){
                j--;
            }
            if(i>=j) break;
            temp = inputArr[i];
            inputArr[i] = inputArr[j];
            inputArr[j] = temp;
        }
        temp = inputArr[j];
        inputArr[j] = inputArr[lo];
        inputArr[lo] = temp;
        return j;
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
    public void shuffle(){
        int r;
        int[] temp = new int[2];
        for(int i=0;i<n;i++){
            r = (int)(Math.random()*i);
            temp = inputArr[i];
            inputArr[i] = inputArr[r];
            inputArr[r] = temp;
        }
    }
    public static void main(String[] args){
        HW07_4108056029_1 test = new HW07_4108056029_1();
        int[][] inputArr = {{2,4},{2,10},{5,4},{4,8},{5,5},{8,4},{10,2},{10,1},{1,1}};
        test.bestPhone(inputArr);
    }
}
