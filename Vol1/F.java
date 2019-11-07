// Avesso à tecnologia

import java.util.*;

class Pessoa {
    int cod, ntarefas, maxtarefas;
    int idtarefas[];

    Pessoa(int codigo, int nt, int maxt, int idt[]) {
        cod = codigo;
        ntarefas = nt;
        maxtarefas = maxt;
        idtarefas = idt;
    }
}

class F {
    public static int avaliarpropostas(int idtarefa, int idpessoa, Pessoa pessoas[], int npessoas)  {
        int naceites=0, v=0, i=0;
        for (i=0; i<npessoas; i++) {
            if (idpessoa == pessoas[i].cod) {
                for (int j=0; j<pessoas[i].idtarefas.length; j++) {
                    if (pessoas[i].idtarefas[j] == idtarefa) {
                        naceites++;
                        v=1;
                    }
                }
            }
        }
        

        return naceites;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int npessoas = stdin.nextInt();
        int ntarefas = stdin.nextInt();
        Pessoa pessoas[] = new Pessoa[npessoas];
        int idt[] = new int[ntarefas];
        
        for (int i=0; i<npessoas; i++) {
            int cod = stdin.nextInt();
            int ntarp = stdin.nextInt();
            int maxtarefas = stdin.nextInt();
            
            for (int j=0; j<ntarp; j++) {
                idt[j] = stdin.nextInt();
            }

            Pessoa p = new Pessoa(cod, ntarp, maxtarefas, idt);
            pessoas[i] = p;
        }

        int npropostas = stdin.nextInt();
        int naceites=0, naceitesP=0;
        int idtarefa = stdin.nextInt();
        int idpessoa = stdin.nextInt();
        boolean visitados[] = new boolean[100];
        for (int i=0; i<100; i++)
            visitados[i] = false;

        for (int i=0; i<npropostas; i++) {
            while (idtarefa!=0 && idpessoa!=0) {
                //if (!visitados[idtarefa]) {
                  //  visitados[idtarefa] = true;
                    naceitesP = avaliarpropostas(idtarefa, idpessoa, pessoas, npessoas);
                    naceites += naceitesP;
                //}
                idtarefa = stdin.nextInt();
                idpessoa = stdin.nextInt();
            }
        }

        System.out.println(naceites + "/" + npropostas);
    }
}