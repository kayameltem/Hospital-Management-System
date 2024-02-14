public class Test extends Operations {

    public Test(Examination e) {
        setExamination(e);
    }
    
    @Override
    public String getInfo() {
        return getExamination().getInfo() + "tests " ;
    }
    
    @Override
    public int cost() {
        return 7 + getExamination().cost() ;
    }
}