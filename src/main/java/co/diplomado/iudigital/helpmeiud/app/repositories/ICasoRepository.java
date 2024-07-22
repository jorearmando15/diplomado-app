package co.diplomado.iudigital.helpmeiud.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.diplomado.iudigital.helpmeiud.app.models.Caso;

import java.util.List;

@Repository 
public interface ICasoRepository extends JpaRepository<Caso, Long> {


    List<Caso> findAllByUsuarioUsername( String username);

    List<Caso> findAllByVisibleTrue();
    
    @Query("SELECT c FROM Caso c LEFT JOIN FETCH c.usuario WHERE c.id = :id")
    Caso findByIdWithUser(@Param("id") Long id);

}
