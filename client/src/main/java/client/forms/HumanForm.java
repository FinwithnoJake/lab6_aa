package client.forms;

import client.util.Interrogator;
import common.exceptions.IncorrectInputInScript;
import common.exceptions.InvalidForm;
import common.model.Human;

public class HumanForm extends Form<Human> {
    private final client.util.console.Console console;

    /**
     * Instantiates a new Human form.
     * @param console the console
     */
    public HumanForm(client.util.console.Console console) {
        this.console = console;
    }

    @Override
    public Human build() throws IncorrectInputInScript, InvalidForm {
        console.println("Введите null или пустую строку, чтобы остаться без имени. Любой другой ввод приведет к созданию личности");
        console.ps2();

        var fileMode = Interrogator.fileMode();
        String input = Interrogator.getUserScanner().nextLine().trim();
        if (fileMode) console.println(input);
        if (input.equals("null") || input.isEmpty())
            return null;
        return null;
    }
}