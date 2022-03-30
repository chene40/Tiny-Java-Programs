import java.io.*;
import java.util.ArrayList;

public class WordCounter {
    // use the m_treemap as the data structure for word counting
    Q1_TreeMapByBST m_treemap = new Q1_TreeMapByBST();

    // implement buildTreeMap() to build a treemap that stores
    // the words and word frequencies of a document into m_treemap.
    // "filename" is the path to the file. Please use the "JingleBell.txt" under folder "data".
    // You can ONLY modify the body of this method including the return statement.
    public void buildTreeMap(String filename){
        try {
            // get the relative path of the file
            String filepath = "./src/data/" + filename;

            // create input stream of bytes
            InputStream input_stream = new FileInputStream(filepath);

            // convert byte stream into character stream
            Reader input_stream_reader = new InputStreamReader(input_stream);

            // create a buffered reader for efficiency
            BufferedReader buffered_reader = new BufferedReader(input_stream_reader);

            // take in the first line to process
            String line = buffered_reader.readLine();

            // keep reading from file if we do not have an empty line
            while (line != null){
                if (line.length() > 0){ // skip lines that are empty (i.e. for lines that partition verses)
                    // split the line into words when encountering a '[', ']', ' '(white space), or '-'.
                    // e.g. \\[ and \\]: [Verse 1]
                    // ,: not a special character inside regex [] - does not need to be escape with \\,
                    // \\s: white space
                    // \\d: 1, 2 (i.e. any digits should not be counted either)
                    // \\-: one-horse
                    String[] words = line.split("[\\[\\s\\]\\d,\\-]");
                    for (String word : words){
                        // an empty string was appearing in arraylist so this just takes care of that issue
                        // could have also done if (!word.equals(""))
                        if (word.length() > 0) {
                            // find if the word is in our tree map: if it is, it returns the value of the frequency
                            // if not, then it returns -1
                            // check for case-sensitivity, can also do ignoreCase(), upperCase, but will convert to lowerCase to stay consistent throughout
                            int freq = m_treemap.get(word.toLowerCase());

                            // the word is not in our tree map, add it to our tree map (can either lowercase or uppercase)
                            if (freq == -1) {
                                m_treemap.put(word.toLowerCase(), 1);   // since this is our first occurrence of word, the frequency should be 1
                            }
                            else {
                                m_treemap.put(word.toLowerCase(), freq + 1);  // if word is already in our tree map, increase the frequency of this word by 1
                            }
                        }
                    }
                }
                line = buffered_reader.readLine();  // keeps reading the file until we run out of lines
            }
            // close the file
            buffered_reader.close();
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    // print all the counted words in the document in alphabetical order.
    public void printWordsAlphabetical(){
        ArrayList<String> words = m_treemap.getKeysInAlphabeticalOrder();
        for (String word : words){
            System.out.println("\t" + word);
        }
    }

    // write an output file named "Output.txt" under folder "data"
    // The output of will consist of two lists.
    // Each list contains all the words from the file, along with the number of times
    // that the word occurred.
    // One list is sorted alphabetically.
    // The other list is sorted according to the number of occurrences, with the most
    // common words (i.e., a word with a larger frequency or count is said to be more common)
    // at the top and the least common at the bottom.
    // IMPORTANT: the output format is given in the file "Output_Example.txt" under folder "data".
    // Input parameter "filename" is the path to the output file.
    public void outputResult(String filename) {
        try{
            // get the relative path of the file
            String filepath = "./src/data/" + filename;

            // open up a byte stream for writing
            FileOutputStream output_stream = new FileOutputStream(filepath);

            // convert byte stream into character stream
            Writer output_stream_writer = new OutputStreamWriter(output_stream);

            // create a BufferedWriter for efficiency
            BufferedWriter buffered_writer = new BufferedWriter(output_stream_writer);

            ArrayList<String> alpha_words = m_treemap.getKeysInAlphabeticalOrder();

            buffered_writer.write(m_treemap.size() + " words found in file:\n");
            buffered_writer.write("\n");
            buffered_writer.write("List of words in alphabetical order (with counts in parentheses):\n");
            buffered_writer.write("\n");

            // print the words in the treemap in alpha order with their frequency beside it
            for (String word : alpha_words){
                buffered_writer.write("\t" + word + " (" + m_treemap.get(word) + ")\n");
            }
            buffered_writer.write("\n");
            buffered_writer.write("\n");

            buffered_writer.write("List of words by frequency of occurrence:\n");
            buffered_writer.write("\n");

            // copy the AlphaOrder List so that we can sort the words in terms of frequency while still
            // holding their alpha order sort
            ArrayList<String> wordByFrequency = new ArrayList<>(m_treemap.getKeysInAlphabeticalOrder());
            // sorts based on which has the higher frequency
            wordByFrequency.sort((a, b) -> m_treemap.get(b) - m_treemap.get(a));

            // print the words in the treemap in freq order while upholding their alpha order
            for (String word : wordByFrequency){
                buffered_writer.write("\t" + word + " (" + m_treemap.get(word) + ")\n");
            }

            // close the file
            buffered_writer.close();
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    // call each of the above methods in the main() method to
    // successfully count the words and produce expected outputs.
    public static void main(String[] args){
        Q2_WordCounter word_counter = new Q2_WordCounter();

        word_counter.buildTreeMap("JingleBell.txt");

        word_counter.printWordsAlphabetical();

        word_counter.outputResult("Output.txt");
    }
}
