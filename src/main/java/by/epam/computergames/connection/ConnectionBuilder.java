package by.epam.computergames.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class ConnectionBuilder {
    private static final Logger logger = LogManager.getLogger(ConnectionBuilder.class);
    private static final String DB_URL = "jdbc:mysql://localhost:3306/CompGames";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12345";

    BlockingQueue<WrapperConnection> create(int size) {
        BlockingQueue<WrapperConnection> connections = new LinkedBlockingQueue<>(size);
        for (int index = 0; index < size; index++) {
            try {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                WrapperConnection wrapperConnection=new WrapperConnection(connection);
                connections.add(wrapperConnection);
            } catch (SQLException e) {
                logger.error("ConnectionBuilder can't create a connection.");
            }
        }
        return connections;
    }
}