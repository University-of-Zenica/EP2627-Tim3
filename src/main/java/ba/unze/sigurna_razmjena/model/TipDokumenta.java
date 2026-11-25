package ba.unze.sigurna_razmjena.model;

public enum TipDokumenta {
    NARUDZBA("Narudžba"),
    FAKTURA("Faktura");

    private final String naziv;

    TipDokumenta(String naziv) {
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }
}