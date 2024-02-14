import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InputTxt implements InputFiles {
    private String path;

    public InputTxt(String path) throws IOException {
        this.path = path;
        new PatientTxt();
        new AdmissionTxt();
        reader(path);
        AdmissionTxt.writer();    
    }
    
    public void reader(String path) throws IOException {
        /* reads Input.txt line by line and if occurs an error while 
            reading the file, returns an error message. */
        BufferedReader reader = null;
        try {
            FileReader file = new FileReader(path);
            reader = new BufferedReader(file);
            String line ;
            while ((line = reader.readLine()) != null) {
                line =  line.trim().replace("\n","");
                if (!line.isEmpty()) 
                    whichInputClass(line);
            } 
        }
        catch (FileNotFoundException e ) {
            System.err.printf("No such a  %s file..\n",path);
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
    private void whichInputClass(String line) throws IOException{
        //  Processes the line acording to the first word.
        if (line.startsWith("AddPatient") || line.startsWith("RemovePatient"))
            new PatientTxt(line);
        else if (line.startsWith("CreateAdmission") || line.startsWith("AddExamination") || line.startsWith("TotalCost"))
            new AdmissionTxt(line);
        else if (line.startsWith("ListPatients"))
            OutputTxt.listPatient(); 
    }
}