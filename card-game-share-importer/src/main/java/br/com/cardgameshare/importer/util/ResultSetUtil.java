package br.com.cardgameshare.importer.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetUtil {

    public static boolean isEmpty(ResultSet resultSet) {
        if (resultSet != null) {
            try {

                resultSet.last();
                int rowNumber = resultSet.getRow();
                resultSet.beforeFirst();
                if (rowNumber > 0) {
                    return false;
                }

            } catch (SQLException e) {
                return true;
            }

        }
        return true;
    }

}
