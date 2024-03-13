package reference;

public class SelfDefinedObject {

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("this object is finalized");
    }
}
