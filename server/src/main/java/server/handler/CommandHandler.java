package server.handler;

import common.req_res.request.Request;
import common.req_res.response.Response;
import common.req_res.response.NoSuchCommandRes;
import server.managers.CommandManager;

/**
 * The type Command handler.
 */
public class CommandHandler {
    private final CommandManager manager;

    /**
     * Instantiates a new Command handler.
     *
     * @param manager the manager
     */
    public CommandHandler(CommandManager manager) {
        this.manager = manager;
    }

    /**
     * Handle response.
     *
     * @param request the request
     * @return the response
     */
    public Response handle(Request request) {
        var command = manager.getCommands().get(request.getName());
        if (command == null) return new NoSuchCommandRes(request.getName());
        return command.apply(request);
    }
}