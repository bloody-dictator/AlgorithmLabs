public enum NameAlg {
    RECURSIVEWITHCASH("recursive with cash"),
    RECURSIVE("recursive"),
    ITERATIVE("iterative");

    private String title;
     NameAlg(String title){
         this.title = title;
     }

     public String getTitle(){
         return title;
     }

     @Override
    public String toString(){
         return title;
     }
}
