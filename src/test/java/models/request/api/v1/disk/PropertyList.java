package models.request.api.v1.disk;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PropertyList {

    private String custom_name;
    private String custom_data;

}
