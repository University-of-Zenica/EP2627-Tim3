package ba.unze.sigurna_razmjena.model;

import jakarta.persistence.*;

@Entity
@Table(name = "partneri")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    @Column(name = "as2_id", nullable = false, unique = true)
    private String as2Id;

    private String email;

    @Column(name = "je_lokalni", nullable = false)
    private boolean jeLokalni = false;

    public Partner() {
    }

    public Partner(String naziv, String as2Id, String email, boolean jeLokalni) {
        this.naziv = naziv;
        this.as2Id = as2Id;
        this.email = email;
        this.jeLokalni = jeLokalni;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public String getAs2Id() { return as2Id; }
    public void setAs2Id(String as2Id) { this.as2Id = as2Id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean isJeLokalni() { return jeLokalni; }
    public void setJeLokalni(boolean jeLokalni) { this.jeLokalni = jeLokalni; }
}