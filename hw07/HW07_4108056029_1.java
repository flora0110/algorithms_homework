public class HW07_4108056029_1 extends Buy_Phone{
    int n;
    int[][] ans;
    int[][] inputArr;
    int[][] aux;
    //static int[] arr;
    //static int[] aux;
    public int[][] bestPhone(int[][] inputArr){
        n = inputArr.length;
        int i,j;
        this.inputArr = inputArr;
        this.aux = new int[n][2];
        sort(0,n,1);
        sort(0,n,0);
        /*arr = new int[n];
        aux = new int[n];*/
        /*for(i=0;i<n;i++){
            //arr[i] = inputArr[i][0];
            System.out.println(inputArr[i][0]+" "+inputArr[i][1]);
        }*/

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
    private void merge(int start, int mid, int end ,int now){
        if(inputArr[mid-1][0] <= inputArr[mid][0]) return;
        System.arraycopy(inputArr, start, aux, start, end-start);
        int i = start;
        int j = mid;
        while(i!=mid && j!=end) {//System.out.println("aux[i] "+aux[i][0]+" "+aux[i][0]+"aux[j] "+aux[j][0]+" "+aux[j][0]);
        inputArr[start++] = (aux[i][now] < aux[j][now])? aux[i++] : aux[j++];
        //System.out.println("i="+i+" j="+j);
    }
        while(i!=mid)           inputArr[start++] = aux[i++];
        while(j!=end)           inputArr[start++] = aux[j++];
    }

    private void sort(int from, int to,int now){
        if(from + 9 > to){
            Insertion(from, to, now);
            return;
        }
        int mid = (from+to) >> 1;
        sort(from, mid,now);
        sort(mid, to,now);
        merge(from, mid, to ,now);
    }

    private void Insertion(int from, int to,int now){
        for(int i=from+1; i<to; i++){
            for(int j=i-1; j>=from && (inputArr[j][now] > inputArr[j+1][now]); j--){
                inputArr[j+1][0] = inputArr[j+1][0] ^ inputArr[j][0];
                inputArr[j][0]   = inputArr[j+1][0] ^ inputArr[j][0];
                inputArr[j+1][0] = inputArr[j+1][0] ^ inputArr[j][0];
                inputArr[j+1][1] = inputArr[j+1][1] ^ inputArr[j][1];
                inputArr[j][1]   = inputArr[j+1][1] ^ inputArr[j][1];
                inputArr[j+1][1] = inputArr[j+1][1] ^ inputArr[j][1];
            }
        }
    }
    public static void main(String[] args){
        HW07_4108056029_1 test = new HW07_4108056029_1();
        //int[][] inputArr = {{2,4},{2,10},{5,4},{4,8},{1,1},{5,5},{8,4},{10,2},{10,1}};
        //int[][] inputArr = {{2,1},{5,3},{5,4},{7,2}};
        int[][] inputArr = {{2,4},{3,1},{0,10},{1,1},{2,3},{4,8},{5,1},{6,4},{4,6}};
        int[][] ans= test.bestPhone(inputArr);
        /*System.out.println("ans");
        for(int i=0;i<ans.length;i++){
            System.out.println(ans[i][0]+" "+ans[i][1]);
        }*/
    }
}
