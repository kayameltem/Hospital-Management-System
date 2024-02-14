import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class AdmissionTxt implements InputFiles {
    String path = "admission.txt";
    private static Map<Integer,Patient> map = new TreeMap<Integer, Patient>();

    public AdmissionTxt() throws IOException {
        reader(path);
        
    }
    
    public AdmissionTxt(String input) throws IOException {
        // does operation on input related with first word of it. 
        int index = input.indexOf(" ")+1;
        String[] line = input.substring(index).split(" ");
        if (input.startsWith("CreateAdmission"))
            OutputTxt.writer(createAdmission(line[0],line[1]));
        else if (input.startsWith("AddExamination")) 
            OutputTxt.writer(addExamination(line));
        else if (input.startsWith("TotalCost")) 
            OutputTxt.writer(totalCostPrint(line));
        
    }
    @Override
        public void reader(String path) throws IOException {
            /* reads Admission.txt line by line and if occurs an error while 
            reading the file, returns an error message. */
            BufferedReader reader = null;
            String[] array ;
        try {
            FileReader file = new FileReader(path);
            reader = new BufferedReader(file);
            String line;
            String s = "";    
            while ((line = reader.readLine()) != null) { 
                line =  line.replace(" ", "\t").trim().replace("\n","");
                if (!line.isEmpty()) {
                 try {
                    array = line.split("\t");
                    createAdmission(array[0],array[1]);
                    s = array[0].concat("\t");
                } catch (NumberFormatException e) {
                    line = s.concat(line);
                    array = line.split("\t");
                    addExamination(array);
                }
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
    
    private String createAdmission(String admissionID ,String patientID) throws IOException{
        // creates an admission with given admissionID to the patient has "patientID"
        for (Patient patient : PatientTxt.getPatients()) {
            if (patient.getId() == Integer.valueOf(patientID)){
                map.put(Integer.valueOf(admissionID), patient);
                return String.format("Admission %s created", admissionID);
        }}
        System.out.printf("Patient has not found and admission %s"
                        + " is not created", admissionID);
                return "";

    }   
    private String addExamination(String[] examinations ) throws IOException{
        /*  adds examination to the patient.
            Used decorator pattern for adding examinations many times 
            within an admission
        */
        Examination examination = null;
        Patient patient = map.get(Integer.valueOf(examinations[0]));
        if (examinations[1].equals("Inpatient"))
            examination = new InPatient();
        else if (examinations[1].equals("Outpatient"))
            examination = new OutPatient();
        for (int i = 2; i< examinations.length; i++) {
            if (examinations[i].equals("tests")) 
                examination = new Test(examination);
            else if (examinations[i].equals("imaging")) 
                examination = new Imaging(examination);
            else if (examinations[i].equals("measurements")) 
                examination = new Measurements(examination);
            else if (examinations[i].equals("doctorvisit")) 
                examination = new DoctorVisit(examination);
        }
        patient.setExamination(examination);
        patient.setTotalCost(examination.cost());
        return String.format("%s examination added to admission %s",
                examinations[1],examinations[0]);
    }
    public static void writer() throws IOException{
        // updates "admission.txt" parallel with input file.
        FileWriter file = new FileWriter("admission.txt");
        PrintWriter writer = new PrintWriter(file,true);
        for ( int i: map.keySet()){
            Patient p = map.get(i);
            if (PatientTxt.getPatients().contains(p)){
            writer.printf("%d\t%s\n",i,p.getId());
            for (Examination e : p.getExamination()) {
                writer.println(e.getInfo());  
            }
            }
        }
}
        private String totalCostPrint(String[] line) {
            // prints out total cost of examinations of the patient.
            String s = String.format("TotalCost for admission %s\n",line[0]);
            Patient patient = map.get(Integer.valueOf(line[0]));
            ArrayList<Examination> examination = patient.getExamination();
            for (Examination e: examination) {
                s = String.format("%s\t%s%d$\n",s,e.getInfo().replace("\t"," "),e.cost()); 
            }
            s = String.format("%s\tTotal: %d$",s,patient.getTotalCost());
            return s; 
        }
}