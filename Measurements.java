public class Measurements extends Operations {

    public Measurements(Examination e) {
        setExamination(e);
    }
    
    @Override
    public String getInfo() {
        return getExamination().getInfo() + "measurements " ;
    }
    @Override
    public int cost() {
        return 5 + getExamination().cost() ;
    }
    
}