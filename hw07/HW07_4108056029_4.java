public class HW07_4108056029_4 extends Buy_Phone{
    int n;
    int[][] ans;
    int[][] inputArr;
    public int[][] bestPhone(int[][] inputArr){
        n = inputArr.length;
        int i,j;
        this.inputArr = inputArr;
        quick_sort(0,n-1);
        //linklist array = new linklist(-1,-1);
        //linklist(-1,-1);
        for(i=n-1;i>=0;i--){
            //System.out.println(inputArr[i][0]+" "+inputArr[i][1]);
            add(inputArr[i][0],inputArr[i][1]);
        }
        add(-1,-1);//title
        //System.out.println("first"+first.x+" "+first.y);
        int num=0,maxy;
        Node last = first;
        Node temp = last.next;
        Node compare;
        while(temp!=null){
            num++;
            compare = temp.next;//start to compare from temp's next to end
            maxy = temp.y;//remind the max y
            while(compare!=null){
                //last_com = last;//compare's last
                System.out.println("compare "+compare.x+" "+compare.y);
                if(compare.y >= maxy){//sofar y is max && x is max
                    //temp = last;
                    //System.out.println("last "+last.x+" "+last.y+" maxy "+maxy);
                    System.out.println("before delete last "+last.x+" "+last.y);
                    last.next = compare;//delete start to now
                    //System.out.println("last_com.next "+last_com.next.x+" "+last_com.next.y);
                    maxy = compare.y;//update maxy
                    //temp = last.next;//
                    //System.out.println("change temp "+temp.x+" "+temp.y);
                    //num--;
                }
                //last_com = last_com.next;
                //System.out.println("last_com "+last_com.x+" "+last_com.y);
                compare = compare.next;
            }
            temp = last.next.next;
            last = last.next;

        }


        ans = new int[num][2];
        num=0;
        temp = first.next;
        while(temp!=null){
            ans[num][0] = temp.x;
            ans[num++][1] = temp.y;
            temp = temp.next;
        }
        return ans;
    }
    //public static class linklist{
        private class Node{
            public int x;
            public int y;
            public Node next;
            public Node(int x,int y){
                this.x = x;
                this.y = y;
                this.next = null;
            }
        }
        public Node first = null;
        public void add(int x,int y){
            Node newN = new Node(x,y);
            newN.next = first;
            first = newN;
        }
    //}
    public void quick_sort(int left,int right){
        if(right<=left+9){
            insertion_sort();
            return;
        }
        int[] temp = new int[2];
        int[] s=inputArr[(left+right)>>1];
        int i=left,j=right;
        while(i<j) {
            while((inputArr[i][0] < s[0]) || ((inputArr[i][0] == s[0])&&(inputArr[i][1] < s[1]))) i++;//to right
            while(inputArr[j][0] > s[0] || ((inputArr[j][0] == s[0])&&(inputArr[j][1] > s[1]))) j--;// to left
            if(i >= j) break;
            temp = inputArr[i];
            inputArr[i] = inputArr[j];
            inputArr[j] = temp;
        }
        quick_sort(left,i-1);
        quick_sort(j+1,right);
    }
    public void insertion_sort(){
        int[] temp = new int[2];
        for(int i = 0; i < n; i++){
            for (int j = i; j > 0; j--){
                if (inputArr[j][0]<inputArr[j-1][0]){
                    temp = inputArr[j];
                    inputArr[j] = inputArr[j-1];
                    inputArr[j-1] = temp;
                }
                else if(inputArr[j][0]==inputArr[j-1][0] && inputArr[j][1]<inputArr[j-1][1]){
                    temp = inputArr[j];
                    inputArr[j] = inputArr[j-1];
                    inputArr[j-1] = temp;
                }
                else break;
            }
        }
    }
    public static void main(String[] args){
        HW07_4108056029_4 test = new HW07_4108056029_4();
        //int[][] inputArr = {{2,4},{2,10},{5,4},{4,8},{1,1},{5,5},{8,4},{10,2},{10,1}};
        //int[][] inputArr = {{2,1},{5,3},{5,4},{7,2}};
        int[][] inputArr = {{2,4},{2,5},{5,3},{5,4},{7,6}};
        //int[][] inputArr = {{0,10},{1,1},{2,3},{2,4},{3,1},{4,6},{4,8},{5,1},{6,4}};
        int[][] ans= test.bestPhone(inputArr);
        System.out.println("ans");
        for(int i=0;i<ans.length;i++){
            System.out.println(ans[i][0]+" "+ans[i][1]);
        }
    }
}
