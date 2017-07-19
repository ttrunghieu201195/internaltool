package BusinessLogicLayers;
//@author Duong Nguyen
public class EnDeCryption{
    private final String pass;
    protected String key;
    public EnDeCryption(String pass){
        this.pass=pass;
        CreateSecretKey();
    }
    
    public final void CreateSecretKey(){
        key="Administrator";
    }
    
    //<editor-fold defaultstate="collapsed" desc="EnCryption Process"> 
    public StringBuffer EnCryption(){
        StringBuffer password=new StringBuffer(pass);
        if(pass.length()<=key.length()){
            password=LogicEN(password);
        }
        else{
            int div=pass.length()/key.length();
            int mod=pass.length()%key.length();
            for(int i=0;i<div;i++){
                key+=key;
            }
            for(int i=0;i<mod;i++){
                key+=key.charAt(i);
            }
            password=LogicEN(password);
        }
        CreateSecretKey();
        return password;
    }
    public StringBuffer LogicEN(StringBuffer password){
        for(int i=0;i<password.length();i++){
            int temp=(int)password.charAt(i) + (int)key.charAt(i);
            temp*=9;
            password.setCharAt(i, (char)temp);
            }
        return password;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="DeCryption Process"> 
    public String DeCryption(String enPass){
        StringBuffer dePass=new StringBuffer(enPass);
        if(enPass.length()<=key.length()){
            dePass=LogicDE(dePass, enPass);
        }
        else{
            int div=pass.length()/key.length();
            int mod=pass.length()%key.length();
            for(int i=0;i<div;i++){
                key+=key;
            }
            for(int i=0;i<mod;i++){
                key+=key.charAt(i);
            }
            LogicDE(dePass, enPass);
        }
        CreateSecretKey();
        return dePass.toString();
    }
    public StringBuffer LogicDE(StringBuffer dePass, String enPass){
        for(int i=0;i<dePass.length();i++){
            int temp=(enPass.charAt(i)/9)-((int)key.charAt(i));
            dePass.setCharAt(i, (char)temp);
        }
        return dePass;
    }
    //</editor-fold>
    
    
    
    /*public static void main(String a[]){
        EnDeCryption demo=new EnDeCryption("b1304575");
        String temp=demo.EnCryption().toString();
        System.out.println(temp);
        System.out.println(demo.DeCryption(temp));
    }*/
}