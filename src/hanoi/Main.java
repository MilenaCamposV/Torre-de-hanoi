/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hanoi;

import hanoi.frontend.Disco;
import hanoi.frontend.Hanoi;

/**
 *
 * @author milena
 */
public class Main {
    static Hanoi hanoi;
    
    public static void main(String[] args) {
        hanoi = new Hanoi(6, 40, new Disco(" "));
        hanoi.iniciar();
    }
}
