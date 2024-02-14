import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;

public class OutputTxt {
    private static boolean bool = false;
    public static void writer(String line) throws IOException{
        // Writes information about input and its process.
        FileWriter file = new FileWriter("output.txt",bool);
        bool = true;
        PrintWriter writer = new PrintWriter(file,true);
        writer.println(line);
    }
    public static void listPatient() throws IOException {
        // prints out all patients into "output.txt"
        Collections.sort(PatientTxt.getPatients(),Comparator.comparing(Patient::getName));
        writer("Patient List:");
        for (Patient patient : PatientTxt.getPatients()) 
            writer(patient.info().replace("\t", " "));
    }
}