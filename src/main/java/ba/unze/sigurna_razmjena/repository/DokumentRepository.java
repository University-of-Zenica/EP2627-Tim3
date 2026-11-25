package ba.unze.sigurna_razmjena.repository;

import ba.unze.sigurna_razmjena.model.Dokument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DokumentRepository extends JpaRepository<Dokument, Long> {
    boolean existsByBrojDokumenta(String brojDokumenta);
}