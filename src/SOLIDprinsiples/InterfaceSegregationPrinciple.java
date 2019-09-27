package SOLIDprinsiples;

//ISP
public class InterfaceSegregationPrinciple {
    /**
     * 인터페이스 분리 원리는
     * 클라이언트가 자신이 이용하지 않는 메소드에 의존하지 않아야 한다는 원칙
     * 기본적으로 인터페이스 분할하는 방법에 대한 권장 사항입니다.
     *
     * 큰 덩어리의 인터페이스들을 구체적이고 작은 단위들로 분리시킴으로써 클라이언트들이 꼭 필요한 메소드들만 이용할 수 있게 한다.
     *
     * 인터페이스를 상속받는 객체에서 해당 메소드에 throws Exception으로 실행하지 못하도록 막으면
     * 해당 에러는 인터페이스까지 전파 된다.
     */
}

class Document {

}

interface Machine {
    void print(Document d);

    void fax(Document d) throws Exception;

    void scan(Document d);
}

class MultiFunctiionPrinter implements Machine {

    @Override
    public void print(Document d) {

    }

    @Override
    public void fax(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}

class OldFashionPrinter implements Machine {

    @Override
    public void print(Document d) {
        //
    }

    @Override
    public void fax(Document d) throws Exception {
        throw new Exception();
    }

    @Override
    public void scan(Document d) {
        //
    }
}

interface Printer {
    void print(Document d);
}

interface Scanner {
    void scan(Document d);
}

// YAGNI = You Ain't Going to Need It

class JustAPrinter implements Printer {

    @Override
    public void print(Document d) {

    }
}

class Photocopier implements Printer, Scanner {

    @Override
    public void print(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}

interface MultiFunctionDevice extends Printer, Scanner {
}

class MultiFunctionMachine implements MultiFunctionDevice {
    private Printer printer;
    private Scanner scanner;

    public MultiFunctionMachine(Printer printer, Scanner scanner) {
        this.printer = printer;
        this.scanner = scanner;
    }

    @Override
    public void print(Document d) {
        printer.print(d);
    }

    @Override
    public void scan(Document d) {
        scanner.scan(d);
    }
}