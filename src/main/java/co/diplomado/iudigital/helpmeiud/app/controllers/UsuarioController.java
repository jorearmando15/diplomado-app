package co.diplomado.iudigital.helpmeiud.app.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import co.diplomado.iudigital.helpmeiud.app.dtos.usuarios.UsuarioRequestDTO;
import co.diplomado.iudigital.helpmeiud.app.dtos.usuarios.UsuarioRequestUpdateDTO;
import co.diplomado.iudigital.helpmeiud.app.dtos.usuarios.UsuarioResponseDTO;
import co.diplomado.iudigital.helpmeiud.app.exceptions.RestException;
import co.diplomado.iudigital.helpmeiud.app.services.ifaces.IUsuarioService;

import javax.validation.Valid;

@Tag(name = "Usuarios Controller", description = "Controlador para gestión de Usuarios")
@RestController
@RequestMapping("/usuarios")
@Slf4j
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "500", description = "Internal Error Server")
            }
    )
    @Operation(
            summary = "SigUp en HelpMe",
            description = "Endpoint para darse de alta en el sistema"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public ResponseEntity<UsuarioResponseDTO> register(
            @Valid @RequestBody UsuarioRequestDTO request
    ) throws RestException {
        log.info("register de UsuarioController");
        final String passwordEncoded = passwordEncoder.encode(request.getPassword());
        request.setPassword(passwordEncoded);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioService.registrar(request));
    }


    @SecurityRequirement(name = "Authorization")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "500", description = "Internal Error Server"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    @Operation(
            summary = "Consultar mi perfil",
            description = "Endpoint para consultar usuario del usuario autenticado"
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/profile")
    public ResponseEntity<UsuarioResponseDTO> profile(
            Authentication authentication
    ) throws RestException {
        log.info("userInfo de UsuarioController");
        return ResponseEntity
                .ok()
                .body(usuarioService.consultarPorUsername(authentication));
    }


    @SecurityRequirement(name = "Authorization")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "500", description = "Internal Error Server"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    @Operation(
            summary = "Actualizar info",
            description = "Endpoint para actualizar información de usuario logueado"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/profile")
    public ResponseEntity<UsuarioResponseDTO> updateProfile(
            @Valid @RequestBody UsuarioRequestUpdateDTO request, Authentication authentication
    ) throws RestException {
        log.info("updateProfile de UsuarioController");
        if(request.getPassword() != null) {
            final String passwordEncoded = passwordEncoder.encode(request.getPassword());
            request.setPassword(passwordEncoded);
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioService.actualizar(request, authentication));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "500", description = "Internal Error Server"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    @Operation(
            summary = "Sube foto de perfil",
            description = "Endpoint para subir foto de perfil del usuario logueado"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/upload")
    public ResponseEntity<UsuarioResponseDTO> uploadImage(
             @RequestParam("image") MultipartFile image, Authentication authentication
    ) throws RestException {
        log.info("uploadImage de UsuarioController");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioService.subirImagen(image, authentication));
    }


    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "500", description = "Internal Error Server"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    @Operation(
            summary = "Ver foto de perfil",
            description = "Endpoint para obtener foto de perfil de usuario autenticado"
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/upload/img/{name:.+}")
    public ResponseEntity<Resource> getImage(
            @PathVariable String name
    ) throws RestException {
        log.info("getImage de UsuarioController");
        Resource resource = usuarioService.obtenerImagen(name);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+ resource.getFilename());
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }


}
