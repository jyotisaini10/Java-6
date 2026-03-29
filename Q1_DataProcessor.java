// You are working on a data processing module for a financial analysis tool. The 
// module should read numeric values from a text file, calculate the average of these 
// values, and output the result to another text file. The program should handle filerelated issues and invalid data using exception handling. 
 
import java.io.*;
import java.util.*; 
class InvalidDataException extends Exception {     
    public InvalidDataException(String message) {         
        super(message); 
    } 
} 
public class Q1_DataProcessor { 
    public static List<Double> readFile(String fileName) 
    throws FileNotFoundException, IOException, InvalidDataException, IllegalArgumentException {         
        BufferedReader br = new BufferedReader(new FileReader(fileName)); 
        String line; 
        List<Double> arr = new ArrayList<>();     
        // BufferedReader br = new BufferedReader(new FileReader(fileName));
        while ((line = br.readLine()) != null) {             
            String[] numbers = line.trim().split("\\s+");             
            for (String number : numbers) {                 
                if (!number.isEmpty()) { 
                    try { 
                        arr.add(Double.parseDouble(number));                     
                    } 
                        catch (NumberFormatException e) {                         
                            throw new InvalidDataException("\"" + number + "\" is not a valid number."); 
                    } 
                } 
            }         }         
            br.close();         
            if (arr.isEmpty()) {             
                throw new IllegalArgumentException("No numeric values found in the file."); 
        }         
        return arr; 
    } 
 
    public static double calculateAverage(List<Double> arr) {         
        double sum = 0;         
        for (double number : arr) {             
            sum += number; 
        }         
        return sum / arr.size(); 
    } 
 
    public static void writeFile(String fileName, double avg)             throws IOException { 
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));         
        bw.write("Average: " + avg);         
        bw.close(); 
    }      
    public static void main(String[] args) { 
        String inputFile = "input.txt";         
        String outputFile = "output.txt";         
        try { 
            List<Double> arr = readFile(inputFile);             
            double avg = calculateAverage(arr);             
            writeFile(outputFile, avg); 
            System.out.println("File written successfully."); 
        } 
        catch (FileNotFoundException e) { 
            System.out.println(e); 
        } 
        catch (InvalidDataException e) { 
            System.out.println(e); 
        } 
        catch (IllegalArgumentException e) { 
            System.out.println(e); 
        } 
        catch (IOException e) {             
            System.out.println(e); 
        } 
    } 
} 
 
