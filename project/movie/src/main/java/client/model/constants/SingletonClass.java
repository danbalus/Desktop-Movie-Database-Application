package client.model.constants;

import client.ClientConnection;

import java.io.Serializable;

public class SingletonClass implements Serializable
{
    // static variable single_instance of type SingletonClass
    private static SingletonClass single_instance = null;

    public String id;
    public /*transient */ClientConnection clientConnection;

    // private constructor restricted to this class itself
    private SingletonClass()
    {
       // s = "Hello I am a string part of SingletonClass class";
    }

    public  void setId(String id){
        this.id = id;
    }

    public void setClientConnection(ClientConnection clientConnection) {
        this.clientConnection = clientConnection;
    }

    // static method to create instance of SingletonClass class
    public synchronized static SingletonClass getInstance()
    {
        if (single_instance == null)
            single_instance = new SingletonClass();

        return single_instance;
    }
}