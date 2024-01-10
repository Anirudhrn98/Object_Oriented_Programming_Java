import java.sql.SQLOutput;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regex {

      public static void main(String[] args) {
//            Pattern p = Pattern.compile("^[a-z&&[^aeiou]]*[a][a-z&&[^aeiou]]*[e][a-z&&[^aeiou]]*[i][a-z&&[^aeiou]]*[o][a-z&&[^aeiou]]*[u][a-z&&[^aeiou]]*$");
//            Pattern p = Pattern.compile("^(AC/DC|ac/dc)$");
//            Pattern p = Pattern.compile("[a-z && [^hpb]]*");
//            Pattern p = Pattern.compile(".*a+..");
//            Pattern p = Pattern.compile("^[(](uno|one|eins)[)]$");
//            Pattern p = Pattern.compile("^[mM]oma$");
//            Pattern p = Pattern.compile("^\\[[a-mO-Z]*\\]$");
//            Pattern p = Pattern.compile("^[a][1-3]{2}$");
//            Pattern p = Pattern.compile("^[a][0-9]+$");
//            Pattern p = Pattern.compile("^[a-z]{2}\\d{3}$");
            Pattern p = Pattern.compile("^[a-z]{2}\\d{3}$");
            Matcher m = p.matcher("br069");
            boolean b = m.matches();
            System.out.println(m);
            System.out.println(b);
      }


}
