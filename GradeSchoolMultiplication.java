import java.util.Arrays;
public class GradeSchoolMultiplication {
    /*
    * Two arrays (x and y) will be multiplied together and the product will also be in a array.
    * There will be a grand total called "sumTotal" which will be added to after every multiplication.
    * The process will go: the 'ones' place element in array 'y' will be multiplied with the
    * 'ones' place element in array 'x', and then the same y with the 'tens' in x and so on.
    * Every product will then be divided by 10 to get the remainder for the next line.
    * The product will be modded 10 and multiplied by a factor of 10 (or 1 for ones place) to ensure
    * the right number is being added to the subtotal (Ex: 'y' ones place times 'x' tens place will be multiplied by 10).
    * The sum total will then be made into an array.
    * */
    private static final int DEFAULT_BASE = 10;
    private static final int [] x = {1, 5};
    private static final int [] y = {1,6};

    public static void main (String [] args){
        System.out.print(Arrays.toString(multiply(x,y)));
    }
    //Takes in two int arrays and multiplies them, taking the rightmost element in the "y" array
    //and multiplying it by every element in "x" from right to left.
    public static int[] multiply(final int[] x, final int[] y) {
        //Will hold the grand total of the products.
        int sumTotal = 0;
        //Holds the remainder.
        int remainder = 0;
        //'line' and 'place' Will help keep track of the numbers place (ones, tens, etc..).
        int line = 0;
        int place = 1;
        //*Takes every element in array 'y' (from right to left)
        for(int i = y.length - 1; i >= 0; i--){
            //*and multiplies it with every x array element, then the 'y' element will cycle to the next.
            for(int j = x.length - 1; j >= 0; j--){
                //Every product will be handled one by one, being held in int 'product.'
                int product = y[i] * x[j] + remainder;
                //Each product will be divided by 10 to get the remainder for the next multiplication.
                //The product will then be updated to show the ones place by being modded by 10
                //The only time these don't happen is when there isn't another multiplication process after with the same y element.
                //(When the next multiplication doesn't give up its remainder)
                if(j != 0) {
                    remainder = product / DEFAULT_BASE;
                    product %= DEFAULT_BASE;
                }
                //The ints line and place keep track of the 0s.
                //'place' will remember which place the x element is, and line will keep track of y's place.
                //This is being done so ,for examples, 2345 will be added to sumTotal as: 5 + 40 + 300 + 2000.
                //fixMultiple will be used to add the correct amount of zeros before adding to sumTotal
                sumTotal += fixMultiple(product, line + place);
                //place is then updated for the next  x place.
                place++;
            }
            //Once the y element has multiplied with every x, remainder and place get reset and line goes up so every
            //next product starts with a 0 (times 10 start).
            remainder = 0;
            place = 1;
            line++;
        }
        //An empty array is made to contain the sumTotal
        int [] answer = new int[count(sumTotal)];
        //and returned.
        return fillUp(answer, sumTotal);
    }
    //Takes in a product and returns the correct amount of zeros depending on the offset (what line it currently is and x's place)
    public static int fixMultiple(int num, int offset){
        return num * square(offset);
    }
    //No MATH. so made one
    public static int square(int exponent){
        int num = 1;
        for(int i = 1; i < exponent; i++){
            num *= DEFAULT_BASE;
        }
        return num;
    }
    //will return the length of the given 'num'
    public static int count(int num){
        int count = 0;
        while (num != 0){
            count++;
            num /= DEFAULT_BASE;
        }
        return count;
    }
    //Will fill in the given array with the given number (from right to left so modding and division can be simple)
    public static int[] fillUp(int [] x, int num){
        for(int i = x.length - 1; i >= 0; i--){
            x[i] = num % DEFAULT_BASE;
            num /= DEFAULT_BASE;
        }
        return x;
    }
}