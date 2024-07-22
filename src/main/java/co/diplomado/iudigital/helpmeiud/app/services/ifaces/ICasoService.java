package co.diplomado.iudigital.helpmeiud.app.services.ifaces;

import org.springframework.security.core.Authentication;

import co.diplomado.iudigital.helpmeiud.app.dtos.casos.CasoRequestDTO;
import co.diplomado.iudigital.helpmeiud.app.dtos.casos.CasoResponseDTO;
import co.diplomado.iudigital.helpmeiud.app.exceptions.RestException;

import java.util.List;

public interface ICasoService {

    List<CasoResponseDTO> consultarCasos() throws RestException;

    List<CasoResponseDTO> consultarCasosVisibles() throws RestException;

    List<CasoResponseDTO> consultarCasosPorUsuario(String username) throws RestException;

    CasoResponseDTO consultarCasoPorId(Long id) throws RestException;

    CasoResponseDTO guardarCaso(CasoRequestDTO casoString, Authentication authentication) throws RestException;

    Boolean visibilizar(Boolean visible, Long id) throws RestException;

	void eliminarCaso(Long id) throws RestException;

	CasoResponseDTO actualizarCaso(Long id, CasoRequestDTO casoRequestDTO) throws RestException;
}
