package co.diplomado.iudigital.helpmeiud.app.services.ifaces;

import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import co.diplomado.iudigital.helpmeiud.app.dtos.usuarios.UsuarioRequestDTO;
import co.diplomado.iudigital.helpmeiud.app.dtos.usuarios.UsuarioRequestUpdateDTO;
import co.diplomado.iudigital.helpmeiud.app.dtos.usuarios.UsuarioResponseDTO;
import co.diplomado.iudigital.helpmeiud.app.exceptions.RestException;
import co.diplomado.iudigital.helpmeiud.app.models.Usuario;

import java.util.List;

public interface IUsuarioService {

    List<UsuarioResponseDTO> consultarTodos() throws RestException;

    UsuarioResponseDTO registrar(UsuarioRequestDTO usuarioRequestDTO)  throws RestException;

    UsuarioResponseDTO consultarPorID(Long id)  throws RestException;

    Usuario findByUsername(String username);

    UsuarioResponseDTO consultarPorUsername(Authentication authentication) throws RestException;

    UsuarioResponseDTO actualizar(UsuarioRequestUpdateDTO usuarioRequestUpdateDTO, Authentication authentication) throws RestException;

    UsuarioResponseDTO subirImagen(MultipartFile image, Authentication authentication) throws RestException;

    Resource obtenerImagen(String name) throws RestException;
}
