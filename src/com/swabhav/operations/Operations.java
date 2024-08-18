package com.swabhav.operations;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.swabhav.DbConnection;

public interface Operations<K> {
	public Connection connection = DbConnection.connect();
    K get(int id) throws SQLException;
    void delete(int id) throws SQLException;
    K update(int id, K entity) throws SQLException;
    void add(K entity) throws SQLException;
    List<K> findSimilar(K entity) throws SQLException;
    List<K> getAll() throws SQLException;
}
