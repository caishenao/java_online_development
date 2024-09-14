package groovy

class Init {
    public static void run() {
        Function function = new Function()
        def hello = function.hello("李明");
        println(hello)
    }

}
