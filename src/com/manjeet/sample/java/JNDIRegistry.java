package com.manjeet.sample.java;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.ConnectionPoolDataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Using RMI registry for database connection
 * @author manjeet
 *
 */
public class JNDIRegistry {
        private static void startRegistry() throws RemoteException {
                System.out.println(LocateRegistry.getRegistry());
                LocateRegistry.createRegistry(1059);
                System.out.println("RMI registry Started.");
        }

        private static InitialContext createInitialContextContext()
                        throws NamingException {
                Properties properties = new Properties();
                properties.put(Context.INITIAL_CONTEXT_FACTORY,
                                "com.sun.jndi.rmi.registry.RegistryContextFactory");
                properties.put(Context.PROVIDER_URL, "rmi://localhost:1059");
                InitialContext initialContextcontext = new InitialContext(properties);
                return initialContextcontext;
        }

        public static void main(String args[]) {
                try {
                        startRegistry();
                        ConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
                        ((MysqlDataSource) dataSource).setUser("root");
                        ((MysqlDataSource) dataSource).setPassword("");
                        ((MysqlDataSource) dataSource).setServerName("localhost");
                        ((MysqlDataSource) dataSource).setPort(3306);
                        ((MysqlDataSource) dataSource).setDatabaseName("test");

                        InitialContext context = createInitialContextContext();
                        context.rebind("db", dataSource);

                } catch (Exception e) {
                        System.out.println(e.getMessage());
                }
        }
}