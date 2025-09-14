package server.commands;

import common.build.request.*;
import common.build.response.*;
import server.repo.CityRepository;

/**
 * Команда 'remove_by_id'. Удаляет элемент из коллекции.
 *
 */
public class RemoveById extends Command {
    private final CityRepository cityRepository;

    /**
     * Instantiates a new Remove by id.
     *
     * @param cityRepository the city repository
     */
    public RemoveById(CityRepository cityRepository) {
        super("remove_by_id <ID>", "удалить элемент из коллекции по ID");
        this.cityRepository = cityRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        var req = (RemoveByIdReq) request;

        try {
            if (!cityRepository.checkExist(req.id)) {
                return new RemoveByIdRes("City с таким ID в коллекции нет!");
            }

            cityRepository.remove(req.id);
            return new RemoveByIdRes(null);
        } catch (Exception e) {
            return new RemoveByIdRes(e.toString());
        }
    }
}