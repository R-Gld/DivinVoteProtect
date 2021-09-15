package fr.tikifirst.divinvote.main;

import org.bukkit.OfflinePlayer;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataManager_SQL implements DataManager {

    /**
     *
     * storageHost: 'localhost'
     * storagePort: 3306
     * storageDatabase: 'Khraal_Bungee_JPremium'
     * storageUser: 'ElAdminServerador'
     * storagePassword: 'Kobalte$19+44'
     * storageProperties: [useSSL=true, verifyServerCertificate=true]
     *
     */


    private final String TABLE_votes = "votes";

    public DataManager_SQL() {
        createVoteTable();
    }

    private Connection connect() throws SQLException, ClassNotFoundException {
        String url = "jdbc:sqlserver://localhost:3306/Khraal_Global_Votes";
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url, "ElAdminServerador", "Kobalte$19+44");
    }


    private void createVoteTable() {
        try (Connection connection = connect()) {
                String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_votes + "(" +
                        "id integer PRIMARY KEY NOT NULL, " +
                        "uuid varchar(255), " +
                        "ip varchar(255), " +
                        "vote_1 integer(11), " +
                        "vote_2 integer(11), " +
                        "vote_3 integer(11), " +
                        "vote_4 integer(11), " +
                        "count_months integer(11), " +
                        "count_all integer(11), " +
                        "count_challenge integer(11), " +
                        "last_voted integer(11)" +
                        ");";
            connection.createStatement().execute(sql);
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private int getCount(OfflinePlayer p, String type) {
        try (Connection connection = connect()) {
            String sql = MessageFormat.format("SELECT count_{1} FROM {0} WHERE uuid = ?", TABLE_votes, type);
            PreparedStatement psst = connection.prepareStatement(sql);
            psst.setString(1, p.getUniqueId().toString());
            ResultSet rs = psst.executeQuery();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private void incrementCount(OfflinePlayer p, String type) {
        try (Connection connection = connect()) {
            String sql = MessageFormat.format("UPDATE {0} SET count_{1} = (SELECT count_months FROM {0} WHERE uuid = ?)+1 WHERE uuid = ?", TABLE_votes, type);
            PreparedStatement psst = connection.prepareStatement(sql);
            psst.setString(1, p.getUniqueId().toString());
            psst.executeUpdate();
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getCountMonth(OfflinePlayer p) {
        return getCount(p, "months");
    }

    @Override
    public void incrementCountMonth(OfflinePlayer p) {
        incrementCount(p, "months");
    }

    @Override
    public int getCountAll(OfflinePlayer p) {
        return getCount(p, "all");
    }

    @Override
    public void incrementCountAll(OfflinePlayer p) {
        incrementCount(p, "all");
    }

    @Override
    public long getLastVoted(OfflinePlayer p) {
        try (Connection connection = connect()) {
            String sql = MessageFormat.format("SELECT last_voted FROM {0} WHERE uuid = ?", TABLE_votes);
            PreparedStatement psst = connection.prepareStatement(sql);
            psst.setString(1, p.getUniqueId().toString());
            ResultSet rs = psst.executeQuery();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void setLastVoted(OfflinePlayer p) {
        try (Connection connection = connect()) {
            String sql = MessageFormat.format("UPDATE {0} SET last_voted = ? WHERE uuid = ?", TABLE_votes);
            PreparedStatement psst = connection.prepareStatement(sql);
            psst.setLong(1, System.currentTimeMillis()/1000);
            psst.setString(2, p.getUniqueId().toString());
            psst.executeUpdate();
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setChall(OfflinePlayer p, long count_chall) {
        try (Connection connection = connect()) {
            String sql = MessageFormat.format("UPDATE {0} SET count_chall = ? WHERE uuid = ?", TABLE_votes);
            PreparedStatement psst = connection.prepareStatement(sql);
            psst.setLong(1, count_chall);
            psst.setString(2, p.getUniqueId().toString());
            psst.executeUpdate();
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void resetMonth() {
        try (Connection connection = connect()) {
            String sql = MessageFormat.format("UPDATE {0} SET count_months = 0, count_challenge = 0", TABLE_votes);
            connection.prepareStatement(sql).executeUpdate();
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void resetChall() {
        try (Connection connection = connect()) {
            String sql = MessageFormat.format("UPDATE {0} SET count_challenge = 0", TABLE_votes);
            connection.prepareStatement(sql).executeUpdate();
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<DivinPlayer> getAllPlayers() {
        List<DivinPlayer> divinPlayerList = new ArrayList<>();
        try (Connection connection = connect()) {
            String sql = MessageFormat.format("SELECT * FROM {0}", TABLE_votes);
            ResultSet rs = connection.prepareStatement(sql).executeQuery();
            while(rs.next()) {
                divinPlayerList.add(new DivinPlayer(UUID.fromString(rs.getString("uuid")),
                        rs.getString("ip"),
                        rs.getLong("vote_1"),
                        rs.getLong("vote_2"),
                        rs.getLong("vote_3"),
                        rs.getLong("vote_4"),
                        rs.getLong("count_months"),
                        rs.getLong("count_all"),
                        rs.getLong("count_challenge"),
                        rs.getLong("last_voted")));
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return divinPlayerList;
    }

    public DivinPlayer getDivinPlayer(OfflinePlayer p) {
        try (Connection connection = connect()) {
            String sql = MessageFormat.format("SELECT * FROM {0} WHERE uuid=?", TABLE_votes);
            PreparedStatement psst = connection.prepareStatement(sql);
            psst.setString(1, p.getUniqueId().toString());
            ResultSet rs = psst.executeQuery();
            if(rs.next()){
                return new DivinPlayer(UUID.fromString(rs.getString("uuid")),
                        rs.getString("ip"),
                        rs.getLong("vote_1"),
                        rs.getLong("vote_2"),
                        rs.getLong("vote_3"),
                        rs.getLong("vote_4"),
                        rs.getLong("count_months"),
                        rs.getLong("count_all"),
                        rs.getLong("count_challenge"),
                        rs.getLong("last_voted"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("This player is not in the database.");
    }

    public void updateLastVote(OfflinePlayer p, int link) {
        try (Connection connection = connect()) {
            String sql = MessageFormat.format("UPDATE {0} SET vote_{1} = ? WHERE uuid = ?", TABLE_votes, link);
            PreparedStatement psst = connection.prepareStatement(sql);
            psst.setLong(1, System.currentTimeMillis()/1000);
            psst.setString(2, p.getUniqueId().toString());
            psst.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
