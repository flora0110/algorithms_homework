public class HW06_4108056029_2 extends Dessert_Desert{
    int[][] inputArr;
    int[] factor;//save all factor for each array
    int[][] A;//copy each array with key
    int[] dif;//save every key's mov
    int factor_num;//length of factor-2
    int len;//each array's length
    int temp_ans;//each array's ans
    public int[] maxBlocks(int[][] inputArr){
        int n=inputArr.length;
        this.inputArr = inputArr;
        int[] ans = new int[n];
        int i,j;

        for(i=0;i<n;i++){

            len=inputArr[i].length;
            System.out.println("array"+i+"'s len is"+len);
            //A[0] = inputArr[i];
            A = new int[len][2];
            for(j=0;j<len;j++){
                A[j][0] = inputArr[i][j];
                A[j][1] = j;
                System.out.print(A[j][0]+" ");
            }
            System.out.println();

            System.out.println("before quick_sort");
            quick_sort(0,len-1);
            System.out.println("after quick_sort");
            for(j=0;j<len;j++){

                System.out.print(A[j][0]+" ");
            }
            System.out.println();


            for(j=0;j<len;j++){
                dif[j] = A[j][1] - j;
                System.out.print(dif[j]+" ");
            }
            System.out.print("\n");

            factor_dfs(0,1);
            temp_ans=1;
            ans[i] = temp_ans;
            System.out.println("ans["+i+"]: "+ans[i]);
        }
        return ans;
    }
    public static void main(String[] args){
        HW06_4108056029_2 test = new HW06_4108056029_2();
        int[][] arr = {
                        {1, 3, 2, 5, 4, 7, 6},
                        {1, 1, 1, 1, 1, 1, 1},
                        {1, 3, 5, 7, 9},
                        {1, 2, 3},
                        {5, 4, 3, 2, 1},
                        {2, 1, 3, 2}};
        test.Factorization(84);
        System.out.println("84848484");
        test.maxBlocks(arr);
    }
    public boolean factor_dfs(int now,int product){
        //product is *factor[now]
        System.out.println("in factor_dfs now: "+now+" product: "+product);
        int product_copy=product;
        for(int i=now;i<factor_num;i+=2){
            product_copy=product*factor[i];
            System.out.println("in for now: "+now+" product: "+product);
            if(factor_dfs(i+2,product)) return true;

            for(int j=0;j<factor[i+1];j++){
                product_copy=product*factor[i];
                System.out.println("in for's for now: "+now+" product: "+product);
                if(factor_dfs(i+2,product)) return true;
            }
        }
        return check(product);
    }
    public boolean check(int block){
        int end,front,last;
        for(int i=0;i<len;i+=block){
            end=i+block*(i+1);
            front = 0;
            last = block;
            for(int j=i;j<end;j++){
                if(dif[j]<front || dif[i] >=last){
                    System.out.println("false");
                    return false;//this block is wrong
                }
                front--;
                last--;
            }
        }
        temp_ans = block;
        System.out.println("true");
        return true;
    }
    public void quick_sort(int left,int right){
        //System.out.println("!!!!!!!!!!!!!!! left: "+left+" right: "+right+" mid="+((left+right)>>1));
        int i,j,s;
        int[] temp;
        if(left<right){
            s=A[(left+right)>>1][0];
            i=left;
            j=right;
            while(i<j) {
                while(A[i][0] <= s) {
                    //System.out.println(A[i][0]+"<"+s);
                    i++;//to right
                    if(i==right){
                        break;
                    }
                }
                //System.out.println("i="+i);
                while(A[j][0] >= s) {
                    j--;// to left
                    if(j==left){
                        break;
                    }
                }
                //System.out.println("j="+j);
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
        System.out.println("in Factorization");
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
        System.out.println("end Factorization");
    }

}
