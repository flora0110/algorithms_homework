public class HW03_test{
    public static void main(String[] args){
        //int A[]={5,7,8,8,1,3,4};
        //int A[]={4,5,1,3,4};
        random_num ran = new random_num(100000,1000000);
        int[] A=ran.create_arr();
        // int[] A = {4,4,5,7,8,8,0,1,3,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4};
        //System.out.println("--------------------------------");

        long start =System.currentTimeMillis();
        HW03_4108056029_2 test2 = new HW03_4108056029_2();
        System.out.println(test2.H_Finding(A));
        long now =System.currentTimeMillis();
        System.out.println("test2 time:"+(now-start)/1000.0);

        start =System.currentTimeMillis();
        HW03_4108056029_1 test01 = new HW03_4108056029_1();
        System.out.println(test01.H_Finding(A));
        now =System.currentTimeMillis();
        System.out.println("test1 time:"+(now-start)/1000.0);

        start =System.currentTimeMillis();
        HW03_4108056029_3 test3 = new HW03_4108056029_3();
        System.out.println(test3.H_Finding(A));
        now =System.currentTimeMillis();
        System.out.println("test3 time:"+(now-start)/1000.0);

        start =System.currentTimeMillis();
        HW03_4108056029_4 test4 = new HW03_4108056029_4();
        System.out.println(test4.H_Finding(A));
        now =System.currentTimeMillis();
        System.out.println("test4 time:"+(now-start)/1000.0);

        start =System.currentTimeMillis();
        HW03_4108056029_1 test = new HW03_4108056029_1();
        System.out.println(test.H_Finding(A));
        now =System.currentTimeMillis();
        System.out.println("test1 time:"+(now-start)/1000.0);

        start =System.currentTimeMillis();
        HW03_4108056029_5 test5 = new HW03_4108056029_5();
        System.out.println(test5.H_Finding(A));
        now =System.currentTimeMillis();
        System.out.println("test5 time:"+(now-start)/1000.0);


        start =System.currentTimeMillis();
        HW03_4108056029_1 test6 = new HW03_4108056029_1();
        System.out.println(test6.H_Finding(A));
        now =System.currentTimeMillis();
        System.out.println("test1 time:"+(now-start)/1000.0);

        start =System.currentTimeMillis();
        HW03_4108056029_5 test7 = new HW03_4108056029_5();
        System.out.println(test7.H_Finding(A));
        now =System.currentTimeMillis();
        System.out.println("time:"+(now-start)/1000.0);

        start =System.currentTimeMillis();
        HW03_4108056029_1 test8 = new HW03_4108056029_1();
        System.out.println(test8.H_Finding(A));
        now =System.currentTimeMillis();
        System.out.println("time:"+(now-start)/1000.0);

    }
}
