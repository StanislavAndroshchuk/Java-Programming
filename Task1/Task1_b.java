package Task1;

public class Task1_b {
    public static void main(String[] args) {
        String input = "programming,great,language";

        String[] words = input.split(",");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (result.length() > 0) {
                result.append(",");
            }

            // Replace
            String transformedWord = word.replace("g", "th");
            result.append(transformedWord);
        }

        System.out.println(result.toString());
    }
}
