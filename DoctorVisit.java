public class DoctorVisit extends Operations {

    public DoctorVisit(Examination e) {
        setExamination(e);
    }

    @Override
    public String getInfo() {
        return getExamination().getInfo() +"doctorvisit " ;
    }

    @Override
    public int cost() {
        return 15 + getExamination().cost() ;
    }
}