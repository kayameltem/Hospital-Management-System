public class Imaging extends Operations {

    public Imaging(Examination e) {
        setExamination(e);
    }

    @Override
    public String getInfo() {
        return getExamination().getInfo() + "imaging " ;
    }

    @Override
    public int cost() {
        return 10 + getExamination().cost() ;
    }  
}