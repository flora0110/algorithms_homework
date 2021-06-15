public class HW11_4108056029_2 extends GroupCounting {
    public static void main(String[] args) {
        HW11_4108056029_2 test = new HW11_4108056029_2();
        String[] A = {"123","345","234"};
        String[] B = {"234","456","556"};
        System.out.println(test.count(A,B));
    }
    public int count(String[] A, String[] B){
        int[] parent = new int[2000000];
        //parent[k] = 0 not exsit ,-1~-n has n child, 1~n n is k's parent
        int n=A.length,node_num=0,group_min=0;
        for(int i=0,a,b,root;i<n;i++){
            a=Integer.parseInt(A[i])+1;
            b=Integer.parseInt(B[i])+1;
            if(parent[a]==0){
                parent[a]=-1;
                node_num++;
            }
            else{
                for(root = a;parent[root]>-1;root=parent[root]);
                for(int next;a!=root;next = parent[a],parent[a] = root,a = next);
            }
            if(parent[b]==0){
                parent[b]=-1;
                node_num++;
            }
            else{
                for(root = b;parent[root]>-1;root=parent[root]);
                for(int next;b!=root;next = parent[b],parent[b] = root,b = next);
            }
            if(a!=b){
                group_min++;
                if(parent[a]<parent[b]){
                    parent[a] += parent[b];
                    parent[b] = a;
                }
                else{
                    parent[b] += parent[a];
                    parent[a] = b;
                }
            }
        }
        return node_num-group_min;
    }
}
