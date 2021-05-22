package projet;

/**
 *
 * @author kouki
 */
public class user {
    private String id;
    private String cin;
    private String nom;
    private String prenom;
    private String username;
    private String tel;
    private String addresse;
    private String type;
    public user(){}
    public user(String id,String cin,String nom,String prenom,String username,String tel,String addresse,String type)
    {
        this.id=id;
        this.cin=cin;
        this.nom=nom;
        this.prenom=prenom;
        this.username=username;
        this.tel=tel;
        this.addresse=addresse;
        this.type=type;
    }
    
    public void setNom(String nom){this.nom=nom;}
    public void setPrenom(String prenom){this.prenom=prenom;}
    public void setUsername(String username){this.username=username;}
    public void setTel(String tel){this.tel=tel;}
    public void setAddresse(String adrs){this.addresse=adrs;}
    
    public String getId(){return this.id;}
    public String getCin(){return this.cin;}
    public String getNom(){return this.nom;}
    public String getPrenom(){return this.prenom;}
    public String getUsername(){return this.username;}
    public String getTel(){return this.tel;}
    public String getAddresse(){return this.addresse;}
    public String getType(){return this.type;}
}
