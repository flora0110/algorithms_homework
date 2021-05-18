# hw08
fastest version : 4
## mergesort  
在merge時先篩選一次
`if(aux[i][0] > aux[j][0]) {
                    //if(aux[i][1] < aux[j][1]) array[k++] = aux[j++];
                    if((aux[i][1] < aux[j][1])||(((aux[i][2] < aux[j][2])|| (aux[i][3] < aux[j][3]))||((aux[i][4] < aux[j][4])||(aux[i][5] < aux[j][5])))) array[k++] = aux[j++];
                    else{
                        top--;
                        j++;
                        //k_plusplus=0;
                    }
                }`
