public class HW09_4108056029_1 extends LSD{
    public int Distance(int[][] array){
        int len = array.length;
        Graph_4108056029 g = new Graph_4108056029(len);
        for(int i=0;i<len;i++){
            //System.out.println("i: "+i);
            g.addEdge(array[i][0],array[i][1]);
            g.addEdge(array[i][1],array[i][0]);
        }
        //g.show();
        return g.count();
    }
    public static void main(String[] args){
        HW09_4108056029_1 test = new HW09_4108056029_1();
        int[][] array = {{0,1},{0,2},{0,4},{1,3},{1,4},{2,5},{6,7}};
        test.Distance(array);
        //System.out.println(test.Distance(array));
        //test[] t = new test[3];
        //t[0] = new test(0);
        //ArrayList<Integer> al = new ArrayList<Integer>();
        //ArrayList<Integer>[] a = new ArrayList[5];
        //Set<Integer> set = new HashSet<Integer>();
        //Set setA = new HashSet();
        //ArrayList<Set<Integer>> groupMembers = new ArrayList<Set<Integer>>();
    }

    public class Graph_4108056029{

        public class headnode{

            int degree;
            adjlist next;
            adjlist tail;
            public headnode(int w){
                adjlist newnode = new adjlist(w);
                degree = 1;
                next = newnode;
                tail = newnode;
                //System.out.print("create");
            }
            public void add(int w){
                degree++;
                adjlist newnode = new adjlist(w);
                tail.next = newnode;
                tail = newnode;
            }
        }
        //public final int V;
        int cap;
        int V;
        public headnode[] head;
        public Graph_4108056029(int V){
            //this.V = V;
            this.cap = (1<<(int)(Math.log(V)/Math.log(2)+1))-1;
            this.V = this.cap+1;
            //System.out.println("cap = "+cap);
            head = new headnode[this.V];
        }
        public void addEdge(int v,int w){
            int key_head = v&cap;
            //System.out.println("Double.valueOf("+v+") = "+Double.valueOf(v));
            //System.out.println("key of "+v+" is "+(v&cap));
            //System.out.println("(Double.valueOf(v).hashCode()) & V = "+((Double.valueOf(v).hashCode()) & V));
            //System.out.println("key : "+key_head);
            if(head[key_head]==null) head[key_head] = new headnode(w&cap);
            else{
                head[key_head].add(w&cap);
            }
            //(Double.valueOf(key).hashCode() & 0x7fffffff) & (b-1)
            //adj[Double.valueOf(v).hashCode()&0x7fffffff].add(w);
            //adj[Double.valueOf(w).hashCode()&0x7fffffff].add(v);
        }
        public void show(){
            for(int i=0;i<cap+1;i++){
                if(head[i]!=null){
                    //System.out.println("degree of "+i+" is "+head[i].degree);
                    adjlist current = head[i].next;
                    while(current!=null){
                        System.out.println(current.n);
                        current = current.next;
                    }
                }
            }
        }
        public class adjlist{
            int n;
            adjlist next;
            public adjlist(int n){
                this.n = n;
                next = null;
            }
        }
        public int count(){
            int[] visited;
            int[] distTo;
            int max_path=0;
            int max_degree=0,max_index=0;
            adjlist current;
            int w;
            queue_29 q = new queue_29(V);
            for(int i=0;i<V;i++){
                if(head[i]!=null){
                if(head[i].degree < 3){
                //if(degree[i]<3){
                    //System.out.println("i = "+i);
                    visited = new int[V];
                    distTo = new int[V];
                    q.add(i);
                    //System.out.println("add "+i);
                    visited[i]=1;
                    distTo[i] = 0;
                    //System.out.println("i : "+i);
                    while(true){
                        int v = q.remove();
                        //System.out.println("move "+v);
                        //System.out.println("adjList.get("+v+").size() = "+adjList.get(v).size());
                        //for(int w : head[v].next){
                        if(head[v]!=null){
                            current = head[v].next;
                            while(current!=null){
                                w=current.n;
                                //for(int w : adjList.get(v)){
                                if(visited[w]<1){
                                    q.add(w);
                                    //System.out.println("add "+w);
                                    visited[w] = 1;
                                    distTo[w] = distTo[v]+1;
                                    //System.out.println("distTo["+w+"] : "+distTo[w]);
                                }
                                current = current.next;
                            }
                        }
                        if(q.isEmpty()){
                            if(max_path<distTo[v]){
                                max_path=distTo[v];
                            }
                            //System.out.println("max_path : "+max_path);
                            break;
                        }
                    }//while

                }//if(degree[i]<3)

                if(max_degree<head[i].degree){
                    max_degree=head[i].degree ;
                    max_index = i;
                }
                }
                q.reset();
            }
            if(max_path==0){
                visited = new int[V];
                distTo = new int[V];
                //queue_29 q = new queue_29(V);
                q.add(max_index);
                visited[max_index]=1;
                distTo[max_index] = 0;
                while(true){
                    int v = q.remove();
                    //System.out.println("adjList.get("+v+").size() = "+adjList.get(v).size());
                    //for(int w : head[v].next){
                    if(head[v]!=null){
                        current = head[v].next;
                        while(current!=null){
                            w=current.n;
                            //for(int w : adjList.get(v)){
                            if(visited[w]<1){
                                q.add(w);
                                visited[w] = 1;
                                distTo[w] = distTo[v]+1;
                                //System.out.println("distTo["+w+"] : "+distTo[w]);
                            }
                            current = current.next;
                        }
                    }
                    if(q.isEmpty()){
                        if(max_path<distTo[v]){
                            max_path=distTo[v];
                        }
                        //System.out.println("max_path : "+max_path);
                        break;
                    }
                }//while
                max_path*=2;
            }
            System.out.println("1 max_path : "+max_path);
            return max_path;
        }
        class queue_29{
            protected int[] queue;
    		protected int size;
    		protected int front = -1;
    		protected int rear = -1;
    		public queue_29(int size) {
    			queue = new int[size];
    			this.size = size;
    		}
    		public void add(int x) {
    			queue[++rear] = x;
    		}
    		public int remove() {
    			return queue[++front];
    		}
    		public boolean isEmpty() {
    			return front==rear;
    		}
    		public boolean isFull() {
    			return rear == size - 1;
    		}
            public void reset(){
                front = rear = -1;
            }
        }
    }
}
