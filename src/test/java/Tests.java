import static org.junit.jupiter.api.Assertions.assertEquals;

import Polynomial.InvalidInput;
import Polynomial.Operations;
import Polynomial.Polinom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Tests {

    @Test
    public void additionTest() throws InvalidInput {

        Operations op = new Operations();
        Polinom pol = new Polinom();
        Polinom p = new Polinom();
        Polinom q = new Polinom();

        p.readPolinom("1x^1 + 1x^0");
        q.readPolinom("1x^1 + 1x^0");

        pol.readPolinom("2x^1 + 2x^0");
        Polinom rez = op.addition(p.getList(), q.getList());

        assertEquals(pol.printPolinom(), rez.printPolinom());
    }

    @Test
    public void subtractionTest() throws InvalidInput {

        Operations op = new Operations();
        Polinom p = new Polinom();
        Polinom q = new Polinom();
        Polinom pol = new Polinom();

        p.readPolinom("1x^1 + 1x^0");
        q.readPolinom("2x^2 + 1x^1 + 3x^0");

        pol.readPolinom("-2x^2 - 2x^0");
        Polinom rez = op.substraction(p.getList(), q.getList());

        assertEquals(rez.printPolinom(), pol.printPolinom());
    }

    @Test
    public void multiplicationTest() throws InvalidInput {

        Operations op = new Operations();
        Polinom p = new Polinom();
        Polinom q = new Polinom();
        Polinom pol = new Polinom();

        p.readPolinom("1x^1 + 1x^0");
        q.readPolinom("2x^2 + 1x^1 + 4x^0");

        pol.readPolinom("2x^3 + 3x^2 + 5x^1 + 4x^0");
        Polinom rez = op.multiplication(p.getList(), q.getList());

        assertEquals(rez.printPolinom(), pol.printPolinom());
    }

    @Test
    public void divisionTest() throws InvalidInput{

        Operations op = new Operations();
        Polinom p = new Polinom();
        Polinom q = new Polinom();
        Polinom cat = new Polinom();
        Polinom rest = new Polinom();

        p.readPolinom("1x^1 + 1x^0");
        q.readPolinom("2x^2 + 1x^1 + 3x^0");

        cat.readPolinom("2x^1 - 1x^0");
        rest.readPolinom("4x^0");
        ArrayList<Polinom> rez = op.division(q, p);


        assertEquals(rez.get(0).printPolinom(), cat.printPolinom());
        assertEquals(rez.get(1).printPolinom(), rest.printPolinom());

    }

    @Test
    public void derivativeTest() throws InvalidInput {

        Operations op = new Operations();
        Polinom p = new Polinom();
        Polinom pol = new Polinom();

        p.readPolinom("1x^1 + 1x^0");

        pol.readPolinom("1x^0");
        Polinom rez = op.derivative(p.getList());

        assertEquals(rez.printPolinom(), pol.printPolinom());
    }

    @Test
    public void integrationTest() throws InvalidInput{

        Operations op = new Operations();
        Polinom p = new Polinom();
        Polinom pol = new Polinom();

        p.readPolinom("1x^1 + 1x^0");

        pol.readPolinom("0.5x^2 + 1x^1");
        Polinom rez = op.integration(p.getList());

        assertEquals(rez.printPolinom(), pol.printPolinom());
    }
}
