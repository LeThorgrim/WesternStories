/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package people;

public enum BanditNames {
    JESSE_JAMES("Jesse James"),
    BILLY_THE_KID("Billy the Kid"),
    BELLE_STARR("Belle Starr"),
    BUTCH_CASSIDY("Butch Cassidy"),
    SUNDANCE_KID("Harry Alonzo 'Sundance Kid' Longabaugh"),
    JOHN_WESLEY_HARDIN("John Wesley Hardin"),
    CLYDE_BARROW("Clyde Barrow"),
    BONNIE_PARKER("Bonnie Parker"),
    DOC_HOLLIDAY("Doc Holliday"),
    FRANK_JAMES("Frank James"),
    KID_CURRY("Harvey Logan 'Kid Curry'"),
    BLACK_BART("Charles E. Boles 'Black Bart'"),
    PEARL_HART("Pearl Hart"),
    JESSE_EVANS("Jesse Evans"),
    ALONZO_THELPS("Alonzo T. 'Lon' Tuggle"),
    RUBE_BURROW("Rube Burrow"),
    BILL_DOOLIN("Bill Doolin"),
    SAM_BASS("Sam Bass"),
    CLELL_MILLER("Clell Miller"),
    GEORGE_COCKEY("George 'Flat Nose' Curry");

    private final String fullName;

    BanditNames(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
