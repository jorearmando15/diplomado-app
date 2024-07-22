package co.diplomado.iudigital.helpmeiud.app.services.ifaces;

import org.springframework.security.core.Authentication;

import co.diplomado.iudigital.helpmeiud.app.exceptions.RestException;
import co.diplomado.iudigital.helpmeiud.app.models.Delito;

import java.util.List;

public interface IDelitoService {

    Delito crearDelito(Delito delito, Authentication authentication) throws RestException;

    Delito actualizarDelitoPorID(Long id, Delito delito) throws RestException;

    void eliminarDelitoPorID(Long id) throws RestException;

    Delito consultarDelitoPorID(Long id) throws RestException;

    List<Delito> consultarDelitos() throws RestException;
}
