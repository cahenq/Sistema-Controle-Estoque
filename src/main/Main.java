package main;

import service.Estoque;
import ui.MenuConsole;

public class Main {
    public static void main(String[] args) {
        Estoque estoque = new Estoque();

        MenuConsole menu = new MenuConsole(estoque);

        menu.iniciar();
    }
}
