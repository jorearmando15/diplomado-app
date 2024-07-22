package co.diplomado.iudigital.helpmeiud.app.dtos.casos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class CasoRequestDTO implements Serializable {

    static final long serialVersionUID = 1L;

    @JsonProperty("fecha_hora")
    LocalDateTime fechaHora; 

    Float latitud;

    Float longitud;

    Float altitud;

    String descripcion;

    @JsonProperty("url_mapa")
    String urlMapa;

    @JsonProperty("rmi_url")
    String rmiUrl;

    @JsonProperty("delito_id")
    @NotNull(message = "delito Id requerido")
    Long delitoId;
}
