public class HW06_4108056029_3 extends Dessert_Desert{
    int[][] inputArr;
    public int[] maxBlocks(int[][] inputArr){
        long start =System.currentTimeMillis();

        int num_thread = 4,lgTNum=2;
        int n=inputArr.length;
        this.inputArr = inputArr;
        int[] ans = new int[n];
        Thread[] t = new Thread[num_thread];
        if(n<num_thread){
            int i,j,len,max;
            int[] min = new int[1000000];
            for(i=0;i<n;i++){
                len = inputArr[i].length;
                max = inputArr[i][0];
                min[len-1] = inputArr[i][len-1];
                for(j=len-2;j>=0;j--){
                    if(inputArr[i][j] < min[j+1]) min[j] = inputArr[i][j];
                    else min[j] = min[j+1];
                }
                for(j=0;j<len-1;j++){
                    if(inputArr[i][j]>max) max = inputArr[i][j];
                    if(max<=min[j+1]){
                        ans[i]++;
                    }
                }
            }
        }
        else{
            for(int control = 0;control<num_thread-1;control++){
                final int k=control;
                t[control] = new Thread(new Runnable(){
                    public void run(){
                        int[] min = new int[1000000];
                        int len,max;
                        final int start = (n*k) >>lgTNum;
                        final int end = (n*(k+1))>>lgTNum;
                        for(int i=start;i<end;i++){
                            len = inputArr[i].length;
                            max = inputArr[i][0];
                            min[len-1] = inputArr[i][len-1];
                            for(int j=len-2;j>=0;j--){
                                if(inputArr[i][j] < min[j+1]) min[j] = inputArr[i][j];
                                else min[j] = min[j+1];
                            }
                            for(int j=0;j<len-1;j++){
                                if(inputArr[i][j]>max) max = inputArr[i][j];
                                if(max<=min[j+1]){
                                    ans[i]++;
                                }
                            }
                            ans[i]++;
                        }
                    }
                });
                t[control].start();

            }
            t[num_thread-1] = new Thread(new Runnable(){
                public void run(){
                    int[] min = new int[1000000];
                    int a,len,max;
                    int start = (n*(num_thread-1)) >>lgTNum;
                    for(int i=start;i<n;i++){
                        a=1;
                        len = inputArr[i].length;
                        max = inputArr[i][0];
                        min[len-1] = inputArr[i][len-1];
                        for(int j=len-2;j>=0;j--){
                            if(inputArr[i][j] < min[j+1]) min[j] = inputArr[i][j];
                            else min[j] = min[j+1];
                        }
                        for(int j=0;j<len-1;j++){
                            if(inputArr[i][j]>max) max = inputArr[i][j];
                            if(max<=min[j+1]){
                                ans[i]++;
                            }
                        }
                        ans[i]++;
                    }
                }
            });
            t[num_thread-1].start();
            try{
                for(int control=0; control<num_thread; control++){
                    t[control].join();
                }
            }catch(InterruptedException e){}
        }
        long now =System.currentTimeMillis();
        System.out.println("test3 time:"+(now-start)/1000.0);
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
