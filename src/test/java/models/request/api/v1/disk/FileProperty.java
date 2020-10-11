package models.request.api.v1.disk;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FileProperty {

    private PropertyList custom_properties;

}
