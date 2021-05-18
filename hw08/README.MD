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
