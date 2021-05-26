public class HW09_4108056029_5 extends LSD {
	public static void main(String[] args) {
		HW09_4108056029_5 test = new HW09_4108056029_5();
		//int[][] inputArr = { { 0, 1 }, { 0, 2 }, { 0, 4 }, { 1, 3 }, { 1, 4 }, { 2, 5 }, { 6, 7 } };	// 4
		//int[][] inputArr = { { 1, 2 }, { 3, 2 }, { 5, 4 }, { 4, 6 }, { 7, 4 }, { 9, 8 } };	// 2
//		int[][] inputArr = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 4 }, { 2, 4 }, { 2, 5 }, { 2, 6 }, { 3, 7 }, { 5, 6 }, { 5, 7 }, { 6, 9 }, { 7, 8 }, { 9, 10 } };	// 5
//		int[][] inputArr = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }};	// 4
		int[][] inputArr = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 1 }, { 2, 4 }, { 5, 4 }, { 6, 4 }, { 3, 7 }, { 7, 8 }, { 7, 10 }, { 8, 9 }};	// 6

		System.out.println("case5:");
		//Stopwatch stopwatch = new Stopwatch();
		int ans = test.Distance(inputArr);
		//double time = stopwatch.elapsedTime();
		//System.out.println("elapsed time " + time);

		System.out.println(ans);
	}


	public int Distance(int[][] array) {
		Graph_4108056029 g = new Graph_4108056029(array.length + 1);

		for (int[] v : array) {
			g.addEdge(v[0], v[1]);
		}

		return g.findMaxDepth()-1;
	}
    public class Graph_4108056029{
        int cap;
        int V;
        int dit;
        public Head[] head;
        int degree[];
        int maxDegree;
        int maxDegreeNode;
        class Head {
			int key;
			int degree;
            //boolean not_null;
			ArrayList_29 sub_node;
            //Head next_head;
		}
        public Graph_4108056029(int V){
            //this.V = V;
            this.cap = (1<<(int)(Math.log(V)/Math.log(2)+1))-1;
            dit = (int)(Math.log(V)/Math.log(2)+1);
            this.V = cap<<dit+1;
            //System.out.println("cap : "+this.cap);
            //System.out.println("V : "+this.V);
            //System.out.println("cap = "+cap);
            head =  new Head[this.V];
            degree = new int[this.V];
            maxDegree=0;
        }
        public void addEdge(int v,int w){
            int key_head = (v&cap)<<dit;
            int key_head_w = (w&cap)<<dit;
            for(;;key_head++){
            //    System.out.println("v ,w : "+v+" "+w+" key_head: "+key_head);
                if(head[key_head]==null){
                    Head h = new Head();
                    h.key = v;
                    h.sub_node = new ArrayList_29();
                    h.degree = 1;
                    head[key_head]=h;
                    //head[key_head].sub_node.add(subnode);
                    break;
                }
                else if(head[key_head].key==v){

                    if (++head[key_head].degree > maxDegree) {
						maxDegree = head[key_head].degree;
						maxDegreeNode = key_head;
					}
					//head[key_head].sub_node.add(subnode);//
					//return;
                    break;
                }
            }
            for(;;key_head_w++){
                //System.out.println("v ,w : "+v+" "+w+" key_head_w: "+key_head_w);
                if(head[key_head_w]==null){
                    Head h = new Head();
                    h.key = w;
                    h.sub_node = new ArrayList_29();
                    h.degree = 1;
                    head[key_head_w]=h;
                    //head[key_head].sub_node.add(subnode);
                    break;
                }
                else if(head[key_head_w].key==w){
                    if (++head[key_head_w].degree > maxDegree) {
						maxDegree = head[key_head_w].degree;
						maxDegreeNode = key_head_w;
					}
					//head[key_head].sub_node.add(subnode);//
					//return;
                    break;
                }
            }
            head[key_head].sub_node.add(key_head_w);
            head[key_head_w].sub_node.add(key_head);
        }

        int maxDepthNode;
        int[] visited;
        int[] distTo;
        int maxDepth;
        FiniteQueue q;
        final int findMaxDepth() {
            q = new FiniteQueue(V);
			// Find the most depth node from "center node", which has the most degree
            //System.out.println("maxDegreeNode: "+maxDegreeNode);
			BFSmark(maxDegreeNode);
			// Find the most depth
            //System.out.println("maxDepthNode: "+head[maxDepthNode].key);

			BFSmark(maxDepthNode);
			return maxDepth;
		}

		final void BFSmark(int a) {
			//Set depth = new Set(this.len);
            visited = new int[this.V];
            distTo = new int[this.V];
            visited[a]=1;
            q.reset();
            q.add(a);
			//depth.put(a, 1);
			// FiniteQueue has been initialized when Graph() was constructed
			//queue.enqueue(a);

			while (!q.empty()) {
				a = q.remove();
                //System.out.println("head : "+head[a].key+" distTo : "+distTo[a]);
				int self_depth = distTo[a] + 1;
				if (self_depth > maxDepth) {
					maxDepth = self_depth;
					maxDepthNode = a;
				}

				ArrayList_29 arr = head[a].sub_node;
				for (arr.read(); arr.hasNext();) {
					int n = arr.next();
					if (visited[n]<1) {
						//depth.put(n, self_depth);
                        distTo[n] = self_depth;
                        visited[n]=1;
						q.add(n);
					}
				}
			}
		}
    }

	/*class Set {
		private int _cap; // _cap = cap - 1
		private Element[] list;

		Set(int cap) {
			this._cap = (1 << (int) (Math.log(cap) / Math.log(2) + 1)) - 1;
			this.list = new Element[this._cap + 1];
		}

		class Element {
			int key;
			int val;
			Element next;
		}

		private int hashcode(int n) {
			return n & _cap;
		}

		void put(int k, int v) {
			Element newE = new Element();
			newE.key = k;
			newE.val = v;
			newE.next = list[hashcode(k)];
			list[hashcode(k)] = newE;
		}

		int get(int n) {
			for (Element c = list[hashcode(n)]; c != null; c = c.next) {
				if (c.key == n)
					return c.val;
			}
			return 0;
		}

		boolean contain(int n) {
			for (Element c = list[hashcode(n)]; c != null; c = c.next) {
				if (c.key == n)
					return true;
			}
			return false;
		}
	}

	class HashAdjList {
		private int _cap; // _cap = cap - 1
		private int maxDegree;
		public int maxDegreeNode;
		Element[] list;

		HashAdjList(int cap) {
			this._cap = (1 << (int) (Math.log(cap) / Math.log(2) + 1)) - 1;
			this.list = new Element[this._cap + 1];
			this.maxDegree = -1;
			this.maxDegreeNode = -1;
		}

		class Element {
			int key;
			int degree;
			ArrayListLite val; // sub node
			Element next;
		}

		private final int hashcode(int n) {
			return n & _cap;
		}

		final void put(int n, int subnode) {
			for (Element c = list[hashcode(n)]; c != null; c = c.next) {
				if (c.key == n) {
					if (++c.degree > maxDegree) {
						maxDegree = c.degree;
						maxDegreeNode = n;
					}
					c.val.add(subnode);
					return;
				}
			}

			Element newE = new Element();
			newE.key = n;
			newE.val = new ArrayListLite();
			newE.val.add(subnode);
			newE.next = list[hashcode(n)];
			list[hashcode(n)] = newE;
		}

		final ArrayListLite get(int n) {
			for (Element c = list[hashcode(n)]; c != null; c = c.next) {
				if (c.key == n)
					return c.val;
			}
			return null;
		}
	}
*/
/*	class Graph {
		private HashAdjList adjList;
		private FiniteQueue queue;
		private int maxDepth;
		private int maxDepthNode;
		private int len;

		Graph(int len) {
			this.len = len;
			this.maxDepth = 0;
			adjList = new HashAdjList(len << 1);
			queue = new FiniteQueue(len);
		}

		final void append(int a, int b) {
			adjList.put(a, b);
			adjList.put(b, a);
		}

		final int findMaxDepth() {
			// Find the most depth node from "center node", which has the most degree
			BFSmark(adjList.maxDegreeNode);
			// Find the most depth
			BFSmark(this.maxDepthNode);
			return this.maxDepth;
		}

		final void BFSmark(int a) {
			Set depth = new Set(this.len);

			depth.put(a, 1);
			// FiniteQueue has been initialized when Graph() was constructed
			queue.enqueue(a);

			while (!queue.empty()) {
				a = queue.dequeue();
				int self_depth = depth.get(a) + 1;
				if (self_depth > maxDepth) {
					maxDepth = self_depth;
					maxDepthNode = a;
				}

				ArrayListLite arr = adjList.get(a);
				for (arr.read(); arr.hasNext();) {
					int n = arr.next();
					if (!depth.contain(n)) {
						depth.put(n, self_depth);
						queue.enqueue(n);
					}
				}
			}
		}
	}
*/
	// insecure
	class FiniteQueue {
		private int _cap;
		private int head;
		private int tail;
		private int[] list;

		FiniteQueue(int cap) {
			this._cap = (1 << (int) (Math.log(cap) / Math.log(2) + 1)) - 1;
			this.list = new int[_cap + 1];
			head = 0; // dequeue
			tail = 0; // enqueue
		}
        public void reset(){
            head = 0; // dequeue
			tail = 0; // enqueue
        }
		public final void add(int n) {
			list[tail] = n;
			tail = (tail + 1) & _cap;
		}

		public final int remove() {
			int ret = list[head];
			head = (head + 1) & _cap;
			return ret;
		}

		public final boolean empty() {
			return head == tail;
		}
	}

	public class ArrayList_29 {
		private int cap;
		private int len;
		private int[] elem;
		private int pointer;

		ArrayList_29() {
			this.cap = 16;
			this.len = -1;
			this.elem = new int[cap];
		}

		public final void add(int n) {
			if (++this.len != this.cap) {
				this.elem[this.len] = n;
			} else {
				this.cap <<= 1;
				int[] newElem = new int[this.cap];
				for (int i = 0; i < len; i++) {
					newElem[i] = elem[i];
				}
				newElem[this.len] = n;
				this.elem = newElem;
			}
		}

		public final void read() {
			this.pointer = 0;
		}

		public final boolean hasNext() {
			return pointer <= len;
		}

		public final int next() {
			return elem[pointer++];
		}
	}
}
