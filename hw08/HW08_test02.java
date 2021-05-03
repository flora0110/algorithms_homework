public class HW08_test02{
    class node{
        int n;
        node next;
        public node(int n,node next){
            this.n = n;
            this.next = next;
        }
    }
    public void fun(){
        node[] array = new node[3];
        array[0] = new node(0,null);
        node new_node = new node(1,null);
        System.out.println(new_node.n);
        array[0].next = new_node;
        //System.out.println(array[0].next.n);
    }
    public static void main(String[] args){
        HW08_test02 test = new HW08_test02();
        test.fun();
    }
}
