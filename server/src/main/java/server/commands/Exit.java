package server.commands;

import common.build.response.ClearRes;
import common.build.request.Request;
import common.build.response.ExitRes;
import common.build.response.Response;
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