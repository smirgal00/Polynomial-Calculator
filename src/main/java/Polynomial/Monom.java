package Polynomial;

import java.math.BigDecimal;

public class Monom {
    private int power;
    private double coefficient;

    public Monom(int power, double coefficient) {
        this.power = power;
        this.coefficient = coefficient;
    }

    public int getPower() {
        return this.power;
    }

    public double getCoefficient() {
        return this.coefficient;
    }

    public void setPower(int x) {
        this.power = x;
    }

    public void addCoefficient(double x) {
        this.coefficient += x;
    }

    public void minusMultiply() {
        this.coefficient *= (-1);
    }

    public String getMonome() {
        if (this.power != 0 && this.coefficient != 0) {
            if ((int) this.coefficient == this.coefficient) {
                return new String((int) this.coefficient + "*x^" + this.power + " ");
            } else {
                return new String(String.format("%.2f", this.coefficient) + "*x^" + this.power + " ");
            }
        } else if (this.coefficient == 0) {
            return new String(" ");
        } else if (this.power == 1) {
            if (this.coefficient == (int) this.coefficient) {
                return new String((int) this.coefficient + "*x ");
            } else {
                return new String(String.format("%.2f", this.coefficient) + "*x ");
            }

        } else if (this.power == 0) {
            if (this.power == (int) this.power)
                return new String((int) this.coefficient + " ");
            else {
                return new String(String.format("%.2f", this.coefficient) + " ");
            }
        } else {
            return new String(" ");
        }

    }
}
