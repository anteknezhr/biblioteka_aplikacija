import java.util.ArrayList;
import java.util.Scanner;

class Knjiga {
    private String naslov;
    private String autor;
    private boolean iznajmljena;
    private String iznajmio;

    public Knjiga(String naslov, String autor) {
        this.naslov = naslov;
        this.autor = autor;
        this.iznajmljena = false;
        this.iznajmio = "";
    }

    public String getNaslov() {
        return naslov;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isIznajmljena() {
        return iznajmljena;
    }

    public String getIznajmio() {
        return iznajmio;
    }

    public void iznajmi(String ime) {
        if (!iznajmljena) {
            iznajmljena = true;
            iznajmio = ime;
            System.out.println("Knjiga " + naslov + " iznajmljena.");
        } else {
            System.out.println("Knjiga " + naslov + " već iznajmljena korisniku: " + iznajmio);
        }
    }

    public void vrati() {
        if (iznajmljena) {
            iznajmljena = false;
            iznajmio = "";
            System.out.println("Knjiga " + naslov + " vraćena.");
        } else {
            System.out.println("Knjiga " + naslov + " nije iznajmljena.");
        }
    }
}

class Korisnik {
    private String ime;
    private ArrayList<Knjiga> iznajmljeneKnjige;

    public Korisnik(String ime) {
        this.ime = ime;
        this.iznajmljeneKnjige = new ArrayList<>();
    }

    public String getIme() {
        return ime;
    }

    public void iznajmiKnjigu(Knjiga knjiga) {
        if (!knjiga.isIznajmljena()) {
            iznajmljeneKnjige.add(knjiga);
            knjiga.iznajmi(ime);
            System.out.println(ime + " je iznajmio/la knjigu " + knjiga.getNaslov());
        } else {
            System.out.println("Knjiga " + knjiga.getNaslov() + " već iznajmljena korisniku: " + knjiga.getIznajmio());
        }
    }

    public void vratiKnjigu(Knjiga knjiga) {
        if (iznajmljeneKnjige.contains(knjiga)) {
            iznajmljeneKnjige.remove(knjiga);
            knjiga.vrati();
            System.out.println(ime + " je vratio/la knjigu " + knjiga.getNaslov());
        } else {
            System.out.println("Knjiga " + knjiga.getNaslov() + " nije iznajmljena od strane korisnika " + ime);
        }
    }
}

class Biblioteka {
    private ArrayList<Knjiga> knjige;

    public Biblioteka() {
        this.knjige= new ArrayList<>();
    }

    public void dodajKnjigu(Knjiga knjiga) {
        knjige.add(knjiga);
    }

    public ArrayList<Knjiga> getKnjige() {
        return knjige;
    }

    public void izlistajDostupneKnjige() {
        System.out.println("Sve knjige u biblioteci:");
        for (int i = 0; i<knjige.size(); i++) {
            Knjiga knjiga = knjige.get(i);
            System.out.println((i + 1) + ". " + knjiga.getNaslov() + " - " + knjiga.getAutor()
                    + (knjiga.isIznajmljena() ? " (Iznajmljena korisniku: " + knjiga.getIznajmio() + ")" : ""));
        }
        System.out.println();
    }
}

public class BibliotekaApp {
    public static void main(String[] args) {
        Biblioteka biblioteka = new Biblioteka();
        Scanner scanner = new Scanner(System.in);

        Knjiga knjiga1 = new Knjiga("Harry Potter", "J.K. Rowling");
        Knjiga knjiga2 = new Knjiga("Lord of the Rings", "J.R.R. Tolkien");
        Knjiga knjiga3 = new Knjiga("To Kill a Mockingbird", "Harper Lee");

        biblioteka.dodajKnjigu(knjiga1);
        biblioteka.dodajKnjigu(knjiga2);
        biblioteka.dodajKnjigu(knjiga3);

        System.out.println("Unesite svoje ime:");
        String imeKorisnika = scanner.nextLine();
        Korisnik korisnik = new Korisnik(imeKorisnika);

        biblioteka.izlistajDostupneKnjige();

        System.out.println("Unesite redni broj knjige koju želite iznajmiti:");
        while (!scanner.hasNextInt()) {
            System.out.println("Neispravan unos. Molimo unesite redni broj knjige.");
            scanner.next();
        }
        int izbor = scanner.nextInt();

        if (izbor >= 1 && izbor <= biblioteka.getKnjige().size()) {
            Knjiga izabranaKnjiga = biblioteka.getKnjige().get(izbor - 1);
            korisnik.iznajmiKnjigu(izabranaKnjiga);
        } else {
            System.out.println("Neispravan unos. Molimo unesite ispravan redni broj knjige.");
        }

        biblioteka.izlistajDostupneKnjige();

        scanner.close();
    }
}
