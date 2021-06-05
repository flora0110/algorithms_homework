public class HW11_4108056029_2 extends GroupCounting {
    public static void main(String[] args){
        HW11_4108056029_2 test = new HW11_4108056029_2();
        String[] A = {"ABA","BAB","BC","C","B","D","F","G"};
        String[] B = {"E","C","E","D","D","E","H","H"};
        System.out.println(test.count(A,B));
    }
    public int count(String[] A, String[] B){
        int n=A.length;
        int cap = ((1<<(int)(Math.log(n+1)/Math.log(2)+1)))*((1<<(int)(Math.log(n+1)/Math.log(2)+1)))-1;
        //System.out.println("cap: "+cap+" n:"+n);
        int[] hashmap = new int[cap+1];
        int key_a,key_b;
        int node_num=0,group_min=0;
		for (int i=0;i<n;i++) {
            key_a=(A[i].hashCode() & 0x7fffffff) & (cap-1) +1;
            key_b=(B[i].hashCode() & 0x7fffffff) & (cap-1) +1;
            System.out.println("\nA: "+A[i]+" code: "+(A[i].hashCode() & 0x7fffffff));
            System.out.println("B: "+B[i]+" code: "+(B[i].hashCode() & 0x7fffffff));
            System.out.println("A :"+A[i]+" key_a = "+key_a);
            System.out.println("B :"+B[i]+" key_b = "+key_b);
            if(hashmap[key_a]==0 && hashmap[key_b]==0){
                node_num+=2;
                group_min++;
                hashmap[key_a] = key_b;
                hashmap[key_b] = -1;
                System.out.println("mode 1 node_num: "+node_num+" group_min: "+group_min);
            }
            else if(hashmap[key_a]==0) {
                node_num++;
                group_min++;
                while(hashmap[key_b]>0) key_b=hashmap[key_b];
                hashmap[key_a] = key_b;
                hashmap[key_b]--;
                System.out.println("mode 2 node_num: "+node_num+" group_min: "+group_min);
            }
            else if(hashmap[key_b]==0) {
                node_num++;
                group_min++;
                while(hashmap[key_a]>0) key_a=hashmap[key_a];
                hashmap[key_b] = key_a;
                hashmap[key_a]--;
                System.out.println("mode 3 node_num: "+node_num+" group_min: "+group_min);
            }
            else{
                while(hashmap[key_a]>0) {
                    //hashmap[key_a] =hashmap[hashmap[key_a]];
                    key_a=hashmap[key_a];
                    System.out.println("key_a = "+key_a);
                }
                while(hashmap[key_b]>0) {
                    //hashmap[key_b] =hashmap[hashmap[key_b]];
                    key_b=hashmap[key_b];
                    System.out.println("key_b = "+key_b);
                    }
                System.out.println("key_a = "+key_a+" key_b = "+key_b);
                if(key_a!=key_b){
                    group_min++;//link diff group => group-1
                    if(hashmap[key_a]<hashmap[key_b]){
                        hashmap[key_a] +=(hashmap[key_b]-1);
                        hashmap[key_b] = key_a;
                    }
                    else{
                        hashmap[key_b] +=(hashmap[key_a]-1);
                        hashmap[key_a] = key_b;
                    }
                }
                System.out.println("mode 4 node_num: "+node_num+" group_min: "+group_min);
            }
		}
        return node_num-group_min;
    }
}
