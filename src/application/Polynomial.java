package application;

import java.util.ArrayList;

public class Polynomial {
    // 다항식을 저장할 객체 ArrayList
    private ArrayList<Element> term = new ArrayList<>();

    // 정수 계수와 정수 지수를 가지는 객체로 항 저장
    private class Element {
        int coefficient, exponent;

        public Element(int coef, int expon) {
            this.coefficient = coef;
            this.exponent = expon;
        }
    }

    public void show() {
        for (Element e : term) {
            System.out.print("(" + e.coefficient + "," + e.exponent + ") ");
        }
        System.out.println();
    }

    /** 정수 다항식 ArrayList 의 모든 항 삭제 */
    public void zero() {
        term.clear();
    }

    /** 정수 다항식 ArrayList 가 비었다면 true 리턴 */
    public boolean isZero() {
        return term.isEmpty();
    }

    /** 정수 계수와 정수 지수를 받아서 다항식 ArrayList 에 삽입 */
    public void attach(int coef, int expon) {
        Element element = new Element(coef, expon);

        for (Element e : term) {
            if (e.exponent == expon) {
                return;
            }
            else if (e.exponent < expon) {
                term.add(term.indexOf(e), element);
                return;
            }
        }
        term.add(element);
    }

    /** 정수 지수를 받아서 그 지수의 항을 ArrayList 에서 삭제 */
    public void remove(int expon) {
        for(Element e : term) {
            if(e.exponent == expon) {
                term.remove(e);
                return;
            }
        }
    }

    /** 정수 계수와 정수 지수를 받아서 이 객체의 정수 다항식에 곱한 새로운 정수 다항식 객체 리턴 */
    public Polynomial singleMult(int coef, int expon) {
        Polynomial resultPoly = new Polynomial();

        for (Element e : term) {
            resultPoly.attach(e.coefficient * coef, e.exponent + expon);
        }

        return resultPoly;
    }

    /** 정수 다항식 객체를 받아서 이 객체의 정수 다항식에 더한 새로운 정수 다항식 객체 리턴 */
    public Polynomial add(Polynomial poly) {
        Polynomial resultPoly = new Polynomial();

        int i = 0, j = 0;
        Element e1,e2;

        while((i != term.size()) && (j != poly.term.size())) {
            e1 = term.get(i);
            e2 = poly.term.get(j);

            if(e1.exponent < e2.exponent) {
                resultPoly.attach(e2.coefficient, e2.exponent);
                j++;
            }
            else if(e1.exponent == e2.exponent) {
                resultPoly.attach(e1.coefficient + e2.coefficient, e1.exponent);
                i++;
                j++;
            }
            else {
                resultPoly.attach(e1.coefficient, e1.exponent);
                i++;
            }
        }

        while(i != term.size()) {
            e1 = term.get(i);
            resultPoly.attach(e1.coefficient, e1.exponent);
            i++;
        }
        while(j != poly.term.size()) {
            e2 = poly.term.get(j);
            resultPoly.attach(e2.coefficient, e2.exponent);
            j++;
        }

        return resultPoly;
    }

    /** 정수 다항식 객체를 받아서 이 객체의 정수 다항식에 곱한 새로운 정수 다항식 객체 리턴 */
    public Polynomial mult(Polynomial poly) {
        Polynomial resultPoly = new Polynomial();

        for (Element e : term) {
            resultPoly = resultPoly.add(poly.singleMult(e.coefficient, e.exponent));
        }

        return resultPoly;
    }
}

