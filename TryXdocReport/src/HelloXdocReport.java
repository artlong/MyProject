import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.XdocReportUtil;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.samples.docxandvelocity.model.Developer;
import fr.opensagres.xdocreport.samples.docxandvelocity.model.Project;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;


public class HelloXdocReport {
	public static void main(String[] args) {
		helloAAMap();
//		helloAA();
//		helloList();
	}
	
	public static void helloAAMap() {
		String docxName = "AAA";
		InputStream in = null;
		try {
			in = new FileInputStream(XdocReportUtil.getTemplateFile(docxName + ".docx"));
			XDocReportRegistry reportRegistry = XDocReportRegistry.getRegistry();
			reportRegistry.clear();
			IXDocReport report = reportRegistry.loadReport(in, TemplateEngineKind.Velocity);
			IContext context = report.createContext();
			
			context.put("reportNo", "AI570");
			context.put("reportNo2", "AI5702");
			
			// add for List gen key 
			/** notice : pre & field must match docx!! **/
			FieldsMetadata metadata = new FieldsMetadata();
			metadata.addFieldAsList("card.A");
			metadata.addFieldAsList("card.B");
			metadata.addFieldAsList("card.C");

			metadata.addFieldAsList("cardB.A");
			metadata.addFieldAsList("cardB.B");
			metadata.addFieldAsList("cardB.C");
			report.setFieldsMetadata(metadata);

			List<Map> developers = new ArrayList<Map>();
			Map data = null;
			data = new HashMap();
			data.put("A", "A1");
			data.put("B", "B1");
			data.put("C", "C1");
			developers.add(data);

			data = new HashMap();
			data.put("A", "A2");
			data.put("B", "B2");
			data.put("C", "C2");
			developers.add(data);
			context.put("card", developers);
			context.put("cardB", developers);
			
			OutputStream out = new FileOutputStream(XdocReportUtil.getOutputFile(docxName + "-OutMap" + ".docx"));
			report.process(context, out);
			System.out.println("HelloXdocReport List done!");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XDocReportException e) {
			e.printStackTrace();
		} finally {
			try { in.close(); } catch (Exception e){}
		}
	}
	
	public static void helloRL000214() {
		String docxName = "AA";
		InputStream in = null;
		try {
			in = new FileInputStream(XdocReportUtil.getTemplateFile(docxName + ".docx"));
			XDocReportRegistry reportRegistry = XDocReportRegistry.getRegistry();
			reportRegistry.clear();
			IXDocReport report = reportRegistry.loadReport(in, TemplateEngineKind.Velocity);
			IContext context = report.createContext();
			
			context.put("reportNo", "AI570");
			context.put("reportNo2", "AI5702");
			
			// add for List gen key 
			/** notice : pre & field must match docx!! **/
			FieldsMetadata metadata = new FieldsMetadata();
			metadata.addFieldAsList("card.A");
			metadata.addFieldAsList("card.B");
			metadata.addFieldAsList("card.C");
			report.setFieldsMetadata(metadata);

			List<Card> developers = new ArrayList<Card>();
			developers.add(new Card("Hi", "A", "a"));
			developers.add(new Card("Hello", "B", "b"));
			context.put("card", developers);
			
			OutputStream out = new FileOutputStream(XdocReportUtil.getOutputFile(docxName + "-Out" + ".docx"));
			report.process(context, out);
			System.out.println("HelloXdocReport List done!");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XDocReportException e) {
			e.printStackTrace();
		} finally {
			try { in.close(); } catch (Exception e){}
		}
	}
	
	public static void helloList() {
		String docxName = "HelloList";
		InputStream in = null;
		try {
			in = new FileInputStream(XdocReportUtil.getTemplateFile(docxName + ".docx"));
			XDocReportRegistry reportRegistry = XDocReportRegistry.getRegistry();
			reportRegistry.clear();
			IXDocReport report = reportRegistry.loadReport(in, TemplateEngineKind.Velocity);
			IContext context = report.createContext();
			
			Project project = new Project("XDocReport");
			context.put("project", project);
			
			// add for List gen key 
			/** notice : pre & field must match docx!! **/
			FieldsMetadata metadata = new FieldsMetadata();
			metadata.addFieldAsList("developers.Name");
			metadata.addFieldAsList("developers.LastName");
			metadata.addFieldAsList("developers.Mail");
			report.setFieldsMetadata(metadata);

			List<Developer> developers = new ArrayList<Developer>();
			developers.add(new Developer("ZERR", "Angelo", "angelo.zerr@gmail.com"));
			developers.add(new Developer("Leclercq", "Pascal", "pascal.leclercq@gmail.com"));
			context.put("developers", developers);
			
			OutputStream out = new FileOutputStream(XdocReportUtil.getOutputFile(docxName + "-Out" + ".docx"));
			report.process(context, out);
			System.out.println("HelloXdocReport List done!");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XDocReportException e) {
			e.printStackTrace();
		} finally {
			try { in.close(); } catch (Exception e){}
		}
	}
	
	public static void helloAA() {
		String docxName = "AA";
		InputStream in = null;
		try {
			in = new FileInputStream(XdocReportUtil.getTemplateFile(docxName + ".docx"));
			XDocReportRegistry reportRegistry = XDocReportRegistry.getRegistry();
			reportRegistry.clear();
			IXDocReport report = reportRegistry.loadReport(in, TemplateEngineKind.Velocity);
			IContext context = report.createContext();
			context.put("reportNo", "AI570");
			OutputStream out = new FileOutputStream(XdocReportUtil.getOutputFile(docxName + "-Out" + ".docx"));
			report.process(context, out);
			System.out.println("HelloXdocReport done!");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XDocReportException e) {
			e.printStackTrace();
		} finally {
			try { in.close(); } catch (Exception e){}
		}
	}
}
