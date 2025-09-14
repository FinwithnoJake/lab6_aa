package client.command;

import client.util.console.Console;
import common.util.Commands;

/**
 * Команда 'exit'. Завершает выполнение.
 *
 */
public class Exit extends Command {
    private final Console console;

    /**
     * Instantiates a new Exit.
     *
     * @param console the console
     */
    public Exit(Console console) {
        super("exit");
        this.console = console;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean apply(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            console.println("Использование: '" + getName() + "'");
            return false;
        }

        console.println("Завершение выполнения...");
        console.println("""
                Так, на этом, пожалуй, закончим,
                хватит бедолагу мучить""".indent(1));
        return true;
    }
}