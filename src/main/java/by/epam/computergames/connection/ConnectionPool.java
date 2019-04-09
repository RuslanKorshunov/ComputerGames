package by.epam.computergames.connection;

public class ConnectionPool
{
    private static final ConnectionPool instance=new ConnectionPool();
    
    private ConnectionPool()
    { }

    public static ConnectionPool getInstance()
    {

        return instance;
    }
}
