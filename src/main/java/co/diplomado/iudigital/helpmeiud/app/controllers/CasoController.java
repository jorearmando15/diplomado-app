package co.diplomado.iudigital.helpmeiud.app.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import co.diplomado.iudigital.helpmeiud.app.dtos.casos.CasoRequestDTO;
import co.diplomado.iudigital.helpmeiud.app.dtos.casos.CasoRequestVisibleDTO;
import co.diplomado.iudigital.helpmeiud.app.dtos.casos.CasoResponseDTO;
import co.diplomado.iudigital.helpmeiud.app.exceptions.RestException;
import co.diplomado.iudigital.helpmeiud.app.services.ifaces.ICasoService;

import java.util.List;

@Tag(name = "Casos Controller", description = "Controlador para gestión de casos")
@RestController
@RequestMapping("/casos")
@Slf4j
public class CasoController {

    @Autowired
    private ICasoService casoService;

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "500", description = "Internal Error Server")
            }
    )
    @Operation(
            summary = "Consultar todos los casos",
            description = "Endpoint para consultar todos los casos"
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<CasoResponseDTO>> index() throws RestException {
        log.info("Ejecutando index de CasoController");
        return ResponseEntity
                .ok()
                .body(casoService.consultarCasos());
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "500", description = "Internal Error Server")
            }
    )
    @Operation(
            summary = "Consultar todos los casos visibles",
            description = "Endpoint para consultar todos los casos visibles"
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/visibles")
    public ResponseEntity<List<CasoResponseDTO>> indexVisible() throws RestException {
        log.info("Ejecutando indexVisible de CasoController");
        return ResponseEntity
                .ok()
                .body(casoService.consultarCasosVisibles());
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @SecurityRequirement(name = "Authorization")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "500", description = "Internal Error Server")
            }
    )
    @Operation(
            summary = "Crear un Caso",
            description = "Endpoint para crear un caso"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<CasoResponseDTO> create(
            @RequestBody CasoRequestDTO caso, Authentication authentication
    ) throws RestException { // TODO: IMPLEMENTAR Authorization para registrar caso con la persona que esté autenticada
        log.info("Ejecutando create de CasoController");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(casoService.guardarCaso(caso, authentication));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Authorization")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "500", description = "Internal Error Server")
            }
    )
    @Operation(
            summary = "Visualizar u ocultar un Caso",
            description = "Endpoint para visualizar u ocultar un caso"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> updateVisible(
            @PathVariable Long id,
            @RequestBody CasoRequestVisibleDTO request
    ) throws RestException {
        log.info("Ejecutando updateVisible de CasoController");
        final Boolean visible = request.getVisible();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(casoService.visibilizar(visible, id));
    }
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Authorization")
    @Operation(
        summary = "Eliminar un Caso",
        description = "Endpoint para eliminar un caso"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCaso(@PathVariable Long id) throws RestException {
        log.info("Ejecutando deleteCaso de CasoController");
        casoService.eliminarCaso(id);
        return ResponseEntity.noContent().build();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Authorization")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "500", description = "Internal Error Server")
            }
    )
    @Operation(
            summary = "Actualizar un Caso",
            description = "Endpoint para actualizar un caso"
    )
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ResponseEntity<CasoResponseDTO> updateCaso(
            @PathVariable Long id,
            @RequestBody CasoRequestDTO casoRequestDTO
    ) throws RestException {
        log.info("Ejecutando updateCaso de CasoController");
        return ResponseEntity
                .ok()
                .body(casoService.actualizarCaso(id, casoRequestDTO));
    }


}
