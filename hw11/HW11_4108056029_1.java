//delete crush_index
//path compression(root,next)
public class HW11_4108056029_1 extends GroupCounting {
    public static void main(String[] args){
        HW11_4108056029_1 test = new HW11_4108056029_1();
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
        Head bigger_head;
        Head smaller_head;
        //Head next_head;//same key
        public Head(String str){
            this.str = str;
        }
    }
    public int count(String[] A, String[] B){
        int n=A.length;
        int cap = n - 1;
        cap |= cap >>> 1;
        cap |= cap >>> 2;
        cap |= cap >>> 4;
        cap |= cap >>> 8;
        cap |= cap >>> 16;
        //System.out.println("cap"+cap);
        int len = (int)(cap*1.5);
        Head[] hashmap = new Head[len];
        int[] parent = new int[len];//-1~-n: has n child, 0: this node not exsit, 1~n: n's child

        int key_a,key_b;
        int node_num=0,group_min=0; //node_num-group_min==group num
		for (int i=0;i<n;i++) {
            key_a=((A[i].hashCode() & 0x7fffffff)& (cap))+1;//key range 1~cap
            key_b=((B[i].hashCode() & 0x7fffffff)& (cap))+1;
            //System.out.println("A: "+A[i]+" key_a="+key_a);
            //System.out.println("B: "+B[i]+" key_b="+key_b);
            if(hashmap[key_a]==null && hashmap[key_b]==null){//both not in map //-> parent[key]==0
                node_num+=2;//num of node +2
                group_min++;//two node(two group) link -> num of group -1
                hashmap[key_a] = new Head(A[i]);
                hashmap[key_b] = new Head(B[i]);
                parent[key_a] = key_b;//link A[i] to B[i]
                parent[key_b] = -1;//node B[i] has one child
            }
            else if(hashmap[key_a]==null) {//key_a's place is empty -> A[i] is not in map
                node_num++;//num of node +1
                group_min++;//link a node to a group == link two group -> num of group-1
                hashmap[key_a] = new Head(A[i]);
                //get B[i]'s head's root
                //find b's Head's index
                int index_of_b;
                //if(hashmap[key_b].str.equals(B[i])) index_of_b=key_b;
                int comp=hashmap[key_b].str.compareTo(B[i]);
                if(comp==0) index_of_b=key_b;
                else{
                    Head head_of_b = hashmap[key_b];
                    while(true){
                        //System.out.println("compare"+head_of_b.str+" and "+B[i]+"=="+comp);
                        if(comp>0){
                            if(head_of_b.bigger_head==null){
                                head_of_b.bigger_head = new Head(B[i]);       //B[i] is not in map ,num of node +1
                                head_of_b.bigger_head.index=cap+(++node_num);//this index is crush -> give B[i] a new index
                                index_of_b = cap+node_num;
                                break;
                            }
                            head_of_b = head_of_b.bigger_head;
                        }
                        else{
                            if(head_of_b.smaller_head==null){
                                head_of_b.smaller_head = new Head(B[i]);       //B[i] is not in map ,num of node +1
                                head_of_b.smaller_head.index=cap+(++node_num);//this index is crush -> give B[i] a new index
                                index_of_b = cap+node_num;
                                break;
                            }
                            head_of_b = head_of_b.smaller_head;
                        }
                        //head_of_b = head_of_b.next_head;
                        comp=head_of_b.str.compareTo(B[i]);
                        if(comp==0) {
                            index_of_b=head_of_b.index;//head_of_b is B[i]'s Head, its index is index_of_b
                            break;
                        }
                    }
                }
                //find B[i]'s root
                int root,next;
                for(root = index_of_b;parent[root]>0;root=parent[root]);
                while(index_of_b!=root){//index_of_b has parent == is not root
                    next = parent[index_of_b];
                    parent[index_of_b] = root;
                    index_of_b = next;
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
                //find a's Head's index
                int index_of_a;
                int comp=hashmap[key_a].str.compareTo(A[i]);
                if(comp==0) index_of_a=key_a;
                else{
                    Head head_of_a = hashmap[key_a];
                    while(true){
                        //System.out.println("compare"+head_of_a.str+" and "+A[i]+"=="+comp);
                        if(comp>0){
                            if(head_of_a.bigger_head==null){
                                head_of_a.bigger_head = new Head(A[i]);       //B[i] is not in map ,num of node +1
                                head_of_a.bigger_head.index=cap+(++node_num);//this index is crush -> give B[i] a new index
                                index_of_a = cap+node_num;
                                break;
                            }
                            head_of_a = head_of_a.bigger_head;
                        }
                        else{
                            if(head_of_a.smaller_head==null){
                                head_of_a.smaller_head = new Head(A[i]);       //B[i] is not in map ,num of node +1
                                head_of_a.smaller_head.index=cap+(++node_num);//this index is crush -> give B[i] a new index
                                index_of_a = cap+node_num;
                                break;
                            }
                            head_of_a= head_of_a.smaller_head;
                        }
                        comp=head_of_a.str.compareTo(A[i]);
                        if(comp==0) {
                            index_of_a=head_of_a.index;//head_of_b is A[i]'s Head, its index is index_of_a
                            break;
                        }
                    }
                }
                int root,next;
                for(root = index_of_a;parent[root]>0;root=parent[root]);
                while(index_of_a!=root){//index_of_b has parent == is not root
                    next = parent[index_of_a];
                    parent[index_of_a] = root;
                    index_of_a = next;
                }
                parent[key_b] = index_of_a;//link b to a
                parent[index_of_a]--;//a's child+1
            }
            else{
                //get A[i]'s head 's root
                //find a's Head's index
                int index_of_a;
                int comp=hashmap[key_a].str.compareTo(A[i]);
                if(comp==0) index_of_a=key_a;
                else{
                    Head head_of_a = hashmap[key_a];
                    while(true){
                        //System.out.println("compare"+head_of_a.str+" and "+A[i]+"=="+comp);
                        if(comp>0){
                            if(head_of_a.bigger_head==null){
                                head_of_a.bigger_head = new Head(A[i]);       //B[i] is not in map ,num of node +1
                                head_of_a.bigger_head.index=cap+(++node_num);//this index is crush -> give B[i] a new index
                                index_of_a = cap+node_num;
                                break;
                            }
                            head_of_a = head_of_a.bigger_head;
                        }
                        else{
                            if(head_of_a.smaller_head==null){
                                head_of_a.smaller_head = new Head(A[i]);       //B[i] is not in map ,num of node +1
                                head_of_a.smaller_head.index=cap+(++node_num);//this index is crush -> give B[i] a new index
                                index_of_a = cap+node_num;
                                break;
                            }
                            head_of_a= head_of_a.smaller_head;
                        }
                        comp=head_of_a.str.compareTo(A[i]);
                        if(comp==0) {
                            index_of_a=head_of_a.index;//head_of_b is A[i]'s Head, its index is index_of_a
                            break;
                        }
                    }
                }
                int root,next;
                for(root = index_of_a;parent[root]>0;root=parent[root]);
                while(index_of_a!=root){//index_of_b has parent == is not root
                    next = parent[index_of_a];
                    parent[index_of_a] = root;
                    index_of_a = next;
                }
                //get B[i]'s head 's root
                //find b's Head's index
                int index_of_b;
                comp=hashmap[key_b].str.compareTo(B[i]);
                if(comp==0) index_of_b=key_b;
                else{
                    Head head_of_b = hashmap[key_b];
                    while(true){
                        //System.out.println("compare"+head_of_b.str+" and "+B[i]+"=="+comp);
                        if(comp>0){
                            if(head_of_b.bigger_head==null){
                                head_of_b.bigger_head = new Head(B[i]);       //B[i] is not in map ,num of node +1
                                head_of_b.bigger_head.index=cap+(++node_num);//this index is crush -> give B[i] a new index
                                index_of_b = cap+node_num;
                                break;
                            }
                            head_of_b = head_of_b.bigger_head;
                        }
                        else{
                            if(head_of_b.smaller_head==null){
                                head_of_b.smaller_head = new Head(B[i]);       //B[i] is not in map ,num of node +1
                                head_of_b.smaller_head.index=cap+(++node_num);//this index is crush -> give B[i] a new index
                                index_of_b = cap+node_num;
                                break;
                            }
                            head_of_b = head_of_b.smaller_head;
                        }
                        comp=head_of_b.str.compareTo(B[i]);
                        if(comp==0) {
                            index_of_b=head_of_b.index;//head_of_b is B[i]'s Head, its index is index_of_b
                            break;
                        }
                    }
                }
                for(root = index_of_b;parent[root]>0;root=parent[root]);
                while(index_of_b!=root){//index_of_b has parent == is not root
                    next = parent[index_of_b];
                    parent[index_of_b] = root;
                    index_of_b = next;
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
