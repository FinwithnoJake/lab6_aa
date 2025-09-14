package common.build.request;

import common.util.Commands;

/**
 * The type Remove by id req.
 */
public class ExecuteScriptReq extends Request {
    /**
     * The Id.
     */
    public final int id;

    /**
     * Instantiates a new Execute Script req.
     * @param id the id
     */
    public ExecuteScriptReq(int id) {
        super(Commands.EXECUTE_SCRIPT);
        this.id = id +1;
    }

    public String getScriptPath() {
        return null;
    }
}
