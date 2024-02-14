public class OutPatient extends Examination {
    @Override
    public String getInfo() {
        return "Outpatient\t" ;
    }
    @Override
    public int cost() {
        return 15 ;
    }   
}