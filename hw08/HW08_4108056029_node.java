//position error -> ok
//win[] -> int win
//header0 -> small to big new_node = new node(,,header0.next); header0.next = new_node;->no
// tail for header0?->no
//header0 node[]->int[]? ->ok
//binary search to find?
//arraycopy??
public class HW08_4108056029_node extends Buy_Phone_v2{
    final static public int[][] aux = new int[10000][6];
    int[][] array;
    //int n;
    public int[][] bestPhone(int[][] inputArr){
        //this.inputArr = inputArr;
        //array = inputArr;
        int n = inputArr.length;
        array = new int[n][6];
        System.arraycopy(inputArr,0,array,0,n);

        sort(array,0,n-1);
        /*System.out.println("sort------------------------");
        for(int i=0;i<n;i++){
            for(int j=0;j<6;j++){
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("----------------------------");*/
        int check;//if win[] is all 1 : check=1 else check=0(can be replace)
        node[] position = new node[6];//if array[i] can remind , than at link header[h] it will after position[h]
        node[] header = new node[6];//small to big
        int is_bigest;//if it is bigest at header[h],position[h] is the last node in header[h]
        for(int i=1;i<6;i++){
            header[i] = new node(null,-1,null);//will point to array[i] which has smallest bit at h
            //position[i] = header[i];
        }
        //node header0 = new node(null,-1,null);
        int[] header0 = new int[n];
        node now;
        int num=0;//ans array's size
        //System.out.println("before for loop");
        for(int i=n-1;i>=0;i--){
            int[] win = new int[n];//win[n] = array[i] win at some bit
            //System.out.println("i= "+i);
            for(int h=1;h<6;h++){
                //System.out.println("h= "+h);
                now = header[h];
                is_bigest = 1;
                while(now.next!=null){
                    //System.out.println("now.next id= "+now.next.id);
                    if(array[i][h]<=now.next.arr[h]){//if array[i] cant win now.next.arr
                        //System.out.println("in if");
                        position[h] = now;//last smaller than i
                        is_bigest=0;//isnt biggest
                        break;
                    }
                    win[now.next.id] = 1;//array[i] win now.next.arr
                    now = now.next;
                }
                if(is_bigest==1) position[h] = now;
                //System.out.println("position "+h+"'s id"+ position[h].id);
            }
            check=1;//if array[i] didnt lose all bit with any array
            for(int j=0;j<num;j++){
                if(win[header0[j]]<=0){
                    check = 0;
                    break;
                }
            }
            /*now = header0;
            //System.out.println("header0's id : "+ header0.id);
            while(now.next!=null){
                //System.out.println("now.next id= "+now.next.id);
                if(win[now.next.id]<=0){//can be replace
                    check=0;
                    break;
                }
                now = now.next;
            }*/

            if(check == 1){//cant be replace
                for(int h=1;h<6;h++){//insert in header[]
                    node new_node = new node(array[i],i,position[h].next);
                    position[h].next = new_node;
                }
                /*node new_node = new node(array[i],i,now.next);
                now.next = new_node;//add it in the last of header0*/
                header0[num] = i;
                num++;//ans's number+1
            }
            /*//test---------------------------------------------
            node test;
            test = header0;
            System.out.println("header 0");
            while(test!=null){
                System.out.println("id = "+test.id);
                test = test.next;
            }
            for(int t=1;t<6;t++){
                test = header[t];
                System.out.println("header : "+t);
                while(test!=null){
                    System.out.println("id = "+test.id);
                    test = test.next;
                }
            }
            //test---------------------------------------------*/
        }
        //System.out.println("num: "+num);
        int[][] ans = new int[num][6];
        int count = 0;
        //now = header0.next;
        for(int i=num-1;i>=0;i--){
            //System.out.println("now.id: "+now.id);
            //System.out.println("now.arr: "+now.arr[0]);
            ans[i] = array[header0[count++]];
            //now = now.next;
        }
        return ans;
    }
    class node{
        int[] arr;
        int id;
        node next;
        public node(int[] arr,int id,node next){
            this.arr = arr;
            this.id = id;
            this.next = next;
        }
    }
    private static void sort(int[][] array,int lo,int hi){
        if(hi<=lo) return;
        int mid = (hi+lo)>>1;

        sort(array,lo,mid);
        sort(array,mid+1,hi);
        merge(array,lo,mid,hi);
    }
    private static void merge(int[][] array,int lo,int mid,int hi){//array->x

        System.arraycopy(array,lo,aux,lo,hi-lo+1);

        int i=lo,j=mid+1;
        for(int k=lo;k<=hi;k++){
            if(i>mid){
                array[k] = aux[j++];
            }
            else if(j>hi){
                array[k] = aux[i++];
            }
            else{
                for(int h=0;h<6;h++){
                    if(aux[i][h]>aux[j][h]) {
                        array[k] = aux[j++];
                        break;
                    }
                    else if(aux[i][h]<aux[j][h]){
                        array[k] = aux[i++];
                        break;
                    }
                }
            }
        }
    }
    /*public static void main(String[] args){
        HW08_4108056029_5 test = new HW08_4108056029_5();
        //int[][] inputArr = {{2,3,5,0,1,2,3},{1,7,3,2,5,0,1},{3,0,0,2,3,4,7},{0,2,3,4,5,6,1},{2,2,5,6,7,1,0}};
        int[][] inputArr = {{8,7,7,4,2,1},{2,4,9,2,2,1},{4,0,5,1,3,2},{5,2,4,3,7,3},{7,5,6,9,8,9},{8,1,1,1,1,1}};
        int[][] ans = test.bestPhone(inputArr);
        for(int i=0;i<ans.length;i++){
            for(int j=0;j<6;j++){
                System.out.print(ans[i][j]+" ");
            }
            System.out.println();
        }
    }*/
}
