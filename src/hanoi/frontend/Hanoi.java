/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hanoi.frontend;

import console.Console;
import hanoi.backend.Jogo;
import hanoi.backend.Menu;
import mecanicas.Carta;
import mecanicas.Tabuleiro;

/**
 *
 * @author milena
 */
public class Hanoi extends Tabuleiro {
    Jogo jogo;
    Menu menu;
    Disco disco;
    Disco torreDisco;

    public Hanoi(int numLinhas, int numColunas, Carta fundo) {
        super(numLinhas, numColunas, fundo);
    }   
    
    public void iniciar() {
        menu = new Menu();
        jogo = new Jogo();
        
        disco = new Disco("\u0017");
        torreDisco = new Disco("|");

        printMenuPrincipal();
    }
    
    private void Jogar() {
        Console.println("Aperte P para pausar o jogo");
        
        printTorres();
        while (!jogo.ganhouJogo()) {
            Console.println("Selecione a torre que deseja mover [1, 2, 3]:");
            int entrada1 = getTecla();
               
            Console.println("Selecione a torre que deseja mover [1, 2, 3]:");
            int entrada2 = getTecla();
             
            String retorno = jogo.movimentarDisco(entrada1, entrada2);
            if(!"".equals(retorno))
                Console.println(retorno);

            printTorres();
        }
        
        if(jogo.ganhouJogo())
        {
            Console.println("Parabéns, você ganhou!");
            printMenuPrincipal();
        }
    }  

    private int getTecla() {
        String entrada;

        do {
            entrada = Console.input();
            try {
                int valor = Integer.parseInt(entrada);
                return valor;
            
            } catch(Exception e) {
                Console.println("Valor inválido");
            }
            
        } while (!"P".equals(entrada.toUpperCase()));
        
        boolean opcaoValida = true;
        do {
            menu.printMenuPause();
            String pause = Console.input();
            pause = pause.toUpperCase();

            if("M".equals(pause)) {
                printMenuPrincipal();
            } else if ("E".equals(pause)) {
                Console.println("Obrigado por jogar!");
                Console.saiDoPrograma();
            } else if ("N".equals(pause)) {
                int discos = 5;
                boolean sucesso = false;
                do {
                    try {
                        discos = Integer.parseInt(Console.input("Quantos discos você deseja? "));
                        sucesso = true;
                    } catch(NumberFormatException e) {
                        Console.println("Valor inválido");
                    }
                } while(!sucesso);

                jogo.novoJogo(discos);
                Jogar();
            } else if ("C".equals(pause)) {
                jogo.carregarJogo();
                Jogar();
            } else if ("S".equals(pause)) {
                jogo.salvarJogo();
                Console.println("Jogo salvo!");
                Jogar();
            } else if ("V".equals(pause)) {
                Jogar();
            } else {
                Console.println("Opção inválida");
                opcaoValida = false;
            }
        } while(!opcaoValida);
        
        return 0;
    }
    
    private void printTorres() {
        for(int i = 0; i < getTotalLinhas(); i++) {
            for(int j = 0; j < 40; j++) {
                setFundo(i, j, new Disco(" "));
            }
        }
        System.out.println(this); 

        for(int i = jogo.getDiscos(); i > 0; i--) {
            printDiscos(jogo.getTorre1(), i, 1);
            printDiscos(jogo.getTorre2(), i, 10);
            printDiscos(jogo.getTorre3(), i, 15);
        }
        
        System.out.println(this); 
    }
    
    private void printDiscos(int[] torre, int i, int coluna) {
        int linha = i;
        if(i > 0)
            i = jogo.getDiscos() - i;
        
        int valor = torre[i];
        if(valor == 0) {
            setFundo(linha, coluna, torreDisco);
            return;
        }
        
        for(int j = 0; j < valor; j++) {
            setFundo(linha, coluna + j, disco);
        }
    }
    
    private void printMenuPrincipal() {
        boolean opcaoValida = true;

        do {
            menu.printMenuPrincipal();
            String entrada = Console.input();
            entrada = entrada.toUpperCase();
            
            if("N".equals(entrada)) {
                int discos = 5;
                boolean sucesso = false;
                do {
                    try {
                        discos = Integer.parseInt(Console.input("Quantos discos você deseja? "));
                        sucesso = true;
                    } catch(Exception e) {
                        Console.println("Valor inválido");
                    }
                } while(!sucesso);
                
                jogo.novoJogo(discos);
                Jogar();
            } else if("C".equals(entrada)) {
                // TODO ler quantidade de colunas no arquivo
                jogo.carregarJogo();
                Jogar();
            } else if("S".equals(entrada)) {
                Console.println("Obrigado por jogar!");
                Console.saiDoPrograma();
            } else {
                opcaoValida = false;
            }
        } while(!opcaoValida);
    }
}
