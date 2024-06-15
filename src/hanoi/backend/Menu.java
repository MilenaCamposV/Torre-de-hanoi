/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hanoi.backend;

import console.Console;

/**
 *
 * @author milena
 */
public class Menu {
    public Menu () {}
    
    public void printMenuPrincipal() {
        Console.println("HANOI");
        Console.println("Aperte S para sair do jogo");
        Console.println("Aperte N para um novo do jogo");
        Console.println("Aperte C para carregar um novo do jogo");
    }
    
    public void printMenuPause() {
        Console.println("N - Novo jogo");
        Console.println("C - Carregar");
        Console.println("S - Salvar partida");
        Console.println("V - Voltar ao jogo");
        Console.println("M - Voltar ao menu principal");
        Console.println("E - Sair do jogo");
    }
}
