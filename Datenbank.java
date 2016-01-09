import java.sql.*;
public class Datenbank {

	private static Connection c; 

	public Datenbank()
	{
		c = null;
		Statement stmt = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:poker.db");
			System.out.println("÷ffnen der Datenbank war erfolgreich");

			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS Poker " +
					"(UTP TEXT PRIMARY KEY NOT NULL," +
					" Anzahl_Wiederholungen INT    NOT NULL, " + 
					" Flush INT NOT NULL, " +
					" Zwilling INT NOT NULL, " + 
					" Drilling INT NOT NULL, " +
					" Poker INT NOT NULL, " + 
					" Straﬂe INT NOT NULL, " +
					" Straight_Flush INT NOT NULL, " +
					" Royal_Flush INT NOT NULL) ";

			stmt.executeUpdate(sql);
			stmt.close();

		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}

		System.out.println("Erstellen der Tabelle war erfolgreich");
	}

	public static void insert(String utp, double[] zeile){

		try {
			c.setAutoCommit(false);
			String sql = "INSERT INTO Poker VALUES(?,?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement stmt = c.prepareStatement(sql);

			stmt.setString(1, utp);

			for(int i = 0; i<zeile.length; i++){

				stmt.setDouble((i+2), zeile[i]);

			}
			stmt.executeUpdate();
			stmt.close();
			c.commit();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}

		System.out.println("\nEinf¸gen der Zeile war erfolgreich\n\n");

	}

	public static void select(){

		Statement stmt = null;
		try {

			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM Poker Order BY UTP DESC limit(5);" );

			System.out.println("||    UTP     || Anzahl Wiederholungen || Wahrsch. Flush || Wahrsch. Zwilling || Wahrsch. Drilling " +
					"|| Wahrsch. Poker || Wahrsch. Straﬂe || Wahrsch. Straight_Flush || Wahrsch. Royal_Flush ||");
			System.out.println("||============||=======================||================||===================||==================="
					+ "||================||=================||=========================||======================||");

			while ( rs.next() ) {
				String utp = rs.getString("UTP");
				int anzahlWiederholungen = rs.getInt("Anzahl_Wiederholungen");
				int flush = rs.getInt("Flush");
				int zwilling = rs.getInt("Zwilling");
				int drilling = rs.getInt("Drilling");
				int poker = rs.getInt("Poker");
				int straﬂe = rs.getInt("Straﬂe");
				int straightFlush = rs.getInt("Straight_Flush");
				int royalFlush = rs.getInt("Royal_Flush");

				System.out.println("|| " + utp + " ||         " + anzahlWiederholungen + "           ||       " + flush + "        ||         " + 
						zwilling +		"        ||         " + drilling + "         ||       " + poker + "        ||        " + straﬂe + "        ||           " 
						+ straightFlush + "             ||           " + royalFlush + "          ||");

			}
			rs.close();
			stmt.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}

	}

}
