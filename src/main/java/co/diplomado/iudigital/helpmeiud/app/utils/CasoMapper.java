package co.diplomado.iudigital.helpmeiud.app.utils;

import org.springframework.stereotype.Component;

import co.diplomado.iudigital.helpmeiud.app.dtos.casos.CasoResponseDTO;
import co.diplomado.iudigital.helpmeiud.app.models.Caso;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CasoMapper {


    public List<CasoResponseDTO> toCasoResponseDTOList(List<Caso> casos) {
        return casos.stream()
                .map(caso -> toCasoResponseDTO(caso))
                .collect(Collectors.toList());
    }

    public CasoResponseDTO toCasoResponseDTO(Caso caso) {
        return CasoResponseDTO.builder()
                .id(caso.getId())
                .fechaHora(caso.getFechaHora())
                .latitud(caso.getLatitud())
                .longitud(caso.getLongitud())
                .altitud(caso.getAltitud())
                .visible(caso.getVisible())
                .descripcion(caso.getDescripcion())
                .urlMapa(caso.getUrlMapa())
                .rmiUrl(caso.getRmiUrl())
                .username(caso.getUsuario().getNombre() + " " + caso.getUsuario().getApellido())
                .delitoId(caso.getDelito().getId())
                .build();
    }
}
