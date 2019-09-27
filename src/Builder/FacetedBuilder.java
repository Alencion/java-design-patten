package Builder;

public class FacetedBuilder {
}

class Person2
{
    // address
    public String streetAddress, postcode, city;

    // employment
    public String companyName, position;
    public int annualIncome;

    @Override
    public String toString()
    {
        return "Person{" +
                "streetAddress='" + streetAddress + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", annualIncome=" + annualIncome +
                '}';
    }
}

// builder facade
class PersonBuilder2
{
    // the object we're going to build
    protected Person2 person2 = new Person2(); // reference!

    public PersonJobBuilder2 works()
    {
        return new PersonJobBuilder2(person2);
    }

    public PersonAddressBuilder2 lives()
    {
        return new PersonAddressBuilder2(person2);
    }

    public Person2 build()
    {
        return person2;
    }
}

class PersonAddressBuilder2 extends PersonBuilder2
{
    public PersonAddressBuilder2(Person2 person2)
    {
        this.person2 = person2;
    }

    public PersonAddressBuilder2 at(String streetAddress)
    {
        person2.streetAddress = streetAddress;
        return this;
    }

    public PersonAddressBuilder2 withPostcode(String postcode)
    {
        person2.postcode = postcode;
        return this;
    }

    public PersonAddressBuilder2 in(String city)
    {
        person2.city = city;
        return this;
    }
}

class PersonJobBuilder2 extends PersonBuilder2
{
    public PersonJobBuilder2(Person2 person2)
    {
        this.person2 = person2;
    }

    public PersonJobBuilder2 at(String companyName)
    {
        person2.companyName = companyName;
        return this;
    }

    public PersonJobBuilder2 asA(String position)
    {
        person2.position = position;
        return this;
    }

    public PersonJobBuilder2 earning(int annualIncome)
    {
        person2.annualIncome = annualIncome;
        return this;
    }
}

class BuilderFacetsDemo
{
    public static void main(String[] args)
    {
        PersonBuilder2 pb = new PersonBuilder2();
        Person2 person2 = pb
                .lives()
                .at("123 London Road")
                .in("London")
                .withPostcode("SW12BC")
                .works()
                .at("Fabrikam")
                .asA("Engineer")
                .earning(123000)
                .build();
        System.out.println(person2);
    }
}