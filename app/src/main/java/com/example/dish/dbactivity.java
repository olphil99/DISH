package com.example.dish;

import java.sql.*;

import android.os.AsyncTask;

public class dbactivity extends android.app.Activity {

    private class psqlcon extends AsyncTask<Void, Void, Void> {
        public psqlcon() {
            super();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Connection conn = null;
            Statement st = null;

            try {
                Class.forName("org.postgresql.Driver");
                System.out.println("connecting to db !! ");
                conn = DriverManager.getConnection("jdbc:postgresql://domain.com:5432/DISH", "root", "root");

                System.out.println("execute query ... ? ");
                st = conn.createStatement();
                String query;
                query = "SELECT id FROM users;";
                ResultSet rs = st.executeQuery(query);

                while (rs.next()) {
                    System.out.println(rs.getString("id"));
                }

                rs.close();
                st.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException see) {
                System.out.println("oops");
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }

            return null;
        }
    }
};



