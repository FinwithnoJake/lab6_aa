package server.commands;

import common.req_res.response.ClearRes;
import common.req_res.request.Request;
import common.req_res.response.Response;
import server.repo.CityRepository;

/**
 * Команда 'clear'. Очищает коллекцию.
 *
 */
public class Clear extends Command {
    private final CityRepository cityRepository;

    /**
     * Instantiates a new Clear.
     *
     * @param cityRepository the city repository
     */
    public Clear(CityRepository cityRepository) {
        super("clear", "очистить коллекцию");
        this.cityRepository = cityRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        try {
            cityRepository.clear();
            return new ClearRes(null);
        } catch (Exception e) {
            return new ClearRes(e.toString());
        }
    }
}