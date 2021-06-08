public class HW11_4108056029_4 extends GroupCounting {
    public static void main(String[] args){
        HW11_4108056029_4 test = new HW11_4108056029_4();
    //    String[] A = {"CCCCC","BAB","BC","C","B","D","F","G","B","B"};
    //    String[] B = {"z","C","E","D","D","E","H","H","H","qqq"};
        //String[] A = {"A","e","A","A"};
        //String[] B = {"B","f","e","g"};
        String[] A = {"C","e","C","C","C","C"};
        String[] B = {"B","f","e","g","J","B"};
        System.out.println(test.count(A,B));
    }
    class Head {
        String str;
        int index;
        Head next_head;//same key
        public Head(String str,int index){
            this.str = str;
            this.index=index;
        }
    }
    public int count(String[] A, String[] B){
        int n=A.length;
        int cap = (1<<(int)(Math.log(n+1)/Math.log(2)+1));
        Head[] hashmap = new Head[cap];
        int[] parent = new int[cap];//-1~-n: has n child, 0: this node not exsit, 1~n: n's child
        int key_a,key_b,index_of_a,index_of_b;
        int node_num=0,group_min=0; //node_num-group_min==group num
		for (int i=0;i<n;i++) {
            //hash------------------------------
            key_a=((A[i].hashCode() & 0x7fffffff)& (cap-1));//key range 1~cap
            key_b=((B[i].hashCode() & 0x7fffffff)& (cap-1));
            if(hashmap[key_a]==null){
                hashmap[key_a]= new Head(A[i],++node_num);
                index_of_a=node_num;
            }
            else{
                Head head_of_a = hashmap[key_a];
                while(true){
                    if(head_of_a.str.equals(A[i])) {
                        index_of_a=head_of_a.index;
                        break;
                    }
                    if(head_of_a.next_head==null){//head_of_a now is point at tail
                        hashmap[key_a]= new Head(A[i],++node_num);
                        index_of_a=node_num;
                        break;
                    }
                    head_of_a = head_of_a.next_head;
                }
            }
            if(hashmap[key_b]==null){
                hashmap[key_b]= new Head(B[i],++node_num);
                index_of_b=node_num;
            }
            else{
                Head head_of_b = hashmap[key_b];
                while(true){
                    if(head_of_b.str.equals(B[i])) {
                        index_of_b=head_of_b.index;
                        break;
                    }
                    if(head_of_b.next_head==null){//head_of_a now is point at tail
                        hashmap[key_b]= new Head(B[i],++node_num);
                        index_of_b=node_num;
                        break;
                    }
                    head_of_b = head_of_b.next_head;
                }
            }
            //hash------------------------------
            ////////////////////////////////////////////////////////////
            //union
            int root,next;
            for(root = index_of_a;parent[root]>0;root=parent[root]);
            while(index_of_a!=root){//index_of_b has parent == is not root
                next = parent[index_of_a];
                parent[index_of_a] = root;
                index_of_a = next;
            }
            for(root = index_of_b;parent[root]>0;root=parent[root]);
            while(index_of_b!=root){//index_of_b has parent == is not root
                next = parent[index_of_b];
                parent[index_of_b] = root;
                index_of_b = next;
            }
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
