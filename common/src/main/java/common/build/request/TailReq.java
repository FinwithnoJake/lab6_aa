package common.build.request;

import common.util.Commands;

/**
 * The type Head req.
 */
public class TailReq extends Request {
    /**
     * Instantiates a new Head req.
     */
    public TailReq() {
        super(Commands.TAIL);
    }
}