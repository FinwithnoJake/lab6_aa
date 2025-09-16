package server.commands;

import common.req_res.request.Request;
import common.req_res.response.ExitRes;
import common.req_res.response.Response;
import server.repo.CityRepository;

/**
 * Команда 'exit'. Завершает выполнение.
 */
public class Exit extends Command {
    private final CityRepository cityRepository;

    /**
     * Instantiates a new Clear.
     *
     * @param cityRepository the city repository
     */
    public Exit(CityRepository cityRepository) {
        super("exit", "выход из программы");
        this.cityRepository = cityRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        try {
            cityRepository.exit();
            return new ExitRes(null);
        } catch (Exception e) {
            return new ExitRes(e.toString());
        }
    }
}