public class HW11_4108056029_3 extends GroupCounting {
    public static void main(String[] args){
        HW11_4108056029_3 test = new HW11_4108056029_3();
        //String[] A = {"CCCCC","BAB","BC","C","B","D","F","G","B","B"};
        //String[] B = {"z",     "C",  "E","D","D","E","H","H","H","qqq"};
        //String[] A = {"A","e","A","A"};
        //String[] B = {"B","f","e","g"};
        //System.out.println(test.count(A,B));
    }
    class Head {
        String str;
        int child;//num of child
        Head parent;//its parent is
        Head next_head;//same key
        public Head(String str){
            this.str = str;
            parent=null;
        }
    }
    public int count(String[] A, String[] B){
        int n=A.length;
        int cap = (1<<(int)(Math.log(n+1)/Math.log(2)+1))-1;
        //System.out.println("cap: "+cap+" n:"+n);
        Head[] hashmap = new Head[cap+1];
        int key_a,key_b;
        int node_num=0,group_min=0; //node_num-group_min==group num
		for (int i=0;i<n;i++) {
            key_a=(A[i].hashCode() & 0x7fffffff)& (cap-1);
            key_b=(B[i].hashCode() & 0x7fffffff)& (cap-1);
            //System.out.println("\nA: "+A[i]+" code: "+(A[i].hashCode() & 0x7fffffff));
            //System.out.println("B: "+B[i]+" code: "+(B[i].hashCode() & 0x7fffffff));
            //System.out.println("A :"+A[i]+" key_a = "+key_a);
            //System.out.println("B :"+B[i]+" key_b = "+key_b);
            if(hashmap[key_a]==null && hashmap[key_b]==null){
                node_num+=2;
                group_min++;
                hashmap[key_a] = new Head(A[i]);
                hashmap[key_b] = new Head(B[i]);
                hashmap[key_a].parent=hashmap[key_b];
                hashmap[key_b].child = 1;
                //System.out.println("mode 1 node_num: "+node_num+" group_min: "+group_min);
            }
            else if(hashmap[key_a]==null) {
                node_num++;
                group_min++;
                hashmap[key_a] = new Head(A[i]);

                //get head
                Head head_of_b = hashmap[key_b];
                //System.out.println("hashmap["+key_b+"]");
                //if(head_of_b.parent!=null) System.out.println("parent not null");
                while(!head_of_b.str.equals(B[i])){
                    //System.out.println("in");
                    if(head_of_b.next_head==null){
                        head_of_b.next_head = new Head(B[i]);
                        head_of_b = head_of_b.next_head;
                        node_num++;
                        break;
                    }
                    head_of_b = head_of_b.next_head;
                }
                //if(head_of_b.parent!=null) System.out.println("parent not null");
                while(head_of_b.parent!=null) {
                    //System.out.println(head_of_b.str+"'s parent is "+head_of_b.parent.str);
                    if(head_of_b.parent.parent==null){
                        head_of_b=head_of_b.parent;
                        break;
                    }
                    head_of_b.parent = head_of_b.parent.parent; //path compression
                    head_of_b=head_of_b.parent;
                    //System.out.println("com 2");
                }
                hashmap[key_a].parent = head_of_b;
                head_of_b.child++;
                //System.out.println("mode 2 node_num: "+node_num+" group_min: "+group_min);
            }
            else if(hashmap[key_b]==null) {
                node_num++;
                group_min++;
                hashmap[key_b] = new Head(B[i]);
                //System.out.println("hashmap["+key_b+"] set null");
                //get head
                Head head_of_a = hashmap[key_a];
                while(!head_of_a.str.equals(A[i])){
                    //System.out.println(head_of_a.str+" "+A[i]);
                    if(head_of_a.next_head==null){
                        //System.out.println("in");
                        head_of_a.next_head = new Head(A[i]);
                        head_of_a = head_of_a.next_head;
                        node_num++;
                        break;
                    }
                    head_of_a = head_of_a.next_head;
                }
                while(head_of_a.parent!=null) {
                    //System.out.println(head_of_a.str+"'s parent is "+head_of_a.parent.str);
                    if(head_of_a.parent.parent==null){
                        head_of_a=head_of_a.parent;
                        break;
                    }
                    head_of_a.parent=head_of_a.parent.parent;
                    //System.out.println("com 3"+head_of_a.str+"'s parent is "+head_of_a.parent.str);
                    head_of_a=head_of_a.parent;

                }
                hashmap[key_b].parent = head_of_a;
                head_of_a.child++;
                //System.out.println("mode 3 node_num: "+node_num+" group_min: "+group_min);
            }
            else{
                //get head
                Head head_of_a = hashmap[key_a];
                while(!head_of_a.str.equals(A[i])){
                    if(head_of_a.next_head==null){
                        head_of_a.next_head = new Head(A[i]);
                        head_of_a = head_of_a.next_head;
                        node_num++;
                        break;
                    }
                    head_of_a = head_of_a.next_head;
                }
                while(head_of_a.parent!=null){
                    //System.out.println(head_of_a.str+"'s parent is "+head_of_a.parent.str);
                    if(head_of_a.parent.parent==null){
                        head_of_a=head_of_a.parent;
                        break;
                    }
                    head_of_a.parent=head_of_a.parent.parent;
                    head_of_a=head_of_a.parent;
                    //System.out.println("com 4");
                }
                Head head_of_b = hashmap[key_b];
                while(!head_of_b.str.equals(B[i])){
                    if(head_of_b.next_head==null){
                        head_of_b.next_head = new Head(B[i]);
                        head_of_b = head_of_b.next_head;
                        node_num++;
                        break;
                    }
                    head_of_b = head_of_b.next_head;
                }
                while(head_of_b.parent!=null) {
                    //System.out.println(head_of_b.str+"'s parent is "+head_of_b.parent.str);
                    if(head_of_b.parent.parent==null){
                        head_of_b=head_of_b.parent;
                        break;
                    }
                    head_of_b.parent = head_of_b.parent.parent;
                    head_of_b=head_of_b.parent;
                    //System.out.println("com 4");
                }
                if(head_of_a!=head_of_b){
                    if(head_of_a.child>head_of_b.child){
                        head_of_a.child +=(head_of_b.child+1);
                        head_of_b.parent = head_of_a;
                    }
                    else{
                        head_of_b.child +=(head_of_a.child+1);
                        head_of_a.parent = head_of_b;
                    }
                    group_min++;
                }
                //System.out.println("mode 4 node_num: "+node_num+" group_min: "+group_min);
            }
		}
        return node_num-group_min;
    }
}
