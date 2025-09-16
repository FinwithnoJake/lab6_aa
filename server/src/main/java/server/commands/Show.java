package server.commands;

import common.req_res.request.Request;
import common.req_res.response.*;
import server.repo.CityRepository;

/**
 * Команда 'show'. Выводит все элементы коллекции.
 */
public class Show extends Command {
    private final CityRepository cityRepository;

    /**
     * Instantiates a new Show.
     *
     * @param cityRepository the city repository
     */
    public Show(CityRepository cityRepository) {
        super("show", "вывести все элементы коллекции");
        this.cityRepository = cityRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        try {
            return new ShowRes(cityRepository.sorted(), null);
        } catch (Exception e) {
            return new ShowRes(null, e.toString());
        }
    }
}