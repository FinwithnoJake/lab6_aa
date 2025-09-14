package server.commands;

import common.build.request.*;
import common.build.response.*;
import server.repo.CityRepository;

/**
 * Команда 'add'. Добавляет новый элемент в коллекцию.
 *
 */
public class Add extends Command {
    private final CityRepository cityRepository;

    /**
     * Instantiates a new Add.
     *
     * @param cityRepository the city repository
     */
    public Add(CityRepository cityRepository) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.cityRepository = cityRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        var req = (AddReq) request;
        try {
            if (!req.city.validate()) {
                return new AddRes(-1, "Поля city не валидны! Cityне добавлен!");
            }
            var newId = cityRepository.add(req.city);
            return new AddRes(newId, null);
        } catch (Exception e) {
            return new AddRes(-1, e.toString());
        }
    }
}