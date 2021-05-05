public class HW08_4108056029_5 extends Buy_Phone_v2{
    int[][] array;
    final static public int[][] aux = new int[10000][6];
    //final static public int[][] aux = new int[10000][2];
    static int[][] ans;
    static int n;
    public int[][] bestPhone(int[][] inputArr){
        array = new int[inputArr.length][6];
        System.arraycopy(inputArr,0,array,0,inputArr.length);
        //array = inputArr;
        n = array.length;
        //this.array = array;
        sort(array);
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
        int win;
        int check;
        for(int i=n-2;i>=0;i--){//control array[i]
            check=1;
            for(int j=0;j<num;j++){//control exist[j]
                win=0;
                for(int h=1;h<6;h++){
                    if(array[i][h]>exist[j][h]){
                        win=1;
                    }
                }
                if(win==0){ check=0; break;}
            }
            if(check==1) exist[num++] = array[i];
        }
        int[][] ans = new int[num][6];
        int count = 0;
        for(int i=num-1;i>=0;i--){
            ans[i] = exist[count++];
        }
        return ans;
    }
    public static void sort(int[][] array){
        int h=1;
        while(h < n/3) h = 3*n+1;
        //System.out.println("over h = "+h);
        int[] temp = new int[6];
        while(h>=1){
            //System.out.println("h="+h);
            for(int i=h;i<n;i++){
                for(int j=i;j>=h && less(array[j],array[j-h]);j-=h){
                    temp = array[j];
                    array[j] = array[j-h];
                    array[j-h] = temp;
                }
            }
            h =h/3;
        }
        //System.out.println("over");
    }
    private static boolean less(int[] v,int[] w)
    {
        if(v[0]>w[0]){
            return false;
        }
        else if(v[0]<w[0]){
            return true;
        }
        else{
            if(v[1]>w[1]){
                return false;
            }
            else if(v[1]<w[1]){
                return true;
            }
            else{
                if(v[2]>w[2]){
                    return false;
                }
                else if(v[2]<w[2]){
                    return true;
                }
                else{
                    if(v[3]>w[3]){
                        return false;
                    }
                    else if(v[3]<w[3]){
                        return true;
                    }
                    else{
                        if(v[4]>w[4]){
                            return false;
                        }
                        else if(v[4]<w[4]){
                            return true;
                        }
                        else{
                            if(v[5]>=w[5]){
                                return false;
                            }
                            else{
                                return true;
                            }
                        }
                    }
                }
            }
        }
    }
    /*private static void sort(int[][] array,int lo,int hi){
        if(hi<=lo) return;
        int mid = (hi+lo)>>1;
        //System.out.println("lo = "+lo+" mid = "+mid+" hi = "+hi);

        sort(array,lo,mid);
        sort(array,mid+1,hi);
        merge(array,lo,mid,hi);
    }*/
    /*
    private static void merge(int[][] array,int lo,int mid,int hi){//array->x
        //System.out.println("lo = "+lo+" hi = "+hi);
        System.arraycopy(array,lo,aux,lo,hi-lo+1);
        System.out.println("aux--------------------------");
        for(int i=lo;i<=hi;i++){
            for(int j=0;j<6;j++){
                System.out.print(aux[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("-----------------------aux");
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

        }

    }*/
    /*public static void main(String[] args){
        HW08_4108056029_5 test = new HW08_4108056029_5();
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
