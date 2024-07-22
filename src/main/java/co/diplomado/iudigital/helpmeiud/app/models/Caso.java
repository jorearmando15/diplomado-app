package co.diplomado.iudigital.helpmeiud.app.models;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Table(name = "casos")
public class Caso implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "fecha_hora")
    LocalDateTime fechaHora;

    @Column
    Float latitud;

    @Column
    Float longitud;

    @Column
    Float altitud;

    @Column
    Boolean visible;

    @Column
    String descripcion;

    @Column(name = "url_mapa")
    String urlMapa;

    @Column(name = "rmi_url")
    String rmiUrl;

    @ManyToOne
    @JoinColumn(name = "usuarios_id")
    Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "delitos_id")
    Delito delito;
}
