package server.commands;

import common.req_res.request.Request;
import common.req_res.response.Response;

/**
 * The interface Executable.
 */
public interface Executable {
    /**
     * Выполнить что-либо.
     *
     * @param request запрос с данными для выполнения команды
     * @return результат выполнения
     */
    Response apply(Request request);
}