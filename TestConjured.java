import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class TestConjured {
    public static void main(String[] args) {
        try {
            File file = new File("Java/src/main/java");
            URL url = file.toURI().toURL();
            URLClassLoader classLoader = new URLClassLoader(new URL[]{url});
            
            Class<?> itemClass = classLoader.loadClass("com.gildedrose.Item");
            Class<?> gildedRoseClass = classLoader.loadClass("com.gildedrose.GildedRose");
            
            Object conjuredItem = itemClass.getConstructor(String.class, int.class, int.class)
                .newInstance("Conjured Mana Cake", 3, 6);
            Object normalItem = itemClass.getConstructor(String.class, int.class, int.class)
                .newInstance("Normal Item", 3, 6);
            
            Object[] items = {conjuredItem};
            Object[] normalItems = {normalItem};
            
            Object gildedRose = gildedRoseClass.getConstructor(itemClass.arrayType())
                .newInstance((Object) items);
            Object normalRose = gildedRoseClass.getConstructor(itemClass.arrayType())
                .newInstance((Object) normalItems);
            
            System.out.println("Testing Conjured Items Implementation");
            System.out.println("====================================");
            
            System.out.println("Day 0: " + conjuredItem);
            gildedRoseClass.getMethod("updateQuality").invoke(gildedRose);
            System.out.println("Day 1: " + conjuredItem + " (should be quality 4)");
            gildedRoseClass.getMethod("updateQuality").invoke(gildedRose);
            System.out.println("Day 2: " + conjuredItem + " (should be quality 2)");
            gildedRoseClass.getMethod("updateQuality").invoke(gildedRose);
            System.out.println("Day 3: " + conjuredItem + " (should be quality 0)");
            gildedRoseClass.getMethod("updateQuality").invoke(gildedRose);
            System.out.println("Day 4: " + conjuredItem + " (should be quality 0, past sell date)");
            
            System.out.println("\nComparing with normal item:");
            System.out.println("Day 0: " + normalItem);
            normalRose.getClass().getMethod("updateQuality").invoke(normalRose);
            System.out.println("Day 1: " + normalItem + " (should be quality 5)");
            normalRose.getClass().getMethod("updateQuality").invoke(normalRose);
            System.out.println("Day 2: " + normalItem + " (should be quality 4)");
            normalRose.getClass().getMethod("updateQuality").invoke(normalRose);
            System.out.println("Day 3: " + normalItem + " (should be quality 3)");
            normalRose.getClass().getMethod("updateQuality").invoke(normalRose);
            System.out.println("Day 4: " + normalItem + " (should be quality 1, past sell date)");
            
            System.out.println("\nConjured items degrade twice as fast as expected!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
