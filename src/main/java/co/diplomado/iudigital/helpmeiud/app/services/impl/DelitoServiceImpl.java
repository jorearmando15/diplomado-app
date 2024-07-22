package co.diplomado.iudigital.helpmeiud.app.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.diplomado.iudigital.helpmeiud.app.exceptions.*;
import co.diplomado.iudigital.helpmeiud.app.models.Delito;
import co.diplomado.iudigital.helpmeiud.app.models.Usuario;
import co.diplomado.iudigital.helpmeiud.app.repositories.IDelitoRepository;
import co.diplomado.iudigital.helpmeiud.app.repositories.IUsuarioRepository;
import co.diplomado.iudigital.helpmeiud.app.services.ifaces.IDelitoService;
import co.diplomado.iudigital.helpmeiud.app.utils.Messages;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class DelitoServiceImpl implements IDelitoService { 

    @Autowired
    private IDelitoRepository delitoRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public Delito crearDelito(Delito delito, Authentication authentication) throws RestException {
        log.info("crearDelito DelitoService");
        String username = authentication.getName();
   

        try{
            Usuario usuario = usuarioRepository.findByUsername(username);
            delito.setUsuario(usuario);
            return delitoRepository.save(delito);
        }catch (Exception e) {
            throw new InternalServerErrorException(
                    ErrorDto.builder()
                            .error("Error General")
                            .status(500)
                            .message(e.getMessage())
                            .date(LocalDateTime.now())
                            .build()
            );
        }

    }

    @Transactional
    @Override
    public Delito actualizarDelitoPorID(Long id, Delito delito) throws RestException {
        log.info("actualizarDelitoPorID DelitoService");
        Delito delitoBD = delitoRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                ErrorDto.builder()
                                        .error(Messages.NO_ENCONTRADO)
                                        .message(Messages.DELIT_NO_EXISTE)
                                        .status(404)
                                        .date(LocalDateTime.now())
                                        .build())
                );
        delitoBD.setNombre(delito.getNombre());
        delitoBD.setDescripcion(delito.getDescripcion());
        try{
            return delitoRepository.save(delitoBD);
        }catch (Exception e) {
            log.error("Error actualizando delito casos: {} {}",e.getMessage(), e.getCause());
            throw new InternalServerErrorException(
                    ErrorDto.builder()
                            .error("Error General")
                            .status(500)
                            .message("ha ocurrido un error, consulta con el desarrollador")
                            .date(LocalDateTime.now())
                            .build()
            );
        }

    }

    @Transactional 
    @Override
    public void eliminarDelitoPorID(Long id) throws RestException {
        log.info("eliminarDelitoPorID DelitoService");
        delitoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Delito consultarDelitoPorID(Long id) throws RestException {
        log.info("consultarDelitoPorID DelitoService");
        return delitoRepository.findById(id)
                .orElseThrow(() ->
                    new NotFoundException(
                        ErrorDto.builder()
                            .error(Messages.NO_ENCONTRADO)
                            .message(Messages.DELIT_NO_EXISTE)
                            .status(400)
                            .date(LocalDateTime.now())
                            .build())
                );
       
    }

    @Override
    public List<Delito> consultarDelitos() throws RestException{
        log.info("consultarDelitos DelitoService");
        try{
            return delitoRepository.findAll();
        }catch (Exception e) {
            throw new InternalServerErrorException(
                    ErrorDto.builder()
                            .error("Error General")
                            .status(500)
                            .message(e.getMessage())
                            .date(LocalDateTime.now())
                            .build()
            );
        }
    }
}
