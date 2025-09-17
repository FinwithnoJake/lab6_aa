package server.commands;

import common.req_res.request.*;
import common.req_res.response.*;
import server.repo.CityRepository;

/**
 * Команда 'update'. Обновляет элемент коллекции.
 *
 */
public class Update extends Command {
    private final CityRepository cityRepository;

    /**
     * Instantiates a new Update.
     *
     * @param cityRepository the city repository
     */
    public Update(CityRepository cityRepository) {
        super("update <ID> {element}", "обновить значение элемента коллекции по ID");
        this.cityRepository = cityRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        var req = (UpdateReq) request;
        try {
            if (!cityRepository.checkExist(req.id)) {
                return new UpdateRes("City с таким ID в коллекции нет!");
            }
            if (!req.updatedCity.validate()) {
                return new UpdateRes( "Поля City не валидны! City не обновлен!");
            }

            cityRepository.getById(req.id).update(req.updatedCity);
            return new UpdateRes(null);
        } catch (Exception e) {
            return new UpdateRes(e.toString());
        }
    }
}