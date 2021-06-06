public class HW11_4108056029_4 extends GroupCounting {
    public static void main(String[] args){
        HW11_4108056029_4 test = new HW11_4108056029_4();
        String[] A = {"CCCCC","BAB","BC","C","B","D","F","G","B","B"};
        String[] B = {"z","C","E","D","D","E","H","H","H","qqq"};
        //String[] A = {"A","e","A","A"};
        //String[] B = {"B","f","e","g"};
        //String[] A = {"C","e","C","C","C","C"};
        //String[] B = {"B","f","e","g","J","B"};
        System.out.println(test.count(A,B));
    }
    class Head {
        String str;
        int index;
        Head next_head;//same key
        public Head(String str){
            this.str = str;
        }
    }
    public int count(String[] A, String[] B){
        int n=A.length;
        int cap = (1<<(int)(Math.log(n+1)/Math.log(2)+1))-1;
        Head[] hashmap = new Head[cap<<1+1];
        int[] parent = new int[cap<<1+1];//0: this node not exsit, 1~n: n's child
        int[] child = new int[cap<<1+1];

        int key_a,key_b;
        int node_num=0,group_min=0; //node_num-group_min==group num
		for (int i=0;i<n;i++) {
            key_a=(A[i].hashCode() & 0x7fffffff)& (cap-1)+1;//key range 1~cap
            key_b=(B[i].hashCode() & 0x7fffffff)& (cap-1)+1;
            if(hashmap[key_a]==null && hashmap[key_b]==null){//both not in map //-> parent[key]==0
                node_num+=2;//num of node +2
                group_min++;//two node(two group) link -> num of group -1
                hashmap[key_a] = new Head(A[i]);
                hashmap[key_b] = new Head(B[i]);
                parent[key_a] = key_b;//link A[i] to B[i]
                parent[key_b] = key_b;
                child[key_b]=1;//node B[i] has one child
            }
            else if(hashmap[key_a]==null) {//key_a's place is empty -> A[i] is not in map
                node_num++;//num of node +1
                group_min++;//link a node to a group == link two group -> num of group-1
                hashmap[key_a] = new Head(A[i]);

                //get B[i]'s head's root
                //find b's Head's index
                int index_of_b;
                if(hashmap[key_b].str.equals(B[i])) index_of_b=key_b;
                else{
                    Head head_of_b = hashmap[key_b];
                    while(true){
                        if(head_of_b.next_head==null){//head_of_b now is point at tail
                            head_of_b.next_head = new Head(B[i]);       //B[i] is not in map ,num of node +1
                            head_of_b.next_head.index=cap+(++node_num);//this index is crush -> give B[i] a new index
                            index_of_b = cap+node_num;
                            parent[index_of_b]=index_of_b;//initial
                            break;
                        }
                        head_of_b = head_of_b.next_head;
                        if(head_of_b.str.equals(B[i])) {
                            index_of_b=head_of_b.index;//head_of_b is B[i]'s Head, its index is index_of_b
                            break;
                        }
                    }
                }
                //find B[i]'s root
                while(parent[index_of_b]!=index_of_b){//index_of_b has parent == is not root
                    parent[index_of_b] = parent[parent[index_of_b]];
                    index_of_b = parent[index_of_b];
                }
                parent[key_a] = index_of_b;
                child[index_of_b]++;
            }
            else if(hashmap[key_b]==null) {//B[i] is not in map
                node_num++;
                group_min++;
                hashmap[key_b] = new Head(B[i]);

                //get a's head's root
                //find a's Head's index
                int index_of_a;
                if(hashmap[key_a].str.equals(A[i])) index_of_a=key_a;
                else{
                    Head head_of_a = hashmap[key_a];
                    while(true){
                        if(head_of_a.next_head==null){//head_of_a now is point at tail
                            head_of_a.next_head = new Head(A[i]);
                            //head_of_a = head_of_a.next_head;//point to right head of B[i]
                            head_of_a.next_head.index=cap+(++node_num);
                            index_of_a=cap+node_num;
                            parent[index_of_a]=index_of_a;//initial
                            //head_of_a.index=cap+node_num;
                            //node_num++;//A[i] is not in map ,num of node +1
                            break;
                        }
                        head_of_a = head_of_a.next_head;
                        if(head_of_a.str.equals(A[i])) {
                            index_of_a=head_of_a.index;
                            break;
                        }
                    }
                }
                while(parent[index_of_a]!=index_of_a){//index_of_a has parent == index_of_a is not root
                    parent[index_of_a] = parent[parent[index_of_a]];
                    index_of_a = parent[index_of_a];
                }
                parent[key_b] = index_of_a;//link b to a
                child[index_of_a]++;//a's child+1
            }
            else{
                //get A[i]'s head 's root
                //find a's Head's index
                int index_of_a;
                if(hashmap[key_a].str.equals(A[i])) index_of_a=key_a;
                else{
                    Head head_of_a = hashmap[key_a];
                    while(true){
                        if(head_of_a.next_head==null){//head_of_a now is point at tail
                            head_of_a.next_head = new Head(A[i]);
                            head_of_a.next_head.index=cap+(++node_num);
                            //head_of_a = head_of_a.next_head;//point to right head of B[i]
                            index_of_a=cap+node_num;
                            parent[index_of_a]=index_of_a;//initial
                            //head_of_a.index=cap+node_num;
                            //node_num++;//A[i] is not in map ,num of node +1
                            break;
                        }
                        head_of_a = head_of_a.next_head;
                        if(head_of_a.str.equals(A[i])) {
                            index_of_a=head_of_a.index;
                            break;
                        }
                    }
                }
                while(parent[index_of_a]!=index_of_a){//index_of_a has parent == index_of_a is not root
                    parent[index_of_a] = parent[parent[index_of_a]];
                    index_of_a = parent[index_of_a];
                }
                //get B[i]'s head 's root
                //find b's Head's index
                int index_of_b;
                if(hashmap[key_b].str.equals(B[i])) index_of_b=key_b;
                else{
                    Head head_of_b = hashmap[key_b];
                    while(true){
                        if(head_of_b.next_head==null){//head_of_b now is point at tail
                            head_of_b.next_head = new Head(B[i]);
                            head_of_b.next_head.index=cap+(++node_num);
                            //head_of_b = head_of_b.next_head;//point to right head of B[i]
                            index_of_b=cap+node_num;
                            parent[index_of_b]=index_of_b;//initial
                            //head_of_b.index=cap+node_num;
                            //node_num++;//B[i] is not in map ,num of node +1
                            break;
                        }
                        head_of_b = head_of_b.next_head;
                        if(head_of_b.str.equals(B[i])) {
                            index_of_b=head_of_b.index;
                            break;
                        }
                    }
                }
                while(parent[index_of_b]!=index_of_b){//index_of_b has parent == index_of_b is not root
                    parent[index_of_b] = parent[parent[index_of_b]];
                    index_of_b = parent[index_of_b];
                }

                if(index_of_a!=index_of_b){//not same group
                    if(child[index_of_a]>child[index_of_b]){//weighted
                        child[index_of_a]+=(child[index_of_b]+1);
                        parent[index_of_b] = index_of_a;
                        //head_of_a.child +=(head_of_b.child+1);//B[i]'s child + B[i] itself
                        //head_of_b.parent = head_of_a;//link B[i] to A[i]
                    }
                    else{
                        child[index_of_b]+=(child[index_of_a]+1);
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
