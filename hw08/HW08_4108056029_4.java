public class HW08_4108056029_4 extends Buy_Phone_v2{
    int[][] array;
    static int[][] ans;
    final static int[][] aux = new int[10000][6];
    int n;
    private static int sort(int[][] array,int lo,int hi){
        if(hi<=lo) return lo;
        int mid = (hi+lo)>>1;
        int i_hi = sort(array,lo,mid);
        int j_hi = sort(array,mid+1,hi);
        return merge(array,lo,i_hi,mid+1,j_hi);
    }
    private static int merge(int[][] array,int i_low,int i_hi,int j_low,int j_hi){
        int k;
        for(k=i_low;k<=i_hi;k++){
            aux[k] = array[k];
        }
        for(k=j_low;k<=j_hi;k++){
            aux[k] = array[k];
        }
        //System.arraycopy(array,i_low,aux,i_low,i_hi-i_low+1);
        //System.arraycopy(array,j_low,aux,j_low,j_hi-j_low+1);
        int i=i_low,j=j_low,top = i_hi+(j_hi-j_low)+1;
        for(k=i_low;i<=i_hi||j<=j_hi;){
            if(i>i_hi){
                array[k++] = aux[j++];
            }
            else if(j>j_hi){
                array[k++] = aux[i++];
            }
            else{
                int ori_k = k;
                //int k_plusplus = 1;

                if(aux[i][0] > aux[j][0]) {
                    //if(aux[i][1] < aux[j][1]) array[k++] = aux[j++];
                    if((aux[i][1] < aux[j][1])||(((aux[i][2] < aux[j][2])|| (aux[i][3] < aux[j][3]))||((aux[i][4] < aux[j][4])||(aux[i][5] < aux[j][5])))) array[k++] = aux[j++];
                    else{
                        top--;
                        j++;
                        //k_plusplus=0;
                    }
                }
                else if(aux[i][0] < aux[j][0]) {
                    if((aux[i][1] > aux[j][1])||(((aux[i][2] > aux[j][2])|| (aux[i][3] > aux[j][3]))||((aux[i][4] > aux[j][4])||(aux[i][5] > aux[j][5])))) array[k++] = aux[i++];

                    else{
                        top--;
                        i++;

                    }
                }
                else{


                    if(aux[i][1]>aux[j][1]) {
                        array[k++] = aux[j++];
                    }
                    else if(aux[i][1]<aux[j][1]){
                        array[k++] = aux[i++];
                    }
                    else{
                        if(aux[i][2]>aux[j][2]) {
                            array[k++] = aux[j++];
                        }
                        else if(aux[i][2]<aux[j][2]){
                            array[k++] = aux[i++];
                        }
                        else{
                            if(aux[i][3]>aux[j][3]) {
                                array[k++] = aux[j++];
                            }
                            else if(aux[i][3]<aux[j][3]){
                                array[k++] = aux[i++];
                            }
                            else{
                                if(aux[i][4]>aux[j][4]) {
                                    array[k++] = aux[j++];
                                }
                                else if(aux[i][4]<aux[j][4]){
                                    array[k++] = aux[i++];
                                }
                                else{
                                    if(aux[i][5]>aux[j][5]) {
                                        array[k++] = aux[j++];
                                    }
                                    else if(aux[i][5]<aux[j][5]){
                                        array[k++] = aux[i++];
                                    }
                                    else{
                                        i++;
                                        top--;
                                    }
                                }
                            }
                        }
                    }

                }
            }//////////////////////////else
        }////////////////////////k loop
        return top;
    }
    public int[][] bestPhone(int[][] inputArr){
        array = inputArr;
        n = array.length;
        int top = sort(array,0,n-1);
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
}
