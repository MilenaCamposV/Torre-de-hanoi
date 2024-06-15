/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hanoi.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author milena
 */
public class Jogo {
    
    private int[] torre1;
    private int[] torre2;
    private int[] torre3;
    private int discos;

    public Jogo() {}
    
    /**
     * @return the torre1
     */
    public int[] getTorre1() {
        return torre1;
    }

    /**
     * @return the torre2
     */
    public int[] getTorre2() {
        return torre2;
    }

    /**
     * @return the torre3
     */
    public int[] getTorre3() {
        return torre3;
    }
    
    /**
     * @return the discos
    **/
    public int getDiscos() {
        return discos;
    }

    
    public boolean ganhouJogo() {
        return getTorre3()[discos - 1] != 0;
    }
    
    public void novoJogo(int numDiscos) {
        discos = numDiscos;

        torre1 = new int[discos];
        torre2 = new int[discos];
        torre3 = new int[discos];
        for (int i = discos; i >= 1; i--) {
            torre1[discos - i] = i;
        }
    }
    
    public void carregarJogo() {
        int linha = 0;
        String valor = "";
            
        try {
            BufferedReader arquivo = new BufferedReader(new FileReader("dado.txt"));
            while (true) {
                
                if(linha == 0) {
                    discos = Integer.parseInt(arquivo.readLine());
                    
                    torre1 = new int[discos];
                    torre2 = new int[discos];
                    torre3 = new int[discos];

                } else if (linha == 1) {
                    carregarTorre(arquivo.readLine(), torre1);
                } else if (linha == 2) {
                    carregarTorre(arquivo.readLine(), torre2);
                } else if (linha == 3) {carregarTorre(arquivo.readLine(), torre3);
                }
                
                linha++;

                if(valor == null)
                    break;
            }
            arquivo.close();

        } catch(Exception e) {}
    }
    
    private void carregarTorre(String linha, int[] torre) {
        String[] valores = linha.split(", ");
        for (int i = 0; i < discos; i++) {
            torre[i] = Integer.parseInt(valores[i]); // Convert each string element to integer
        }
    }
    
    public void salvarJogo() {
        try {
            FileWriter arquivo = new FileWriter("dado.txt");
            PrintWriter escrever = new PrintWriter(arquivo);

            escrever.println(discos);
            escrever.println(printTorre(torre1));
            escrever.println(printTorre(torre2));
            escrever.println(printTorre(torre3));

            arquivo.close();
        } catch (Exception e) {}
    }
    
    
    public String printTorre(int[] torre) {
        int x = torre.length - 1;
        String texto = "";

        for (int i = 0; i <= x; i++) {
            texto += torre[i];
            if (i < x) {
                texto += ", ";
            }
        }
        return texto;
    }
    
    public String movimentarDisco(int de, int para) {
        salvarJogo();
        
        if (de < 0 || para > 3) {
            return "Torre selecionada inválida.";
                        
        } else if(de == 1 && para == 2) {
            return moverTorre(torre1, torre2);

        } else if (de == 1 && para == 3) {
            return moverTorre(torre1, torre3);

        } else if (de == 2 && para == 1) {
            return moverTorre(torre2, torre1);

        } else if (de == 2 && para == 3) {
            return moverTorre(torre2, torre3);

        } else if (de == 3 && para == 1) {
            return moverTorre(torre3, torre1);

        } else if (de == 3 && para == 2) {
            return moverTorre(torre3, torre2);

        } else {
            return "Movimento inválido";
        }
    }

    private String moverTorre(int[] de, int[] para) {
        int ultimaPosicaoDe = 0;
        for(int i = 0; i < de.length; i++) {
            if(de[i] != 0) {
                ultimaPosicaoDe = i;
            }
        }

        int ultimaPosicaoPara = 0;
        for(int i = 0; i < para.length - 1; i++) {
            if(para[i] != 0) {
                ultimaPosicaoPara = i + 1;
            }
        }

        int ultimoValorDe = de[ultimaPosicaoDe];
        int ultimoValorPara = para[ultimaPosicaoPara];

        if(ultimaPosicaoPara > 0 )
            ultimoValorPara = para[ultimaPosicaoPara - 1];

        if(ultimoValorDe == 0) {
            return "Não há discos para mover na torre.";
        } else if (ultimoValorPara != 0 && ultimoValorDe > ultimoValorPara) {
            return "Não é possível colocar um disco maior sobre um menor.";
        } else {
            de[ultimaPosicaoDe] = 0;
            para[ultimaPosicaoPara] = ultimoValorDe;
        }
        
        return "";
    }
}
