import java.util.ArrayList;


public class Categories {
    ArrayList<String> cat1;
    ArrayList<String> cat2;
    ArrayList<String> cat3;
    String firstCat = "Animals";
    String secondCat = "U.S States";
    String thirdCat = "Food Cuisines";
    int size1, size2, size3;

    Categories() {
        size1 = 0; size2 = 0; size3 = 0;
        cat1 = new ArrayList<>();
        cat2 = new ArrayList<>();
        cat3 = new ArrayList<>();
    }

    public void init1() {
        cat1.add("Dog");
        cat1.add("Cat");
        cat1.add("Giraffe");
        cat1.add("Whale");
        cat1.add("Eagle");
        cat1.add("Lion");
        cat1.add("Tiger");
        cat1.add("Cheetah");
        cat1.add("Alpaca");
        cat1.add("Penguin");
        cat1.add("Ant");
        cat1.add("Avocet");
        cat1.add("Bison");
        cat1.add("Eel");
        cat1.add("Kangaroo");
        cat1.add("Alligator");
        cat1.add("Turtle");
        cat1.add("Rabbit");
        size1 = cat1.size();
    }

    public void init2() {
        cat2.add("Illinois");
        cat2.add("Ohio");
        cat2.add("Alabama");
        cat2.add("Arizona");
        cat2.add("Delaware");
        cat2.add("Washington");
        cat2.add("Kentucky");
        cat2.add("Tennessee");
        cat2.add("New York");
        cat2.add("Connecticut");
        cat2.add("North Carolina");
        cat2.add("South Carolina");
        cat2.add("Florida");
        cat2.add("Texas");
        cat2.add("Nevada");
        cat2.add("California");
        cat2.add("Idaho");
        cat2.add("Utah");
        size2 = cat2.size();
    }

    public void init3() {
        cat3.add("Indian");
        cat3.add("American");
        cat3.add("African");
        cat3.add("Chinese");
        cat3.add("Thai");
        cat3.add("Lebanese");
        cat3.add("Moroccan");
        cat3.add("Japanese");
        cat3.add("Korean");
        cat3.add("Mediterranean");
        cat3.add("Afghani");
        cat3.add("Mexican");
        cat3.add("Balochi");
        cat3.add("Nepalese");
        cat3.add("Polish");
        cat3.add("British");
        cat3.add("French");
        cat3.add("Greek");
        size3 = cat3.size();
    }

    public String getCatOneWord() {
        return cat1.get(size1 - 1);
    }

    public void removeCatOneWord() {
        cat1.remove(size1 - 1);
        size1--;
    }

    public String getCatTwoWord() {
        return cat2.get(size2 - 1);
    }

    public void removeCatTwoWord() {
        cat2.remove(size2 - 1);
        size2--;
    }
    public String getCatThreeWord() {
        return cat3.get(size3 - 1);
    }

    public void removeCatThreeWord() {
        cat3.remove(size3 - 1);
        size3--;
    }

    public int getSize1() {
        return size1;
    }

    public int getSize2() {
        return size2;
    }

    public int getSize3() {
        return size3;
    }
}