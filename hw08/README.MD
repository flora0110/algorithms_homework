# hw08
fastest version : 4  
## mergesort  
* 在merge時先篩選一次  
```
if(aux[i][0] > aux[j][0]) {
    if((aux[i][1] < aux[j][1])||(((aux[i][2] < aux[j][2])|| (aux[i][3] < aux[j][3]))||((aux[i][4] < aux[j][4])||(aux[i][5] < aux[j][5])))) array[k++] = aux[j++];
    else{//刪掉輸了的i
        top--;
        j++;
    }
}
```
top紀錄最後合併時的array的最後一格，並return 回 sort  
* mergesort後以第一個變數為優先排好
## bestphone  
因為已排好，所以前項不可能代替後項，由後往前和確定留下的phone比就好  
```
        for(int i=top-1;i>=0;i--){//control array[i]
            check=1;
            for(int j=0;j<num;j++){//control exist[j]
                win=0;
                for(int h=1;h<6;h++){
                    if(array[i][h]>exist[j][h]){
                        win=1;
                    }
                }
                if(win==0){ check=0; break;}
            }
            if(check==1) exist[num++] = array[i];
        }
```  
1	HW08_4108056020_3	Correct	0.001532  
2	HW08_4108056020_4	Correct	0.001555  
3	HW08_4108056018_2	Correct	0.001835  
<font color=#FF0000>4	HW08_4108056029_4	Correct	0.002127</font>   
<font color=#FF6600>橘色</font>	
<span style="color:blue">some *blue* text</span>.  
## 改進方向  
把merge()塞進sort()減少return時間?
