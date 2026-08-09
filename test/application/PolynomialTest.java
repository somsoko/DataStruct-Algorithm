package application;

public class PolynomialTest {
    public static void main(String[] args) {
        Polynomial p = new Polynomial();

        System.out.println(p.isZero());
        p.attach(3,3);
        p.attach(2,1);
        p.attach(5,4);
        p.attach(4,2);
        p.attach(3,2);
        p.show();
        Polynomial p2 = p.singleMult(2,2);
        p2.show();
        Polynomial p3 = p.add(p2);
        p3.show();
        p3.remove(1);
        p3.remove(5);
        p3.show();
        p.zero();
        p.attach(2,2);
        p.attach(1,0);
        p2.zero();
        p2.attach(3,1);
        p2.attach(2,0);
        Polynomial p4 = p.mult(p2);
        p4.show();
    }
}
