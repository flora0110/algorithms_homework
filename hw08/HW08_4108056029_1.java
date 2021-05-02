public class HW08_4108056029_1 extends Buy_Phone_v2{
    static int[][] aux = new int[20000][16];//20000??
    static int[] auxmark = new int[16];
    public int[][] bestPhone(int[][] inputArr){
        int n = inputArr.length;
        int len = inputArr[0].length;
        int[] mark = new int[n];
        sort(inputArr,mark,0,n-1,0);
        int[][] loser = new int[n][n];
        for(int i=0;i<n;i++){
            mark[i] = i;
        }
        for(int i=1;i<len;i++){
            sort(inputArr,mark,0,n-1,i);
            for(int j=0;j<n;j++){
                for(int k=j;k>=0;k--){
                    loser[mark[j]][mark[k]] = 1;
                }
            }
        }
        sort(inputArr,mark,0,n-1,0);
        for(int j=0;j<n;j++){
            for(int k=j;k>=0;k--){
                loser[mark[j]][mark[k]] = 1;
            }
        }
        int important,count=0;
        //System.out.println("ans");
        for(int k=0;k<n;k++){
            important = 1;
            for(int j=0;j<n;j++){
                //System.out.print(loser[k][j]+" ");
                if(loser[k][j]==0) important = 0;
            }
            //System.out.println();
            if(important==1){
                count++;
                /*for(int j=0;j<len;j++){
                    System.out.print(inputArr[k][j]+" ");
                }
                System.out.println();*/
            }
            else mark[k]=-1;
        }
        /*for(int i=0;i<n;i++){
            System.out.print(mark[i]+" ");
        }
        System.out.println();*/
        int[][] ans = new int[count][len];
        //System.out.println("count: "+count);
        count=0;
        for(int i=0;i<n;i++){
            if(mark[i] > -1){
                //System.out.println("ansi= "+i);
                for(int j=0;j<len;j++){
                    ans[count][j]  = inputArr[i][j];
                }
                /*for(int j=0;j<len;j++){
                    System.out.print(ans[count][j]+" ");
                }
                System.out.println();*/
                count++;
            }
        }
        /*sort(inputArr,0,n-1,1);
        System.out.println("\nmajor 1\n--------------------");
        for(int i=0;i<n;i++){
            for(int j=0;j<len;j++){
                System.out.print(inputArr[i][j]+" ");
            }
            System.out.println();
        }

        sort(inputArr,0,n-1,2);
        System.out.println("\nmajor 2\n--------------------");
        for(int i=0;i<n;i++){
            for(int j=0;j<len;j++){
                System.out.print(inputArr[i][j]+" ");
            }
            System.out.println();
        }

        sort(inputArr,0,n-1,3);
        System.out.println("\nmajor 3\n--------------------");
        for(int i=0;i<n;i++){
            for(int j=0;j<len;j++){
                System.out.print(inputArr[i][j]+" ");
            }
            System.out.println();
        }*/

        return ans;
    }
    public static void sort(int[][] inputArr,int[] mark,int lo,int hi,int d){//inputArr->x
        if(hi<=lo) return;
        int [] count = new int[12];
        int i;
        for(i=lo;i<=hi;i++){
            count[inputArr[i][d]+2]++;
        }
        for(i=1;i<11;i++){
            count[i+1] += count[i];
        }
        for(i=lo;i<=hi;i++){
            aux[count[inputArr[i][d]+1]] = inputArr[i];
            auxmark[count[inputArr[i][d]+1]++] = mark[i];
        }
        for(i=lo;i<=hi;i++){
            inputArr[i] = aux[i-lo];
            mark[i] = auxmark[i-lo];
        }//System.arraycopy?
        /*for(i=0;i<10;i++){
            System.out.print(count[i]+" ");
        }
        System.out.print("\n");*/
        for(i=0;i<10;i++){
            //System.out.println("lo: "+(lo+count[i])+" hi: "+(lo+count[i+1]-1));
            if(d==3) d=-1;
            sort(inputArr,mark,lo+count[i],lo+count[i+1]-1,d+1);
        }
    }
    public static void main(String[] args){
        HW08_4108056029_1 test = new HW08_4108056029_1();
        //int[][] inputArr = {{8,7,7,4,2,1},{2,4,4,6,2,1},{4,0,5,1,3,2},{5,2,4,3,7,3},{7,5,6,9,8,9}};
        int[][] inputArr = {{1,0,4,1},{1,0,4,2},{1,1,2,3},{1,6,9,1},{1,1,4,1},{1,5,5,3},{1,8,7,5},{1,7,6,0}
            ,{2,0,7,0},{2,5,3,1},{2,5,2,3},{2,2,3,2},{2,0,4,1},{2,2,0,3},{2,1,2,3},{2,4,6,0}
    };
        //int[][] inputArr = {{5,3,1},{5,2,5},{4,6,0},{2,3,2},{2,0,3}};
        int[][] ans= test.bestPhone(inputArr);
        /*System.out.println("ans");
        for(int i=0;i<ans.length;i++){
            for(int j=0;j<ans[0].length;j++){
                System.out.print(ans[i][j]+" ");
            }
            System.out.println();
        }*/
    }
}
