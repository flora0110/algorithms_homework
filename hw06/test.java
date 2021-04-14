public class test{
    public static void main(String[] args){
        test test = new test();
        int[][] a = new int[5][2];
        for(int i=0;i<5;i++){
            a[i][0] = i;
            a[i][1] = 100+i;
        }
        int[] temp;
        temp = a[0];
        a[0] = a[1];
        a[1] = temp;

        int[][] L = new int[5][2];
        for(int i=0;i<5;i++){
            L[i] = a[i];
        }
        a[3] = L[4];
        for(int i=0;i<5;i++){
            System.out.print(a[i][0]+" ");
        }
        System.out.println();
        for(int i=0;i<5;i++){
            System.out.print(a[i][1]+" ");
        }
        System.out.println();
        int i=0;
        for(;i<0;i++){
        }
        System.out.println("j="+i);
    }
}
