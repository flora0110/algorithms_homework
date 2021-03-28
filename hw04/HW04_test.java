public class HW04_test{
    public static void main(String[] args){
        //int A[]={5,7,8,8,1,3,4};
        //int A[]={4,5,1,3,4};
        random_num ran = new random_num(10000,2000);
        String[] A=ran.create_arr();
        // int[] A = {4,4,5,7,8,8,0,1,3,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4};
        //System.out.println("--------------------------------");

        long start =System.currentTimeMillis();
        HW04_4108056029_1 test1 = new HW04_4108056029_1();
        boolean[] ans=test1.one0k(A);
        long now =System.currentTimeMillis();
        /*for(int i=0;i<ans.length;i++){
            System.out.print(ans[i]+" ");
        }
        System.out.print("\n");*/
        System.out.println("test1 time:"+(now-start)/1000.0);

        /*start =System.currentTimeMillis();
        HW04_4108056029_2 test2 = new HW04_4108056029_2();
        boolean[] ans2=test2.one0k(A);
        now =System.currentTimeMillis();
        /*for(int i=0;i<ans2.length;i++){
            System.out.print(ans2[i]+" ");
        }
        System.out.print("\n");*/
        //System.out.println("test2 time:"+(now-start)/1000.0);*/

        start =System.currentTimeMillis();
        HW04_4108056029_3 test3 = new HW04_4108056029_3();
        boolean[] ans3=test3.one0k(A);
        now =System.currentTimeMillis();
        /*for(int i=0;i<ans3.length;i++){
            System.out.print(ans3[i]+" ");
        }
        System.out.print("\n");*/
        System.out.println("test3 time:"+(now-start)/1000.0);
    }
}
