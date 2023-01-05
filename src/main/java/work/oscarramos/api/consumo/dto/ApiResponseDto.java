package work.oscarramos.api.consumo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiResponseDto implements Serializable {
    private static final long serialVersionUID = 4676905652686424400L;

    private String icon_url;
    private String id;
    private String url;
    private String value;

    @Override
    public String toString() {
        return "ApiResponseDto{" +
                "icon_url='" + icon_url + '\'' +
                ", id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
