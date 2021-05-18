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
