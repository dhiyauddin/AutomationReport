package com.th.report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class GenerateReport {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap hm = null;
		// System.out.println("Usage: ReportGenerator ....");

		try {
			System.out.println("Start ....");
			// Get jasper report - windows
			//String jrxmlFileName = "C:/reports/report1.jrxml";
			//String jasperFileName = "C:/reports/report1.jasper";
			//String pdfFileName = "C:/reports/report1.pdf";

			// Get jasper report - unix
			String jrxmlFileName = "/home/vitria/testingcronjob/reports/report1.jrxml";
			String jasperFileName = "/home/vitria/testingcronjob/reports/report1.jasper";
			String pdfFileName = "/home/vitria/testingcronjob/reports/report1.pdf";
			
			JasperCompileManager.compileReportToFile(jrxmlFileName, jasperFileName);

			// String dbUrl = props.getProperty("jdbc.url");
			String dbUrl = "jdbc:mysql://172.20.40.134:3306/recon";
			// String dbDriver = props.getProperty("jdbc.driver");
			String dbDriver = "com.mysql.jdbc.Driver";
			// String dbUname = props.getProperty("db.username");
			String dbUname = "ism";
			// String dbPwd = props.getProperty("db.password");
			String dbPwd = "P@ssw0rd";

			// Load the JDBC driver
			Class.forName(dbDriver);
			// Get the connection
			Connection conn = DriverManager.getConnection(dbUrl, dbUname, dbPwd);

			// Create arguments
			//Map params = new HashMap();
			hm = new HashMap();
			hm.put("ID", "123");
			hm.put("DATENAME", "April 2019");

			// Generate jasper print
			JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(jasperFileName, hm, conn);

			// Export pdf file
			JasperExportManager.exportReportToPdfFile(jprint, pdfFileName);

			System.out.println("Done exporting reports to pdf");

		} catch (Exception e) {
			System.out.print("Exceptiion" + e);
		}
	}

}
