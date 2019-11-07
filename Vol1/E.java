// Cigarras Tontas

import java.util.*;

class E {
    public static int procurar(int local, int locais[], int i) {
        int j;
        for (j=0; j<i; j++) {
            if (locais[j] == local) {
                locais[j] = local;
                break;
            }
        }

        return j;
    }
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int locais[] = new int[30];
        int i=0, local, k=0;
        locais[i] = stdin.nextInt();
        while (locais[i]!=0) {
            i++;
            local = stdin.nextInt();
            k = procurar(local, locais, i);
            i=k;
            locais[i] = local;
        }

        for (int j=0; j<i; j++)
            System.out.println(locais[j]);
    }
}
