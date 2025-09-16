package server.commands;

import common.req_res.request.Request;
import common.req_res.response.HeadRes;
import common.req_res.response.Response;
import server.repo.CityRepository;

/**
 * Команда 'head'. Выводит первый элемент коллекции.
 */
public class Head extends Command {
    private final CityRepository cityRepository;

    /**
     * Instantiates a new Head.
     * @param cityRepository the city repository
     */
    public Head(CityRepository cityRepository) {
        super("head", "вывести первый элемент коллекции");
        this.cityRepository = cityRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        try {
            return new HeadRes(cityRepository.first(), null);
        } catch (Exception e) {
            return new HeadRes(null, e.toString());
        }
    }
}