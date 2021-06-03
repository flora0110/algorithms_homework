public class HW10_4108056029_1 extends SortingArray{
    static public int[] aux = new int[20010];
    public int[] sorting(int[] A){
        sort(A,0,A.length-1);
        return A;
    }
    private static void sort(int[] array,int lo,int hi){
        if(hi<=lo) return;
        int mid = (hi+lo)>>1;
        sort(array,lo,mid);
        sort(array,mid+1,hi);
        if(array[mid]<=array[mid+1]) return;
        System.arraycopy(array,lo,aux,lo,hi-lo+1);
        int i=lo,j=mid+1;
        for(int k=lo;k<=hi;k++){
            if(i>mid){
                array[k] = aux[j++];
            }
            else if(j>hi){
                array[k] = aux[i++];
            }
            else if(aux[i]>aux[j]) {
                array[k] = aux[j++];
            }
            else{
              array[k] = aux[i++];
            }
        }
    }
}
