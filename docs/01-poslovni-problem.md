# 1. Poslovni problem i ciljevi projekta

## 1.1 Poslovni kontekst
Mala i srednja preduzeća u B2B poslovanju često razmjenjuju narudžbe i fakture putem standardnog e-maila (PDF/Word prilozi). Takav način razmjene donosi rizike:
- nedostatak enkripcije u prenosu i pohrani,
- nemogućnost kriptografske potvrde identiteta pošiljaoca i integriteta dokumenta,
- izostanak pravno/tehnički pouzdane potvrde prijema (non-repudiation),
- ručno prepisivanje podataka i podložnost ljudskim greškama.

## 1.2 Predloženo rješenje
Razvoj web aplikacije u Spring Bootu integrisane sa **OpenAS2** serverom za sigurnu B2B razmjenu strukturiranih XML dokumenata (**Narudžba** i **Faktura**) između dva poslovna partnera (`PartnerA` i `PartnerB`) preko AS2 protokola uz:
- digitalno potpisivanje poruka (integritet i autentičnost),
- enkripciju sadržaja (povjerljivost),
- automatsku provjeru potpisanih MDN potvrda prijema (neporecivost).

## 1.3 Funkcionalni zahtjevi (nacrt)
1. Upravljanje partnerima (pregled i definisanje AS2 identifikatora).
2. Kreiranje narudžbi i faktura putem web forme sa dinamičkim stavkama i automatskim obračunom ukupnog iznosa.
3. Generisanje standardizovanog XML dokumenta i slanje putem AS2 integracije (`outbox` direktorij).
4. Praćenje statusa razmjene (`KREIRANO`, `POSLANO`, `POTVRDJENO_MDN`, `ODBIJENO`, `GRESKA`, `ISTEKLO`) na osnovu OpenAS2 direktorija (`sent`, `mdn`, `error`).
5. Pregled detalja dokumenta, XML sadržaja i historije razmjene.

## 1.4 Nefunkcionalni zahtjevi
- Sigurnost: privatni ključevi i certifikati (`.p12`, `.jks`, `.key`) ne smiju biti dio Git repozitorija.
- Tehnologije: Java 21, Spring Boot, Spring Data JPA, H2 baza, Thymeleaf, OpenAS2.
- Kvalitet procesa: rad preko feature grana i Pull Request pregleda na GitHubu.