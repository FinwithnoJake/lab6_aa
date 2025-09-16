package server.commands;

import common.req_res.request.Request;
import common.req_res.response.Response;
import common.req_res.response.TailRes;
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