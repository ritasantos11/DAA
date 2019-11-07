// Disse que disse

import java.util.*;

class A {
    public static void printGroup(int x[], int size) {
        System.out.print(size + " ");
        int max=1, j=0;
        for (int i=0; i<size; i++) {
            if (x[i] > max) {
                max = x[i];
                j=i;
            }
        }

        System.out.print(max + " ");
        for (int i=j+1; i<size; i++) {
            System.out.print(x[i] + " ");
        }

        int i=0;
        while (i!=j) {
            System.out.print(x[i]);
            i++;
        }
            
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int v[] = new int[n+1];
        Boolean visitados[] = new Boolean[n+1];
        for (int i=1; i<=n; i++) {
            v[i] = stdin.nextInt();
            visitados[i] = false;
        }

        int j, corrente, size=0, cont=0, k;
        int x[] = new int[n+1];
        for (int i=1; i<=n; i++) {
            if (visitados[i] == false) {
                j = i;
                corrente = i;
                k=0; size=0;
                do {
                    visitados[corrente] = true;
                    corrente = v[corrente];
                    x[k] = corrente;
                    k++;
                    size++;
                } while(corrente!=j);

                if (size==1) cont++;
                else if (size>=3) printGroup(x,size);
            }
        }

        System.out.println(cont);
    }
}
