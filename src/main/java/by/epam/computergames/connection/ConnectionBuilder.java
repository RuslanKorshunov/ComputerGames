package by.epam.computergames.connection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class ConnectionBuilder
{
    private static final String URL="jdbc:mysql://localhost:3306/CompGames";
    private static final String USER="root";
    private static final String PASSWORD="12345";

    private BlockingQueue<ProxyConnection> connections;

    void create(int size) throws ConnectionException
    {
        connections =new LinkedBlockingQueue<>(size);
        for(int index=0; index<size; index++)
        {
            ProxyConnection connection=new ProxyConnection(URL, USER, PASSWORD);
            connections.add(connection);
        }
    }

    BlockingQueue<ProxyConnection> getConnections()
    {
        return connections;
    }
}