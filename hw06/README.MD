# hw06
>Your program will take an integer arrays as input.  
>Please return the maximum possible number of blocks in a valid partitioning.  
>A valid partitioning of the integer array is defined as follows.  
>The numbers are split into blocks — groups of consecutive numbers. The blocki,j consists of number ni, ni + 1, ..., nj. A block can have only a single number.  
>Every number belongs to one block only.  
>The partitioning should satisfy the condition that after each block is sorted, the whole sequence becomes sorted as well.  
>Input :  
>[[1, 1, 1, 1, 1, 1, 1],  
> [1, 3, 5, 7, 9],  
> [1, 2, 3],  
> [5, 4, 3, 2, 1],  
> [2, 1, 3, 2]]  
> Output :  
> [7, 5, 3, 1, 2]  
```
        for(i=0;i<n;i++){
            len = inputArr[i].length;
            max = inputArr[i][0];
            min[len-1] = inputArr[i][len-1];
            for(j=len-2;j>=0;j--){
                min[j] = Math.min(min[j+1],inputArr[i][j]);
            }
            for(j=0;j<len-1;j++){
                if(inputArr[i][j]>max) max = inputArr[i][j];
                if(max<=min[j+1]){
                    ans[i]++;
                }
            }
            ans[i]++;
        }
```
* 先從最後到前找出到目前格數時的最小值
* 在從前到後找出到目前格數時的最大值
* 若n格的最大 <= n+1格的最小 -> 不用交換 -> num_of_gap++
* num of block = num of gap+1
