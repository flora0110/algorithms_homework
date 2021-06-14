public class HW11_4108056029_4 extends GroupCounting {
    class Head {
        final String str;
        final int index;
        Head next_head;//same key
        public Head(String str,int index){
            this.str = str;
            this.index=index;
        }
    }
    public int count(String[] A, String[] B){
        int n=A.length;
        int cap =(1<<32-Integer.numberOfLeadingZeros(n-1))-1;
        Head[] hashmap = new Head[cap+1];
        int[] parent = new int[cap+1];//-1~-n: has n child, 0: this node not exsit, 1~n: n's child
        int key_a,key_b,index_of_a,index_of_b;
        int node_num=0,group_min=0; //node_num-group_min==group num
		for (int i=0;i<n;i++) {
            //hash------------------------------
            key_a=A[i].hashCode()&cap;//key range 1~cap
            key_b=B[i].hashCode()&cap;
            if(hashmap[key_a]==null){
                hashmap[key_a]= new Head(A[i],++node_num);
                index_of_a=node_num;
            }
            else{
                for(Head head_of_a = hashmap[key_a];;head_of_a = head_of_a.next_head){
                    if(head_of_a.str.equals(A[i])) {
                        index_of_a=head_of_a.index;
                        break;
                    }
                    if(head_of_a.next_head==null){//head_of_a now is point at tail
                        head_of_a.next_head= new Head(A[i],++node_num);
                        index_of_a=node_num;
                        break;
                    }
                }
            }
            if(hashmap[key_b]==null){
                hashmap[key_b]= new Head(B[i],++node_num);
                index_of_b=node_num;
            }
            else{
                for(Head head_of_b = hashmap[key_b];;head_of_b = head_of_b.next_head){
                    if(head_of_b.str.equals(B[i])) {
                        index_of_b=head_of_b.index;
                        break;
                    }
                    if(head_of_b.next_head==null){//head_of_a now is point at tail
                        head_of_b.next_head= new Head(B[i],++node_num);
                        index_of_b=node_num;
                        break;
                    }
                }
            }
            //hash------------------------------
            ////////////////////////////////////////////////////////////
            //union
            int root,next;
            for(root = index_of_a;parent[root]>0;root=parent[root]);
            /*while(index_of_a!=root){//index_of_b has parent == is not root
                next = parent[index_of_a];
                parent[index_of_a] = root;
                index_of_a = next;
            }*/
            for(;index_of_a!=root;next = parent[index_of_a],parent[index_of_a] = root,index_of_a = next);
            for(root = index_of_b;parent[root]>0;root=parent[root]);
            /*while(index_of_b!=root){//index_of_b has parent == is not root
                next = parent[index_of_b];
                parent[index_of_b] = root;
                index_of_b = next;
            }*/
            for(;index_of_b!=root;next = parent[index_of_b],parent[index_of_b] = root,index_of_b = next);

            //System.out.println(A[i]+" index_of_a=="+index_of_a);
            //System.out.println(B[i]+" index_of_b=="+index_of_b);
            if(index_of_a!=index_of_b){//not same group
                if(parent[index_of_a]<parent[index_of_b]){//weighted
                    parent[index_of_a]+=(parent[index_of_b]-1);
                    parent[index_of_b] = index_of_a;
                }
                else{
                    parent[index_of_b]+=(parent[index_of_a]-1);
                    parent[index_of_a] = index_of_b;
                }
                group_min++;//link two group -> num of group-1
            }
            //System.out.println("group_min "+group_min);
            //System.out.println("group "+(node_num-group_min)+"\n");
            //union-------------------------------------------
            //////////////////////////////////////////////////
        }
        return node_num-group_min;
    }
}
