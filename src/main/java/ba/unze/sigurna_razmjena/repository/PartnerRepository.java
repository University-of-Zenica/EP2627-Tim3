package ba.unze.sigurna_razmjena.repository;

import ba.unze.sigurna_razmjena.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    Optional<Partner> findByAs2Id(String as2Id);
}