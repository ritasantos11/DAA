// Sentar ou não sentar

import java.util.*;

class C {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int ntipos = stdin.nextInt();
        int tipos[] = new int[ntipos+1];
        boolean[] visitado = new boolean[ntipos+1];
        for (int i=1; i<=ntipos; i++) {
            int tipo = stdin.nextInt();
            tipos[tipo] = stdin.nextInt();
            visitado[tipo] = false;
        }

        int cadeiras=0;
        for (int i=1; i<=ntipos; i++)
            cadeiras += tipos[i];

        int npref, pref, pessoassentadas=0, cadusadas=0, n;
        int numH = stdin.nextInt();
        for (int i=0; i<numH; i++) {
            npref = stdin.nextInt();
            for (int j=1; j<=npref; j++) {
                pref = stdin.nextInt();
                if (!visitado[pref] && tipos[pref]>0) {
                    visitado[pref] = true;
                    cadusadas++;
                    pessoassentadas++;
                    tipos[pref]--;

                    for (int k=j+1; k<=npref; k++)
                        n = stdin.nextInt();
                    break;
                }
                
                else if (visitado[pref] && tipos[pref]>0) {
                    cadusadas++;
                    pessoassentadas++;
                    tipos[pref]--;

                    for (int k=j+1; k<=npref; k++)
                        n = stdin.nextInt();
                    break;
                }
            } 
        }
       
        System.out.println(numH-pessoassentadas);
        System.out.println(cadeiras-cadusadas);
    }
}
