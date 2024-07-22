package co.diplomado.iudigital.helpmeiud.app.utils;

import org.springframework.stereotype.Component;

import co.diplomado.iudigital.helpmeiud.app.dtos.usuarios.UsuarioRequestDTO;
import co.diplomado.iudigital.helpmeiud.app.dtos.usuarios.UsuarioResponseDTO;
import co.diplomado.iudigital.helpmeiud.app.models.Usuario;

import java.util.Collections;

@Component
public class UsuarioMapper {

    public Usuario toUsuario(UsuarioRequestDTO usuarioRequestDTO){
        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioRequestDTO.getUsername());
        usuario.setNombre(usuarioRequestDTO.getNombre());
        usuario.setApellido(usuarioRequestDTO.getApellido());
        usuario.setPassword(usuarioRequestDTO.getPassword());
        usuario.setFechaNacimiento(usuarioRequestDTO.getFechaNacimiento());
        usuario.setEnabled(usuarioRequestDTO.getEnabled());
        usuario.setRedSocial(usuarioRequestDTO.getRedSocial());
        usuario.setImage(usuarioRequestDTO.getImage());
        return usuario;
    }

    public UsuarioResponseDTO toUsuarioResponseDTO(Usuario usuario){
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .username(usuario.getUsername())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .password(usuario.getPassword())
                .fechaNacimiento(usuario.getFechaNacimiento())
                .enabled(usuario.getEnabled())
                .redSocial(usuario.getRedSocial())
                .image(usuario.getImage())
                .roles(Collections.singletonList(usuario.getRoles().get(0).getNombre()))
                .build();
    }
}
