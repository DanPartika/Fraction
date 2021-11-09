import java.util.ArrayList;

import javax.swing.undo.UndoManager;

public class Frac {

    int numerator;
    int denominator;
    /**
    * Constructor, base case
    */
    public Frac () {
        numerator = 1;
        denominator = 0;
    }

    /*
    *Constructior that takes 2 parameters 
    */
    public Frac(int numr, int denr) {
        if (denr == 0) {
            throw new IllegalArgumentException("Error: Denominator cannot equal 0.");
        }
	    numerator = numr;
	    denominator = denr;
        simplify();
    }

    public int getDenominator() {
        return denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public static boolean isEven(int num) {
        if (num % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static Frac max(Frac frac1, Frac frac2) {
        if (frac1.getDenominator() > frac2.getDenominator()) {
            return frac1;
        } else {
            return frac2;
        }
    }

    public static Frac min(Frac frac1, Frac frac2) {
        if (frac1.getDenominator() > frac2.getDenominator()) {
            return frac2;
        } else {
            return frac1;
        }
    }

    public int getGCD(int numerator1, int denominator1){
        if (numerator1 % denominator1 == 0) {
            return denominator1;
        }
        return getGCD(denominator1, numerator1 % denominator1);
    }

    public static int findGCD(Frac frac1, Frac frac2) { //finds the gcd between 2 fractions
        if (frac1.getDenominator() == 1 || frac2.getDenominator() == 1) {     // check if either number is a whole number
            if (frac2.getDenominator() == 1) {                           //if it is we already have the gcd
                return frac1.getDenominator();
            } else {
                return frac2.getDenominator();
            }
        } else {                                                    //otherwise, 
            Frac max = max(frac1, frac2);
            Frac min = min(frac1, frac2);
            if (max.getDenominator() % min.getDenominator() == 0){            // if one of den. is the GDC
                return max.getDenominator();
            } else {
                int num = frac1.getDenominator() * frac2.getDenominator();
                System.out.println(num);
                return num;
            }
        }
    }

    void simplify() {
        int gcd = getGCD(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
    }


    public static Frac Add(Frac frac1, Frac frac2) {
        if (frac1.denominator == frac2.denominator) {
            return new Frac(frac1.numerator + frac2.numerator, frac1.denominator);
        } else {
            int numer = (frac1.getNumerator() * frac2.getDenominator()) + (frac2.getNumerator() * frac1.getDenominator());
            int denom = frac1.getDenominator() * frac2.getDenominator();
            return new Frac(numer, denom);
        }
        
    }

    public Frac Subtract(Frac frac2) {
        int numer = (numerator * frac2.getDenominator()) - (denominator * frac2.getNumerator());
        int denom = denominator * frac2.getDenominator();
        return new Frac(numer, denom);
    }

    public static Frac Multiply(Frac frac1, Frac frac2) {
        int numer = frac1.getNumerator() * frac2.getNumerator();
        int denom = frac1.getDenominator() * frac2.getDenominator();
        return new Frac(numer, denom);
    }

    public Frac Divide(Frac frac2) {
        int numer = numerator * frac2.getDenominator();
        int denom = denominator * frac2.getNumerator();
        return new Frac(numer, denom);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        if (this.denominator != 1) {
            return this.numerator + "/" + this.denominator;
        } else {
            return this.numerator + " ";
        }
        
    }

    public static Frac simplifyFrac(Frac ans) {
        int temp = ans.numerator % ans.denominator;
        int wholeNum = ans.numerator / ans.denominator;
        if (ans.numerator > ans.denominator && temp != 0) { // improper fraction that is mixed number
            ans.numerator += temp;
            return ans;
        } else if (temp == 0) { //just whole number 
            return new Frac(wholeNum,1);
        } else {
            return ans;
        }
    }

    public static void main(String[] args) {
        
        ArrayList<Frac> ints = new ArrayList<Frac>();
        for (int i = 1; i <= 10; i++) {
            Frac number = new Frac(i,1);
            ints.add(number);
        }
    
        ArrayList<Frac> fracts = new ArrayList<Frac>();
        int[] a = {3,1,1,3,1,2,2,1,1,2};
        int[] b = {5,6,8,4,2,4,3,4,3,5};
        for (int i = 0; i < b.length; i++) {
            Frac number = new Frac(a[i], b[i]);
            fracts.add(number);
        }

        System.out.println("--------------------------Addition--------------------------");
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b.length; j++) {
                Frac ans = Add(ints.get(i), fracts.get(j));
                int temp = ans.numerator % ans.denominator;
                int wholeNum = ans.numerator/ans.denominator;
                if (ans.numerator > ans.denominator && temp != 0) { // improper fraction that is mixed number
                    ans.numerator = temp;
                    System.out.println(ints.get(i).numerator + " + " + fracts.get(j) + " = " + wholeNum + " " + ans.toString()); 
                } else if (temp == 0) { //just whole number 
                    System.out.println(ints.get(i).numerator + " + " + fracts.get(j) + " = " + wholeNum);
                } else {
                    System.out.println(ints.get(i).numerator + " + " + fracts.get(j) + " = " + ans.toString());
                }

            }
        }
        
        System.out.println("--------------------------Subtraction--------------------------");
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b.length; j++) {
                Frac ans = ints.get(i).Subtract(fracts.get(j));
                int temp = ans.numerator % ans.denominator;
                int wholeNum = ans.numerator/ans.denominator;
                if (ans.numerator > ans.denominator && temp != 0) { // improper fraction that is mixed number
                    ans.numerator = temp;
                    System.out.println(ints.get(i).numerator + " - " + fracts.get(j) + " = " + wholeNum + " " + ans.toString()); 
                } else if (temp == 0) { //just whole number 
                    System.out.println(ints.get(i).numerator + " - " + fracts.get(j) + " = " + wholeNum);
                } else {
                    System.out.println(ints.get(i).numerator + " - " + fracts.get(j) + " = " + ans.toString());
                }   
            }
        }
        System.out.println("--------------------------Multiplaction--------------------------");
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b.length; j++) {
                Frac ans = Multiply(ints.get(i), fracts.get(j));
                int temp = ans.numerator % ans.denominator;
                int wholeNum = ans.numerator/ans.denominator;
                if (ans.numerator > ans.denominator && temp != 0) { // improper fraction that is mixed number
                    ans.numerator = temp;
                    System.out.println(ints.get(i).numerator + " ✖ " + fracts.get(j) + " = " + wholeNum + " " + ans.toString()); 
                } else if (temp == 0) { //just whole number 
                    System.out.println(ints.get(i).numerator + " ✖ " + fracts.get(j) + " = " + wholeNum);
                } else {
                    System.out.println(ints.get(i).numerator + " ✖ " + fracts.get(j) + " = " + ans.toString());
                }
            }
        }
    
       System.out.println("--------------------------Division--------------------------");
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b.length; j++) {
                Frac ans = ints.get(i).Divide(fracts.get(j));
                int temp = ans.numerator % ans.denominator;
                int wholeNum = ans.numerator/ans.denominator;
                if (ans.numerator > ans.denominator && temp != 0) { // improper fraction that is mixed number
                    ans.numerator = temp;
                    System.out.println(ints.get(i).numerator + " ÷ " + fracts.get(j) + " = " + wholeNum + " " + ans.toString()); 
                } else if (temp == 0) { //just whole number 
                    System.out.println(ints.get(i).numerator + " ÷ " + fracts.get(j) + " = " + wholeNum);
                } else { // just a fraction
                    System.out.println(ints.get(i).numerator + " ÷ " + fracts.get(j) + " = " + ans.toString());
                }
            }
        }
    }
}
