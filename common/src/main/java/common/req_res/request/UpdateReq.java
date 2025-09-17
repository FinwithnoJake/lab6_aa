package common.req_res.request;

import common.model.City;
import common.util.Commands;

/**
 * The type Update req.
 */
public class UpdateReq extends Request {
    /**
     * The ID.
     */
    public final int id;
    /**
     * The Updated city.
     */
    public final City updatedCity;

    /**
     * Instantiates a new Update req.
     *
     * @param id            the id
     * @param updatedCity the updated city
     */
    public UpdateReq(int id, City updatedCity) {
        super(Commands.UPDATE);
        this.id = id;
        this.updatedCity = updatedCity;
    }
}
