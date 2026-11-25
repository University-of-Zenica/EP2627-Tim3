package ba.unze.sigurna_razmjena.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dokumenti")
public class Dokument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipDokumenta tip;

    @Column(name = "broj_dokumenta", nullable = false, unique = true)
    private String brojDokumenta;

    @Column(name = "datum_dokumenta", nullable = false)
    private LocalDate datumDokumenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posiljalac_id", nullable = false)
    private Partner posiljalac;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primalac_id", nullable = false)
    private Partner primalac;

    @OneToMany(mappedBy = "dokument", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stavka> stavke = new ArrayList<>();

    @Column(name = "ukupan_iznos", nullable = false, precision = 12, scale = 2)
    private BigDecimal ukupanIznos = BigDecimal.ZERO;

    @Column(nullable = false, length = 3)
    private String valuta = "BAM";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusRazmjene status = StatusRazmjene.KREIRANO;

    @Lob
    @Column(name = "xml_sadrzaj")
    private String xmlSadrzaj;

    @Column(name = "vrijeme_kreiranja", nullable = false)
    private LocalDateTime vrijemeKreiranja;

    @PrePersist
    protected void onCreate() {
        if (this.vrijemeKreiranja == null) {
            this.vrijemeKreiranja = LocalDateTime.now();
        }
        if (this.status == null) {
            this.status = StatusRazmjene.KREIRANO;
        }
        izracunajUkupanIznos();
    }

    public void dodajStavku(Stavka stavka) {
        stavke.add(stavka);
        stavka.setDokument(this);
        izracunajUkupanIznos();
    }

    public void ukloniStavku(Stavka stavka) {
        stavke.remove(stavka);
        stavka.setDokument(null);
        izracunajUkupanIznos();
    }

    public void izracunajUkupanIznos() {
        this.ukupanIznos = stavke.stream()
                .map(Stavka::getUkupnaCijena)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public TipDokumenta getTip() { return tip; }
    public void setTip(TipDokumenta tip) { this.tip = tip; }

    public String getBrojDokumenta() { return brojDokumenta; }
    public void setBrojDokumenta(String brojDokumenta) { this.brojDokumenta = brojDokumenta; }

    public LocalDate getDatumDokumenta() { return datumDokumenta; }
    public void setDatumDokumenta(LocalDate datumDokumenta) { this.datumDokumenta = datumDokumenta; }

    public Partner getPosiljalac() { return posiljalac; }
    public void setPosiljalac(Partner posiljalac) { this.posiljalac = posiljalac; }

    public Partner getPrimalac() { return primalac; }
    public void setPrimalac(Partner primalac) { this.primalac = primalac; }

    public List<Stavka> getStavke() { return stavke; }
    public void setStavke(List<Stavka> stavke) { this.stavke = stavke; }

    public BigDecimal getUkupanIznos() { return ukupanIznos; }
    public void setUkupanIznos(BigDecimal ukupanIznos) { this.ukupanIznos = ukupanIznos; }

    public String getValuta() { return valuta; }
    public void setValuta(String valuta) { this.valuta = valuta; }

    public StatusRazmjene getStatus() { return status; }
    public void setStatus(StatusRazmjene status) { this.status = status; }

    public String getXmlSadrzaj() { return xmlSadrzaj; }
    public void setXmlSadrzaj(String xmlSadrzaj) { this.xmlSadrzaj = xmlSadrzaj; }

    public LocalDateTime getVrijemeKreiranja() { return vrijemeKreiranja; }
    public void setVrijemeKreiranja(LocalDateTime vrijemeKreiranja) { this.vrijemeKreiranja = vrijemeKreiranja; }
}