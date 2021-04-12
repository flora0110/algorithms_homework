public class HW06_4108056029_1 extends Dessert_Desert{
    int[][] inputArr;
    int now;
    int[] block_count;
    int aux[];
    int[] factor;
    int[] use_factor;
    public int[] maxBlocks(int[][] inputArr){
        int n=inputArr.length;
        this.inputArr = inputArr;
        int[] ans = new int[n];
        int i,j,len;
        for(i=0;i<n;i++){
            now = i;
            len = inputArr[i].length;
            block_count = new int[len+1];
            aux = new int[len];
            Sort(0,len-1);
            for(j=1;j<=len;j++){
                if(block_count[j]==0)   ans[i] = len-j;
            }
            System.out.println(ans[i]);
        }
        return ans;
    }
    public static void main(String[] args){
        HW06_4108056029_1 test = new HW06_4108056029_1();
        int[][] arr = {{1, 1, 1, 1, 1, 1, 1},
                        {1, 3, 5, 7, 9},
                        {1, 2, 3},
                        {5, 4, 3, 2, 1},
                        {2, 1, 3, 2}};
        //test.maxBlocks(arr);
        test.Factorization(84);
    }
    public void Sort(int start,int end){
        if(start<end) {
            int mid = (start+end)>>1;
            Sort(start,mid);
            Sort(mid+1,end);
            int leftcount = mid-start+1,rightcount = end-mid;
            if(leftcount!=rightcount){
                block_count[leftcount] = 1;
                if(rightcount!=0)   block_count[rightcount] = 1;
            }
            if(inputArr[now][mid+1]<inputArr[now][mid])   block_count[rightcount] = 1;//block in this len cant be a block
            else return;
            Merge(start,mid,end);
        }
    }
    public void Merge(int start,int mid,int end){
        int k;
        for(k=start;k<=end;k++){
            aux[k]=inputArr[now][k];
        }
        int index = start,i=start,j=mid+1;
        for(k=start;k<=end;k++){
            if(k>mid)   inputArr[now][k] = aux[i++];
            else if(k>end)  inputArr[now][k] = aux[j++];
            else if(aux[i]<aux[j])  inputArr[now][k] = aux[i++];
            else    inputArr[now][k] = aux[j++];
        }
    }
    public void Factorization(int n){
        factor = new int[2*(int)Math.sqrt(n)];
        int c;
        for(int i=2;i<=n;i++){
            c=0;
            while(n%i==0){
                //System.out.println(i+" "+n/i);
                n/=i;
                c++;
                factor[c] = n;
                System.out.println(i);
            }
        }
        use_factor = new int[c];
    }
}
