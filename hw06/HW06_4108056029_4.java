public class HW06_4108056029_4 extends Dessert_Desert{
    int[][] inputArr;
    int len;//each array's length
    int[][] A,check;
    public int[] maxBlocks(int[][] inputArr){
        int n=inputArr.length;
        this.inputArr = inputArr;
        int[] ans = new int[n];
        int i,j,k,temp,c=0,new_blen;
        int[] dif;
        for(i=0;i<n;i++){
            show(inputArr[i]);
            len = inputArr[i].length;
            dif = new int[len];
            A = new int[len][2];
            check = new int[len][2];
            for(j=0;j<len;j++){
                A[j][0] = inputArr[i][j];
                A[j][1] = j;
            }
            sort(0,len-1);
            show_2d(A);
            for(j=0;j<len;j++){
                dif[j] = A[j][1] - j;
            }
            System.out.println("dif: ");
            show(dif);
            for(j=0;j<len;j++){
                if(dif[j]>check[j][0]){
                    new_blen = dif[j]+check[j][1];
                    for(k=0;k<dif[j];k++){
                        check[j+k][0]=new_blen;
                        check[j+k][1]=k+check[j][1];
                    }
                }
                if(check[j][1] == check[j][0]) ans[i]++;
            }
            System.out.println("ans: "+ans[i]);
        }
        return ans;
    }
    public static void main(String[] args){
        HW06_4108056029_4 test = new HW06_4108056029_4();
        int[][] arr = {
                        {4,3,1,5,2},
                        {1, 3, 2, 5, 4, 7, 6},
                        {1, 1, 1, 1, 1, 1, 1},
                        {1, 3, 5, 7, 9},
                        {1, 2, 3},
                        {5, 4, 3, 2, 1},
                        {2, 1, 3, 2}};
        test.show(arr[0]);
        test.show(arr[1]);
        test.maxBlocks(arr);
    }
    /*public void sort(int lo,int hi){
        if(hi<=lo) return;
        int j=partition(lo,hi);
        sort(lo,j-1);
        sort(j+1,hi);
    }
    public int partition(int lo,int hi){
        int i=lo,j=hi+1;
        int[] temp;
        while(true){
            while(A[++i][0]<A[lo][0]){
                if(i==hi) break;

            }
            while(A[lo][0]<A[--j][0]){
                if(j==lo) break;
            }
            if(i>=j) break;
            temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }

        temp = A[lo];
        A[lo] = A[j];
        A[j] = temp;
        return j;
    }*/
    public void show(int[] dif){
        //System.out.println();
        for(int i=0;i<dif.length;i++){
            System.out.print(dif[i]+" ");
        }
        System.out.println();
    }
    public void show_2d(int[][] a){
        //System.out.println();
        for(int i=0;i<a.length;i++){
            System.out.print(a[i][0]+" ");
        }
        System.out.println();
        for(int i=0;i<a.length;i++){
            System.out.print(a[i][1]+" ");
        }
        System.out.println();
    }
    void merge(int l, int m, int r){
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[][] = new int[n1][2];
        int R[][] = new int[n2][2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = A[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = A[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i][0] <= R[j][0]) {
                A[k] = L[i];
                i++;
          }
            else {
                A[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            A[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            A[k] = R[j];
            j++;
            k++;
        }
    }
    public void sort(int l, int r){
        if (l < r) {
            // Find the middle point
            int m =l+ ((r-l)>>1);
            //System.out.println("l,m,r "+l+" "+m+" "+r);
            // Sort first and second halves
            sort(l, m);
            sort(m + 1, r);

            // Merge the sorted halves
            merge(l, m, r);
        }
    }
}
