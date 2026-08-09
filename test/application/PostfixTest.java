package application;

public class PostfixTest {
    public static void main(String[] args) {
        Postfix p = new Postfix();

        String s = p.infix_to_postfix("6/2-3+4*2");
        System.out.println(s);

        p.calculate_postfix("62/3-42*+");
    }
}
