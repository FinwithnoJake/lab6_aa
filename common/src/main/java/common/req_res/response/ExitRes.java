package common.req_res.response;

import common.util.Commands;

/**
 * The type Clear res.
 */
public class ExitRes extends Response {
    /**
     * Instantiates a new Clear res.
     *
     * @param error the error
     */
    public ExitRes(String error) {
        super(Commands.EXIT, error);
    }
}