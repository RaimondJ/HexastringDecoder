package ee.decoder.hexastring;

public class Main {
    public static void main(String[] args) {
        //Google
        Decoder test1 = new Decoder("7C7FBFCCE230");
        //Omniva
        Decoder test2 = new Decoder("FC6BB3D6F030");

        System.out.println(test1.getResult());
        System.out.println(test2.getResult());
    }
}