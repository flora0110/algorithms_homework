public class HW11_4108056029_5 extends GroupCounting {
    class Head {
        String str;
        //int child;//num of child
        int index;
        //Head parent;//its parent is
        Head next_head;//same key
        public Head(String str){
            this.str = str;
            //parent=null;
        }
    }
    public int count(String[] A, String[] B){
        int n=A.length;
        int cap = (1<<(int)(Math.log(n+1)/Math.log(2)+1))-1;
        Head[] hashmap = new Head[cap<<1+1];
        int[] parent = new int[cap<<1+1];//-1~-n: has n chile, 0: not exsit, 1~n: n's child
        int crush_index=cap+2;

        int key_a,key_b;
        int node_num=0,group_min=0; //node_num-group_min==group num
		for (int i=0;i<n;i++) {
            key_a=(A[i].hashCode() & 0x7fffffff)& (cap-1)+1;
            key_b=(B[i].hashCode() & 0x7fffffff)& (cap-1)+1;
            if(hashmap[key_a]==null && hashmap[key_b]==null){//both not in max
                node_num+=2;//num of node +2
                group_min++;//two node(two group) link -> num of group -1
                hashmap[key_a] = new Head(A[i]);
                hashmap[key_b] = new Head(B[i]);

                //hashmap[key_a].parent=hashmap[key_b];//link a to b
                parent[key_a] = key_b;
                parent[key_b] = -1;
                //hashmap[key_b].child = 1;//b has one child
            }
            else if(hashmap[key_a]==null) {//A[i] is not in map
                node_num++;//num of node +1
                group_min++;//link a node to a group == link two group -> num of group-1
                hashmap[key_a] = new Head(A[i]);

                //get b's head's root
                Head head_of_b = hashmap[key_b];
                //find b's Head's index
                int index_of_b;
                if(head_of_b.str.equals(B[i])) index_of_b=key_b;
                else{
                    while(true){
                        if(head_of_b.next_head==null){//head_of_b now is point at tail
                            head_of_b.next_head = new Head(B[i]);
                            head_of_b = head_of_b.next_head;//point to right head of B[i]
                            index_of_b=crush_index;
                            head_of_b.index=crush_index++;
                            node_num++;//B[i] is not in map ,num of node +1
                            break;
                        }
                        head_of_b = head_of_b.next_head;
                        if(head_of_b.str.equals(B[i])) {
                            index_of_b=head_of_b.index;
                            break;
                        }
                    }
                }
                while(parent[index_of_b]>0){//index_of_b has parent == head_of_b is not root
                    parent[index_of_b] = parent[parent[index_of_b]];//path compression
                    //index_of_b = parent[index_of_b];
                }
                parent[key_a] = index_of_b;
                parent[index_of_b]--;
                //hashmap[key_a].parent = head_of_b;
                //head_of_b.child++;
            }
            else if(hashmap[key_b]==null) {//B[i] is not in map
                node_num++;
                group_min++;
                hashmap[key_b] = new Head(B[i]);

                //get a's head's root
                Head head_of_a = hashmap[key_a];
                //find a's Head's index
                int index_of_a;
                if(head_of_a.str.equals(A[i])) index_of_a=key_a;
                else{
                    while(true){
                        if(head_of_a.next_head==null){//head_of_a now is point at tail
                            head_of_a.next_head = new Head(A[i]);
                            head_of_a = head_of_a.next_head;//point to right head of B[i]
                            index_of_a=crush_index;
                            head_of_a.index=crush_index++;
                            node_num++;//A[i] is not in map ,num of node +1
                            break;
                        }
                        head_of_a = head_of_a.next_head;
                        if(head_of_a.str.equals(A[i])) {
                            index_of_a=head_of_a.index;
                            break;
                        }
                    }
                }
                while(parent[index_of_a]>0){//index_of_a has parent == index_of_a is not root
                    parent[index_of_a] = parent[parent[index_of_a]];//path compression
                    //index_of_a = parent[index_of_a];
                }
                parent[key_b] = index_of_a;//link b to a
                parent[index_of_a]--;//a's child+1
            }
            else{
                //get A[i]'s head 's root
                Head head_of_a = hashmap[key_a];
                //find a's Head's index
                int index_of_a;
                if(head_of_a.str.equals(A[i])) index_of_a=key_a;
                else{
                    while(true){
                        if(head_of_a.next_head==null){//head_of_a now is point at tail
                            head_of_a.next_head = new Head(A[i]);
                            head_of_a = head_of_a.next_head;//point to right head of B[i]
                            index_of_a=crush_index;
                            head_of_a.index=crush_index++;
                            node_num++;//A[i] is not in map ,num of node +1
                            break;
                        }
                        head_of_a = head_of_a.next_head;
                        if(head_of_a.str.equals(A[i])) {
                            index_of_a=head_of_a.index;
                            break;
                        }
                    }
                }
                while(parent[index_of_a]>0){//index_of_a has parent == index_of_a is not root
                    parent[index_of_a] = parent[parent[index_of_a]];//path compression
                    //index_of_a = parent[index_of_a];
                }
                //get B[i]'s head 's root
                Head head_of_b = hashmap[key_b];
                //find b's Head's index
                int index_of_b;
                if(head_of_b.str.equals(B[i])) index_of_b=key_b;
                else{
                    while(true){
                        if(head_of_b.next_head==null){//head_of_b now is point at tail
                            head_of_b.next_head = new Head(B[i]);
                            head_of_b = head_of_b.next_head;//point to right head of B[i]
                            index_of_b=crush_index;
                            head_of_b.index=crush_index++;
                            node_num++;//B[i] is not in map ,num of node +1
                            break;
                        }
                        head_of_b = head_of_b.next_head;
                        if(head_of_b.str.equals(B[i])) {
                            index_of_b=head_of_b.index;
                            break;
                        }
                    }
                }
                while(parent[index_of_b]>0){//index_of_b has parent == head_of_b is not root
                    parent[index_of_b] = parent[parent[index_of_b]];//path compression
                    //index_of_b = parent[index_of_b];
                }

                if(index_of_a!=index_of_b){//not same group
                    if(parent[index_of_a]<parent[index_of_b]){//weighted
                        parent[index_of_a]+=(parent[index_of_b]-1);
                        parent[index_of_b] = index_of_a;
                        //head_of_a.child +=(head_of_b.child+1);//B[i]'s child + B[i] itself
                        //head_of_b.parent = head_of_a;//link B[i] to A[i]
                    }
                    else{
                        parent[index_of_b]+=(parent[index_of_a]-1);
                        parent[index_of_a] = index_of_b;
                        //head_of_b.child +=(head_of_a.child+1);
                        //head_of_a.parent = head_of_b;
                    }
                    group_min++;//link two group -> num of group-1
                }
            }
		}
        return node_num-group_min;
    }
}
