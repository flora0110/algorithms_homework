//position error
public class HW08_4108056029_4 extends Buy_Phone_v2{
    int[][] inputArr;
    int n;
    final static int[][] aux = new int[10000][2];
    public int[][] bestPhone(int[][] inputArr){
        n = inputArr.length;
        this.inputArr = inputArr;
        sort(0,n);
        int check;
        int[] win = new int[n];
        node[] position = new node[6];
        node[] header = new node[6];//small to big
        for(int i=1;i<6;i++){
            header[i] = new node(null,-1,null);
            position[i] = header[i];
        }
        node header0 = new node(null,-1,null);

        node now;
        int num=0;
        for(int i=n-1;i>=0;i--){
            for(int h=1;h<6;h++){
                now = header[h];
                while(now.next!=null){
                    if(inputArr[i][h]<=now.next.arr[h]){
                        position[h] = now;//last smaller than i
                        break;
                    }
                    win[now.next.ip] = 1;
                    now = now.next;
                }
            }
            check=1;
            now = header0;
            while(now.next!=null){
                if(win[now.next.ip]<=0){
                    check=1;
                    break;
                }
            }
            if(check == 1){
                for(int h=1;h<6;h++){
                    node new_node = new node(inputArr[i],i,position[h].next);
                    position[h].next = new_node;
                }
                node new_node = new node(inputArr[i],i,now.next);
                now.next = new_node;
                num++;
            }
        }
        int[][] ans = new int[n][6];
        now = header0.next;
        for(int i=num-1;i>=0;i++){
            ans[i] = now.arr;
            now = now.next;
        }
        return ans;
    }
    class node{
        int[] arr;
        int ip;
        node next;
        public node(int[] arr,int ip,node next){
            this.arr = arr;
            this.ip = ip;
            this.next = next;
        }
    }
    private void merge(int start, int mid, int end){
        if(inputArr[mid-1][0] <= inputArr[mid][0]) return;
        System.arraycopy(inputArr, start, aux, start, end-start);
        int i = start;
        int j = mid;
        while(i!=mid && j!=end) {
        inputArr[start++] = (aux[i][0] < aux[j][0])? aux[i++] : aux[j++];
    }
        while(i!=mid)           inputArr[start++] = aux[i++];
        while(j!=end)           inputArr[start++] = aux[j++];
    }

    private void sort(int from, int to){
        if(from + 9 > to){
            Insertion(from, to);
            return;
        }
        int mid = (from+to) >> 1;
        sort(from, mid);
        sort(mid, to);
        merge(from, mid, to);
    }

    private void Insertion(int from, int to){
        for(int i=from+1; i<to; i++){
            for(int j=i-1; j>=from && (inputArr[j][0] > inputArr[j+1][0]); j--){
                inputArr[j+1][0] = inputArr[j+1][0] ^ inputArr[j][0];
                inputArr[j][0]   = inputArr[j+1][0] ^ inputArr[j][0];
                inputArr[j+1][0] = inputArr[j+1][0] ^ inputArr[j][0];
                inputArr[j+1][1] = inputArr[j+1][1] ^ inputArr[j][1];
                inputArr[j][1]   = inputArr[j+1][1] ^ inputArr[j][1];
                inputArr[j+1][1] = inputArr[j+1][1] ^ inputArr[j][1];
            }
        }
    }
    public static void main(String[] args){
        HW08_4108056029_4 test = new HW08_4108056029_4();
        int[][] inputArr = {{2,3,5,0,1,2,3},{1,7,3,2,5,0,1},{3,0,0,2,3,4,7},{0,2,3,4,5,6,1},{2,2,5,6,7,1,0}};
        int[][] ans = test.bestPhone(inputArr);
        for(int i=0;i<5;i++){
            for(int j=0;j<6;j++){
                System.out.print(inputArr[i][j]+" ");
            }
            System.out.println();
        }
    }
}
