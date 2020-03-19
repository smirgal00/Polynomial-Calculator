package Polynomial;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

public class Operations {

    public Operations() {

    }

    public Polinom addition(ArrayList<Monom> p, ArrayList<Monom> q) {
        Polinom temp = new Polinom();

        for (Monom x : p) {
            for (Monom y : q) {
                if (x.getPower() == y.getPower() && x.getPower() != -1) {
                    temp.addMonome(new Monom(x.getPower(), x.getCoefficient() + y.getCoefficient()));
                    x.setPower(-1);
                    y.setPower(-1);
                }
            }
        }

        for (Monom x : p) {
            if (x.getPower() != -1) {
                temp.addMonome(x);
            }
        }

        for (Monom y : q) {
            if (y.getPower() != -1) {
                temp.addMonome(y);
            }
        }

        temp.removeZeroes();
        temp.sortPolinom();
        return temp;
    }

    public Polinom substraction(ArrayList<Monom> p, ArrayList<Monom> q) {
        Polinom temp = new Polinom();

        for (Monom x : p) {
            for (Monom y : q) {
                if (x.getPower() == y.getPower() && x.getPower() != -1) {
                    temp.addMonome(new Monom(x.getPower(), x.getCoefficient() - y.getCoefficient()));
                    x.setPower(-1);
                    y.setPower(-1);
                }
            }
        }

        for (Monom x : p) {
            if (x.getPower() != -1) {
                temp.addMonome(x);
            }
        }

        for (Monom y : q) {
            if (y.getPower() != -1) {
                y.minusMultiply();
                temp.addMonome(y);
            }
        }

        temp.removeZeroes();
        temp.sortPolinom();
        return temp;
    }

    public Polinom multiplication(ArrayList<Monom> p, ArrayList<Monom> q) {
        Polinom rez = new Polinom();

        for (Monom x : p) {
            for (Monom y : q) {
                rez.addMonome(new Monom(x.getPower() + y.getPower(),
                        x.getCoefficient() * y.getCoefficient()));


            }
        }

        rez.removeZeroes();
        rez.sortPolinom();
        return rez;
    }

    public Polinom derivative(ArrayList<Monom> p) {
        Polinom rez = new Polinom();

        for (Monom x : p) {
            if (x.getPower() > 0) {
                rez.addMonome(new Monom(x.getPower() - 1, x.getCoefficient() * x.getPower()));
            } else {
                rez.addMonome(new Monom(0, 0));
            }
        }

        rez.removeZeroes();
        rez.sortPolinom();
        return rez;
    }

    public Polinom integration(ArrayList<Monom> p) {
        Polinom rez = new Polinom();

        for (Monom x : p) {
           if(x.getCoefficient() != 0) {
               rez.addMonome(new Monom(x.getPower() + 1, x.getCoefficient() / (x.getPower() + 1)));
           }
        }

        rez.sortPolinom();
        return rez;
    }

    public ArrayList<Polinom> division(Polinom p, Polinom q) {
        Polinom rez = new Polinom();
        ArrayList<Polinom> div = new ArrayList<Polinom>();
        p.sortPolinom();
        q.sortPolinom();

        while (p.getDegree() >= q.getDegree()) {
            p.removeZeroes();
            q.removeZeroes();

            Monom divTemp = new Monom(p.getElement(0).getPower() - q.getElement(0).getPower(),
                    p.getElement(0).getCoefficient() / q.getElement(0).getCoefficient());

            rez.addMonome(divTemp);
            ArrayList<Monom> temp = new ArrayList<Monom>();
            temp.add(divTemp);
            Polinom multiTemp = multiplication(q.getList(), temp);
            p = substraction(p.getList(), multiTemp.getList());
            p.sortPolinom();
        }

        div.add(rez);
        div.add(p);

        return div;
    }
}
