public class HW06_4108056029_3 extends Dessert_Desert{
    int[][] inputArr;
    int len;//each array's length
    public int[] maxBlocks(int[][] inputArr){
        int num_thread = 8,lgTNum=3;
        int n=inputArr.length;
        this.inputArr = inputArr;
        int[] ans = new int[n];
        Thread[] t = new Thread[num_thread];
        if(n<num_thread){
            System.out.println("in");
            int i,j;
            int[] max;
            int[] min;
            int a;
            for(i=0;i<n;i++){
                a=1;
                len = inputArr[i].length;
                max = new int[len];
                min = new int[len];
                max[0] = inputArr[i][0];
                min[len-1] = inputArr[i][len-1];
                for(j=len-2;j>=0;j--){
                    min[j] = Math.min(min[j+1],inputArr[i][j]);
                }
                for(j=1;j<len;j++){
                    max[j] = Math.max(max[j-1],inputArr[i][j]);
                    if(max[j-1]<=min[j]){
                        a++;
                    }
                }
                ans[i]=a;
                System.out.println(ans[i]);
            }
        }
        else{
            System.out.println("else");
            for(int control = 0;control<num_thread;control++){
                final int k=control;
                t[control] = new Thread(new Runnable(){
                    public void run(){
                        int[] max;
                        int[] min;
                        int a;
                        final int start = (n*k) >>lgTNum;
                        final int end = (n*(k+1))>>lgTNum;
                        for(int i=start;i<end;i++){
                            a=1;
                            len = inputArr[i].length;
                            max = new int[len];
                            min = new int[len];
                            max[0] = inputArr[i][0];
                            min[len-1] = inputArr[i][len-1];
                            for(int j=len-2;j>=0;j--){
                                min[j] = Math.min(min[j+1],inputArr[i][j]);
                            }
                            for(int j=1;j<len;j++){
                                max[j] = Math.max(max[j-1],inputArr[i][j]);
                                if(max[j-1]<=min[j]){
                                    a++;
                                }
                            }
                            ans[i]=a;
                        }
                    }
                });
                t[control].start();
            }
            try{
                for(int control=0; control<num_thread; control++){
                    t[control].join();
                }
            }catch(InterruptedException e){}
        }
        for(int i=0;i<n;i++){
            System.out.print(ans[i]+" ");
        }
        System.out.println();
        return ans;
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
                        {2,5,4,1,2},
                        {1, 3, 2, 5, 4, 7, 6},
                        {1, 1, 1, 1, 1, 1, 1},
                        {1, 3, 5, 7, 9},
                        {1, 2, 3},
                        {5, 4, 3, 2, 1},
                        {2, 1, 3, 2},
                        {2,5,4,1,2},

                    };
        test.maxBlocks(arr);
    }
}
