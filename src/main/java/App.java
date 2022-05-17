public class App {
    public static void main(String[] args) {
        Empresa emp = Empresa.getInstance();

        emp.initDataBase();
        emp.Departamentos();
        emp.Programadores();
    }
}
