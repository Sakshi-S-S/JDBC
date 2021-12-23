import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;

public class Assn_6
{
	static final String DB_URL="jdbc:mysql://localhost:3306/shoppingcart";
	static final String USER="Ash";
	static final String PASS="ash123";
	public static void main(String args[]) throws SQLException
	{
		Connection conn=null;
		Statement stmt=null;
		//char c='y';
		int n;
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Connecting to the database");
		
		conn=DriverManager.getConnection(DB_URL,USER,PASS);
		stmt=conn.createStatement();
		
		do
		{
			System.out.println("Menu:\n1.Insert\n2.Display\n3.Update\n4.Delete");
			System.out.println("Enter choice: ");
			BufferedReader ob=new BufferedReader(new InputStreamReader(System.in));
			int a=Integer.parseInt(ob.readLine());
			switch(a)
			{
				case 1: System.out.println("Inserting: ");
				String query="insert into employee(empno,ename,job,salary,commission,date_of_joining,dept_no)"+"values(?,?,?,?,?,?,?)";
				System.out.println("Enter empno: ");
				int empno=Integer.parseInt(ob.readLine());
				System.out.println("Enter emp name: ");
				String name=ob.readLine();
				System.out.println("Enter job: ");
				String job=ob.readLine();
				System.out.println("Enter salary: ");
				int salary=Integer.parseInt(ob.readLine());
				System.out.println("Enter commission: ");
				int comm=Integer.parseInt(ob.readLine());
				System.out.println("Enter date of joining: ");
				String doj=ob.readLine();
				System.out.println("Enter dept no: ");
				int dept_no=Integer.parseInt(ob.readLine());
				
				PreparedStatement prep_stmt;
				prep_stmt = conn.prepareStatement(query);
				prep_stmt.setInt(1,empno);
				prep_stmt.setString(2,name);
				prep_stmt.setString(3,job);
				prep_stmt.setInt(4,salary);
				prep_stmt.setInt(5,comm);
				prep_stmt.setString(6,doj);
				prep_stmt.setInt(7,dept_no);
				prep_stmt.execute();
				//conn.close();
				
				//stmt.executeUpdate(query);
				break;
				
				case 2: System.out.println("Database contains: ");
				query="Select * from employee";
				int count=0;
				ResultSet rs=stmt.executeQuery(query);
				while(rs.next())
				{
					dept_no=rs.getInt("dept_no");
					empno=rs.getInt("empno");
					salary=rs.getInt("salary");
					comm=rs.getInt("commission");
					name=rs.getString("ename");
					job=rs.getString("job");
					doj=rs.getString("date_of_joining");
					
					String output= "User #%d: %d - %s - %s - %d - %d - %s - %d";
					System.out.println(String.format(output, ++count, empno, name, job, salary, comm, doj, dept_no));
				}
				//conn.close();
				break;
				
				case 3:System.out.println("Updating: ");
				System.out.println("Enter empno to be updated");
				empno=Integer.parseInt(ob.readLine());
				
				System.out.println("Want to update job? ");
				int c=Integer.parseInt(ob.readLine());
				if(c==1)
				{
					System.out.println("Enter job: ");
					job=ob.readLine();
					query = "update employee set job=? "+"where empno=?";
					prep_stmt=conn.prepareStatement(query);
					prep_stmt.setString(1,job);
					prep_stmt.setInt(2,empno);
					prep_stmt.execute();
				}
				
				System.out.println("Want to update salary? ");
				c=Integer.parseInt(ob.readLine());
				if(c==1)
				{
					System.out.println("Enter salary: ");
					salary=Integer.parseInt(ob.readLine());
					query = "update employee set salary=? "+"where empno=?";
					prep_stmt=conn.prepareStatement(query);
					prep_stmt.setInt(1,salary);
					prep_stmt.setInt(2,empno);
					prep_stmt.execute();
				}
				
				System.out.println("Want to update commission? ");
				c=Integer.parseInt(ob.readLine());
				if(c==1)
				{
					System.out.println("Enter commission: ");
					comm=Integer.parseInt(ob.readLine());
					query = "update employee set commission=? "+"where empno=?";
					prep_stmt=conn.prepareStatement(query);
					prep_stmt.setInt(1,comm);
					prep_stmt.setInt(2,empno);
					prep_stmt.execute();
				}
				
				System.out.println("Want to update dept no? ");
				c=Integer.parseInt(ob.readLine());
				if(c==1)
				{
					System.out.println("Enter dept no: ");
					dept_no=Integer.parseInt(ob.readLine());
					query = "update employee set dept_no=? "+"where empno=?";
					prep_stmt=conn.prepareStatement(query);
					prep_stmt.setInt(1,dept_no);
					prep_stmt.setInt(2,empno);
					prep_stmt.execute();
				}
				break;
				
				case 4:System.out.println("Deleting: ");
				System.out.println("Enter empno to be deleted: ");
				empno=Integer.parseInt(ob.readLine());
				query = "delete from employee where empno=?";
				prep_stmt=conn.prepareStatement(query);
				prep_stmt.setInt(1,empno);
				prep_stmt.execute();
				//conn.close();
				break;
				
				default:System.out.println("Invalid input!");
			}
			System.out.println("Continue? (1=yes/0=no) ");
			n=Integer.parseInt(ob.readLine());
		}while(n==1);
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}




