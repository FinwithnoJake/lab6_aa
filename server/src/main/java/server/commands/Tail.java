package server.commands;

import common.build.request.Request;
import common.build.response.Response;
import common.build.response.TailRes;
import server.repo.CityRepository;

/**
 * Команда 'tail'. Выводит последний элемент коллекции.
 */
public class Tail extends Command {
    private final CityRepository cityRepository;

    /**
     * Instantiates a new Tail.
     * @param cityRepository the city repository
     */
    public Tail(CityRepository cityRepository) {
        super("tail", "вывести последний элемент коллекции");
        this.cityRepository = cityRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        try {
            return new TailRes(cityRepository.last(), null);
        } catch (Exception e) {
            return new TailRes(null, e.toString());
        }
    }
}