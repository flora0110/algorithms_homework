public class HW08_4108056029_3 extends Buy_Phone_v2{
    static int[][] array;
    final static public int[] array1 = new int[10000];
    final static public int[] array2 = new int[10000];
    final static public int[] array3 = new int[10000];
    final static public int[] array4 = new int[10000];
    final static public int[] array5 = new int[10000];
    final static public int[][] aux = new int[10000][6];
    //final static public int[][] aux = new int[10000][2];
    static int[][] ans;
    int n;
    public int[][] bestPhone(int[][] inputArr){
        //array = new int[inputArr.length][6];
        //System.arraycopy(inputArr,0,array,0,inputArr.length);
        array = inputArr;
        n = array.length;
        //this.array = array;
        sort(0,n-1);
        /*System.out.println("---------------------------");
        for(int i=0;i<n;i++){
            for(int j=0;j<6;j++){
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");*/
        int[][] exist = new int[n][6];
        exist[0] = array[n-1];
        int num=1;//ans array's size
        int replace = 0;
        //int check;
        array1[0] = array[n-1][1];
        array2[0] = array[n-1][2];
        array3[0] = array[n-1][3];
        array4[0] = array[n-1][4];
        array5[0] = array[n-1][5];

        for(int i=n-2;i>=0;i--){//control array[i]
            replace =0;
            for(int j=0;j<num;j++){//control exist[j]
                if(array[i][1]>array1[j]){
                }
                else if(array[i][2]>array2[j]){
                }
                else if(array[i][3]>array3[j]){
                }
                else if(array[i][4]>array4[j]){
                }
                else if(array[i][5]>array5[j]){
                }
                else{
                    replace=1;
                    break;
                }
            }
            if(replace<=0){
                exist[num] = array[i];
                array1[num] = array[i][1];
                array2[num] = array[i][2];
                array3[num] = array[i][3];
                array4[num] = array[i][4];
                array5[num++] = array[i][5];
            }
        }
        int[][] ans = new int[num][6];
        int count = 0;
        for(int i=num-1;i>=0;i--){
            ans[i] = exist[count++];
        }
        return ans;
    }
    private static void sort(int lo,int hi){
        if(hi<=lo) return;
        int mid = (hi+lo)>>1;
        //System.out.println("lo = "+lo+" mid = "+mid+" hi = "+hi);

        sort(lo,mid);
        sort(mid+1,hi);
        merge(lo,mid,hi);
    }
    private static void merge(int lo,int mid,int hi){//array->x
        //System.out.println("lo = "+lo+" hi = "+hi);
        System.arraycopy(array,lo,aux,lo,hi-lo+1);
        /*System.out.println("aux--------------------------");
        for(int i=lo;i<=hi;i++){
            for(int j=0;j<6;j++){
                System.out.print(aux[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("-----------------------aux");*/
        int i=lo,j=mid+1;
        for(int k=lo;k<=hi;k++){
            if(i>mid){
                array[k] = aux[j++];
            }
            else if(j>hi){
                array[k] = aux[i++];
            }
            else{
                for(int h=0;h<6;h++){
                    //System.out.println("h = "+h);
                    //System.out.print("aux["+i+"]["+h+"] : "+aux[i][h]);
                    //System.out.println("v.s. aux["+j+"]["+h+"] : "+aux[j][h]);

                    if(aux[i][h]>aux[j][h]) {
                        array[k] = aux[j++];
                        break;
                    }
                    else if(aux[i][h]<aux[j][h]){
                        array[k] = aux[i++];
                        break;
                    }
                }
            }
            /*System.out.print("array["+k+"]  : ");
            for(int f=0;f<6;f++){
                System.out.print(array[k][f]+" ");
            }
            System.out.println();*/
        }
        /*System.out.println("array--------------------------");
        for(i=lo;i<=hi;i++){
            for(j=0;j<6;j++){
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("-----------------------arr");*/
    }
    /*public static void main(String[] args){
        HW08_4108056029_3 test = new HW08_4108056029_3();
        //int[][] inputArr = {{2,3,5,0,1,2,3},{1,7,3,2,5,0,1},{3,0,0,2,3,4,7},{0,2,3,4,5,6,1},{2,2,5,6,7,1,0}};
        int[][] inputArr = {{8,7,7,4,2,1},{2,4,9,2,2,1},{4,0,5,1,3,2},{5,2,4,3,7,3},{7,5,6,9,8,9}};
        int[][] ans = test.bestPhone(inputArr);
        for(int i=0;i<ans.length;i++){
            for(int j=0;j<6;j++){
                System.out.print(ans[i][j]+" ");
            }
            System.out.println();
        }
    }*/
}
