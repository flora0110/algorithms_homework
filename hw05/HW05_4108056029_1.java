
public class HW05_4108056029_1 extends LLK{
    int n;
    static boolean ans=false;
    public boolean checkLLK(int[][] array){
        double len,x_dif,y_dif,unit_x,unit_y;
        n=array.length;
        double[][] code = new double[n][n];
        int i,j,k;
        double[][] buckets = new double[n][n];
        int[][] count = new int[n][n];
        for(i=0;i<n;i++){
            for(j=i+1;j<n;j++){
                //System.out.println("x : "+array[i][0]+" "+array[j][0]);
                x_dif = array[i][0]-array[j][0];
                y_dif = array[i][1]-array[j][1];
                //System.out.println("x_dif : "+x_dif+" y_dif : "+y_dif);
                len = Math.sqrt((x_dif*x_dif)+(y_dif*y_dif));
                unit_x=x_dif/len;
                unit_y=y_dif/len;
                if(unit_y<0){
                    unit_x = -unit_x;
                    unit_y = -unit_y;
                }
                //System.out.println("len: "+len+" unit_x : "+unit_x+" unit_y : "+unit_y);
                if(unit_x < 1/Math.sqrt(2)){
                    code[i][j]=(unit_x+unit_y)*100+100;
                    code[j][i]=code[i][j];
                }
                else{
                    code[i][j]=(unit_x+unit_y)*100+142;
                    code[j][i]=code[i][j];
                }
            }
        }
        int now;
        for(i=0;i<n && !ans;i++){
            for(j=i+1;j<n && !ans;j++){
                //System.out.println("i : "+i+" j :"+j);

                now=(int)code[i][j]*(n/283);
                buckets[now][count[i][now]] = code[i][j];
                //System.out.println("i : "+i+" j :"+j+" code: "+code[i][j]);
                for(k=0;k<count[i][now];k++){
                    if(buckets[now][k]==code[i][j]){
                        ans=true;
                        break;
                    }
                }
                count[i][now]++;
            }
        }
        return ans;
    }
    public static void main(String[] args){
        HW05_4108056029_1 test = new HW05_4108056029_1();
        int[][] arr = {{1,1},{2,2},{3,2},{4,1}};
        System.out.println(test.checkLLK(arr));
    }
}
