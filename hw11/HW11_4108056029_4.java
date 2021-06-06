public class HW11_4108056029_4 extends GroupCounting {
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
        Head[] hashmap = new Head[cap+1];
        int key_a,key_b;
        int node_num=0,group_min=0; //node_num-group_min==group num
		for (int i=0;i<n;i++) {
            key_a=(A[i].hashCode() & 0x7fffffff)& (cap-1);
            key_b=(B[i].hashCode() & 0x7fffffff)& (cap-1);
            if(hashmap[key_a]==null && hashmap[key_b]==null){//both not in max
                node_num+=2;//num of node +2
                group_min++;//two node(two group) link -> num of group -1
                hashmap[key_a] = new Head(A[i]);
                hashmap[key_b] = new Head(B[i]);
                hashmap[key_a].parent=hashmap[key_b];//link a to b
                hashmap[key_b].child = 1;//b has one child
            }
            else if(hashmap[key_a]==null) {//A[i] is not in map
                node_num++;//num of node +1
                group_min++;//link a node to a group == link two group -> num of group-1
                hashmap[key_a] = new Head(A[i]);

                //get b's head's root
                Head head_of_b = hashmap[key_b];
                //find b's Head
                while(!head_of_b.str.equals(B[i])){
                    if(head_of_b.next_head==null){//head_of_b now is point at tail
                        head_of_b.next_head = new Head(B[i]);
                        head_of_b = head_of_b.next_head;//point to right head of B[i]
                        node_num++;//B[i] is not in map ,num of node +1
                        break;
                    }
                    head_of_b = head_of_b.next_head;
                }
                if(head_of_b.parent!=null){//head_of_b has parent == head_of_b is not root
                    while(head_of_b.parent.parent!=null) {//head_of_b.parent is root
                        head_of_b.parent = head_of_b.parent.parent; //path compression
                        head_of_b=head_of_b.parent;
                    }
                    head_of_b = head_of_b.parent;
                }
                hashmap[key_a].parent = head_of_b;
                head_of_b.child++;
            }
            else if(hashmap[key_b]==null) {//B[i] is not in map
                node_num++;
                group_min++;
                hashmap[key_b] = new Head(B[i]);

                //get A[i]'s head 's root
                Head head_of_a = hashmap[key_a];
                //get A[i]'s head
                while(!head_of_a.str.equals(A[i])){
                    if(head_of_a.next_head==null){//head_of_a is already point at tail
                        head_of_a.next_head = new Head(A[i]);
                        head_of_a = head_of_a.next_head;//point to the right head
                        node_num++;
                        break;
                    }
                    head_of_a = head_of_a.next_head;
                }
                if(head_of_a.parent!=null){//head_of_a has parent == head_of_a is not root
                    while(head_of_a.parent.parent!=null) {//head_of_a.parent is root
                        head_of_a.parent = head_of_a.parent.parent; //path compression
                        head_of_a=head_of_a.parent;
                    }
                    head_of_a = head_of_a.parent;
                }
                hashmap[key_b].parent = head_of_a;//link B[i] to A[i]
                head_of_a.child++;
            }
            else{
                //get A[i]'s head 's root
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
                if(head_of_a.parent!=null){//head_of_a has parent == head_of_a is not root
                    while(head_of_a.parent.parent!=null) {//head_of_a.parent is root
                        head_of_a.parent = head_of_a.parent.parent; //path compression
                        head_of_a=head_of_a.parent;
                    }
                    head_of_a = head_of_a.parent;
                }
                //get B[i]'s head 's root
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
                if(head_of_b.parent!=null){//head_of_b has parent == head_of_b is not root
                    while(head_of_b.parent.parent!=null) {//head_of_b.parent is root
                        head_of_b.parent = head_of_b.parent.parent; //path compression
                        head_of_b=head_of_b.parent;
                    }
                    head_of_b = head_of_b.parent;
                }

                if(head_of_a!=head_of_b){//not same group
                    if(head_of_a.child>head_of_b.child){//weighted
                        head_of_a.child +=(head_of_b.child+1);//B[i]'s child + B[i] itself
                        head_of_b.parent = head_of_a;//link B[i] to A[i]
                    }
                    else{
                        head_of_b.child +=(head_of_a.child+1);
                        head_of_a.parent = head_of_b;
                    }
                    group_min++;//link two group -> num of group-1
                }
            }
		}
        return node_num-group_min;
    }
}
