package ba.unze.sigurna_razmjena.model;

public enum StatusRazmjene {
    KREIRANO("Kreirano"),
    POSLANO("Poslano"),
    POTVRDJENO_MDN("Potvrđeno (MDN)"),
    ODBIJENO("Odbijeno"),
    GRESKA("Greška"),
    ISTEKLO("Isteklo");

    private final String naziv;

    StatusRazmjene(String naziv) {
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }
}