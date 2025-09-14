package common.build.request;

import common.model.City;
import common.util.Commands;

/**
 * The type Add req.
 */
public class AddReq extends Request {
    public final City city;
    public AddReq(City city) {
         super(Commands.ADD);
         this.city = city;
        }
    }

