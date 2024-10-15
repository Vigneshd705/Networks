// import java.rmi.server.UnicastRemoteObject;
// import java.rmi.RemoteException;

import java.rmi.*;
import java.rmi.server.*;

public class FibonacciImpl extends UnicastRemoteObject implements Fibonacci {

    public FibonacciImpl() throws RemoteException {
        super();
    }

    @Override
    public int getFibonacci(int n) throws RemoteException {
        if (n <= 1) {
            return n;
        } else {
            return getFibonacci(n - 1) + getFibonacci(n - 2);
        }
    }
}
