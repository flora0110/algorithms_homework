public class HW06_4108056029_2 extends Dessert_Desert{
    int[][] inputArr;
    int[] factor;
    int[][] A;
    int[] dif;
    int factor_num;
    int len;
    int temp_ans;
    public int[] maxBlocks(int[][] inputArr){
        int n=inputArr.length;
        this.inputArr = inputArr;
        int[] ans = new int[n];
        int i,j;
        for(i=0;i<n;i++){
            len=inputArr[i].length;
            A[0] = inputArr[i];
            A[1] = new int[len];
            for(j=0;j<len;j++){
                A[1][j] = j;
            }
            quick_sort(0,len);
            for(j=0;j<len;j++){
                dif[j] = A[1][j] - j;
            }
            temp_ans=0;
            if(factor_dfs(0,1)){
                ans[i] = temp_ans;
            }
            else ans[i] = 1;
            System.out.println("ans["+i+"]: "+ans[i]);
        }
        return ans;
    }
    public static void main(String[] args){
        HW06_4108056029_2 test = new HW06_4108056029_2();
        int[][] arr = {{1, 1, 1, 1, 1, 1, 1},
                        {1, 3, 5, 7, 9},
                        {1, 2, 3},
                        {5, 4, 3, 2, 1},
                        {2, 1, 3, 2}};
        test.Factorization(84);
        test.maxBlocks(arr);
    }
    public boolean factor_dfs(int now,int product){
        int product_copy=product;
        for(int i=now+2;i<factor_num;i+=2){
            product_copy*=factor[i];
            if(factor_dfs(i,product)) return true;

            for(int j=0;j<factor[i+1];j++){
                product_copy*=factor[i];
                if(factor_dfs(i,product)) return true;
            }
        }
        return check(product*factor[now]);
    }
    public boolean check(int block){
        int end,front,last;
        for(int i=0;i<len;i+=block){
            end=i+block*(i+1);
            front = 0;
            last = block;
            for(int j=i;j<end;j++){
                if(dif[j]<front || dif[i] >=last){
                    return false;//this block is wrong
                }
                front--;
                last--;
            }
        }
        temp_ans = block;
        return true;
    }
    public void quick_sort(int left,int right){
        int i,j,s;
        int[] temp;
        if(left<right){
            s=A[0][(left+right)>>1];
            i=left;
            j=right;
            while(i<j) {
                while(A[0][i] < s) i++;//to right
                while(A[0][j] > s) j--;// to left
                if(i >= j) break;
                temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
            quick_sort(left,i-1);
            quick_sort(j+1,right);
        }
    }
    public void Factorization(int n){
        factor = new int[2*(int)Math.sqrt(n)];
        int c,num=0,at=0;
        for(int i=2;i<=n;i++){
            c=0;
            while(n%i==0){
                //System.out.println(i+" "+n/i);
                n/=i;
                c++;
                System.out.println(i);
            }
            if(c!=0){
                factor[at++] = i;
                factor[at++] = c;
            }
        }
        factor_num = factor.length-2;
    }
}
