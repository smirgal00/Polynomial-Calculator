package Polynomial;

import org.junit.platform.commons.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polinom {
    ArrayList<Monom> polinoame = new ArrayList<Monom>();

    public Polinom() {

    }

    public void addMonome(Monom e) {
        boolean ok = true;

        for (Monom temp : polinoame) {
            if (temp.getPower() == e.getPower() && e.getPower() != -1) {
                temp.addCoefficient(e.getCoefficient());
                ok = false;
                break;
            }
        }

        if (ok == true && e.getPower() != -1) {
            polinoame.add(e);
        }

    }

    public Monom getElement(int i) {
        return this.polinoame.get(0);
    }

    public void removeZeroes() {
        int i = 0;
        while (i < polinoame.size()) {
            if (polinoame.get(i).getCoefficient() == 0) {
                polinoame.remove(i);
            } else {
                i++;
            }
        }
    }

    private int countChar(String s) {
        int nr = 0;
        for(char x : s.toCharArray()) {
            if (x == 'x' || x == 'X') {
                nr++;
            }
        }

        return nr;
    }

    public boolean readPolinom(String s) throws InvalidInput{
        Pattern reg = Pattern.compile("(-?\\b\\d+\\.?\\d*)[xX+]\\^(-?\\d+\\b)");
        int matchCondition = 0;
        boolean ret = false;
        int nrOfX = countChar(s);

        if(s.equals("")) {
            throw new InvalidInput();
        }

        s = s.replace(" ", "");
        Matcher mat = reg.matcher(s);

        while(!polinoame.isEmpty()) {
            polinoame.remove(0);
        }
        while (mat.find()) {
            addMonome(new Monom(Integer.parseInt(mat.group(2)), Double.parseDouble(mat.group(1))));
            matchCondition++;
            ret = true;
        }

        if(matchCondition != nrOfX) {
            throw new InvalidInput();
        }

        return ret;
    }

    public int getDegree() {
        int max = Integer.MIN_VALUE;

        for (Monom x : polinoame) {
            if (x.getPower() > max) {
                max = x.getPower();
            }
        }

        return max;
    }

    public void sortPolinom() {
        polinoame.sort(Comparator.comparingInt(Monom::getPower).reversed());
    }

    public String printPolinom() {
        removeZeroes();
        int cnt = 0;
        String poli = "";
        for (Monom temp : polinoame) {
            if (cnt == 0) {
                poli = poli.concat(temp.getMonome());
                cnt++;
            } else if (cnt < polinoame.size()) {
                if (temp.getCoefficient() > 0) {
                    poli = poli.concat("+ " + temp.getMonome());
                    cnt++;
                } else {
                    poli = poli.concat(temp.getMonome());
                }
            }
        }

        return poli;
    }

    public ArrayList<Monom> getList() {
        return polinoame;
    }

}
