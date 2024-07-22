package co.diplomado.iudigital.helpmeiud.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.diplomado.iudigital.helpmeiud.app.models.Delito;

@Repository 
public interface IDelitoRepository extends JpaRepository<Delito, Long> {
}
