public class InPatient extends Examination {
    @Override
    public String getInfo() {
        return "Inpatient\t" ;
    }

    @Override
    public int cost() {
        return 10 ;
    }  
}