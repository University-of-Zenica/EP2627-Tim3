package ba.unze.sigurna_razmjena.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "stavke")
public class Stavka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    @Column(nullable = false)
    private Integer kolicina;

    @Column(name = "jedinicna_cijena", nullable = false, precision = 12, scale = 2)
    private BigDecimal jedinicnaCijena;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dokument_id", nullable = false)
    private Dokument dokument;

    public Stavka() {
    }

    public Stavka(String naziv, Integer kolicina, BigDecimal jedinicnaCijena) {
        this.naziv = naziv;
        this.kolicina = kolicina;
        this.jedinicnaCijena = jedinicnaCijena;
    }

    public BigDecimal getUkupnaCijena() {
        if (kolicina == null || jedinicnaCijena == null) {
            return BigDecimal.ZERO;
        }
        return jedinicnaCijena.multiply(BigDecimal.valueOf(kolicina));
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public Integer getKolicina() { return kolicina; }
    public void setKolicina(Integer kolicina) { this.kolicina = kolicina; }

    public BigDecimal getJedinicnaCijena() { return jedinicnaCijena; }
    public void setJedinicnaCijena(BigDecimal jedinicnaCijena) { this.jedinicnaCijena = jedinicnaCijena; }

    public Dokument getDokument() { return dokument; }
    public void setDokument(Dokument dokument) { this.dokument = dokument; }
}