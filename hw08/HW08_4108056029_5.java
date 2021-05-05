public class HW08_4108056029_5 extends Buy_Phone_v2{
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

    private static int sort(int lo,int hi){
        /*System.out.println("\n---------------------------");
        System.out.println("lo = "+lo+" hi = "+hi);
        for(int i=lo;i<=hi;i++){
            for(int j=0;j<6;j++){
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");*/
        if(hi<=lo) return lo;
        int mid = (hi+lo)>>1;
        int i_hi = sort(lo,mid);
        int j_hi = sort(mid+1,hi);
        /*System.out.println("\n--------------------------------------------------");
        //System.out.println("####################################################");
        System.out.println("lo = "+lo+" hi = "+hi);
        System.out.println("i_hi : "+i_hi+" j_hi : "+j_hi);
        System.out.println("i array... "+lo+" ~ "+i_hi);
        for(int i=lo;i<=i_hi;i++){
            for(int j=0;j<6;j++){
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("j array..."+(mid+1)+" ~ "+j_hi);
        for(int i=mid+1;i<=j_hi;i++){
            for(int j=0;j<6;j++){
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
        //System.out.println("---------------------------");*/
        return merge(lo,i_hi,mid+1,j_hi);
    }
    private static int merge(int i_low,int i_hi,int j_low,int j_hi){
        int k;
        for(k=i_low;k<=i_hi;k++){
            aux[k] = array[k];
        }
        for(k=j_low;k<=j_hi;k++){
            aux[k] = array[k];
        }
        /*for(int f=0;f<6;f++){
            System.out.print(aux[3][f]+" ");
        }
        System.out.println();
        for(int f=0;f<6;f++){
            System.out.print(aux[4][f]+" ");
        }
        System.out.println();*/
        int i=i_low,j=j_low,top = i_hi+(j_hi-j_low)+1;
        for(k=i_low;i<=i_hi||j<=j_hi;){
            //System.out.println("in for");
            if(i>i_hi){
                array[k++] = aux[j++];
                /*System.out.println("i>i_hi");
                for(int f=0;f<6;f++){
                    System.out.print(array[k-1][f]+" ");
                }
                System.out.println();*/
            }
            else if(j>j_hi){
                array[k++] = aux[i++];

                /*System.out.println("j>j_hi");
                for(int f=0;f<6;f++){
                    System.out.print(array[k-1][f]+" ");
                }
                System.out.println();*/
            }
            else{
                //System.out.println("in else");
                /*System.out.println("i: "+i);
                for(int f=0;f<6;f++){
                    System.out.print(aux[i][f]+" ");
                }
                System.out.println("\nj: "+j);
                for(int f=0;f<6;f++){
                    System.out.print(aux[j][f]+" ");
                }
                System.out.println();*/
                int ori_k = k;
                int k_plusplus = 1;

                if(aux[i][0] > aux[j][0]) {
                    if(aux[i][1] < aux[j][1]) array[k++] = aux[j++];
                    else if(aux[i][2] < aux[j][2]) array[k++] = aux[j++];
                    else if(aux[i][3] < aux[j][3]) array[k++] = aux[j++];
                    else if(aux[i][4] < aux[j][4]) array[k++] = aux[j++];
                    else if(aux[i][5] < aux[j][5]) array[k++] = aux[j++];
                    else{
                        top--;
                        //hi--;
                        j++;
                        //array[k++] = aux[i++];
                        k_plusplus=0;
                    }
                }
                else if(aux[i][0] < aux[j][0]) {

                    //System.out.println("aux[i][0] < aux[j][0]");
                    if(aux[i][1] > aux[j][1]) array[k++] = aux[i++];
                    else if(aux[i][2] > aux[j][2]) array[k++] = aux[i++];
                    else if(aux[i][3] > aux[j][3]) array[k++] = aux[i++];
                    else if(aux[i][4] > aux[j][4]) array[k++] = aux[i++];
                    else if(aux[i][5] > aux[j][5]) array[k++] = aux[i++];
                    else{
                        top--;
                        //mid--;
                        i++;
                        //array[k++] = aux[j++];
                        k_plusplus=0;
                    }
                }
                else{
                    int h;
                    for(h=1;h<6;h++){
                        if(aux[i][h]>aux[j][h]) {
                            array[k++] = aux[j++];
                            break;
                        }
                        else if(aux[i][h]<aux[j][h]){
                            array[k++] = aux[i++];
                            break;
                        }
                    }
                    if(h==6){
                        i++;
                        top--;
                    }
                }
                /*System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!! ori_k: "+ori_k);


                for(int f=0;f<6;f++){
                    System.out.print(array[ori_k][f]+" ");
                }
                System.out.println();
                System.out.println("k_plusplus = "+k_plusplus);*/
            }//////////////////////////else
        }////////////////////////k loop
        //System.out.println("---------------------------------------------");
        /*System.out.println("\ni_low : "+i_low+" i_hi : "+i_hi+" ji_low : "+j_low+" j_hi : "+j_hi);
        System.out.println("i_low~top: "+i_low+" ~ "+top);
        for(int g=i_low;g<=top;g++){
            for(int f=0;f<6;f++){
                System.out.print(array[g][f]+" ");
            }
            System.out.println();
        }
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("---------------------------------------------");*/
        return top;
    }
    public int[][] bestPhone(int[][] inputArr){
        array = inputArr;
        n = array.length;
        int top = sort(0,n-1);
        /*System.out.println("---------------------------");
        for(int i=0;i<=top;i++){
            for(int j=0;j<6;j++){
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
        System.out.println("top = "+top);*/
        int[][] exist = new int[top+1][6];
        exist[0] = array[top];
        int num=1;//ans array's size
        int win;
        int check;
        for(int i=top-1;i>=0;i--){//control array[i]
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

    public static void main(String[] args){
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
    }
}
