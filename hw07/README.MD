# hw07
>Your program will take a two-dimensional integer array as input.   
>Each element represents a smartphone model which contains two features (e.g., screen size and performance ranking).  
>Please return an array containing a list of unbeatable smartphone models (In other words, filter out the phone models that can be beat by some model).  
>Given two items A =(Xa, Ya) and B =(Xb, Yb), Item A beats Item B iff xa≥xb and ya≥yb  
>Input:[[1,1],[2,4],[2,10],[5,4],[4,8],[5,5],[8,4],[10,2],[10,1]]  
>Output: [[2,10],[4,8],[5,5],[8,4],[10,2]]  

fastest vesion : 2  
**best version : 1**
## bestphone  
先排好(x優先)
```
for(int i=n-2;i>=0;i--){
  if(inputArr[i][1]>maxy){
    remind[num--] = inputArr[i];
    maxy = inputArr[i][1];
  }
}
```
從後往前，紀錄路目前最大y，因為前項x>=後項x，所以須贏maxy才可留  
1	HW07_4108056022_2	Correct	0.001129  
2	HW07_4108056007_5	Correct	0.001168  
3	HW07_4108056022_3	Correct	0.001189  
4	HW07_4108056020_1	Correct	0.001191  
5	HW07_4108056036_4	Correct	0.001266  
6	HW07_4108056036_3	Correct	0.00131  
7	HW07_8108056001_1	Correct	0.001415  
**8	HW07_4108056029_2	Correct	0.001468**  

