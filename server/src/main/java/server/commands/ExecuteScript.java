package server.commands;

import common.req_res.request.ExecuteScriptReq;
import common.req_res.request.Request;
import common.req_res.response.ExecuteScriptRes;
import common.req_res.response.Response;
import server.repo.CityRepository;

import java.io.File;

/**
 * Команда 'execute_script'. Выполняет скрипт из файла.
 */
public class ExecuteScript extends Command {
    private final CityRepository cityRepository;

    /**
     * Instantiates a new Execute script.
     * @param cityRepository the city repository
     */
    public ExecuteScript(CityRepository cityRepository) {
        super("execute_script <file_name>", "запустить код из скрипта");
        this.cityRepository = cityRepository;
    }


    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        var req = (ExecuteScriptReq) request;

        try {
            File scriptFile = new File(req.getScriptPath());
            if (!scriptFile.exists()) {
                return new ExecuteScriptRes("Файл скрипта не найден: " + req.getScriptPath());
            }
            cityRepository.executeScript(req.id);
            return new ExecuteScriptRes(null);
        } catch (Exception e) {
            return new ExecuteScriptRes(e.toString());
        }
    }
}

