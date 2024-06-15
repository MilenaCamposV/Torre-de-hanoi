/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hanoi.frontend;

import cores.StringColorida;
import mecanicas.Carta;

/**
 *
 * @author milena
 */
public class Disco extends Carta {
    private final StringColorida stringColorida;

    public Disco(String valor) {
        super(new StringColorida(valor, "vermelho", "preto"));
        stringColorida = new StringColorida(valor, "vermelho", "preto");
    }

    public StringColorida getCor() {
        return stringColorida;
    }
}
