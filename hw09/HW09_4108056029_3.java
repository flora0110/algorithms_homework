public class HW09_4108056029_3 extends LSD {
    public static void main(String[] args) {
    		HW09_4108056029_3 test = new HW09_4108056029_3();
    		int[][] inputArr = { { 0, 1 }, { 0, 2 }, { 0, 4 }, { 1, 3 }, { 1, 4 }, { 2, 5 }, { 6, 7 } };	// 4
    		test.Distance(inputArr);
    }
	public int Distance(int[][] array) {
		Graph_4108056029_3 g = new Graph_4108056029_3(array.length + 1);
		for (int[] v : array) {
			g.addEdge(v[0], v[1]);
		}
		return g.findMaxDepth()-1;
	}
    public class Graph_4108056029_3{
        int cap;
        int V;
        public Head[] head;
        int maxDegree;
        int maxDegreeNode;
        class Head {
			int key;
			int degree;
            int visited;
            int visited_1;
            int distTo;
            int distTo_1;
			ArrayList_29 sub_node;
            Head next_head;
		}
        public Graph_4108056029_3(int V){
            this.cap = (1<<(int)(Math.log(V)/Math.log(2)+1))-1;
            this.V = cap+1;
            head =  new Head[this.V];
            maxDegree=0;
        }
        public void addEdge(int v,int w){
            int key_head = (v&cap);
            int key_head_w = (w&cap);
            for (Head c = head[key_head];; c = c.next_head) {
                if(c==null){
                    Head h = new Head();
                    h.key = v;
                    h.sub_node = new ArrayList_29();
                    //h.degree = 1;
                    h.sub_node.add(w);
                    h.next_head = head[key_head];
                    head[key_head] = h;
                    break;
                }
                else if(c.key==v){
                    if (++c.degree > maxDegree) {
    					maxDegree = c.degree;
    					maxDegreeNode = v;
    				}
                    c.sub_node.add(w);
                    break;
                }
            }
            for (Head c = head[key_head_w];; c = c.next_head) {
                if(c==null){
            	    Head h = new Head();
                    h.key = w;
                    h.sub_node = new ArrayList_29();
                    //h.degree = 1;
                    h.sub_node.add(v);
                    h.next_head = head[key_head_w];
                    head[key_head_w] = h;
                    break;
                }
                else if(c.key==w){
                    if (++c.degree > maxDegree) {
    					maxDegree = c.degree;
    					maxDegreeNode = w;
    				}
                    c.sub_node.add(v);
                        break;
                }
            }
        }
        public Head get_head(int n){
            for (Head c = head[n&cap]; c != null; c = c.next_head) {
                if (c.key == n) return c;
    		}
    		return null;
        }
        int maxDepthNode;
        int maxDepth;
        FiniteQueue_29 q;
        int a;
        final int findMaxDepth() {
            maxDepth=0;
            q = new FiniteQueue_29(V);

            Head h = get_head(maxDegreeNode);
            h.visited_1=1;
            //h.distTo_1=0;
            q.reset();
            q.add(maxDegreeNode);
            int self_depth;
			while (!q.empty()) {
				a = q.remove();
                h = get_head(a);
				self_depth = h.distTo_1 + 1;
				if (self_depth > maxDepth) {
					maxDepth = self_depth;
                    maxDepthNode = a;
				}
				ArrayList_29 arr = h.sub_node;
				for (arr.read(); arr.hasNext();) {
					int n = arr.next();
                    h=get_head(n);
                    if (h.visited_1<1) {
                        h.distTo_1 = self_depth;
                        h.visited_1=1;
						q.add(n);
					}
				}
			}

            h = get_head(maxDepthNode);
            h.visited=1;
            //h.distTo=0;
            q.reset();
            q.add(maxDepthNode);
			while (!q.empty()) {
				a = q.remove();
                h = get_head(a);
				self_depth = h.distTo + 1;
				if (self_depth > maxDepth) {
					maxDepth = self_depth;
                    //maxDepthNode = a;
				}
				ArrayList_29 arr = h.sub_node;
				for (arr.read(); arr.hasNext();) {
					int n = arr.next();
                    h=get_head(n);
                    if (h.visited<1) {
                        h.distTo = self_depth;
                        h.visited=1;
						q.add(n);
					}
				}
            }


			return maxDepth;
		}
    }
	class FiniteQueue_29 {
		private int _cap;
		private int head;
		private int tail;
		private int[] list;

		FiniteQueue_29(int cap) {
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
