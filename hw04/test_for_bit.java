public class test_for_bit{
    public static void main(String[] args){
        /*String text = "Hello World!";
        System.out.println("Text: "+text);

        String binary = new BigInteger(text.getBytes()).toString(2);
        System.out.println("As binary: "+binary);

        String text2 = new String(new BigInteger(binary, 2).toByteArray());
        System.out.println("As text: "+text2);*/
        //String s="000000000000000000000000000000000000000000000000000000000000";
        String s="1111111";
        //Converting String into int using Integer.parseInt()
        int i=Integer.parseInt(s);
        //Printing value of i
        System.out.println(i);
    }
}
