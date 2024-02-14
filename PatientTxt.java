import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PatientTxt implements InputFiles{
    private String path = "patient.txt" ;
    private static ArrayList<Patient> patients = new ArrayList<Patient>();

    public PatientTxt() throws IOException {
        reader(path);
    }
         
    public PatientTxt(String input) throws IOException {
        int index = input.indexOf(" ")+1;
        if (input.startsWith("AddPatient"))
            OutputTxt.writer(addPatient(input.substring(index)));
        else if (input.startsWith("RemovePatient"))
            OutputTxt.writer(removePatient(input.substring(index)));
    }
    @Override
    public void reader(String path) throws IOException {
        /* reads patient.txt line by line and creates patient objects. 
        If occurs an error while reading the file, returns an error message. */
        BufferedReader reader = null;
        String[] p = new String[5];
    try {
        FileReader file = new FileReader(path);
        reader = new BufferedReader(file);
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.isEmpty()) {
            line =  line.trim().replace("\n","");
            line = line.replaceFirst(" ", "\t");
            p = line.split("\t");
            patients.add(new Patient(p[0],p[1],p[2],p[3],p[4].substring(9)));
        } 
        }
    }
    catch (FileNotFoundException e ) {
        System.err.printf("No such a  %s file..\n", path);
        System.exit(0);
    }
    catch (IOException e){
        System.err.printf("Occurs an error while reading %s file..\n", path);
        System.exit(0);
    }    
    finally {
        if (reader != null) 
            reader.close(); 
        }
    }
    private String addPatient(String s) throws IOException {
      // adds the patient into patients arraylist and returns an information message. 
            String[] x = new String[4] ;
            int i = 0;
            while (i< 4) {
            x[i] = s.substring(0, s.indexOf(" "));
            s =s.substring(s.indexOf(" ")+1);
            i++; }
            patients.add(new Patient(x[0],x[1],x[2],x[3],s));
            writer();
            return String.format("Patient %s %s added", x[0],x[1]);
    }
    private String removePatient(String id) throws IOException {
        // removes the patient has given ID and returns an information message.
        for (Patient patient:patients) {
            if (patient.getId() == Integer.valueOf(id)) {
                patients.remove(patient); 
            writer();
            return String.format("Patient %s %s removed", patient.getId(),patient.getName());
     }}System.out.printf("Patient with ID: %s has not found.. ",id);
        return "" ;
    }
    private static void writer() throws IOException{
    // updates "patient.txt" parallel with given inputs and its process.
    Collections.sort(patients,Comparator.comparing(Patient::getId));
    FileWriter file = new FileWriter("patient.txt");
    PrintWriter writer = new PrintWriter(file,true);
    for (Patient patient : patients) 
        writer.println(patient.info());
    }

    public static ArrayList<Patient> getPatients() {
        return patients;
    }
}