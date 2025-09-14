package server.commands;

import common.build.request.Request;
import common.build.response.HeadRes;
import common.build.response.Response;
import server.repo.CityRepository;
import server.repo.CityRepository;

/**
 * Команда 'head'. Выводит первый элемент коллекции.
 *
 */
public class Head extends Command {
    private final CityRepository cityRepository;

    /**
     * Instantiates a new Head.
     *
     * @param cityRepository the human being repository
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