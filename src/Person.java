public class Person implements Comparable<Person>{
    private String name;
    private int birthYear;

    public Person(){
        this.name = "";
        this.birthYear = 0;
    }

    public Person(String name, int birthYear){
        this.name = name;
        this.birthYear = birthYear;
    }

    public String getName(){
        return this.name;
    }

    public int getBirthYear(){
        return this.birthYear;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    @Override
    public boolean equals(Object o) {
//        equals(Object obj): boolean	//all attributes must match for 2 Course objects to be considered equal
       if(o instanceof Person){
           Person temp = (Person) o;
           if(temp.name.equals(this.name) && temp.birthYear == this.birthYear){
               return true;
           }
       }
       return false;
    }


    public String toString(){
        return String.format("Person: Name: %30s | Birth Year: %4d",name, birthYear);
    }

    @Override
    public int compareTo(Person p) {
        if( this.birthYear > p.birthYear){
            return 1;
        }
        else if(this.birthYear < p.birthYear){
            return -1;
        }
        return 0;
    }

}


/*

name: String
birthYear: int

Person()			// name = “”, birthYear = 0
Person(String name, int birthYear)
getName():String
getBirthYear():int
setName(String name): void
setBirthYear(int year): void
equals(Object obj): boolean	//all attributes must match for 2 Course objects to be considered equal
toString(): String	//”Person: Name: %30s | Birth Year: %4d ”, name, birthYear
compareTo(Person p): int	//use the Comparable interface specification. Sort by birthYear.

 */